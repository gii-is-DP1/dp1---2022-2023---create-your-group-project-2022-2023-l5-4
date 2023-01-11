package org.springframework.samples.dobble.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.dobble.comment.Comment;
import org.springframework.samples.dobble.forum.ForumService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/friends")
public class FriendController {

    private static final String VIEW_FRIEND_LIST = "users/friendList";
    private static final String PAGE_FRIEND_LIST = "redirect:/friends";
    private final UserService userService;
    private final ForumService forumService;

    @Autowired
    public FriendController(UserService userService, ForumService forumService) {
        this.userService = userService;
        this.forumService = forumService;
    }

    @GetMapping("/search")
        @ResponseBody
        public List<String> search(@RequestParam String term) {
            List<User> usersTerm = new ArrayList<User>();
            Iterable<User> users = userService.findAll();
            Integer count=0;
            for(User user : users){
                if(user.getUsername().toLowerCase().contains(term.toLowerCase()) && count<4){
                    usersTerm.add(user);
                    count+=1;
                }if(count>=4){
                    break;
                }
            }
        return usersTerm.stream().map(User::getUsername).collect(Collectors.toList());
    }

    @PostMapping("/search")
        public String search(@RequestParam String username, Model model) {
        User user = userService.findUser(username);
        if (user == null) {
            model.addAttribute("errorMessage", "User not found");
            return "friends";
        }
        return "redirect:/friends";
    }

    @RequestMapping
    public String listFriends(@RequestParam(defaultValue = "0") int page,  ModelMap model) {
        page = page < 0 ? 0 : page;
        Pageable pageable = PageRequest.of(page, 3);
        List<User> friends = userService.getFriends();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        User user = userService.findUser(userId);
        List<Comment> comments = new ArrayList<Comment>();
        for(Comment comment: forumService.findById(2).get().getComments()){
            if(friends.contains(comment.getUser()) || comment.getUser().equals(user)){
                comments.add(comment);
            }
        }
		model.put("formid", 2);
		model.put("comment", new Comment());		
		model.put("comments", comments);
        if (friends.size() < 3) {
            model.put("friendsList", friends);
            model.put("isNext", false);
        }
         
        Page<User> friendsPage = userService.getFriendsPaged(pageable);
        if (!friends.isEmpty() && friendsPage.isEmpty()) {
            page = friends.size() / 5;
            pageable = PageRequest.of(page, 3);
            friendsPage = userService.getFriendsPaged(pageable);
        }
        model.put("isNext", friendsPage.hasNext());
        model.addAttribute("friendsList", friendsPage.getContent());
        model.put("page", page);
        return VIEW_FRIEND_LIST;
    }


    /*@GetMapping("/search")
    @ResponseBody
    public List<String> search(@RequestParam String term) {
        Integer count = 0;
        Integer j=0;
        List<User> usersTerm = new ArrayList<User>();
        Iterable<User> users = userService.findAll();
        for(User user : users){
            if(j < 4){
                count=0;
                while(count<user.getUsername().length() && count<term.length()){
                    if(term.charAt(count) == user.getUsername().charAt(count)){
                        usersTerm.add(user);
                    }
                    count += 1;
                }
            }
            j += 1;
            
        }
        return usersTerm.stream().map(User::getUsername).collect(Collectors.toList());
    }
    @GetMapping("/search")
    @ResponseBody
    public List<String> search(@RequestParam String term) {
    Iterable<User> users = userService.findAll();
    Integer count = 0;
    Integer j=0;
    List<User> usersTerm = new ArrayList<User>();
    for(User user : users){
        if(j < 4){
            count=0;
            while(count<user.getUsername().length() && count<term.length()){
                if(term.charAt(count) == user.getUsername().charAt(count)){
                    usersTerm.add(user);
                }
                count += 1;
            }
        }
        j += 1;
         
    }
    return usersTerm.stream().map(User::getUsername).collect(Collectors.toList());
    }*/

    @GetMapping("/add/{username}")
    public String addFriend(@PathVariable("username") String username) {
        userService.addFriend(username);
        return PAGE_FRIEND_LIST;
    }

    @GetMapping("/remove/{username}")
    public String removeFriend(@PathVariable("username") String username) {
        userService.removeFriend(username);
        return PAGE_FRIEND_LIST;
    }
}
