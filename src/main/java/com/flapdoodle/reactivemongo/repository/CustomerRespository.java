package com.flapdoodle.reactivemongo.repository;

import com.flapdoodle.reactivemongo.customer.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 *
 */
public interface CustomerRespository extends ReactiveMongoRepository<Customer,String> {

    Flux<Customer> findByName(String name);

}
