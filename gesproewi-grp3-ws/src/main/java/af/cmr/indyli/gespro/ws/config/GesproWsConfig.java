package af.cmr.indyli.gespro.ws.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import af.cmr.indyli.gespro.business.config.GesproBusinessConfig;

@Import({ GesproBusinessConfig.class, WebSecurityConfig.class })

@ComponentScan(basePackages = { "af.cmr.indyli.gespro.ws.controller" })
public class GesproWsConfig {

}
