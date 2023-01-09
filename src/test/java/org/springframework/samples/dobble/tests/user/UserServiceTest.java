package org.springframework.samples.dobble.tests.user;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.dobble.user.UserRepository;

public class UserServiceTest {
    
    @MockBean
    protected UserRepository userRepository;
}
