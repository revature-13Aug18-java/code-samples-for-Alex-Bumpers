package com.revature.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.models.Friend;
import com.revature.models.Location;
import com.revature.models.MUser;
import com.revature.models.Preference;
import com.revature.models.PreferenceId;
import com.revature.services.FriendService;
import com.revature.services.LocationService;
import com.revature.services.MUserService;
import com.revature.services.PreferenceService;

@Controller
@Component
public class DAOController {

	/**
	 * Injects the dependencies needed for any of the following methods.
	 */
	@Autowired
	MUserService mUserService;
	
	@Autowired
	PreferenceService preferenceService;
	
	@Autowired
	FriendService friendService;
	
	@Autowired
	LocationService locationService;
	/**
	 * Return locations from the database
	 * @return a List<Location> populated from the Database.
	 */
	@GetMapping(value="/locations", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Location> findAllLocations(){
		return locationService.findAllLocations();
	}	
	/**
	 * Returns a single location
	 * @param id operand used to search
	 * @return a single location from {id}
	 */
	@GetMapping(value="/locations/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Location getLocationById(@PathVariable("id") int id) {
		return locationService.findLocationById(id);
	}	
	/**
	 * A single Location object is passed into this method and added to the database.
	 * @param l operand used to add.
	 * @return
	 */
	@PostMapping(value="/locations",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Location addLocation(@RequestBody Location l) {
		return locationService.addLocation(l);
	}
	/**
	 * A single Location object is passed into this method and updates the
	 * 		Location row in the database with equivalent id.
	 * @param l operand used to update.
	 * @return
	 */
	@PutMapping(value="/locations",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Location updateLocation(@RequestBody Location l) {
		return locationService.updateLocation(l);
	}
	/**
	 * Deletes a Location that matches the Location object passed into it.
	 * @param l operand used to delete.
	 * @return
	 */
	@DeleteMapping(value="/locations",consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Location deleteLocation(@RequestBody Location l) {
		return locationService.deleteLocation(l);
	}
	/**
	 * Returns a List of Preferences from the Database.
	 * @return
	 */
	@GetMapping(value="/preferences", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Preference> findAllPreferences(){
		return preferenceService.findAllPreferences();
	}
	/**
	 * Gets a single Preference based on the Composite Key id/pLevel
	 * @param id single part of the composite key used to search
	 * @param pLevel single part of the composite key used to search
	 * @return
	 */
	@GetMapping(value="/preferences/pref/{id}/{pLevel}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Preference getPreferenceById(@PathVariable("id") int id, @PathVariable("pLevel") int pLevel) {
		PreferenceId pId = new PreferenceId(id,pLevel);
		return preferenceService.findPreferenceByKey(pId);
	}
	/**
	 * Gets a List of Preferences from the Database by id.
	 * @param id operand used to filter the list
	 * @return
	 */
	@GetMapping(value="/preferences/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Preference> getPreferencesByUserId(@PathVariable("id") int id){
		List<Preference> preferences= preferenceService.findAllPreferences();
		return preferences.stream().filter(pref -> pref.getpId().getmuser_id()==id).collect(Collectors.toList());
	}

	/**
	 * Adds a single Preference to the Database
	 * @param p operand to be added to the Database
	 * @return
	 */
	@PostMapping(value="/preferences",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Preference addPreference(@RequestBody Preference p) {
		return preferenceService.addPreference(p);
	}
	
	/**
	 * Updates a single Preference in the Database.
	 * @param id single part of composite key
	 * @param pLevel single part of composite key
	 * @param genre Data identified by the composite key
	 * @return
	 */
	@PutMapping(value="/preferences/add/{id}/{pLevel}/{genre}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Preference addPreferencePath(@PathVariable("id") int id, @PathVariable("pLevel") int pLevel, @PathVariable("genre") String genre) {
		Preference p = new Preference(new PreferenceId(id,pLevel),genre);
		return preferenceService.addPreference(p);
	}

	/**
	 * Updates a single preference in the Database
	 * @param p preference to update in the database
	 * @return
	 */
	@PutMapping(value="/preferences",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Preference updatePreference(@RequestBody Preference p) {
		return preferenceService.updatePreference(p);
	}

	/**
	 * Deletes a single Preference in the Database
	 * @param p Preference to be deleted from the Database.
	 * @return
	 */
	@DeleteMapping(value="/preferences",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Preference deletePreference(@RequestBody Preference p) {
		return preferenceService.deletePreference(p);
	}
	
	@DeleteMapping(value="/preferences/{id}/{pLevel}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void deletePreferenceByPId(@PathVariable("id") int id, @PathVariable("pLevel") int pLevel) {
		PreferenceId pId = new PreferenceId(id,pLevel);
		preferenceService.deletePreferenceByPId(pId);
	}
	/**
	 * Returns a full list of Friends in the database.
	 * Noted by a Friend id, mUserId person, mUserId isFriendsWith.
	 * @return
	 */
	@GetMapping(value="/friends", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Friend> findAllFriends(){
		return friendService.findAllFriends();
	}
	/**
	 * Returns a person/isFriendsWith pairing based on the {id}
	 * @param id
	 * @return
	 */
	@GetMapping(value="/friends/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Friend findFriendById(@PathVariable("id") int id){
		return friendService.findFriendById(id);
	}
	/**
	 * Returns a List of Friends where {person} is the common value in the 
	 * person column.
	 * @param person
	 * @return
	 */
	@GetMapping(value="/friends/p/{person}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Friend> findFriendByPerson(@PathVariable("person") int person){
		return friendService.findFriendsByPerson(person);
	}
	/**
	 * Returns a List of Friends where {isFriendsWith} is the common value
	 * in the isFriendsWith column.
	 * @param isFriendsWith
	 * @return
	 */
	@GetMapping(value="/friends/i/{isFriendsWith}",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Friend> findFriendByIsFriendsWith(@PathVariable("isFriendsWith") int isFriendsWith){
		return friendService.findFriendsByIsFriendsWith(isFriendsWith);
	}
	/**
	 * Updates a Friend in the Database
	 * @param friend Friend to update.
	 * @return
	 */
	@PutMapping(value="/friends",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Friend updateFriend(Friend friend) {
		friendService.addFriend(friend);
		return friend;
	}
	/**
	 * Adds a friend to the Database.
	 * @param friend friend to add.
	 * @return
	 */
	@PostMapping(value="/friends",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Friend addFriend(@RequestBody Friend friend) {
		friendService.addFriend(friend);
		return friend;
	}
	/**
	 * Deletes a friend from the Database.
	 * @param friend Friend to delete.
	 */
	@DeleteMapping(value="/friends/{id}", consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void deleteFriend(Friend friend) {
		friendService.deleteFriend(friend);
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value="/users/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MUser getMUserById(@PathVariable("id") int id) {
		return mUserService.findMUserById(id);
	}
	/**
	 * Returns a full list of users in the Database.
	 * @return
	 */
	@GetMapping(value="/users", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<MUser> findAllUsers(){
		return mUserService.findAllMUsers();
	}
	/**
	 * Returns a list of users who have a matching firstName
	 * @param firstName operand to match to
	 * @return
	 */
	@GetMapping(value="/users/name/{name}",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<MUser> findByFirstName(@PathVariable("name") String firstName){
		return mUserService.findByFirstName(firstName);
	}
	/**
	 * Returns a user with the matching email
	 * @param email operand to match to
	 * @return
	 */
	@GetMapping(value="/users/byemail/{email}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MUser getMUserByUsername(@PathVariable("email") String email) {
		return mUserService.findMUserByUsername(email);
	}

	/**
	 * Adds a user to the database
	 * @param m operand to be added.
	 * @return
	 */
	@PostMapping(value="/users",consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MUser addMUser(@RequestBody MUser m) {
		locationService.addLocation(m.getLocation());
		return mUserService.addMUser(m);
	}
	/**
	 * Updates a user in the database
	 * @param m user to be updated
	 * @return
	 */
	@PutMapping(value="/users",consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MUser updateUser(@RequestBody MUser m) {
		return mUserService.updateMUser(m);
	}
	/**
	 * Deletes a user from the database.
	 * @param m user to be deleted
	 * @return
	 */
	@DeleteMapping(value="/users",consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MUser deleteUser(@RequestBody MUser m) {
		return mUserService.deleteMUser(m);
	}
	/**
	 * Gets a List of Users by if they have a Preference with that genre.
	 * @param genre
	 * @return
	 */
	@GetMapping(value="/users/genre/{genre}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<MUser> getMUsersByGenre(@PathVariable("genre") String genre) {
		return mUserService.findMUserByGenre(genre);
	}
	/**
	 * Returns a float value from 0 to 100 based on the matching of two users.
	 * @param one first user to be matched
	 * @param two second user to be matched
	 * @return
	 */
	@GetMapping(value="/users/match/{one}/{two}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public float getMatchScore(@PathVariable("one") int one, @PathVariable("two") int two) {
		return mUserService.matchTwoUsers(mUserService.findMUserById(one), mUserService.findMUserById(two));
	}
	/**
	 * Deprecated method for session management.
	 * @param request
	 * @param response
	 * @return
	 */
	@GetMapping(value="/profile")
	@ResponseBody
	public MUser getProfile(HttpServletRequest request, HttpServletResponse response) {
		HttpSession s = request.getSession();
		int id= Integer.parseInt(s.getAttribute("id").toString());
		return mUserService.findMUserById(id);
	}
	/**
	 * Deprecated method for session management.
	 * @param request
	 * @param response
	 * @return
	 */
	@GetMapping(value="/session")
	@ResponseBody
	public MUser session(HttpServletRequest request, HttpServletResponse response) {
		HttpSession s = request.getSession(false);
		return mUserService.findMUserById(Integer.parseInt(s.getAttribute("id").toString()));
	}
	/**
	 * Deprecated method for logging in.
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@GetMapping(value="/logins/{id}")
	@ResponseBody
	public MUser loggedInMUser(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) {
		MUser u = mUserService.findMUserById(id);
		HttpSession s = request.getSession();
		s.setAttribute("email", u.getEmail());
		s.setAttribute("firstName",u.getFirstName());
		s.setAttribute("lastName", u.getLastName());
		s.setAttribute("aboutMe", u.getAboutMe());
		s.setAttribute("gender", u.getGender());
		s.setAttribute("id", u.getId());
		s.setAttribute("location", u.getLocation());
		s.setAttribute("orientation", u.getOrientation());
		s.setAttribute("password", u.getPassword());
		s.setAttribute("phoneNumber", u.getPhoneNumber());
		s.setAttribute("pictureUrl", u.getPictureUrl());
		s.setAttribute("prefs", u.getPrefs());
		
		return mUserService.findMUserById(id);
	}

}
