package sg.edu.nus.iss.d13revision.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void healthCheck_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("HEALTH CHECK OK!"));
    }

    @Test
    void version_shouldContainVersion() throws Exception {
        mockMvc.perform(get("/version"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1.0.0")));
    }

    @Test
    void nations_shouldReturnArrayOf10Objects() throws Exception {
        mockMvc.perform(get("/nations"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.length()").value(10))
                .andExpect(jsonPath("$[0].nationality").exists())
                .andExpect(jsonPath("$[0].capitalCity").exists())
                .andExpect(jsonPath("$[0].flag").exists())
                .andExpect(jsonPath("$[0].language").exists());
    }

    @Test
    void currencies_shouldReturnArrayOf20Objects() throws Exception {
        mockMvc.perform(get("/currencies"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.length()").value(20))
                .andExpect(jsonPath("$[0].name").exists())
                .andExpect(jsonPath("$[0].code").exists());
    }
}

