package cyng.mmview.service;

import cyng.mmview.domain.Members;
import cyng.mmview.repository.MembersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class MembersService {

    private final MembersRepository membersRepository;
    private final PasswordEncoder passwordEncoder;

    public void join(Members members) {
        members.setAccntPw(passwordEncoder.encode(members.getAccntPw()));
        membersRepository.save(members);
    }
    public Members chkMembers(Members members) {
        Members chkMembers = membersRepository.findMemberById(members.getAccntId())
                .filter(m -> !passwordEncoder.matches(members.getAccntPw(), m.getAccntPw()))
                .orElse(null);

        return chkMembers;
    }

    public Members getMembers(String accntId) {
        return membersRepository.allMembers().stream()
                .filter(m -> m.getAccntId().equals(accntId))
                .findAny()
                .orElse(null);
    }
}

