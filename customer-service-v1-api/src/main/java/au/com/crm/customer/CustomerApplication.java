package au.com.crm.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableJpaRepositories
public class CustomerApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
		LOGGER.debug("Spring Boot Application Intialized");
	}
}
