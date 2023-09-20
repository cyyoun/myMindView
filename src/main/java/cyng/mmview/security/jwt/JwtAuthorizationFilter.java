package cyng.mmview.security.jwt;

import cyng.mmview.domain.Members;
import cyng.mmview.repository.MembersRepository;
import cyng.mmview.security.auth.MembersDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//BasicAuthenticationFilter : 권한이나 인증이 필요한 특정 주소를 요청했을 때 거치고, 그 외에는 거치지 않는 시큐리티 필터 중 하나
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final MembersRepository membersRepository;
    private final JwtUtils jwtUtils;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils, MembersRepository membersRepository) {
        super(authenticationManager);
        this.membersRepository = membersRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("====================JwtAuthorizationFilter.doFilterInternal====================");

        String serlvetPath = request.getServletPath();
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        System.out.println("serlvetPath = " + serlvetPath);
        System.out.println("header = " + header);


        if (header == null || !header.startsWith("Bearer ")) {
            System.out.println("jwt 이 존재하지 않습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        /**
         * jwt 토큰을 검증해서 권한이 맞는지 확인
         */
        String token = header.replace("Bearer ", "");

        String accntId =jwtUtils.getAccount(token);

        if (accntId != null) {
            Members members = membersRepository.findMemberById(accntId).orElse(null);

            if (members != null) {
                MembersDetails membersDetails = new MembersDetails(members);

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        membersDetails.getUsername(), "", membersDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}


