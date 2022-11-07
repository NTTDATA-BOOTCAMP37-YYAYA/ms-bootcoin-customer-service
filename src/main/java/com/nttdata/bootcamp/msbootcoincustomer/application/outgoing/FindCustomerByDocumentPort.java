package com.nttdata.bootcamp.msbootcoincustomer.application.outgoing;

import com.nttdata.bootcamp.msbootcoincustomer.domain.model.Customer;

import reactor.core.publisher.Mono;

public interface FindCustomerByDocumentPort {

	Mono<Customer> findCustomerByDocument(String customerDocument,String documentTypeId);
}
