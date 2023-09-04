package de.ait.artcake.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ait.artcake.dto.UserDto;
import org.junit.jupiter.api.*;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("UsersController is works: ")
@ActiveProfiles("test")
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    @DisplayName("GET /api/users/role/{role} method is works: ")
    class GetUsersByRoleTests {

        @WithUserDetails(value = "test@mail.com")
        @Sql(scripts = "/sql/data_for_users.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        public void getAllUsersByRole() throws Exception {
            mockMvc.perform(get("/api/users/role/CONFECTIONER")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("GET /api/users/manager/orders method is works: ")
    class GetAllOrdersAsManagerTests {
        @WithUserDetails(value = "manager@mail.com")
        @Sql(scripts = "/sql/data_for_orders.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        void getAllOrdersAsManager() throws Exception {

            mockMvc.perform(get("/api/users/manager/orders")
                            .param("page", "0"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.count", is(2)));
        }

        @Sql(scripts = "/sql/data_for_orders.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        void getAllOrdersAsManagerUnauthorized() throws Exception {

            mockMvc.perform(get("/api/users/manager/orders")
                            .param("page", "0"))
                    .andExpect(status().isUnauthorized());
        }
    }
}

    @Nested
    @DisplayName("GET /api/me method is works:")
    class GetMyProfile {

        @Sql(scripts = "/sql/data_for_users.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        public void getMyProfileReturnsForbiddenForNotAuthenticatedUser() throws Exception {
            mockMvc.perform(get("/api/users/me"))
                    .andExpect(status().isUnauthorized());
        }

        @WithUserDetails(value = "test@mail.com")
        @Sql(scripts = "/sql/data_for_users.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        public void getMyProfileReturnsProfileOfAuthenticatedUser() throws Exception {

            mockMvc.perform(get("/api/users/me"))
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.firstName", is("Jim")))
                    .andExpect(jsonPath("$.lastName", is("Carrey")))
                    .andExpect(jsonPath("$.email", is("test@mail.com")))
                    .andExpect(jsonPath("$.town", is("Hamburg")))
                    .andExpect(jsonPath("$.zipCode", is("22339")))
                    .andExpect(jsonPath("$.street", is("Norbert-Schmid-Platz")))
                    .andExpect(jsonPath("$.houseNumber", is(55)))
                    .andExpect(jsonPath("state", is("CONFIRMED")))
                    .andExpect(jsonPath("$.role", is("MANAGER")))
                    .andExpect(jsonPath("$.phoneNumber", is("+4917611223344")))
                    .andExpect(status().isOk());
        }
    }
}
