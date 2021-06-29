package example.spring.Service;

import example.spring.Domain.Member;
import example.spring.Repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceMemberTest {

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    public void BeforeEach() {
        memoryMemberRepository = new MemoryMemberRepository(); //메모리레퍼지토리를 생성하고
        memberService = new MemberService(memoryMemberRepository);// 그것을 서비스에 넣어준다.그래서 똑같은 멤버레퍼지토리를 사용한다.
    }

    @AfterEach
    public void clean() {
        memoryMemberRepository.clear();
    }

    @Test
    public void 회원가입() {
        Member member = new Member();
        member.setName("spring");

        Long Id = memberService.join(member);
        assertEquals(member.getId(),Id);
    }

    @Test
    public void 중복예외() throws Exception {
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
