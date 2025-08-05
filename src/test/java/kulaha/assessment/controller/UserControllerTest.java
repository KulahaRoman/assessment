package kulaha.assessment.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@Import(MockUserService.class)
@TestPropertySource("/application.properties")
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getUser_happyPath_isOk() throws Exception {
        mockMvc.perform(get(String.format("/users/%s", "test_user")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.repositories", hasSize(1)))
                .andExpect(jsonPath("$.repositories[0].name").value("test_repo"))
                .andExpect(jsonPath("$.repositories[0].owner").value("test_user"))
                .andExpect(jsonPath("$.repositories[0].branches", hasSize(1)))
                .andExpect(jsonPath("$.repositories[0].branches[0].name").value("master"))
                .andExpect(jsonPath("$.repositories[0].branches[0].hash").value("somehash"));

//        mockMvc.perform(get(String.format("/users/%s", "invalid_user")))
//                .andExpect(status().isNotFound())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.status").value(404))
//                .andExpect(jsonPath("$.message").value("user not found"));
    }
}
