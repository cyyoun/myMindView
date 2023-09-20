package cyng.mmview.service;

import cyng.mmview.domain.Members;
import cyng.mmview.repository.MembersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class MembersService {

    private final MembersRepository membersRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void join(Members members) {
        String encode = passwordEncoder.encode(members.getAccntPw());
        members.setAccntPw(encode);
        members.setRoles("user");
        membersRepository.save(members);
    }

    public Members chkMembers(Members members) {
        Members mem = membersRepository.findMemberById(members.getAccntId()).orElse(null);

        Members chkMembers = membersRepository.findMemberById(members.getAccntId())
                .filter(m -> passwordEncoder.matches(members.getAccntPw(), m.getAccntPw()))
                .orElse(null);

        return chkMembers;
    }
    public Members getMembers(String accntId) {
        return membersRepository.findMemberById(accntId).orElse(null);
    }
}

