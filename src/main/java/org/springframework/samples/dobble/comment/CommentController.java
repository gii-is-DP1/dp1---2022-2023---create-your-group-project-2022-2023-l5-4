package org.springframework.samples.dobble.comment;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.dobble.forum.Forum;
import org.springframework.samples.dobble.forum.ForumService;
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
@RequestMapping("/comments")
public class CommentController {
	
	@Autowired
	ForumService forumService;
	
	@Autowired
	CommentService commentsService;

	@Autowired
	UserService userService;
	
	
	@GetMapping(path="/{ForumId}")
	public ModelAndView listadoComments(@PathVariable("ForumId") long ForumId){		
		ModelAndView result=new ModelAndView("comments/CommentsListing");		
		List<Comment> comments = forumService.findById(ForumId).get().getComments();
		result.addObject("formid", ForumId);
		result.addObject("comment", new Comment());		
		result.addObject("comments", comments);
		return result;
	}
	
	@GetMapping(path="/create")
	public ModelAndView crearComment(){		
		ModelAndView result=new ModelAndView("comments/EditComment");	
		result.addObject("comment", new Comment());		
		return result;
	}
	
	@GetMapping(path="/edit/{id}")
	public ModelAndView editarComment(@PathVariable("id") long id){		
		ModelAndView result=new ModelAndView("comments/EditComment");	
		result.addObject("comment", commentsService.findById(id));		
		return result;
	}
	
	
	@PostMapping(path="/{ForumId}/save")
	public String salvarComment(   @ModelAttribute("comment")  Comment comment, @PathVariable("ForumId") long ForumId){	
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String userId = authentication.getName();
			User user = userService.findUser(userId);
			Optional<Forum> forum = forumService.findById(ForumId);
			comment.setUser(user);
			comment.setForum(forum.get());
			List<String> PALABRAS_INAPROPIADAS = Arrays.asList(
				"mierda", "puta", "cabrón", "coño", "joder", "marica", "polla", "puto", "zorra"
			);
			for (String palabra : PALABRAS_INAPROPIADAS) {
				if (comment.getText().contains(palabra)) {
					comment.setText("***");
				}
			}
			commentsService.save(comment);
			ModelAndView result=listadoComments(ForumId);		
			result.addObject("mensaje", "Comment saved sucessfully!");		
			result.addObject("tipomensaje", "success");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return "redirect:/";
	}
	
	@GetMapping(path="/{ForumId}/delete/{id}")
	public ModelAndView delteComment(@PathVariable("id") long id, @PathVariable("ForumId") long ForumId){		
		commentsService.delete(id);
		ModelAndView result=listadoComments(ForumId);		
		result.addObject("mensaje", "Comment borrado con éxito!");		
		result.addObject("tipomensaje", "success");
		return result;
	}
	

}
