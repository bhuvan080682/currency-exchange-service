package com.bhuni.microservices.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bhuni.microservices.currencyexchangeservice.model.CurrencyExchange;
import com.bhuni.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchageController {
	
	@Autowired
	private Environment env;
	
	Logger logger = LogManager.getLogger(CurrencyExchageController.class);
	
	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retriveExchangeValue(@PathVariable String from,@PathVariable String to ) {
		logger.info("calling retriveExchangeValue.......");
		logger.info("calling retriveExchangeValue {}" , from,to);
		CurrencyExchange currencyExchage = currencyExchangeRepository.findByFromAndTo(from, to);
		if(currencyExchage == null) {
			throw new RuntimeException("Unable to find data for " + from + " to " + to);
		}
		currencyExchage.setEnvironment(env.getProperty("local.server.port"));
		logger.info("End of retriveExchangeValue.......");
		return currencyExchage;
	}
	
	
	

}
