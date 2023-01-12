package org.springframework.samples.dobble.tests.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.faces.validator.Validator;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.dobble.user.User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
public class friendsTest {

    private static final String USERNAME = "user1";
    private static final String PASSWORD = "password1";

    // @Autowired
    // private MockMvc mockMvc;

    // @BeforeEach
    // void setup(){
    //     User g = new User();
    //     g.setUsername(USERNAME);
    //     g.setPassword(PASSWORD);
    //     User c = new User();
    //     g.addFriend(c);

    // }

    @Test
    public void testFriends(){
        addFr();
        removeFr();
    }

    @Test
    public void addFr(){
        User g = new User();
        User c = new User();
        g.addFriend(c);
        g.getFriends().contains(c);

    }

    @Test
    public void removeFr(){
        User g = new User();
        User c = new User();
        g.addFriend(c);
        g.removeFriend(c);
        g.getFriends().isEmpty();
        
    }

    // @Test
    // @WithMockUser(USERNAME)
    // public void añadirFr() throws Exception {
    //     mockMvc.perform(get("/friends/add/admin1"))
    //             .andExpect(view().name("redirect:/friends"));
                
        
    // }
}
