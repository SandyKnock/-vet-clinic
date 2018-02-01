package su.vistar.vetclinic.tests.controllertest;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import su.vistar.vetclinic.tests.data.UserFunctions;
import su.vistar.vetclinic.configuration.AppConfig;
import su.vistar.vetclinic.controller.AppController;
import su.vistar.vetclinic.entities.User;
import su.vistar.vetclinic.service.UserService;
import su.vistar.vetclinic.token.AccountCredentials;

import javax.servlet.Filter;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Created by Владислав on 23.03.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class ControllerTest {

    static final Logger logger = LoggerFactory.getLogger(ControllerTest.class);

    private static final String[] admin = {"Sandy", "sandy"};
    private static final String[] client = {"client", "sandy"};
    private static final String[] employee = {"Knock", "sandy"};
    private static final int numberOfProfiles = 3;
    private static final String[] autToken = new String[numberOfProfiles];

    @InjectMocks
    private AppController appController;

    @Mock
    private UserService userService;

    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mvc;

    private UserFunctions userFunctions = new UserFunctions();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(appController)
                .addFilters(springSecurityFilterChain)
                .build();
        authorization();
    }

    private String getJsonSerializesStrLogAndPass(String userName, String password){
        AccountCredentials accountCredentials = new AccountCredentials();
        accountCredentials.setUsername(userName);
        accountCredentials.setPassword(password);
        Gson gson = new Gson();
        gson.toJson(accountCredentials);
        return gson.toJson(accountCredentials);
    }

    private void authorization(){
        for (int i = 1; i < numberOfProfiles; i++) {
            switch (i){
                case 1:{
                    saveToken(getJsonSerializesStrLogAndPass(admin[0], admin[1]), i);
                    break;
                }
                case 2:{
                    saveToken(getJsonSerializesStrLogAndPass(client[0], client[1]), i);
                    break;
                }
                case 3:{
                    saveToken(getJsonSerializesStrLogAndPass(employee[0], employee[1]), i);
                    break;
                }
            }
        }
    }

    private void saveToken(String jsonObjectSerializesStrLogAndPass,int numberOfProfiles ){
        MvcResult result = null;
        try {
            logger.info(jsonObjectSerializesStrLogAndPass);
            result = mvc.perform(get("/login")
                    .content(jsonObjectSerializesStrLogAndPass))
                    .andExpect(status().isOk()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            assert result != null;
            logger.info(String.valueOf(result.getResponse().getStatus()));
        }
        autToken[numberOfProfiles] =  result.getResponse().getHeader("Authorization");
        logger.info(autToken[numberOfProfiles]);
    }

    @Test
    public void testFindBySSO_Success_Client() throws Exception {
        User user = new User();
        user.setSsoId(client[0]);
        when(userService.findBySSO(client[0])).thenReturn(user);

        mvc.perform(get("/client").header("Authorization", autToken[2]))
                .andExpect(status().isOk());
        verify(userService, times(1)).findBySSO(client[0]);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testFindAllUsers_Success_Admin() throws Exception {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 100 ; i++) {
            users.add(userFunctions.createUser());
        }
        when(userService.findAllUsers()).thenReturn(users);
        mvc.perform(get("/admin").header("Authorization", autToken[1]))
                .andExpect(status().isOk());
        verify(userService, times(1)).findAllUsers();
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testDeleteUserBySSO__Success_Admin() throws Exception {
        User user = userFunctions.createUser();
        when(userService.findBySSO(user.getSsoId())).thenReturn(user);
        mvc.perform(get("/admin_delete").content(user.getSsoId()).header("Authorization", autToken[1]))
                .andExpect(status().isOk());
        verify(userService, times(1)).findBySSO(user.getSsoId());
        verify(userService, times(1)).deleteUserBySSO(user.getSsoId());
        verifyNoMoreInteractions(userService);
    }
}
