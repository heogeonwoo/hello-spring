package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 스프링 데이터 JPA : 구현 클래스를 작성하지 않고, interface 만으로 DB 접근 가능
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // 객체(비지니스)가 다양하여 공통화하기 어려운 메서드의 경우, 메서드 이름 만으로 조회 가능
    @Override
    Optional<Member> findByName(String name); // select m from Member m where m.name = ?
}
