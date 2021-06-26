package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //이건 각 테스트의 순서에 따라 값이 저장되어져 있어 오류가 뜰 수 있으므로 메모리를 비워주고 테스트함
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);
        Member result = repository.findId(member.getId()).get();
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public  void findByName() {
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        Member result = repository.findName("Spring1").get();
        Assertions.assertThat(member1).isEqualTo(result);
    }

    @Test
    public void findall() {
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
