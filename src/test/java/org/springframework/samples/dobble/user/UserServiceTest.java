package org.springframework.samples.dobble.user;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository srepo;

    @Test    
    public void saveTestSucessful(){
        User u2=new User();
        u2.setUsername("Fernan10");
        u2.setPassword("Dp1test");
        UserService userService=new UserService(srepo, null);
        try{
            userService.saveUser(u2);
        }catch(Exception e){
            fail("This expeception should not be thrown!");
        }

    }

}