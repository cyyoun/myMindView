package cyng.mmview.service;

import cyng.mmview.domain.Members;
import cyng.mmview.repository.MembersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class MembersService {

    private final MembersRepository membersRepository;

    public void join(Members members) {
        membersRepository.save(members);
    }

    public Members chkMembers(String accntId, String accntPw) {
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

