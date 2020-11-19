package com.flapdoodle.reactivemongo;

import com.flapdoodle.reactivemongo.customer.Customer;
import com.flapdoodle.reactivemongo.repository.CustomerRespository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;


/**
 *
 */
@Configuration
public class CustomerWebConfiguration {


    @Bean
    RouterFunction<ServerResponse> routes(final CustomerRespository respository) {
        return route(RequestPredicates.GET("/customers"),
                request -> ServerResponse.ok().body( respository.findAll(),Customer.class));
    }
}
