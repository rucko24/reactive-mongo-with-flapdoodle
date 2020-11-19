package com.flapdoodle.reactivemongo;

import com.flapdoodle.reactivemongo.customer.Customer;
import com.flapdoodle.reactivemongo.repository.CustomerRespository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

/**
 *
 */
@WebFluxTest
@Import(CustomerWebConfiguration.class)
@RunWith(SpringRunner.class)
public class CustomerWebTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private CustomerRespository respository;

    @Test
    public void getAll() {
        Mockito.when(respository.findAll())
                .thenReturn(Flux.just(new Customer("1","A"), new Customer("2","B")));

        this.client.get()
                .uri("/customers")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.[0].id").isEqualTo("1")
                .jsonPath("$.[0].name").isEqualTo("A")
                .jsonPath("$.[1].id").isEqualTo("2")
                .jsonPath("$.[1].name").isEqualTo("B");


    }
}
