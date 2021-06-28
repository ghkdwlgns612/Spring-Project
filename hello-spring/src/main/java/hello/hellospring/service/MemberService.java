package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memeberRepository;

    public MemberService(MemberRepository memeberRepository) {
        this.memeberRepository = memeberRepository;
    }

    public Long join(Member member) { //회원가입.
        validateDuplicateMember(member); //중복 회원은 불가능.
        memeberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
        memeberRepository.findName(member.getName())
             .ifPresent(m -> {
                 throw new IllegalStateException("이미 존재하는 이름입니다.");
             });
    }

    public List<Member> findMembers() { //전체 회원 조회
        return memeberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) { //특정 회원 조회
        return memeberRepository.findId(memberId);
    }
}
