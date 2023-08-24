package de.ait.final_project.controllers;

import de.ait.final_project.dto.NewCakeDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.ait.final_project.dto.UpdateCakeDto;
import de.ait.final_project.repositories.UsersRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("CakesController is works: ")
@ActiveProfiles("test")
public class CakeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UsersRepository usersRepository;

    @Nested
    @DisplayName("POST /api/cakes method is works: ")
    class AddCakeTest {

        @Test
        void add_cake_as_Client() throws Exception {

            String body = objectMapper.writeValueAsString(NewCakeDto.builder()
                    .name("oreoCheesecake")
                    .ingredients("oreo, mascarpone, sugar")
                    .price(180.00)
                    .category("CHEESECAKE")
                    .build());

            mockMvc.perform(post("/api/cakes")
                            .header("Content-Type", "application/json")
                            .content(body))
                    .andDo(print())
                    .andExpect(status().isForbidden());

        }
    }

    @Nested
    @DisplayName("GET /api/cakes method is works: ")
    class GetAllCakesTests {
        @Test
        public void getAllCakes() throws Exception {
            mockMvc.perform(get("/api/cakes")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("DELETE /api/cakes/{cakesId} method is works: ")
    class DeleteCakeTests {

        @Test
        void delete_cake_as_Client() throws Exception {
            mockMvc.perform(delete("/api/cakes/2"))
                    .andExpect(status().isForbidden());
        }
    }

    @Nested
    @DisplayName("PUT /api/cakes/{cakesId} method is works: ")
    class UpdateCakeTests {

        @Test
        void update_cake_as_Client() throws Exception {

            String body = objectMapper.writeValueAsString(UpdateCakeDto.builder()
                    .name("ferrero-cheesecake")
                    .ingredients("ferrero, mascarpone, egg....")
                    .price(210.50)
                    .build());

            mockMvc.perform(put("/api/cakes/1")
                            .header("Content-Type", "application/json")
                            .content(body))
                    .andDo(print())
                    .andExpect(status().isForbidden());
        }
    }
}
//@WithMockUser(roles = "MANAGER")
//@Sql(scripts = "/sql/data_for_cakes.sql")
//@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)