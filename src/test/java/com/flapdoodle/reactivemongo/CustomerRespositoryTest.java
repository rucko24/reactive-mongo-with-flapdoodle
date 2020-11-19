package com.flapdoodle.reactivemongo;

import com.flapdoodle.reactivemongo.customer.Customer;
import com.flapdoodle.reactivemongo.repository.CustomerRespository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.function.Predicate;

/**
 *
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class CustomerRespositoryTest {

    @Autowired
    private CustomerRespository respository;

    @Test
    public void findByName() {
        final String commonName = "Paul";
        final Customer one = new Customer("1",commonName);
        final Customer two = new Customer("2","John");
        final Customer three = new Customer("3",commonName);

        final Publisher<Customer> setup = this.respository
                .deleteAll()
                .thenMany(respository.saveAll(Flux.just(one,two,three)))
                .thenMany(respository.findByName(commonName));

        final Predicate<Customer> customerPredicate = customer -> commonName.equalsIgnoreCase(customer.getName());

        StepVerifier
                .create(setup)
                .expectNextMatches(customerPredicate)
                .expectNextMatches(customerPredicate)
                .verifyComplete();

    }

}
