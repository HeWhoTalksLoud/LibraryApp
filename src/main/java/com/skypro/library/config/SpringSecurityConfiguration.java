package com.skypro.library.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.withUsername("user1")
                .password(passwordEncoder().encode("bbb"))
                .roles("USER")
                .build();
        UserDetails user2 = User.withUsername("user2")
                .password(passwordEncoder().encode("bbb"))
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("aaa"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1, user2, admin);
    }
    @Bean
//Создаем метод в классе конфигурации -
//который возвращает бин SecurityFilterChain -
//основной бин для конфигурации Spring Security
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Отключение защиты от подделки межсайтовых запросов (CSRF)
        // это необходимо чтобы нормально использовать REST API,
        // не запрашивая CSRF коды
        httpSecurity.csrf().disable()
                // Настройка правил авторизации
                .authorizeRequests()
                // Разрешение доступа к URL-адресу "/web" для всех пользователей
                .antMatchers("/web").permitAll()
                // Запрет доступа к любым другим URL-адресам, если пользователь не прошел аутентификацию
                .anyRequest().authenticated()
                // Настройка аутентификации через базовую HTTP-аутентификацию
                .and().httpBasic();

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
