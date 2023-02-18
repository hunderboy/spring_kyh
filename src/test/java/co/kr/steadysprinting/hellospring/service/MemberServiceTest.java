package co.kr.steadysprinting.hellospring.service;

import co.kr.steadysprinting.hellospring.domain.Member;
import co.kr.steadysprinting.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test 의 핵심은 정상처리확인이 아니라, 예외가 잘 처리가 되는지가 핵심이다.
 */
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); // memberRepository를 파라미터 로
    }


    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        /**
         문법
         given : 뭔가 주어짐
         when : 이런 상황 일때,
         then : 어떻게 나오나?
         */
        //Given
        Member member = new Member();
        member.setName("hello");
        //When
        Long saveId = memberService.join(member);
        //Then
//        Member findMember = memberService.findOne(saveId).get();
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void 중복_회원_예외() throws Exception {

        //Given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //When
        memberService.join(member1);
        IllegalStateException e = assertThrows(
                IllegalStateException.class,        // IllegalStateException 예외가 발생해야 한다.
                () -> memberService.join(member2)   // 이 코드를 실행 시켰을때
        );

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }

}