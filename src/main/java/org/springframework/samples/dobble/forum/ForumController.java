package org.springframework.samples.dobble.forum;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.dobble.comment.Comment;
import org.springframework.samples.dobble.comment.CommentService;
import org.springframework.samples.dobble.user.User;
import org.springframework.samples.dobble.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("api/forum")
public class ForumController<ParalamentarioSevice> {
	
	@Autowired
	ForumService forumService;
	
	@Autowired
	CommentService commentService;

	@Autowired
	UserService userService;
	
	@GetMapping
	public ModelAndView showForums(){
		Iterable<Forum> parlamentarios=forumService.findAll();
		ModelAndView result=new ModelAndView("forums/ForumsListing");
		result.addObject("forums", parlamentarios);
		return result;
		
	}
	
	@GetMapping(path="/create")
	public ModelAndView crearteForum(){		
		ModelAndView result=new ModelAndView("forums/EditForum");	
		result.addObject("forum", new Forum());
		result.addObject("allComments", commentService.findAll());
		return result;
	}


	@PostMapping(path="/create")
	public ModelAndView saveNewForum(@ModelAttribute("forum")  Forum forum) {
		forumService.save(forum);
		ModelAndView result=showForums();	
		result.addObject("message", "Forum created sucessfully!");
		result.addObject("messageType", "sucess");
		return result;
	}
	
	@GetMapping(path="/edit/{id}")
	public ModelAndView editarForum(@PathVariable("id") long id){		
		ModelAndView result=new ModelAndView("forums/EditForum");	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
		User user = userService.findUser(userId);
		Optional<Forum> forum = forumService.findById(id);
		Comment comment = new Comment();
		comment.setUser(user);
		comment.setForum(forum.get());
		result.addObject("comment", comment);
		result.addObject("forum", forum);
		result.addObject("allComments", forum.get().getComments());
		return result;
	}
	
	@PostMapping(path="/edit/{id}")
	public ModelAndView grabarForum(@ModelAttribute("forum")  Forum forum, @ModelAttribute("comment") Comment comment, @PathVariable("id") long id) {
		forumService.save(forum);
		commentService.save(comment);
		ModelAndView result=showForums();	
		result.addObject("message", "Forum sucessfully updated");
		result.addObject("messageType", "sucess");
		return result;
	}
	
	@GetMapping(path="/delete/{id}")
	public ModelAndView borrarForum(@PathVariable("id") long id){
		forumService.deleteById(id);
		ModelAndView result=showForums();	
		result.addObject("message", "Forum borrado con éxito");
		result.addObject("messageType", "sucess");
		return result;
	}
}
