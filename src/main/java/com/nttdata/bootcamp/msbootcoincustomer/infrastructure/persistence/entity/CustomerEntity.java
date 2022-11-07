package com.nttdata.bootcamp.msbootcoincustomer.infrastructure.persistence.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nttdata.bootcamp.msbootcoincustomer.domain.enums.States;
import com.nttdata.bootcamp.msbootcoincustomer.domain.model.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**.*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document (collection = "Customer")
public class CustomerEntity {

  @Id
  private String customerId;
  private String customerDocument;
  private String documentTypeId;
  private String customerName;
  private String customerLastName;
  private String customerState;
  private String customerCreateDate;


  /**.*/
  public static Customer toCustomer(CustomerEntity customerEntity) {
	Customer customer = new Customer();
    BeanUtils.copyProperties(customerEntity, customer);
    return customer;
  }
  
  /**.*/
  public static CustomerEntity toCustomerEntity(Customer customer) {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
	LocalDateTime now = LocalDateTime.now();
	String customerCreateDate = now.format(formatter);
    CustomerEntity customerEntity = new CustomerEntity();
    BeanUtils.copyProperties(customer, customerEntity);
    customerEntity.setCustomerState(States.ACTIVE.getValue());
    customerEntity.setCustomerCreateDate(customerCreateDate);
    return customerEntity;
  }
}
