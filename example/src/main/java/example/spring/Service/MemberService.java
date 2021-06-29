package example.spring.Service;

import example.spring.Domain.Member;
import example.spring.Repository.MemberRepository;

import java.util.List;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**회원가입 **/
    public Long join (Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return (member.getId());
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

}
