package com.nttdata.bootcamp.msbootcoincustomer.infrastructure.web.rest.adapter.controller.response;

import com.nttdata.bootcamp.msbootcoincustomer.domain.model.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**.*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCustomer {
  
  private int httpStatus;
  private Customer customer;
  private String message;

}