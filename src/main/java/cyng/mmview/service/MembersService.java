package cyng.mmview.service;

import cyng.mmview.domain.Members;
import cyng.mmview.repository.MembersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class MembersService {

    private final MembersRepository membersRepository;

    public void join(Members members) {
        membersRepository.save(members);
    }

    public Members chkAccount(String accntId, String accntPw) {
        return membersRepository.findMemberById(accntId)
                .filter(m -> m.getAccntPw().equals(accntPw))
                .orElse(null);
    }
}
