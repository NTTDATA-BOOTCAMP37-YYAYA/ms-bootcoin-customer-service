package com.nttdata.bootcamp.msbootcoincustomer.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**.*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
  

  private String customerId;
  private String customerCellPhone;
  private String customerEmail;

}
