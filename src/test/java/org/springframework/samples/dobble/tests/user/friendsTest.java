package org.springframework.samples.dobble.tests.user;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.faces.validator.Validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.samples.dobble.user.User;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class friendsTest {


    @Test
    public void testFriends(){
        addFr();
        removeFr();
    }

    public void addFr(){
        User g = new User();
        User c = new User();
        g.addFriend(c);
        g.getFriends().contains(c);

    }

    public void removeFr(){
        User g = new User();
        User c = new User();
        g.addFriend(c);
        g.removeFriend(c);
        g.getFriends().isEmpty();
        
    }
}
