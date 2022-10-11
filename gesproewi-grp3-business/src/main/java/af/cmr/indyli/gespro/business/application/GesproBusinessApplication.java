package af.cmr.indyli.gespro.business.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("af.cmr.indyli.gespro.business")
public class GesproBusinessApplication {

	public static void main(String[] args) {
		SpringApplication.run(GesproBusinessApplication.class, args);
	}

}
