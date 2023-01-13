/*
* Copyright 2002-2013 the original author or authors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.springframework.samples.dobble.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.jms.Message;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.dobble.game.Game;
import org.springframework.samples.dobble.game.GameUserService;
import org.springframework.samples.dobble.tournament.Tournament;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
* @author Juergen Hoeller
* @author Ken Krebs
* @author Arjen Poutsma
* @author Michael Isvy
*/
@Controller
@RequestMapping("/users")
public class UserController {

   private static final String VIEWS_USER_CREATE_OR_UPDATE_FORM = "users/createOrUpdateUserForm";
   private String VIEW_INDEX_USERS = "users/userListing";
   private static final String MESSAGE = "message";

   UserService userService;
   AuthoritiesService authoritiesService;
   GameUserService gameUserService;
   @Autowired
   public UserController(UserService userService, AuthoritiesService authoritiesService, GameUserService gameUserService){
	 this.userService = userService;
	 this.authoritiesService = authoritiesService;
	 this.gameUserService = gameUserService;
   }

   @InitBinder
   public void setAllowedFields(WebDataBinder dataBinder) {
	   dataBinder.setDisallowedFields("id");
   }


	
   @GetMapping(value = "/new")
   public String initCreationForm(Map<String, Object> model) {
	   User user = new User();
	   model.put("user", user);
	   return VIEWS_USER_CREATE_OR_UPDATE_FORM;
   }

   @PostMapping(value = "/new")
   public String processCreationForm(@Valid User user, BindingResult result) {
	   if (result.hasErrors()) {
		   return VIEWS_USER_CREATE_OR_UPDATE_FORM;
	   }
	   else {
		   //creating user and authority
		   this.userService.saveUser(user);
		   return "redirect:/games/";
		}
   }

   @PreAuthorize("hasRole('admin')")
   @GetMapping()
   public String showUser(ModelMap modelMap, @PathParam("pageNumber") Integer pageNumber, RedirectAttributes redirectAttributes){
		String view = VIEW_INDEX_USERS;
		
		List<Integer> pages = userService.calculatePages(pageNumber);
		Integer previousPageNumber = pages.get(0);
		Integer nextPageNumber = pages.get(1);
		List<User> usuarios = userService.getPaginatedUsers(pageNumber);
		User loggedUser = userService.getLoggedUser();

		if (authoritiesService.findAuthorities(loggedUser, "admin")==null) {
			redirectAttributes.addFlashAttribute("error","You are not authorized to edit other users");
			modelMap.addAttribute(MESSAGE,"You haven't got permission");
			
		}else{
			modelMap.addAttribute("users", usuarios);
			modelMap.addAttribute("pageNumber", pageNumber==null?0:pageNumber);
			modelMap.addAttribute("nextPageNumber", nextPageNumber);
			modelMap.addAttribute("previousPageNumber", previousPageNumber);
		}

		return view;
	}


   @PreAuthorize("hasRole('admin')")
   @GetMapping("/{username}")
   public ModelAndView showUser(@PathVariable("username") String username){
	    ModelAndView mav = new ModelAndView(VIEWS_USER_CREATE_OR_UPDATE_FORM);
		  User user = userService.findUser(username);
		  mav.addObject(user);
	    return mav;

   }

   @PostMapping(value = "/edit/{username}")
   public String UpdateUser(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
		   return "users/EditUser";
	   }
	   else {
			userService.saveUser(user);
		   	return "redirect:/";
	   }
   }

   @GetMapping(path="/edit/{username}")
	public ModelAndView editarUser(@PathVariable("username") String username, RedirectAttributes redirectAttributes){	
		User user = userService.findUser(username);
		User loggedUser = userService.getLoggedUser();
		
	if (authoritiesService.findAuthorities(loggedUser, "admin")==null && !user.equals(loggedUser)) {
		redirectAttributes.addFlashAttribute("error","You are not authorized to edit other users");
		return new ModelAndView("redirect:/games");
		
	   }
	   	
		ModelAndView result=new ModelAndView("users/EditUser");
		result.addObject("user", user);
		return result;
	}

	@PreAuthorize("hasRole('admin')")
	@GetMapping(path="/delete/{id}")
	public String deletePlayer(@PathVariable("id") String id, ModelMap modelMap, HttpServletRequest request){
		String view =  VIEW_INDEX_USERS;
		User user = userService.findUser(id);
		user.setEnabled(false);
		userService.saveUser(user);
		gameUserService.deleteGameUser(user.getCurrentGame().getId(), user.getUsername());
		return view;
	}

   


}
