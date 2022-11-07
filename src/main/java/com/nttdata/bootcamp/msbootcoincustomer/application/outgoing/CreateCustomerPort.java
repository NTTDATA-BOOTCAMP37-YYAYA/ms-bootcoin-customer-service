package com.nttdata.bootcamp.msbootcoincustomer.application.outgoing;

import com.nttdata.bootcamp.msbootcoincustomer.domain.model.Customer;

import reactor.core.publisher.Mono;

public interface CreateCustomerPort {

	Mono<Customer> createCustomer(Customer customer);
}
