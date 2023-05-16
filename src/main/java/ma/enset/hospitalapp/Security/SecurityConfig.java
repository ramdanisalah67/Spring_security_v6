package ma.enset.hospitalapp.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	@Bean
	public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}

    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		return new InMemoryUserDetailsManager(
				User.withUsername("salah").password(passwordEncoder.encode("123")).roles("ADMIN","USER").build(),
				User.withUsername("ahmed").password(passwordEncoder.encode("111")).roles("USER").build(),
				User.withUsername("ayoub").password(passwordEncoder.encode("000")).roles("USER").build()
				);
	}

    @Bean
    SecurityFilterChain securityFilterChaine(HttpSecurity httpSecurity) throws Exception {
       // httpSecurity.formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll();
    	 httpSecurity.formLogin();
    	httpSecurity.rememberMe();
        httpSecurity.authorizeHttpRequests().requestMatchers("/webjars/**","h2-console/**").permitAll();
    //    httpSecurity.authorizeHttpRequests().requestMatchers("user/**").hasRole("USER");
      //  httpSecurity.authorizeHttpRequests().requestMatchers("admin/**").hasRole("ADMIN");
		httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
		httpSecurity.exceptionHandling().accessDeniedPage("/api/v1/error_page");
		return httpSecurity.build();
	}
    
    
}
