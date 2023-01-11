package org.springframework.samples.dobble.tests.chat;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.dobble.comment.Comment;
import org.springframework.samples.dobble.comment.CommentRepository;
import org.springframework.samples.dobble.forum.Forum;
import org.springframework.samples.dobble.forum.ForumRepository;
import org.springframework.samples.dobble.forum.ForumService;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class chatTest {

    @MockBean
    CommentRepository repo1;

    @MockBean
    ForumRepository repo2;

    @MockBean
    ForumService serv2;

    @Test
    public void createForumAndComment(){
        Comment c = new Comment();
        Forum m = new Forum();
        List<Comment> r = new ArrayList<>();
        r.add(c);
        m.setComments(r);
        m.hasComment();
        when(repo2.save(any(Forum.class))).thenReturn(m);

    }
    @Test
    public void resetCommentsTest(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Comment c = new Comment();
        Forum m = new Forum();
        List<Comment> r = new ArrayList<>();
        r.add(c);
        m.setComments(r);
        m.hasComment();
        serv2.resetComments();
        assertFalse(validator.validate(m).isEmpty(), "Los mensajes se han borrado con exito");

    }

    
}
