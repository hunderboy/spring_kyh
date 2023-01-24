package co.kr.steadysprinting.hellospring.service;

import co.kr.steadysprinting.hellospring.domain.Member;
import co.kr.steadysprinting.hellospring.repository.MemberRepository;
import co.kr.steadysprinting.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;



/**
 - service 와 repository 의 네이밍을 보면
 service : 비지니스 에 대한 설명에 훨씬 가까운 네이밍 (일반적인 회의에서 나오는 명명) : 비지니스에 의존적으로 설계하고 명명
 repository : DB 에 CRUD 를 하는 설명에 가까운 네이밍 (개발회의에서 나오는 명명) : 개발에 가깝게 설계하고 명명
 */
public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 인터페이스 '객체이름' = new 클래스명()

    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증 memberRepository.save(member); 예시로 같은 이름의 회원은 안된다.
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
        /**
         - 변수 추출하기
         Todo : Command + Option + V (김영한이 제일 좋아하는 변수를 만들어주는 단축키)
         memberRepository.findByName(member.getName());
         => Optional<Member> result = memberRepository.findByName(member.getName());
         Optional 클래스를 통해서 예전이면 if(result == null) 이면 같은 특별case 를 처리하는 조건문을 만들어 줘야 했지만
         Optional 클래스에서 제공해주는 함수로, if 조건문을 만들어줄 필요가 없다.
         */
        // Optional<Member> result = memberRepository.findByName(member.getName());
        // result.ifPresent(m -> {
        //     throw new IllegalStateException("이미 존재하는 회원입니다.");
        // });
        // Todo : 위의 형태는 가독성이 좋지 않다.

        // Todo : 범위 drag 하고 ctrl + t(코드 리팩토링) => Extract Method 하면, 해당 부분을 함수로 추출한다.
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     *전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
