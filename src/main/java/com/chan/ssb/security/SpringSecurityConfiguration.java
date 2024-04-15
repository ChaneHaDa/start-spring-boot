package com.chan.ssb.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(requests -> requests.anyRequest().permitAll())
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults());

//        http.authorizeHttpRequests(requests -> requests.anyRequest().denyAll())
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults());

        http.cors().configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(List.of("http://localhost:3000"));
                config.setAllowedMethods(List.of("GET", "POST"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(List.of("*"));
                config.setExposedHeaders(List.of("Authorization"));
                config.setMaxAge(1800L);
                return config;
            }
        }).and().csrf().disable();

        http.authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/players", "/h2-console/**").authenticated()
                        .requestMatchers("/api-docs","/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**", "/user", "/api/v1/team").permitAll()
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        http.headers().frameOptions().sameOrigin();

        http.csrf().disable();
        return http.build();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails admin = User.withUsername("admin")
//                .username("admin")
//                .password("12345")
//                .authorities("admin")
//                .build();
//        UserDetails user = User.withUsername("user")
//                .password("12345")
//                .authorities("read")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
//        return NoOpPasswordEncoder.getInstance();
//        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_5();
//        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
//        return SCryptPasswordEncoder.defaultsForSpringSecurity_v4_1();
//        return SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8();
//        return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_2();
//        return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
//        return new StandardPasswordEncoder();

    }

}
