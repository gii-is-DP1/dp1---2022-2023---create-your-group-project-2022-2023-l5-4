package org.springframework.samples.dobble.tests.user;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.samples.dobble.user.User;
import org.springframework.samples.dobble.user.UserService;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UserTest {

    UserService serv;

    @Test
    public void testUsers(){
        createUserTest();

    }

    public void createUserTest(){
        User c = new User();
        User p = new User();
        User n = new User();
        List<User> r = new ArrayList<>();
        r.add(p);
        r.add(n);
        c.setUsername("testeo1");
        c.setPassword("test1");
        c.setFriends(r);
        c.setEmail("testeo1@gmail.com");
        r.add(c);
        r.contains(c);
    }

    

    


    
}
