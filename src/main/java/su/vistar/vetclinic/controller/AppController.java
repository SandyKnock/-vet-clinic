package su.vistar.vetclinic.controller;

import com.mongodb.gridfs.GridFSDBFile;
import su.vistar.vetclinic.converter.ElasticPet;
import su.vistar.vetclinic.converter.ImageID;
import su.vistar.vetclinic.entities.Pet;
import su.vistar.vetclinic.entities.User;
import su.vistar.vetclinic.entities.PetEL;
import su.vistar.vetclinic.mail.SendMail;
import su.vistar.vetclinic.service.*;
import su.vistar.vetclinic.converter.Image;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import su.vistar.vetclinic.service.elasticsearch.PetRepositoryServiceImpl;
import su.vistar.vetclinic.service.mongo.FileStorageServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController  {

	static final Logger logger = LoggerFactory.getLogger(AppController.class);

	@Autowired
	UserService userService;

	@Autowired
	PetService petService;

	@Autowired
	FileStorageServiceImpl fileStorageService;

	@Autowired
	private PetRepositoryServiceImpl repositoryServiceet;


	//**********************************Admin********************************************

	@RequestMapping(value = { "/admin" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.findAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@RequestMapping(value = { "/saveUser" }, method = RequestMethod.POST, consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody ResponseEntity<String> save(@RequestBody User user) {
		userService.updateUser(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = { "/admin_delete" }, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity adminDelete(@RequestBody String sso) {
		User user = userService.findBySSO(sso);
		userService.deleteUserBySSO(user.getSsoId());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = { "/addElastic" }, method = RequestMethod.POST,consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody ResponseEntity<String> addElastic(@RequestBody ElasticPet elasticPet) {
		List<PetEL> pet = repositoryServiceet.getById(elasticPet.getId());
		int size = pet.size();
		if(size == 0) {
			PetEL first = getFirst(elasticPet.getId(), elasticPet.getName());
			repositoryServiceet.addPetEL(first);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private PetEL getFirst(String id, String name) {
		PetEL first = new PetEL();
		first.setId(id);
		first.setPetName(name);
		List<String> pets = petService.getAllName();
		first.setAllNickName(pets);
		return first;
	}

	private String getAuth(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}



	//**********************************Client********************************************

	@RequestMapping(value = { "/client" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<User> getClient() {
		User user = userService.findBySSO(getAuth());
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@RequestMapping(value = { "/saveClient" }, method = RequestMethod.POST,consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody ResponseEntity<String> saveClient(@RequestBody User user) {
		userService.updateUser(user);
		return new ResponseEntity<>("ok", HttpStatus.OK);
	}

	@RequestMapping(value = { "/getAllPets" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Pet>> getAllPets(){
		User user = userService.findBySSO(getAuth());
		List<Pet> pets= petService.petByClientId(user.getId());
		return new ResponseEntity<>(pets, HttpStatus.OK);
	}

	@RequestMapping(value = { "/postAllImages" }, method = RequestMethod.POST,consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Image[]> postAllImages(@RequestBody ImageID[] imagesId){
		Image[] images = new Image[imagesId.length];
		for (int i = 0; i < imagesId.length; i++) {
			GridFSDBFile image = fileStorageService.getById(imagesId[i].getId());
			images[i] = new Image(imagesId[i].getId(), getBytesFromInputStream(image));
		}
		return new ResponseEntity<>(images, HttpStatus.OK);
	}

	@Nullable
	private byte[] getBytesFromInputStream(GridFSDBFile image){
		try {
			return IOUtils.toByteArray(image.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = { "/el_search" }, method = RequestMethod.POST, consumes = "application/text", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<String>> search(@RequestBody String search) {
		List <PetEL> pet = repositoryServiceet.getByName(search);
		List<String> allName = new ArrayList<>();
		for (int i = 0; i < pet.size(); i++) {
			for (int j = 0; j < pet.get(i).getAllNickName().size() ; j++) {
				allName.add(j, pet.get(i).getAllNickName().get(j));
			}
		}
		if (allName.size() != 0){
			return new ResponseEntity<>(allName, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
	}

	//**********************************Permit all********************************************

	@RequestMapping(value = { "/registration" }, method = RequestMethod.POST,consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody ResponseEntity<String> registration(@RequestBody User user) {
		if (userService.checkingSSO(user.getSsoId()) == null && userService.checkingEmail(user.getEmail()) == null) {
			    SendMail mail = new SendMail();
			    mail.sendMail(user);
			    user.setId(null);
				userService.saveUser(user);

		}
		return new ResponseEntity<>("ok", HttpStatus.OK);
	}




//	//todo create controller "add elasticsearch"
//	private void addSome() {
//		PetEL first = getFirst();
//		repositoryServiceet.addPetEL(first);
//	}
//
//	private PetEL getFirst() {
//		PetEL first = new PetEL();
//		first.setId("1");
//		first.setPetName("name");
//		List<String> pets = petService.getAllName();
//		first.setAllNickName(pets);
//		return first;
//	}
//
//	private String getAuth(){
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		return auth.getName();
//	}

}