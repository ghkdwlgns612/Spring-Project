package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long squence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++squence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findId(Long id) {
        return Optional.ofNullable(store.get(id));//null일 수도 있으니까 감싸줌.
    }

    @Override
    public Optional<Member> findName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}