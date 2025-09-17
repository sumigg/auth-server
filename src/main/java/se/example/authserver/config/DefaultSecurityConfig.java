package se.example.authserver.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class DefaultSecurityConfig {

  private static final Logger LOG = LoggerFactory.getLogger(DefaultSecurityConfig.class);

  // formatter:off
  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests(authorizeRequests -> authorizeRequests
        .requestMatchers("/actuator/**").permitAll()
        .anyRequest().authenticated()
      )
      .formLogin(withDefaults());
    return http.build();
  }
  // formatter:on

  // @formatter:off
  @Bean
  UserDetailsService users() {
    UserDetails user = User.withDefaultPasswordEncoder()
      .username("u")
      .password("p")
      .roles("USER")
      .build();
    return new InMemoryUserDetailsManager(user);
  }


}