package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save (Member member);
    Optional<Member> findId(Long id);
    Optional<Member> findName(String name);
    List<Member> findAll();
}
