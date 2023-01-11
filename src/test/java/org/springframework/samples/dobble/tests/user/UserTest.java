package org.springframework.samples.dobble.tests.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.dobble.user.User;
import org.springframework.samples.dobble.user.UserRepository;
import org.springframework.samples.dobble.user.UserService;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UserTest {

    @MockBean
    UserService serv;

    @MockBean
    UserRepository rep;

    @Test
    public void createUserTest(){
        User c = new User();
        c.setUsername("testeo1");
        c.setPassword("test1");
        c.setEmail("testeo1@gmail.com");
        when(rep.save(any(User.class))).thenReturn(c);
        
    }


    

    


    
}
