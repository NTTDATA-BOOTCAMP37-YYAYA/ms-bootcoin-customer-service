package com.nttdata.bootcamp.msbootcoincustomer.infrastructure.producer.adapter.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.nttdata.bootcamp.msbootcoincustomer.application.outgoing.SendWalletPort;
import com.nttdata.bootcamp.msbootcoincustomer.domain.model.Wallet;

import lombok.RequiredArgsConstructor;

/**.*/
@Component
@RequiredArgsConstructor
public class WallettProducerAdapter implements SendWalletPort {
  
  final  Logger logger = LoggerFactory.getLogger(WallettProducerAdapter.class);
  
  @Value("${kafka.topic.bootcoin.wallet.create.name}")
  private String topic;

  private  final KafkaTemplate<String, Wallet> kafkaTemplate;
  
  @Override
  public void sendWallet(Wallet wallet) {
    
    ListenableFuture<SendResult<String, Wallet>> future = kafkaTemplate.send(this.topic, wallet);
    
    future.addCallback(new ListenableFutureCallback<SendResult<String, Wallet>>() {

      @Override
      public void onSuccess(SendResult<String, Wallet> result) {
        logger.info("Message {} has been sent", result);
      }

      @Override
      public void onFailure(Throwable ex) {
        logger.error("Something went wrong with the wallet");
        
      }

    });
  }

}
