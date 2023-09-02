package de.ait.artcake.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ait.artcake.dto.OrderDto;
import de.ait.artcake.dto.OrderInProcessDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
    @DisplayName("PUT /api/orders/{order-id} method is works:")
    class AddOrderToProcess {

        @WithUserDetails(value = "manager@mail.com")
        @Sql(scripts = "/sql/data_for_orders.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        void move_order_to_process_as_Manager() throws Exception {

            String body = objectMapper.writeValueAsString(OrderInProcessDto.builder()
                    .confectionerId(1L)
                    .extra(5.50)
                    .build());

            mockMvc.perform(put("/api/orders/1")
                            .param("orderId", "1")
                            .header("Content-Type", "application/json")
                            .content(body))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.count", is(1)))
                    .andExpect(jsonPath("$.clientWishes", is("For birthday(30 years)")))
                    .andExpect(jsonPath("$.creationDate", is("2023-09-01")))
                    .andExpect(jsonPath("$.deadline", is("2023-10-10")))
                    .andExpect(jsonPath("$.state", is("IN_PROCESS")))
                    .andExpect(jsonPath("$.totalPrice", is(206.0)));
//                  .andExpect(jsonPath("$.client", is(jsonPath("$.id",is(1)),
//                            jsonPath("$.firstName", is("Jim")),
//                            jsonPath("$.lastName", is("Carrey")),
//                            jsonPath("$.email", is("manager @mail.com")),
//                            jsonPath("$.town", is("Hamburg")),
//                            jsonPath("$.zipCode", is("22339")),
//                            jsonPath("$.street", is("Norbert-Schmid-Platz")),
//                            jsonPath("$.houseNumber",is(55)),
//                            jsonPath("$.phoneNumber", is("+4917611223344")),
//                            jsonPath("$.role",is("MANAGER")),
//                            jsonPath("$.state", is("CONFIRMED")));
        }

        @Sql(scripts = "/sql/data_for_orders.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        void move_order_to_process_as_Unauthorized() throws Exception {

            String body = objectMapper.writeValueAsString(OrderInProcessDto.builder()
                    .confectionerId(1L)
                    .extra(5.50)
                    .build());

            mockMvc.perform(put("/api/orders/1")
                            .param("orderId", "1")
                            .header("Content-Type", "application/json")
                            .content(body))
                    .andExpect(status().isUnauthorized());
        }

        @WithUserDetails(value = "client@mail.com")
        @Sql(scripts = "/sql/data_for_orders.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        void move_order_to_process_as_client_is_Forbidden() throws Exception {

            String body = objectMapper.writeValueAsString(OrderInProcessDto.builder()
                    .confectionerId(1L)
                    .extra(5.50)
                    .build());

            mockMvc.perform(put("/api/orders/1")
                            .param("orderId", "1")
                            .header("Content-Type", "application/json")
                            .content(body))
                    .andExpect(status().isForbidden());
        }

        @WithUserDetails(value = "confectioner@mail.com")
        @Sql(scripts = "/sql/data_for_orders.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        void move_order_to_process_as_confectioner_is_Forbidden() throws Exception {

            String body = objectMapper.writeValueAsString(OrderInProcessDto.builder()
                    .confectionerId(1L)
                    .extra(5.50)
                    .build());

            mockMvc.perform(put("/api/orders/1")
                            .param("orderId", "1")
                            .header("Content-Type", "application/json")
                            .content(body))
                    .andExpect(status().isForbidden());
        }
    }

    @Nested
    @DisplayName("PUT /api/users/client/orders method is works:")
    class GetAllOrdersForClient {

        @WithUserDetails(value = "client@mail.com")
        @Sql(scripts = "/sql/data_for_orders.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        void get_all_orders_for_Client_as_Client() throws Exception {

            mockMvc.perform(get("/api/users/client/orders")
                            .param("page", "0"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.count", is(1)));
        }

        @Sql(scripts = "/sql/data_for_orders.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        void get_all_orders_for_Client_as_Unauthorized() throws Exception {

            mockMvc.perform(get("/api/users/client/orders")
                            .param("page", "0"))
                    .andExpect(status().isUnauthorized());
        }

        @WithUserDetails(value = "confectioner@mail.com")
        @Sql(scripts = "/sql/data_for_orders.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        void get_all_orders_for_Client_as_Confectioner() throws Exception {

            mockMvc.perform(get("/api/users/client/orders")
                            .param("page", "0"))
                    .andExpect(status().isForbidden());
        }

        @WithUserDetails(value = "manager@mail.com")
        @Sql(scripts = "/sql/data_for_orders.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        void get_all_orders_for_Client_as_Manager() throws Exception {

            mockMvc.perform(get("/api/users/client/orders")
                            .param("page", "0"))
                    .andExpect(status().isForbidden());
        }
    }

}
