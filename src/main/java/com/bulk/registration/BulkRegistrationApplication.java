package com.bulk.registration;

import com.bulk.registration.model.Sim;
import com.bulk.registration.util.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BulkRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BulkRegistrationApplication.class, args);
	}
}
