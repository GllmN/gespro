package af.cmr.indyli.gespro.ws.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

import af.cmr.indyli.gespro.ws.config.GesproWsConfig;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Import({ GesproWsConfig.class })
@EnableSwagger2
public class GesproWsApplication {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GesproWsApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(GesproWsApplication.class, args);
	}

}
