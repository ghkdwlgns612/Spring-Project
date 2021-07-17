package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long squence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    //싱글톤으로 생성
    public static MemberRepository getInstance() {
        return instance;
    }
    //바깥에서 사용하는 것을 막아줌
    private MemberRepository() {
    }

    public Member save(Member member) {
        member.setId(squence++);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
