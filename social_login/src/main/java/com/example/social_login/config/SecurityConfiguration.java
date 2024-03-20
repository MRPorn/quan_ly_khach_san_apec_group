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
                        .requestMatchers("/api/auth/quan-ly-khach-san/**").permitAll()


                        // API dat-phong

                        .requestMatchers(GET,"/api/quan-ly-khach-san/dat-phong/danh-sach-dat-phong").hasAnyAuthority(ADMIN.name(), USER.name())
                        .requestMatchers(PUT,"/api/quan-ly-khach-san/dat-phong/cap-nhat-trang-thai").hasAuthority(ADMIN.name())
                        .requestMatchers(POST,"/api/quan-ly-khach-san/dat-phong/them-moi").hasAuthority(ADMIN.name())

                        // APT phong

                        .requestMatchers(GET,"/api/quan-ly-khach-san/phong/danh-sach-phong").hasAnyAuthority(ADMIN.name(), USER.name())
                        .requestMatchers(PUT,"/api/quan-ly-khach-san/phong/cap-nhat-phong").hasAuthority(ADMIN.name())
                        .requestMatchers(POST,"/api/quan-ly-khach-san/phong/them-phong-moi").hasAuthority(ADMIN.name())

                        // APT loai-phong

                        .requestMatchers(GET,"/api/quan-ly-khach-san/loai-phong/danh-sach-loai-phong").hasAnyAuthority(ADMIN.name(), USER.name())
                        .requestMatchers(PUT,"/api/quan-ly-khach-san/loai-phong/cap-nhat-loai-phong").hasAuthority(ADMIN.name())
                        .requestMatchers(POST,"/api/quan-ly-khach-san/loai-phong/them-loai-phong-moi").hasAuthority(ADMIN.name())





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
