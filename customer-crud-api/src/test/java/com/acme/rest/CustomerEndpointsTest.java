package com.acme.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.acme.model.Address;
import com.acme.model.Customer;
import com.acme.model.Customer.Gender;
import com.acme.service.CustomerService;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;

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

  @Test
  public void updateAndGetCustomer() throws Exception {
    Long customerId = testCustomer.getId();
    Customer newCustomer = new Customer("John", "Lennings");
    newCustomer.setMiddleName("Walli");
    String jsonBody = json(newCustomer);
    mockMvc.perform(patch("/api/customers/{id}", customerId)
                  .accept(JSON_MEDIA_TYPE)
                  .content(jsonBody)
                  .contentType(JSON_MEDIA_TYPE))
        .andDo(print())
        .andExpect(status().isOk());

    mockMvc.perform(get("/api/customers/{id}", customerId))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(JSON_MEDIA_TYPE))
        .andExpect(jsonPath("$.id").value(customerId))
        .andExpect(jsonPath("$.firstName").value("John"))
        .andExpect(jsonPath("$.lastName").value("Lennings"));
  }

  @Test
  public void CreateGetUpdateAndDelete() throws Exception {
    Customer newCustomer = createFullCustomer();
    String jsonBody = json(newCustomer);
    MvcResult result = mockMvc.perform(post("/api/customers")
        .accept(JSON_MEDIA_TYPE)
        .content(jsonBody)
        .contentType(JSON_MEDIA_TYPE))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").isNumber())
        .andExpect(jsonPath("$.middleName").value("Walli"))
        .andExpect(jsonPath("$.nabCustomer").value(false))
        .andExpect(jsonPath("$.creditRating").value(25))
        .andExpect(jsonPath("$.addresses[0].addressNumber").value(15))
        .andExpect(jsonPath("$.addresses[0].street").value("Shortland"))
        .andExpect(jsonPath("$.addresses[0].suburb").value("Richmond"))
        .andExpect(jsonPath("$.addresses[0].city").value("Melbourne"))
        .andReturn();

    Customer responseCustomer = toCustomer(result.getResponse().getContentAsString());

    mockMvc.perform(get("/api/customers/{id}", responseCustomer.getId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(JSON_MEDIA_TYPE))
        .andExpect(jsonPath("$.firstName").value(newCustomer.getFirstName()))
        .andExpect(jsonPath("$.lastName").value(newCustomer.getLastName()))
        .andExpect(jsonPath("$.middleName").value("Walli"))
        .andExpect(jsonPath("$.nabCustomer").value(false))
        .andExpect(jsonPath("$.creditRating").value(25))
        .andExpect(jsonPath("$.addresses[0].addressNumber").value(15))
        .andExpect(jsonPath("$.addresses[0].street").value("Shortland"))
        .andExpect(jsonPath("$.addresses[0].suburb").value("Richmond"))
        .andExpect(jsonPath("$.addresses[0].city").value("Melbourne"));

    Customer updateCustomer = new Customer("Thomas", "Edison");
    updateCustomer.setMiddleName("Alva");
    String updateJson = json(updateCustomer);
    mockMvc.perform(patch("/api/customers/{id}", responseCustomer.getId())
        .accept(JSON_MEDIA_TYPE)
        .content(updateJson)
        .contentType(JSON_MEDIA_TYPE))
        .andDo(print())
        .andExpect(status().isOk());

    mockMvc.perform(get("/api/customers/{id}", responseCustomer.getId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(JSON_MEDIA_TYPE))
        .andExpect(jsonPath("$.firstName").value(updateCustomer.getFirstName()))
        .andExpect(jsonPath("$.lastName").value(updateCustomer.getLastName()))
        .andExpect(jsonPath("$.middleName").value(updateCustomer.getMiddleName()));

    mockMvc.perform(delete("/api/customers/{id}", responseCustomer.getId()))
        .andDo(print())
        .andExpect(status().isNoContent());

  }

  private Customer createFullCustomer() {
    Address address = new Address("15", "Shortland", "Richmond", "Melbourne");
    Set<Address> addresses = new HashSet<>();
    addresses.add(address);

    Customer newCustomer = new Customer("John", "Lennings");
    newCustomer.setMiddleName("Walli");
    newCustomer.setTitle("Mr");
    newCustomer.setNabCustomer(false);
    newCustomer.setCreditRating(25);
    newCustomer.setSex(Gender.M);
    newCustomer.setMaritalStatus("Single");
    newCustomer.setAddresses(addresses);
    return newCustomer;
  }
}
