package br.com.treinamento.aulaspring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebMvc
public class CorsConfig extends WebMvcConfigurerAdapter {
	
	public void addCorsMappings(CorsRegistry registry) {
		registry
		.addMapping("/**") /*aqui todos podem colocar informações*/
		.allowedOrigins("*")
		.allowedMethods("GET","POST","PUT","DELETE"); /*aqui esta chamando os metodos*/
		
		
	}
	
	

}
