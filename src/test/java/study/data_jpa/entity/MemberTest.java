package study.data_jpa.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberTest {
    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void EntityTest () {

    }

    @Test
    void changeTeam() {
        Team beforeTeam = new Team("before Team");
        Team afterTeam = new Team("after Team");
        Member member1 = new Member("member1");
        member1.changeTeam(beforeTeam);

        entityManager.persist(beforeTeam);
        entityManager.persist(afterTeam);
        entityManager.persist(member1);

        entityManager.flush();
        entityManager.clear();

        Member findMember = entityManager.find(Member.class, member1.getId());
        Team findAfterTeam = entityManager.find(Team.class, afterTeam.getId());

        findMember.changeTeam(findAfterTeam);

        Assertions.assertEquals(findMember.getTeam().getId(), findAfterTeam.getId());
    }
}