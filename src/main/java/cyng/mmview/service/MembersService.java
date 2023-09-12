package cyng.mmview.service;

import cyng.mmview.domain.JoinMembersForm;
import cyng.mmview.domain.Members;
import cyng.mmview.repository.MembersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class MembersService {

    private final MembersRepository membersRepository;

    public Members createMember(JoinMembersForm form) {
        return new Members(
                form.getAccntId(),
                form.getAccntPw(),
                form.getName(),
                form.getPhone(),
                form.getBirth(),
                form.getGender());
    }

    public void join(Members members) {
        membersRepository.save(members);
    }

    public Members chkAccount(String accntId, String accntPw) {
        return membersRepository.findMemberById(accntId)
                .filter(m -> m.getAccntPw().equals(accntPw))
                .orElse(null);
    }

    public Members getMembers(String accntId) {
        return membersRepository.allMembers().stream()
                .filter(m -> m.getAccntId().equals(accntId))
                .findAny()
                .orElse(null);
    }
}

