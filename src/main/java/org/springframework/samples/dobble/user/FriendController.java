package org.springframework.samples.dobble.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/friends")
public class FriendController {

    private static final String VIEW_FRIEND_LIST = "users/friendList";
    private static final String PAGE_FRIEND_LIST = "redirect:/friends";
    private final UserService userService;

    @Autowired
    public FriendController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping
    public String listFriends(@RequestParam(defaultValue = "0") int page, ModelMap model) {
        page = page < 0 ? 0 : page;
        Pageable pageable = PageRequest.of(page, 3);
        List<User> friends = userService.getFriends();
        
        if (friends.size() < 3) {
            model.put("friendsList", friends);
            model.put("isNext", false);
            return VIEW_FRIEND_LIST;
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
