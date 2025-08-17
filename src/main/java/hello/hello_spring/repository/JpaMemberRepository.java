package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

// Jpa : Query를 작성할 필요 없이, DB 접근 가능 (JPQL 사용)
public class JpaMemberRepository implements MemberRepository {

    // Jpa를 사용하려면, EntityManager를 주입 받아야 함
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    // JPA에게 어떤 엔티티를 조회할지 알려주기 위해서 JPQL 사용
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    // JPA에게 어떤 엔티티를 조회할지 알려주기 위해서 JPQL 사용
    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // jpql 작성 필요
                .getResultList();
    }
}
