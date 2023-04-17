package co.kr.steadysprinting.hellospring.controller;

import co.kr.steadysprinting.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    // MemberService 는 여러개를 만들 필요가 없다.
    // MemberService 는 다른 Controller 에서도 공용으로 사용할수 있다.
    private final MemberService memberService;


    @Autowired // MemberController 와 memberService 간에 연결을 생성과 동시에 시켜준다.
    public MemberController(MemberService memberService) { // 생성자 파라머터로 설정(의존성 주입)
        this.memberService = memberService;
    }


}
