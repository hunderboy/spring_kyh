package co.kr.steadysprinting.hellospring.repository;

import co.kr.steadysprinting.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() { // 전체 Test 는 순서를 보장하지 않는다. 그래서 테스트 1개씩 종료할때마다 clear를 해주는 코드를 작성한다.
        repository.clearStore();
    }

    @Test   // @Test 을 적어줌으로써 테스트 함수 save 를 실행할 수 있게끔 해준다.
    public void save() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        //when
        repository.save(member1);
        //then
        Member result = repository.findById(member1.getId()).get();
        assertThat(member1).isEqualTo(result);
    }

    @Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        Member result = repository.findByName("spring1").get();
        //then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
    }

}
