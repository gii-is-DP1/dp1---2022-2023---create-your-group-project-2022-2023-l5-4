package org.springframework.samples.dobble.tests.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.dobble.configuration.SecurityConfiguration;
import org.springframework.samples.dobble.game.GameUser;
import org.springframework.samples.dobble.user.Authorities;
import org.springframework.samples.dobble.user.AuthoritiesRepository;
import org.springframework.samples.dobble.user.AuthoritiesService;
import org.springframework.samples.dobble.user.User;
import org.springframework.samples.dobble.user.UserController;
import org.springframework.samples.dobble.user.UserRepository;
import org.springframework.samples.dobble.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

    private User user1;
    
    @Mock
    private UserService serv;

    @Autowired
	private MockMvc mockMvc;
    
    @BeforeEach
    void setup(){
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("password1");
        user1.setEmail("testeo1@gmail.com");
        given(this.serv.findUser(user1.getUsername())).willReturn(user1);

    }

    @WithMockUser(value = "spring")
    @Test
    public void testInitCreationForm() throws Exception{
        mockMvc.perform(get("/users/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("users/createOrUpdateUserForm"));
    }

    @WithMockUser(value = "spring")
    @Test
    public void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/users/new").param("username", "user1").param("password", "password1").with(csrf())
				.param("email", "testeo1@gmail.com"))
				.andExpect(status().is3xxRedirection());
	}

    @WithMockUser(value = "spring")
    @Test
    public void testShowUser() throws Exception {
        given(this.serv.findUser("user1")).willReturn(user1);

        mockMvc.perform(get("/users/user1")).andExpect(status().isOk())
                                                        .andExpect(view().name("users/createOrUpdateUserForm"));
    }

    @WithMockUser(value = "spring")
    @Test
    public void testUpdateUser() throws Exception{
        mockMvc.perform(get("/users/edit/user1"))
                .andExpect(status().isOk());
                //.andExpect(view().name("users/EditUser"));
    }

    @WithMockUser(value = "spring")
    @Test
    public void testUserProfile() throws Exception{
        mockMvc.perform(get("/users/profile/user1"))
                .andExpect(status().isOk())
                .andExpect(view().name("users/ProfileUser"))
                .andExpect(model().attributeExists("user"));

    }


    
}
