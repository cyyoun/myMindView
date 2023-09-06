package cyng.mmview.repository;

import cyng.mmview.domain.Members;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

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

    public String findId(String phone) {
        return em.createQuery("select m from Members m where m.phone = :phone", Members.class)
                .setParameter("phone", phone).getSingleResult().getAccntId();
    }

    public void delete(Members members) {
        em.remove(members);
    }
}
