package cyng.mmview.service;

import cyng.mmview.domain.Members;
import cyng.mmview.repository.MembersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MembersService {

    private final MembersRepository membersRepository;

    @Transactional
    public void join(Members member) {
        membersRepository.save(member);
    }

    @Transactional
    public Members chkAccount(String accntId, String accntPw) {
        return membersRepository.findMemberById(accntId)
                .filter(m -> m.getAccntPw().equals(accntPw))
                .orElse(null);
    }
}
