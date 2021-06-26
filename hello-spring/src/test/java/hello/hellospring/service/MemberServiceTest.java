package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;


    @BeforeEach //테스트를 할 경우 같은 메모리멤버레퍼지토리로 같이 실행하기 위해 만들었음. DI로 외부에서 넣어준다.
    public void BeforeEach() {
        memberRepository = new MemoryMemberRepository(); //메모리레퍼지토리를 생성하고
        memberService = new MemberService(memberRepository);// 그것을 서비스에 넣어준다.그래서 똑같은 멤버레퍼지토리를 사용한다.
    }

    @AfterEach //이건 각 테스트의 순서에 따라 값이 저장되어져 있어 오류가 뜰 수 있으므로 메모리를 비워주고 테스트함
    public void afterEach() {
       memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //GIVEN
        Member member = new Member();
        member.setName("Spring");

        //WHEN
        Long saveId = memberService.join(member);

        //THEN
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복예외검증() {
        //GIVEN
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");
        //WHEN
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));//(예외가 터질지 실험할 예외, 터지게 할 수 있는 로직)
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
        //THEN
    }

    @Test
    void 전체멤버찾기() {
    }

    @Test
    void 특정멤버찾기() {
    }
}