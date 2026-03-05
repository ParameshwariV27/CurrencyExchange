package com.pv.currency_exchange_k8s;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeK8sRepository extends JpaRepository<CurrencyExchange , Long>{
	
	CurrencyExchange findByFromAndTo(String from, String to);

}
