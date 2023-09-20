package cyng.mmview.security.jwt;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("----------------------------진입 JwtAuthenticationFilter.attemptAuthentication----------------------------");
        String accntId = request.getParameter("accntId");
        String accntPw = request.getParameter("accntPw");


        if (accntId != null && accntPw != null) {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(accntId, accntPw);
            System.out.println(" 토큰생성완료 authenticationToken = " + authenticationToken);

            return authenticationToken;
        }
        return null;
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("----------------------------진입 JwtAuthenticationFilter.successfulAuthentication----------------------------");

        String accntId = String.valueOf(authResult.getPrincipal());
        String token = jwtUtils.createToken(accntId);

        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        chain.doFilter(request, response);
    }

}
