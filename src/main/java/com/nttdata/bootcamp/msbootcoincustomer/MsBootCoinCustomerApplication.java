package com.nttdata.bootcamp.msbootcoincustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**.*/
@SpringBootApplication
@EnableEurekaClient
public class MsBootCoinCustomerApplication {

  public static void main(String[] args) {
    SpringApplication.run(MsBootCoinCustomerApplication.class, args);
  }

}
