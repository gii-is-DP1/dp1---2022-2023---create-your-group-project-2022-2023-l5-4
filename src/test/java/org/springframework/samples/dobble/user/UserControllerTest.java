// package org.springframework.samples.petclinic.user;
// import static org.assertj.core.api.Assertions.assertThat;

// import java.util.Collection;
// import java.util.Optional;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.context.annotation.ComponentScan;
// import org.springframework.context.annotation.FilterType;
// import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
// import org.springframework.samples.petclinic.user.User;
// import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
// import org.springframework.security.test.context.support.WithMockUser;
// import org.springframework.stereotype.Service;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.RequestBuilder;
// import org.springframework.transaction.annotation.Transactional;

// @WebMvcTest(controllers=UserController.class,
// excludeFilters=@ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE, classes=WebSecurityConfigurer.class),
// excludeAutoConfiguration=SecurityConfiguration.class)
// public class UserServiceTest {
//     public static final String NAME_USER_TO_EDIT="edurobrus";

//     @MockBean
// 	protected UserService userService;

    // @Autowired
    // private MockMvc mockMvc;

    // @WithMockUser
    // @Test
    // public void testUserListing() throws Exception{
    //     mockMvc.perform(get("/users")).
    //             andExpect(status().isOk()).
    //             andExpect(view().name("showAllUsers")).
    //             andExpect(model().attributeExists("users"));
    // }

    // @WithMockUser
    // @Test
    // public void testUserEditForm() throws Exception{
    //     when(userService.getUser().thenReturn(new User()));

    //     mockMvc.perform(get("/user/edit",NAME_USER_TO_EDIT)).
    //             andExpect(status().isOk()).
    //             andExpect(view().name("EditUser")).
    //             andExpect(model().attributeExists("user"));
    // }
    