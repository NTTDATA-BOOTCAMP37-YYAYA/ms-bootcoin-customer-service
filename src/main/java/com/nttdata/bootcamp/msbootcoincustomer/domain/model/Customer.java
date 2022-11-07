package com.nttdata.bootcamp.msbootcoincustomer.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**.*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	private String customerId;
	private String customerDocument;
	private String documentTypeId;
	private String customerName;
	private String customerLastName;
	private String customerCelPhone;
	private String customerEmail;
}
