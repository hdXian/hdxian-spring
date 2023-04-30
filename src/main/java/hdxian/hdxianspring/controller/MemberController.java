package hdxian.hdxianspring.controller;

import hdxian.hdxianspring.domain.Member;
import hdxian.hdxianspring.repository.MemoryMemberRepository;
import hdxian.hdxianspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("members/new") // 회원가입 버튼울 누르면 회원 정보 작성 폼으로 연결
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String createMember(MemberForm memberForm) {
        Member newMember = new Member();
        newMember.setName(memberForm.getName());

        memberService.join(newMember);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String getMemberList(Model model) {
        List<Member> memberList = memberService.findMembers();
        model.addAttribute("members", memberList);

        return "members/memberList";
    }


}
