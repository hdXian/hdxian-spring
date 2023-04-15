package hdxian.hdxianspring;

import hdxian.hdxianspring.repository.MemberRepository;
import hdxian.hdxianspring.repository.MemoryMemberRepository;
import hdxian.hdxianspring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }


}
