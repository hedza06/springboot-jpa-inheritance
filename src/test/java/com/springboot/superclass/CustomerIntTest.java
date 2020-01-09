package com.springboot.superclass;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.supperclass.Application;
import com.springboot.supperclass.controllers.CustomerController;
import com.springboot.supperclass.domain.Customer;
import com.springboot.supperclass.enumerations.CustomerType;
import com.springboot.supperclass.repositories.CustomerRepository;
import com.springboot.supperclass.services.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "no-liquibase")
public class CustomerIntTest {

    private static final String API = "/api/customer";

    private MockMvc mockMvc;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;


    @PostConstruct
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        CustomerController customerController = new CustomerController(customerService);

        mockMvc = MockMvcBuilders
            .standaloneSetup(customerController)
            .build();
    }

    @Test
    @Transactional
    public void storeCustomerTest() throws Exception
    {
        Customer customer = createCustomer();
        MvcResult mvcResult = mockMvc
            .perform(post(API)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsBytes(customer))
            )
            .andExpect(status().isCreated())
            .andReturn();

        Customer storedCustomer = new ObjectMapper().readValue(
            mvcResult.getResponse().getContentAsString(), Customer.class
        );
        assertThat(storedCustomer.getValidFrom()).isEqualTo(customer.getValidFrom());
        assertThat(storedCustomer.getValidTo()).isEqualTo(customer.getValidTo());
        assertThat(storedCustomer.getSourceIdentifier()).isEqualTo(customer.getSourceIdentifier());
        assertThat(storedCustomer.getActive()).isEqualTo(customer.getActive());
    }

    @Test
    @Transactional
    public void getCustomerByIdTest() throws Exception
    {
        Customer customer = createCustomer();
        customerRepository.save(customer);

        mockMvc
            .perform(get(API.concat("/").concat(customer.getId().toString()))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.validFrom").value(customer.getValidFrom()))
            .andExpect(jsonPath("$.validTo").isEmpty())
            .andExpect(jsonPath("$.sourceIdentifier").value(customer.getSourceIdentifier()))
            .andExpect(jsonPath("$.active").value(customer.getActive()));
    }

    @Test
    @Transactional
    public void getAllCustomersTest() throws Exception
    {
        Customer customer = createCustomer();
        customerRepository.save(customer);

        mockMvc
            .perform(get(API)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.[*].validFrom").value(hasItem(customer.getValidFrom().getTime())))
            .andExpect(jsonPath("$.[*].validTo").exists())
            .andExpect(jsonPath("$.[*].sourceIdentifier").value(hasItem(customer.getSourceIdentifier())))
            .andExpect(jsonPath("$.[*].active").value(hasItem(customer.getActive())));
    }

    private Customer createCustomer()
    {
        Customer customer = new Customer();
        customer.setFullName("Heril Muratovic");
        customer.setAddress("Dummy address");
        customer.setActive(Boolean.TRUE);
        customer.setValidFrom(new Date());
        customer.setSourceIdentifier("dummy-identifier");
        customer.setCustomerType(CustomerType.INDIVIDUAL);

        return customer;
    }
}
