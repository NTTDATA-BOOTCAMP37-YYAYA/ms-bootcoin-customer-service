package com.nttdata.bootcamp.msbootcoincustomer.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.msbootcoincustomer.application.incoming.CreateCustomerUseCase;
import com.nttdata.bootcamp.msbootcoincustomer.application.incoming.FindCustomerByDocumentUseCase;
import com.nttdata.bootcamp.msbootcoincustomer.application.outgoing.CreateCustomerPort;
import com.nttdata.bootcamp.msbootcoincustomer.application.outgoing.FindCustomerByDocumentPort;
import com.nttdata.bootcamp.msbootcoincustomer.application.outgoing.SendWalletPort;
import com.nttdata.bootcamp.msbootcoincustomer.domain.model.Wallet;
import com.nttdata.bootcamp.msbootcoincustomer.domain.model.Customer;

import reactor.core.publisher.Mono;

/**.*/
@Service
public class CustomerService implements CreateCustomerUseCase,
                                       FindCustomerByDocumentUseCase{

final  Logger logger = LoggerFactory.getLogger(CustomerService.class);
  
@Autowired
private SendWalletPort sendWalletPort;


@Autowired
private CreateCustomerPort createCustomerPort;

@Autowired
private FindCustomerByDocumentPort findCustomerByDocumentPort;


@Override
public Mono<Customer> findCustomerByDocument(String customerDocument, String documentTypeId) {
	return findCustomerByDocumentPort.findCustomerByDocument(customerDocument, documentTypeId);
}

@Override
public Mono<Customer> createCustomer(Customer customer) {
	return createCustomerPort.createCustomer(customer)
		        .flatMap(newCustomer -> {
		          Wallet wallet = new Wallet();
		          wallet.setCustomerId(newCustomer.getCustomerId());
		          wallet.setCustomerCellPhone(customer.getCustomerCelPhone());
		          wallet.setCustomerEmail(customer.getCustomerEmail());
		          this.sendWallet(wallet);
		          customer.setCustomerId(newCustomer.getCustomerId());
		          return Mono.just(customer);
		        });
}

/**.*/
public Wallet sendWallet(Wallet wallet) {
  if (wallet != null) {
    logger.info("Send  Wallet {} ", wallet);
    sendWalletPort.sendWallet(wallet);
  }
  return wallet;
}


}
