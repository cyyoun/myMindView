package cyng.mmview.security.auth;

import cyng.mmview.domain.Members;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


//Spring Security는 유저 인증과정에서 UserDetails를 참조하여 인증을 진행한다.
public class MembersDetails implements UserDetails {
    private final Members members;

    public MembersDetails(Members members) {
        this.members = members;
    }
    public final Members getMembers() {
        return members;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    } //(+-) 나는 Role 기능은 없음

    @Override
    public String getPassword() {
        return members.getAccntPw();
    }

    @Override
    public String getUsername() {
        return members.getAccntId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

