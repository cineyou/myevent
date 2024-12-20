package adeo.leroymerlin.cdp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EventIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindEvents_whenCall_shouldReturnListOf5Events() throws Exception {

        mockMvc.perform(get("/api/events/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));

    }

    @Test
    public void testFindEventsWithQuery_whenCallwithwaQuery_shouldReturnOneEvent() throws Exception {

        mockMvc.perform(get("/api/events/search/{query}", "wa"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

    }

    @Test
    public void testDeleteEvent_whenCall_shouldDeleteOneEvent() throws Exception {

        mockMvc.perform(delete("/api/events/{id}", 1003))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/events/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    public void testUpdateEvent() throws Exception {

        mockMvc.perform(put("/api/events/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1000,\"nbStars\":5, \"comment\":\"Updated Comment\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/events/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nbStars").value(5))
                .andExpect(jsonPath("$[0].comment").value("Updated Comment"));


    }
}

