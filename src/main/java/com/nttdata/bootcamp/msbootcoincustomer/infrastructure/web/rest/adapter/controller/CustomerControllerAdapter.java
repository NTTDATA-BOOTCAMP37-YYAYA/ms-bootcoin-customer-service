package com.nttdata.bootcamp.msbootcoincustomer.infrastructure.web.rest.adapter.controller;


import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.msbootcoincustomer.application.incoming.CreateCustomerUseCase;
import com.nttdata.bootcamp.msbootcoincustomer.application.incoming.FindCustomerByDocumentUseCase;
import com.nttdata.bootcamp.msbootcoincustomer.domain.model.Customer;
import com.nttdata.bootcamp.msbootcoincustomer.infrastructure.web.rest.adapter.controller.response.ResponseCustomer;

import reactor.core.publisher.Mono;

/**.*/
@RestController
@RequestMapping("/bootcoinCustomer")
public class CustomerControllerAdapter {

  final Logger logger = LoggerFactory.getLogger(CustomerControllerAdapter.class);
  
  @Autowired
  private  CreateCustomerUseCase createCustomerUseCase;
  @Autowired
  private  FindCustomerByDocumentUseCase findCustomerByDocumentUseCase;

  /**.*/
  @PostMapping()
  public Mono<ResponseEntity<ResponseCustomer>> createCustomer(@RequestBody Customer customer) {
    return   findCustomerByDocumentUseCase.findCustomerByDocument(customer.getCustomerDocument(),customer.getDocumentTypeId())
    .flatMap(findCustomer ->Mono.just(new ResponseEntity<ResponseCustomer>(
               new ResponseCustomer(HttpStatus.SC_NOT_FOUND, findCustomer, "Customer already exists"),
               null, HttpStatus.SC_NOT_FOUND)))
    .switchIfEmpty(createCustomerUseCase.createCustomer(customer)
                    .flatMap(newCustomer -> Mono.just(new ResponseEntity<ResponseCustomer>(
                    new ResponseCustomer(HttpStatus.SC_NOT_FOUND, newCustomer, "Customer has been created"),
                    null, HttpStatus.SC_NOT_FOUND))))
    .onErrorResume(e->Mono.just(new ResponseEntity<ResponseCustomer>(
        new ResponseCustomer(HttpStatus.SC_INTERNAL_SERVER_ERROR, null, e.getMessage()),
        null, HttpStatus.SC_INTERNAL_SERVER_ERROR)));
  
  }
 
  /**.*/
  @GetMapping("/findCustomerByDocument/{customerDocument}/{documentTypeId}")
  public Mono<ResponseEntity<ResponseCustomer>> findCustomerByDocument(@PathVariable("customerDocument") String customerDocument, @PathVariable("documentTypeId") String documentTypeId) {
    return findCustomerByDocumentUseCase.findCustomerByDocument(customerDocument, documentTypeId)
        .flatMap(findCustomer -> 
              Mono.just(new ResponseEntity<ResponseCustomer>(
              new ResponseCustomer(HttpStatus.SC_CREATED, findCustomer, "Customer found"),
              null, HttpStatus.SC_CREATED))
            ).onErrorResume(e->Mono.just(new ResponseEntity<ResponseCustomer>(
                  new ResponseCustomer(HttpStatus.SC_INTERNAL_SERVER_ERROR, null, e.getMessage()),
                  null, HttpStatus.SC_INTERNAL_SERVER_ERROR)));

  }

}
