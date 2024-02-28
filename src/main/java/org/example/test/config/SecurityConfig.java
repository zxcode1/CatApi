package org.example.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                            .anyRequest().permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable
                );

        return http.build();
    }

//    @Bean
//    public CatUserDetailsService catUserDetailsService() {
//        return new CatUserDetailsService();
//    }

//    @Bean
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(catUserDetailsService());
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize -> authorize
////                        .requestMatchers("/admin/**").hasRole("ADMIN")
////                        .requestMatchers("/user/**").hasRole("USER")
////                        .requestMatchers("/blog/**","/dashboard","/register").permitAll()
////                        .anyRequest().authenticated()
//                                .anyRequest().permitAll()
//                )
////                .formLogin(formLogin -> formLogin
////                        .loginPage("/register")
////                        .permitAll()
////                )
//                .rememberMe(Customizer.withDefaults());
//
//        return http.build();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
