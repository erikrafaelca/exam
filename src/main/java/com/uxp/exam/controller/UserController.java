/**
 * 
 */
package com.uxp.exam.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.uxp.exam.domain.User;
import com.uxp.exam.service.UserServiceImpl;


/**
 * @author Erik
 *
 */
@RestController()
public class UserController {
	
	@Autowired
	UserServiceImpl userService; 

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<User> getUsers() {
    	return userService.getAllUsers();
    }
	
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public User getUser(@PathVariable(value="userId", required=true) long userId) throws Exception {
    	User user = userService.getUser(userId);
		return user;
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody    
    public User postUser(@RequestBody(required=true) User user) throws Exception {
        user = userService.addUser(user);
        return user;
    }    

    @RequestMapping(value = "/user", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public User putUser(@RequestBody(required=true) User user) throws Exception{
        user = userService.updateUser(user);
        return user;
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable(value="userId", required=true) long userId) throws Exception {
        userService.deleteUser(userId);
    }  

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
}