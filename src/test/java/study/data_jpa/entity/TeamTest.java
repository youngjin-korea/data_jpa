package study.data_jpa.entity;

import jakarta.persistence.EntityManager;
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
class TeamTest {

    @Autowired
    private EntityManager em;

    @Test
    void teamTest() throws Exception {
        Team team = new Team("team1");
        Member member = new Member("username1");

        em.persist(team);
        Team team1 = em.find(Team.class, team.getId());

        Assertions.assertEquals(team.getId(), team1.getId());

        member.changeTeam(team);

        em.persist(member);

        Member member1 = em.find(Member.class, member.getId());

        Assertions.assertEquals(member1.getTeam().getId(), team.getId());
    }
}
