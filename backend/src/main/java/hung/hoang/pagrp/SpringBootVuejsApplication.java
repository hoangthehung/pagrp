package hung.hoang.pagrp;

import hung.hoang.pagrp.util.CorsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootVuejsApplication {

	public static void main(String[] args) {
		new SpringApplication(SpringBootVuejsApplication.class, CorsConfig.class).run(args);
	}
}
