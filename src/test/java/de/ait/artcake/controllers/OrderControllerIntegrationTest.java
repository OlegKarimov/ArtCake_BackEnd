package de.ait.artcake.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ait.artcake.dto.NewCakeDto;
import de.ait.artcake.dto.NewOrderDto;
import de.ait.artcake.dto.OrderDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("OrdersController is works: ")
@ActiveProfiles("test")
public class OrderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    @DisplayName("POST /api/orders/cakes/ method is works: ")
    class AddCakeTest {

        @WithUserDetails(value = "client@mail.com")
        @Sql(scripts = "/sql/data_for_orders.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        void addOrderAsAuthenticatedClient() throws Exception {

            String body = objectMapper.writeValueAsString(NewOrderDto.builder()
                            .clientWishes("Make in blue and white colours")
                            .count(1)
                            .deadline("2023-10-10")
                            .build());

            mockMvc.perform(post("/api/orders/cakes/1")
                            .param("cakeId", "1")
                            .header("Content-Type", "application/json")
                            .content(body))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id", is(3)))
                    .andExpect(jsonPath("$.count", is(1)))
                    .andExpect(jsonPath("$.clientWishes", is("Make in blue and white colours")))
                    .andExpect(jsonPath("$.totalPrice", is(33.33)))
                    .andExpect(jsonPath("$.creationDate", is("2023-09-03")))
                    .andExpect(jsonPath("$.deadline", is("2023-10-10")))
                    .andExpect(jsonPath("$.state", is("CREATED")));
        }

        @Sql(scripts = "/sql/data_for_orders.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        void addOrderAsNotAuthenticatedClient() throws Exception {

            String body = objectMapper.writeValueAsString(NewOrderDto.builder()
                    .clientWishes("Make in blue and white colours")
                    .count(2)
                    .deadline("2023-10-10")
                    .build());

            mockMvc.perform(post("/api/orders/cakes/1")
                            .header("Content-Type", "application/json")
                            .content(body))
                    .andDo(print())
                    .andExpect(status().isUnauthorized());
        }
    }

    @Nested
    @DisplayName("PUT /api/orders/{order-id} method is works: ")
    class GetUsersByRoleTests {
        @WithUserDetails(value = "confectioner@mail.com")
        @Sql(scripts = "/sql/data_for_orders.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        void orderIsFinishedAsConfectioner() throws Exception {

            String body = objectMapper.writeValueAsString(OrderDto.builder()
                    .state("IN_PROCESS")
                    .build());

            mockMvc.perform(put("/api/orders/1/done")
                            .param("orderId", "1")
                            .header("Content-Type", "application/json")
                            .content(body))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.state", is("FINISHED")));
        }

        @WithUserDetails(value = "manager@mail.com")
        @Sql(scripts = "/sql/data_for_orders.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        void orderIsFinishedAsManagerIsForbidden() throws Exception {

            String body = objectMapper.writeValueAsString(OrderDto.builder()
                    .state("IN_PROCESS")
                    .build());

            mockMvc.perform(put("/api/orders/1/done")
                            .param("orderId", "1")
                            .header("Content-Type", "application/json")
                            .content(body))
                    .andDo(print())
                    .andExpect(status().isForbidden());
        }

        @WithUserDetails(value = "confectioner@mail.com")
        @Sql(scripts = "/sql/data_for_orders.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        void orderCantFinishAsConfectioner() throws Exception {

            String body = objectMapper.writeValueAsString(OrderDto.builder()
                    .state("IN_PROCESS")
                    .build());

            mockMvc.perform(put("/api/orders/1/decline")
                            .param("orderId", "1")
                            .header("Content-Type", "application/json")
                            .content(body))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.state", is("IN_PROCESS")));
        }

        @WithUserDetails(value = "manager@mail.com")
        @Sql(scripts = "/sql/data_for_orders.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        void orderCantFinishedAsManagerIsForbidden() throws Exception {

            String body = objectMapper.writeValueAsString(OrderDto.builder()
                    .state("IN_PROCESS")
                    .build());

            mockMvc.perform(put("/api/orders/1/decline")
                            .param("orderId", "1")
                            .header("Content-Type", "application/json")
                            .content(body))
                    .andDo(print())
                    .andExpect(status().isForbidden());
        }
    }
}
