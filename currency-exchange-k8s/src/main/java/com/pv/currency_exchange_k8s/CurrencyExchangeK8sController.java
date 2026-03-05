package com.pv.currency_exchange_k8s;

import java.math.BigDecimal;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeK8sController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CurrencyExchangeK8sRepository repository;
	@Autowired
	private Environment environment;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue( @PathVariable String from, @PathVariable String to)

	{
		logger.info("retriveExchangeValue called with {} to {}", from,to);
		//CurrencyExchange currencyExchange = new CurrencyExchange(100L, from, to,BigDecimal.valueOf(50));
		CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
		if(currencyExchange == null)
		{
			throw new RuntimeException("Unable tp find");
		}
		String port =environment.getProperty("local.server.port");
		
		String host = environment.getProperty("HOSTNAME");
		String version = "v12";
		currencyExchange.setEnvironment(port+" "+host+" "+version);
		return currencyExchange;
	}

}
