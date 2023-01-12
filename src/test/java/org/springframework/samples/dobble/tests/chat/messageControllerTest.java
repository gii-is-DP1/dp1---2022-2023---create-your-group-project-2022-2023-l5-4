package org.springframework.samples.dobble.tests.chat;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.dobble.comment.Comment;
import org.springframework.samples.dobble.comment.CommentService;
import org.springframework.samples.dobble.forum.Forum;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class messageControllerTest {

    private Comment comment1;
    
    @Mock
    private CommentService serv;

    @Autowired
	private MockMvc mockMvc;

    @BeforeEach
    void setup(){
        Comment comment1 = new Comment();
        comment1.setText("Hola soy un texto");
        comment1.setDate("1923/12/16");
        Forum forum1 = new Forum();
        forum1.setName("forum1");
        forum1.setCreationDate(LocalDateTime.now());
        forum1.setId((long) 19);
        Comment comment2 = new Comment();
        comment2.setText("zorra");
        comment2.setDate("1923/12/16");
        List<Comment> c = new ArrayList<>();
        forum1.setComments(c);

    }
    
    @Test
    @WithMockUser(value = "spring")
    public void showCommentsTest() throws Exception{
        mockMvc.perform(get("/comments/19"))
                //.andExpect(view().name("comments/CommentsListing"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void createCommentTest() throws Exception{
        mockMvc.perform(get("/comments/create"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("comment"))
                .andExpect(view().name("comments/EditComment"));
    }

    @Test
    @WithMockUser
    public void saveCommentTest() throws Exception{
        mockMvc.perform(post("/comments/19/save"))
                .andExpect(status().is3xxRedirection());
                

    }

    

}
