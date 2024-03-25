package com.example.social_login.config;

import com.example.social_login.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.social_login.constant.Role.ADMIN;
import static com.example.social_login.constant.Role.USER;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/hotel-management/**").permitAll()


                        // API dat-phong

                        .requestMatchers(GET,"/api/hotel-management/reservation/reservation-page").hasAnyAuthority(ADMIN.name(), USER.name())
                        .requestMatchers(PUT,"/api/hotel-management/reservation/update-reservation").hasAuthority(ADMIN.name())
                        .requestMatchers(POST,"/api/hotel-management/reservation/add-reservation").hasAuthority(ADMIN.name())

                        // APT phong

                        .requestMatchers(GET,"/api/hotel-management/room/room-page").hasAnyAuthority(ADMIN.name(), USER.name())
                        .requestMatchers(PUT,"/api/hotel-management/room/update-room").hasAuthority(ADMIN.name())
                        .requestMatchers(POST,"/api/hotel-management/room/add-room").hasAuthority(ADMIN.name())

                        // APT loai-phong

                        .requestMatchers(GET,"/api/hotel-management/room-type/room-type-page").hasAnyAuthority(ADMIN.name(), USER.name())
                        .requestMatchers(PUT,"/api/hotel-management/room-type/update-room-type").hasAuthority(ADMIN.name())
                        .requestMatchers(POST,"/api/hotel-management/room-type/add-room-type").hasAuthority(ADMIN.name())





                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//                .oauth2Login(withDefaults())
//                .formLogin(withDefaults())
//                .logout((logout) -> logout.logoutUrl("/api/quan-ly-khach-san/logout")
//                        .logoutSuccessUrl("/api/quan-ly-khach-san/dat-phong/danh-sach-dat-phong")
//                        .invalidateHttpSession(true)
//                        .clearAuthentication(true)
//                        .deleteCookies("JSESSIONID", "your-oauth2-cookie-name")
//                        .permitAll())
                ;
        return http.build();
    }
}
