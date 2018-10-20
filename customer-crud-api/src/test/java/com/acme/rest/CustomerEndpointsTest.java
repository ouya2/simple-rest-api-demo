package com.acme.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.acme.model.Customer;
import com.acme.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerEndpointsTest extends EndpointTest {

  @Autowired
  private CustomerService customerService;

  Customer testCustomer;

  @Before
  public void setup() {
    super.setup();
    testCustomer = new Customer("Dick", "Smith");
    customerService.createCustomer(testCustomer);
  }

  @Test
  public void getCustomerById() throws Exception {
    Long customerId = testCustomer.getId();
    mockMvc.perform(get("/api/customers/{id}", customerId))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(JSON_MEDIA_TYPE))
        .andExpect(jsonPath("$.id").value(customerId))
        .andExpect(jsonPath("$.firstName").value(testCustomer.getFirstName()))
        .andExpect(jsonPath("$.lastName").value(testCustomer.getLastName()));
  }

  @Test
  public void createCustomer() throws Exception {
    Customer newCustomer = new Customer("John", "Lennings");
    newCustomer.setMiddleName("Walli");
    String jsonBody = json(newCustomer);
    mockMvc.perform(post("/api/customers")
                    .accept(JSON_MEDIA_TYPE)
                    .content(jsonBody)
                    .contentType(JSON_MEDIA_TYPE))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").isNumber())
            .andExpect(jsonPath("$.firstName").value("John"))
            .andExpect(jsonPath("$.lastName").value("Lennings"));
  }

  @Test
  public void deleteCustomer() throws Exception {
    Long customerId = testCustomer.getId();
    mockMvc.perform(delete("/api/customers/{id}", customerId))
          .andDo(print())
          .andExpect(status().isNoContent());
  }
}
