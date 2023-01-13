package org.springframework.samples.dobble.tests.chat;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.dobble.forum.Forum;
import org.springframework.samples.dobble.forum.ForumService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class forumControllerTest {

    private Forum forum1;

    @Mock
    private ForumService serv;

    @Autowired
	private MockMvc mockMvc;

    @BeforeEach
    void setup(){
        Forum forum1 = new Forum();
        forum1.setName("forum1");
        forum1.setId((long) 19);
        forum1.setCreationDate(LocalDateTime.now());
        given(this.serv.findByNombre(forum1.getName())).willReturn(forum1);

    }

    @WithMockUser(value = "spring")
    @Test
    public void createForum() throws Exception{
        mockMvc.perform(get("/api/forum/create"))
                .andExpect(view().name("forums/EditForum"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("forum"));

    }

    @WithMockUser(value = "spring")
    @Test
    public void deleteForum() throws Exception{
        mockMvc.perform(get("/api/forum/delete/19"))
                        .andExpect(status().isOk())
                        .andExpect(model().attributeDoesNotExist("forum"));
    }

    
    
}
