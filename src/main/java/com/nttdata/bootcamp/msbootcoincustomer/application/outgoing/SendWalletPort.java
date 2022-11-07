package com.nttdata.bootcamp.msbootcoincustomer.application.outgoing;

import com.nttdata.bootcamp.msbootcoincustomer.domain.model.Wallet;

public interface SendWalletPort {

	void sendWallet(Wallet wallet);
}
