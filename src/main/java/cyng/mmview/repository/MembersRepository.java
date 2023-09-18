package cyng.mmview.repository;

import cyng.mmview.domain.Members;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MembersRepository {

    private final EntityManager em;

    public Members findMember(Long id) {
        return em.find(Members.class, id);
    }

    public void save(Members members) {
        em.persist(members);
    }

    public String findIdByPhone(String phone) {
        return em.createQuery("select m from Members m where m.phone = :phone", Members.class)
                .setParameter("phone", phone).getSingleResult().getAccntId();
    }

    public Optional<Members> findMemberById(String accntId) {
     return em.createQuery("select m from Members m where accntId = :accntId", Members.class)
                .setParameter("accntId", accntId)
                .getResultList().stream().findAny();
    }

    public void delete(Members members) {
        em.remove(members);
    }

    public List<Members> allMembers() {
        return em.createQuery("select m from Members m", Members.class)
                .getResultList();
    }
}
