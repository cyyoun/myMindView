package cyng.mmview.security.config;

import cyng.mmview.repository.MembersRepository;
import cyng.mmview.security.auth.MembersDetailsService;
import cyng.mmview.security.jwt.JwtAuthenticationFilter;
import cyng.mmview.security.jwt.JwtAuthorizationFilter;
import cyng.mmview.security.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtUtils jwtUtils;
    private final CorsConfig corsConfig;
    private final MembersRepository membersRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                // 쿠키 기반이 아닌 JWT 기반이므로 사용하지 않음
                .csrf().disable()
                //Token 방식이라 세션 필요없음
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                // ID, Password 문자열을 Base64로 인코딩하여 전달하는 구조
                .httpBasic().disable()
                .apply(new MyCustom())
                .and()
                .authorizeRequests() // 조건별로 요청 허용/제한 설정
                .anyRequest().permitAll()
                .and()
                .build();
    }
    public class MyCustom extends AbstractHttpConfigurer<MyCustom, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
            builder
                    .addFilter(corsConfig.corsFilter())
                    .addFilter(new JwtAuthenticationFilter(authenticationManager, jwtUtils))
                    .addFilter(new JwtAuthorizationFilter(authenticationManager, jwtUtils, membersRepository));

            super.configure(builder);
        }
    }
}

