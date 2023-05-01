package hdxian.hdxianspring.service;

import hdxian.hdxianspring.domain.Member;
import hdxian.hdxianspring.repository.MemberRepository;
import hdxian.hdxianspring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    // private final MemberRepository repository = new MemoryMemberRepository();
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    // 회원 가입 기능
    public Long join(Member member) {
        // 중복된 이름으로는 가입 불가.
        checkNameDuplication(member);
        repository.save(member);
        return member.getId();
    }

    private void checkNameDuplication(Member member) {
        repository.findByName(member.getName()) // findbyName()의 리턴 타입은 Optional<Member>
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findMembers() {
        return repository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return repository.findById(memberId);
    }


}
