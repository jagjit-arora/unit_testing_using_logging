package com.example.springretry;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {
	Logger l=LogManager.getLogger(this.getClass());

	@Autowired
	BackendAdapterImpl backendAdapter;

	@GetMapping("/retry")
	@ExceptionHandler({ Exception.class })
	public String validateSPringRetryCapability(@RequestParam(required = false) boolean simulateretry,
			@RequestParam(required = false) boolean simulateretryfallback) throws RemoteServiceNotAvailableException {
		l.info("asasdssasadad");
		System.out.println("===============================");
		System.out.println("Inside RestController mathod..");
		return backendAdapter.getBackendResponse(simulateretry, simulateretryfallback);
	}
}
