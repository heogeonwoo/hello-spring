package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 컨포넌트 스캔
public class MemberController {
    /* 1. 필드 주입: 좋지 않은 방법
    @Autowired private MemberService memberService;
     */

    /* 2. setter 주입: public으로 열려있어야 가능
    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
         this.memberService = memberService;
    }
     */

    // 3. 생성자 주입: 의존관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 생상자 주입을 권장
    private final MemberService memberService;

    @Autowired // 자동 의존관계 설정: 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        // System.out.println("memberService = " + memberService.getClass()); // 프록시 확인
    }

    @GetMapping("/members/new") // 데이터 조회
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") // 데이터 등록
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
