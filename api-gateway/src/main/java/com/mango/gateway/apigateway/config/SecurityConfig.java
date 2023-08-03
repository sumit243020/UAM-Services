package com.mango.gateway.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;


@AllArgsConstructor
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

	private AuthenticationManager authenticationManager;
	private SecurityContextRepository securityContextRepository;
	

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

		System.out.println("security");
		http.exceptionHandling()
				.authenticationEntryPoint(
						(swe, e) -> Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED)))
							
				.accessDeniedHandler(
						(swe, e) -> Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN)))
				.and().csrf().disable().formLogin().disable().httpBasic().disable()
				.authenticationManager(authenticationManager).securityContextRepository(securityContextRepository)
				.authorizeExchange().pathMatchers("/auth-service/token/**").permitAll();

			http.authorizeExchange().pathMatchers("/uam-service/reset/validateUser").hasAnyAuthority("GET_ALL");
			http.authorizeExchange().pathMatchers("/uam-service/user/getbyid/{userId}").hasAnyAuthority("GET_BY_ID");
			http.authorizeExchange().pathMatchers("/uam-service/user/create").hasAnyAuthority("CREATE");
			http.authorizeExchange().pathMatchers("/uam-service/user/update").hasAnyAuthority("UPDATE");
			http.authorizeExchange().pathMatchers("/uam-service/delete/{userId}").hasAnyAuthority("DELETE");
			
//			http.authorizeExchange().pathMatchers("/").hasAnyAuthority("GET_ALL");
//			http.authorizeExchange().pathMatchers("/").hasAnyAuthority("GET_ALL");
//			http.authorizeExchange().pathMatchers("/").hasAnyAuthority("GET_ALL");
//			http.authorizeExchange().pathMatchers("/").hasAnyAuthority("GET_ALL");

		

		http.authorizeExchange().anyExchange().denyAll();
				

		return http.build();
	}

	
	
	
	
	
	
	
//	
//	 public  Map<String, Object> config;
//
//	   public   String filePath = "C:/CRM_Avisys_API_Gateway/api-gateway/api-gateway/src/main/resources/config.properties";
//
//	   
//
//	    private void initConfigs() {
//	        Properties properties = new Properties();
//	        try {
//	            properties.load(Files.newInputStream(Paths.get(filePath)));
//	        } catch (IOException e) {
////	            LOG.error("Error loading configuration:", e);
//	        	e.printStackTrace();
//	        }
//	        config = new HashMap<>();
//	        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
//	            config.put(String.valueOf(entry.getKey()), entry.getValue());
//	        }
//	    }
//
//	    public Object getConfig(String key) {
//	        return config.get(key);
//	    }
//	    
//	    
//	    public void reinitializeConfig() {
//	        initConfigs();
//	    }
//	
//	

//	@Bean
//	public JwtAuthFilter authenticationTokenFilterBean() throws Exception {
//		return new JwtAuthFilter();
//	}
}
