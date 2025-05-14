package study.data_jpa.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import study.data_jpa.entity.Member;

import java.util.List;

/*
    지켜줄 규칙 1. spring data jpa repository의 인터페이스 이름에 Impl을 뒤에 붙힌 이름을 사용자 정의 리포지토리의 구현체 이름으로 쓸것.
                사용자 정의 리포지토리의 인터페이스의 이름은 상관없음.

    사용자 정의 리포지토리: Mybatis 나 다른 DAO 기술을 함께 쓸때 interface로 하나 만들고 그것의 구현체를 만들고,
    spring data jpa 레파지토리 인터페이스에 사용자 정의 인터페이스를 상속 시켜주면 됨.
 */

@RequiredArgsConstructor // 생성자가 하나면 spring bean 컨테이너가 의존성 주입해줌
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final EntityManager em;

    @Override
    public List<Member> findMemberCustom() {
        return em.createQuery("select m from Member m")
                .getResultList();
    }
}
