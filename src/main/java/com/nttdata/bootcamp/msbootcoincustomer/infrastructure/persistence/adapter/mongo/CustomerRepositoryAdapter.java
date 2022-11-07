package com.nttdata.bootcamp.msbootcoincustomer.infrastructure.persistence.adapter.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nttdata.bootcamp.msbootcoincustomer.application.outgoing.CreateCustomerPort;
import com.nttdata.bootcamp.msbootcoincustomer.application.outgoing.FindCustomerByDocumentPort;
import com.nttdata.bootcamp.msbootcoincustomer.domain.model.Customer;
import com.nttdata.bootcamp.msbootcoincustomer.infrastructure.persistence.entity.CustomerEntity;

import reactor.core.publisher.Mono;

@Component
public class CustomerRepositoryAdapter implements CreateCustomerPort,FindCustomerByDocumentPort{

  @Autowired
  private ReactiveMongoCustomerRepository reactiveMongoDB;

	@Override
	public Mono<Customer> createCustomer(Customer customer) {
	  return reactiveMongoDB.insert(CustomerEntity.toCustomerEntity(customer))
			  .map(CustomerEntity::toCustomer);
	}

	@Override
	public Mono<Customer> findCustomerByDocument(String customerDocument, String documentTypeId) {
	    return reactiveMongoDB.findCustomerByDocument(customerDocument, documentTypeId)
                .map(CustomerEntity::toCustomer);
	}
	 

	


}
