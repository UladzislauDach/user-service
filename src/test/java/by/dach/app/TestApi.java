package by.dach.app;

import by.dach.app.model.dto.UserCreateDto;
import by.dach.app.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestApi {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;

    @Test
    public void shouldReturnHelloMessage() throws Exception {
        this.mockMvc
                .perform(get("/").header("Authorization", 123))  //wtf?
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(content().string(containsString("Hello, World")));
    }

    @Test
    public void checkAuthorizationGet() throws Exception {
        this.mockMvc.perform(get("/api/users"))
                .andDo(print())
                .andExpect(status().is(403));
        this.mockMvc.perform(get("/api/users").header("Authorization", 123))
                .andExpect(status().is(200));
    }

  //  @Test
    public void checkAuthorizationPost() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        UserCreateDto user = new UserCreateDto("Jack123", "123", "Jack", "Smith");
        this.mockMvc.perform(post("/api/users")
                .header("Authorization", 123)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        ).andDo(print())
                .andExpect(status().is(200))
                .andExpect(content().string(objectMapper.writeValueAsString(user)));
        userRepository.deleteAll();
    }
}
