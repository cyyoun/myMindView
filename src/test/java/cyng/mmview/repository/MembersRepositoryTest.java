package cyng.mmview.repository;

import cyng.mmview.domain.Gender;
import cyng.mmview.domain.Members;
import cyng.mmview.service.MembersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MembersRepositoryTest {

    @Autowired
    MembersService membersService;
    @Autowired
    MembersRepository membersRepository;
    @Autowired
    EntityManager em;


    @Test
    @Rollback(value = false)
    public void newMemberSave() {
        //given
        Members members = new Members("cyyoun123", "1234", "cyy", "010-1234-1234", "1234-12-08", Gender.WOMAN);

        //when
        membersService.join(members);

        //then
        Members getMembers = membersRepository.findMember(members.getId());
        System.out.println("members = " + members);
        assertThat(getMembers).isEqualTo(members);
    }

    @Test
    @Rollback(value = false)
    public void findId() {
        //given
        Members members = new Members("cyyoun", "1234", "cyy", "010-1234-1234", "1234-12-08", Gender.WOMAN);
        membersService.join(members);

        //when
        String phoneNum = "010-1234-1234";
        String findMemberId = membersRepository.findId(phoneNum);

        //then
        assertThat(findMemberId).isEqualTo(members.getAccntId());
        System.out.println("id = " + findMemberId);
    }

    @Test
    @Rollback(value = false)
    public void deleteMember() {
        //given
        Members members = new Members("cyyoun", "1234", "cyy", "010-1234-1234", "1234-12-08", Gender.WOMAN);
        membersService.join(members);
        membersRepository.save(members);

        //when
        long id = 1l;

        //then
        Members member = membersRepository.findMember(id);
        assertThat(member).isEqualTo(members);

    }
}
