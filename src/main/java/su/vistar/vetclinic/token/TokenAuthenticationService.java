package su.vistar.vetclinic.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import su.vistar.vetclinic.utilities.Clipboard;
import su.vistar.vetclinic.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


/**
 * Created by Владислав on 14.02.2017.
 */

public class TokenAuthenticationService {

    private UserProfileConverter userProfileConverter = new UserProfileConverter();
    private Clipboard inputOutputController = new Clipboard();

    static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationService.class);
    private final long EXPIRATIONTIME = 1000 * 60 * 60 * 24 * 10; // 10 days


    private String secret = "ThisIsASecret";
    private String headerString = "Authorization";
    private UserService userService;
    private String headerURL = "URL";

//TODO http://localhost:3000
    private final Map<String, String> map = new HashMap<String, String>(){{
        put("USER","/ClientMainPage");
        put("ADMIN","/AdminMainPage");
        put("EMPLOYEE","/EmployeeMainPage");
    }};

    public TokenAuthenticationService(UserService userService) {
        this.userService = userService;
    }

    public void addAuthentication(HttpServletResponse response, String username) throws IOException {
        String userType  = userProfileConverter.converterUserTypes(userProfileConverter.converterUserTypesArray(userService.findBySSO(username)));
        String JWT = Jwts.builder()
                .setSubject(username)
                .setIssuer(userType)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        response.addHeader(headerString, JWT);
        response.addHeader(headerURL, redirect(userType));
        response.setContentType("application/text");
      //  logger.info(JWT);
        //копировать в буфер обмена
      //  inputOutputController.copyToClipboard(JWT);
    }


    public String redirect(String type){
        return map.get(type);
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(headerString);
        if (token != null) {
            String username = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            String type = "ROLE_" + Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getIssuer();
            if (username != null) // we managed to retrieve a user
            {
                return new AuthenticatedUser(username,type);
            }
        }
        return null;
    }


}
