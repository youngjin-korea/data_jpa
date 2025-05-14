package study.data_jpa.repository;

import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import study.data_jpa.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    /*
        벌크성 수정 쿼리 : jpa 엔티티를 각각 변경하지 않고 쿼리로 한번에 수정을 하는 작업을 이름.
        executeUpdate()를 호출하게 됨, 없으면 getSingleResult 또는 getResultList를 호출함,
        쿼리를 직접 날리면 영속성컨텍스트의 캐시에 반영이 안되므로 쿼리를 날린 후 캐시를 비워준다.
        따라서 다음에 데이터를 조회하면 업데이트 내용이 반영된 데이터가 조회된다.
    */
    @Modifying(clearAutomatically = true) // 메서드 실행 후 clear()로 캐시를 리셋
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    /*
     *   fetch join 이라고 하고 지연로딩 문제를(N + 1) 해결함. -> 한방에 조인으로 조회
     * */
    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberFetchJoin();

    // EntityGrapth: N + 1 해결 fetch join을 다른 방식으로 해결
    @EntityGraph(attributePaths = {"team"})
    @Override
    List<Member> findAll();

    // 조회된 Member에서 더티 체크를 위한 원본을 만들지 않음
    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Member findReadOnlyByUsername(String username);
}
