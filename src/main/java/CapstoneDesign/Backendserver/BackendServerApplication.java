package CapstoneDesign.Backendserver;

import CapstoneDesign.Backendserver.configuration.ServerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(ServerConfiguration.class)
@SpringBootApplication
public class BackendServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendServerApplication.class, args);
	}

}
