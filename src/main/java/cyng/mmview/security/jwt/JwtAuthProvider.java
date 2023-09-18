package cyng.mmview.security.jwt;

import cyng.mmview.security.auth.MembersDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;


@AllArgsConstructor
public class JwtAuthProvider implements AuthenticationProvider {
    private final MembersDetailsService membersDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("??????????????????????????JwtAuthProvider.authenticate??????????????????????????");
        String accntId = authentication.getName();
        System.out.println("accntId = " + accntId);
        String accntPw = (String) authentication.getCredentials();
        System.out.println("accntPw = " + accntPw);

        UserDetails userDetails = membersDetailsService.loadUserByUsername(accntId);

        //pw 검사
        if (!passwordEncoder.matches(accntPw, userDetails.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
