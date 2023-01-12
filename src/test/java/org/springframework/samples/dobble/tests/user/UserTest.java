package org.springframework.samples.dobble.tests.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.dobble.user.Authorities;
import org.springframework.samples.dobble.user.AuthoritiesRepository;
import org.springframework.samples.dobble.user.AuthoritiesService;
import org.springframework.samples.dobble.user.User;
import org.springframework.samples.dobble.user.UserRepository;
import org.springframework.samples.dobble.user.UserService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringRunner.class)
public class UserTest {
	

	@InjectMocks
    private UserService serv;
	
	@InjectMocks
	private AuthoritiesService authoritiesService;

    @Mock
    private UserRepository userRepository;
    
    @Mock
    private AuthoritiesRepository authoritiesRepository;
    
    @Mock
    private UserService userService;
    
    @Before
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
    	ReflectionTestUtils.setField(serv, "userRepository", userRepository);
    	ReflectionTestUtils.setField(serv, "authoritiesService", authoritiesService);
    	ReflectionTestUtils.setField(authoritiesService, "userService", userService);
    	ReflectionTestUtils.setField(authoritiesService, "authoritiesRepository", authoritiesRepository);
    }
    
    @Test
    public void createUserTest(){
        User c = new User();
        c.setUsername("testeo1");
        c.setPassword("test1");
        c.setEmail("testeo1@gmail.com");
        
        Authorities authorities = new Authorities();
        authorities.setUser(c);
        authorities.setAuthority("user");
        
        when(userRepository.save(any(User.class))).thenReturn(c);
        when(authoritiesRepository.save(any(Authorities.class))).thenReturn(authorities);
        when(userService.findUser(any(String.class))).thenReturn(c);
        serv.saveUser(c);
        
    }

    
}