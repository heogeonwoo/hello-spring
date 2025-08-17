package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

// test 순서는 보장이 안되기 때문에 모든 test는 따로 동작하게 작성해야 함
public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        // Assertions: 특정 조건이 참인지 검사하는 메서드
        Member result = repository.findById(member.getId()).get();
        // System.out.println("result = " + (result == member));
        // Assertions.assertEquals(member, result); // 간단하고 직관적, 기본 테스트 라이브러리이므로 별도 의존성 없이 사용 가능, 메시지 커스터마이징이 불편, 기능이 단순 (복잡한 검증이나 체이닝 불가)
        assertThat(member).isEqualTo(result); // 풍부한 검증 메서드 지원 (contains, hasSize, matches 등), 코드 읽기가 자연스러워서 테스트 문서 역할도 가능, 커스텀 메시지, 조건별 다양한 검증 가능, 별도의 라이브러리 의존성 필요 (assertj-core)
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
