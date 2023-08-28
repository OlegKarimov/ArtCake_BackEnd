package de.ait.artcake.controllers;

import de.ait.artcake.dto.NewCakeDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.ait.artcake.dto.UpdateCakeDto;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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

    @Nested
    @DisplayName("POST /api/cakes method is works: ")
    class AddCakeTest {

        @WithUserDetails(value = "test@mail.com")
        @Sql(scripts = "/sql/data_for_cakes.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        void add_cake_as_Manager() throws Exception {


            String body = objectMapper.writeValueAsString(NewCakeDto.builder()
                    .name("oreoCheesecake")
                    .ingredients("oreo, mascarpone, sugar")
                    .price(180.00)
                    .category("CHEESECAKES")
                    .build());

            mockMvc.perform(post("/api/cakes")
                            .header("Content-Type", "application/json")
                            .content(body))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id", is(4)))
                    .andExpect(jsonPath("$.name", is("oreoCheesecake")))
                    .andExpect(jsonPath("$.ingredients", is("oreo, mascarpone, sugar")))
                    .andExpect(jsonPath("$.price", is(180.00)))
                    .andExpect(jsonPath("$.category", is("CHEESECAKES")));
        }

        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        void add_cake_as_Client() throws Exception {

            String body = objectMapper.writeValueAsString(NewCakeDto.builder()
                    .name("oreoCheesecake")
                    .ingredients("oreo, mascarpone, sugar")
                    .price(180.00)
                    .category("CHEESECAKES")
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
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        public void getAllCakes() throws Exception {
            mockMvc.perform(get("/api/cakes")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("GET /api/cakes/category/{category} method is works: ")
    class GetCakesByCategoryTests {
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        public void getCakesByCategory() throws Exception {
            mockMvc.perform(get("/api/cakes/category/MOUSSE")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("DELETE /api/cakes/{cakesId} method is works: ")
    class DeleteCakeTests {
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        void delete_cake_as_Client() throws Exception {
            mockMvc.perform(delete("/api/cakes/1"))
                    .andExpect(status().isForbidden());
        }

        @WithUserDetails(value = "test@mail.com")
        @Sql(scripts = "/sql/data_for_cakes.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        void delete_cake_as_Manager() throws Exception {
            mockMvc.perform(delete("/api/cakes/1"))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("PUT /api/cakes/{cakesId} method is works: ")
    class UpdateCakeTests {

        @Test
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
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

        @Test
        @WithUserDetails(value = "test@mail.com")
        @Sql(scripts = "/sql/data_for_cakes.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        void update_cake_as_Manager() throws Exception {
            String body = objectMapper.writeValueAsString(UpdateCakeDto.builder()
                    .name("ferrero-cheesecake")
                    .ingredients("ferrero, mascarpone, egg....")
                    .price(210.50)
                    .build());

            mockMvc.perform(put("/api/cakes/1")
                            .header("Content-Type", "application/json")
                            .content(body))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.name", is("ferrero-cheesecake")))
                    .andExpect(jsonPath("$.ingredients", is("ferrero, mascarpone, egg....")))
                    .andExpect(jsonPath("$.price", is(210.50)))
                    .andExpect(jsonPath("$.category", is("CHEESECAKES")));
        }
    }
}
