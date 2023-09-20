package cyng.mmview.security.auth;

import cyng.mmview.domain.Members;
import cyng.mmview.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembersDetailsService implements UserDetailsService {
    //Spring Security의 UserDetailsService는 UserDetails 정보를 토대로 유저 정보를 불러올 때 사용된다.
    //Jpa를 이용하여 DB에서 유저 정보를 조회할 것이므로 이에 맞춰서 구현해주면 된다.

    private final MembersRepository membersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Members members = membersRepository.findMemberById(username).orElse(null);
        return new MembersDetails(members);
    }
}
