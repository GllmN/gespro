package af.cmr.indyli.gespro.business.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableJpaAuditing
@EntityScan("af.cmr.indyli.gespro.business.entity")
@EnableJpaRepositories("af.cmr.indyli.gespro.business.dao")
@ComponentScan(basePackages = { "af.cmr.indyli.gespro.business.service" })

public class GesproBusinessConfig {
	@Bean(value = "gespro-modelmapper")
	@Scope(value = "singleton")
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}

	@Bean(value = "gespro-passwordEncoder")
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
