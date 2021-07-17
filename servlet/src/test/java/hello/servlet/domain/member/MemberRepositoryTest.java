package hello.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();
    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member hello = new Member("hello", 20);

        //when
        Member saveMember = memberRepository.save(hello);
        //then
        Member findMember = memberRepository.findById(hello.getId());
        assertThat(findMember).isEqualTo(saveMember);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("member1",20);
        Member member2 = new Member("member2",25);

        memberRepository.save(member1);
        memberRepository.save(member2);
        //when

        List<Member> members = memberRepository.findAll();
        //then

        assertThat(members.size()).isEqualTo(2);
        assertThat(members).contains(member1,member2);
    }
}