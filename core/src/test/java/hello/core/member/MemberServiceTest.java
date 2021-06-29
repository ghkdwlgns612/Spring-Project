package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();
    @Test
    void join() {
        //given
        Member member = member = new Member(1L,"memberA",Grade.VIP);
        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
    @Test
    void findMember() {
        //given
        Member member = new Member(34L,"jihuhwan",Grade.BASIC);
        //when
        memberService.join(member);
        Member member1 = memberService.findMember(34L);
        //then
        Assertions.assertThat(member).isEqualTo(member1);
    }
}
