package com.codeup.codeupspringblog;
import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.Repositories.PostRepository;
import com.codeup.codeupspringblog.Repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import com.codeup.codeupspringblog.models.User;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CodeupSpringBlogApplication.class)
@AutoConfigureMockMvc
public class PostsIntegrationTests {
    private User testUser;
    private HttpSession httpSession;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userDao;

    @Autowired
    private PostRepository postDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setup() throws Exception {
        testUser = userDao.findByUsername("k");

        if (testUser == null) {
            User newUser = new User();
            newUser.setUsername("k");
            newUser.setPassword(passwordEncoder.encode("pass"));
            newUser.setEmail("khinememekyaw223@gmail.com");
            testUser = userDao.save(newUser);
        }

        httpSession = this.mvc.perform(post("/login").with(csrf())
                        .param("username", "k")
                        .param("password", "k"))
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(redirectedUrl("/profile"))
                .andReturn()
                .getRequest()
                .getSession();
    }

    @Test
    public void contextLoads() {
        // Sanity Test, just to make sure the MVC bean is working
        assertNotNull(mvc);
    }

    @Test
    public void testIfUserSessionIsActive() throws Exception {
        // It makes sure the returned session is not null
        System.out.println(userDao.findByUsername("k"));
        assertNotNull(httpSession);
    }

    @Test
    public void testCreatePost() throws Exception {
        // Makes a Post request to /ads/create and expect a redirection to the Ad
        this.mvc.perform(
                        post("/posts/create").with(csrf())
                                .session((MockHttpSession) httpSession)
                                // Add all the required parameters to your request like this
                                .param("title", "test")
                                .param("body", "for sale"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testPostsIndex() throws Exception {
        Post existingPost = postDao.findAll().get(0);

        // Makes a Get request to /ads and verifies that we get some of the static text of the ads/index.html template and at least the title from the first Ad is present in the template.
        this.mvc.perform(get("/posts"))
                .andExpect(status().isOk())
                // Test the static content of the page
                .andExpect(content().string(containsString("All ads")))
                // Test the dynamic content of the page
                .andExpect(content().string(containsString(existingPost.getTitle())));
    }

    @Test
    public void testShowPosts() throws Exception {

        Post existingPost = postDao.findAll().get(0);

        // Makes a Get request to /ads/{id} and expect a redirection to the Ad show page
        this.mvc.perform(
                        get("/posts/" + existingPost.getId()).with(csrf()).session((MockHttpSession) httpSession))
                .andExpect(status().isOk())
                // Test the dynamic content of the page
                .andExpect(content().string(containsString(existingPost.getBody())));
    }
}