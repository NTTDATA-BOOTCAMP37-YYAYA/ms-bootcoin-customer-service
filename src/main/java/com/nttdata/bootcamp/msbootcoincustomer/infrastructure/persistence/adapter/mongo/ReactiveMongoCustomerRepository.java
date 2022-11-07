package com.nttdata.bootcamp.msbootcoincustomer.infrastructure.persistence.adapter.mongo;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.msbootcoincustomer.infrastructure.persistence.entity.CustomerEntity;

import reactor.core.publisher.Mono;

public interface ReactiveMongoCustomerRepository extends ReactiveMongoRepository<CustomerEntity, String>{

  @Query("{'customerDocument': ?0,'documentTypeId' : ?1}")
  public Mono<CustomerEntity> findCustomerByDocument(String customerDocument, 
	                                                          String documentTypeId);

}