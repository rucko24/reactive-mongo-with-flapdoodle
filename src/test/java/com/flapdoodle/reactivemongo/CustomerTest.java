package com.flapdoodle.reactivemongo;

import com.flapdoodle.reactivemongo.customer.Customer;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTest {

	@Test
	void contextLoads() {
		final Customer customer = new Customer("123","foo");

		assertEquals(customer.getId(),"123");
		assertThat(customer.getId(), Matchers.is("123"));
		Assertions.assertThat(customer.getName()).isEqualToIgnoringWhitespace("foo");
	}

}
