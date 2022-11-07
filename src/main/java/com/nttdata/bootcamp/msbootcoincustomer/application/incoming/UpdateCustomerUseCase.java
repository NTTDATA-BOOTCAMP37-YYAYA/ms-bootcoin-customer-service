package com.nttdata.bootcamp.msbootcoincustomer.application.incoming;

import com.nttdata.bootcamp.msbootcoincustomer.domain.model.Customer;

import reactor.core.publisher.Mono;

public interface UpdateCustomerUseCase {


	Mono<Customer> updateCustomer(String documentCustomer);
	
}
