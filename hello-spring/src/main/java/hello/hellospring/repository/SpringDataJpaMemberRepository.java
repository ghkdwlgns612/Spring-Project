package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface SpringDataJpaMemberRepository extends JpaRepository<Member,
        Long>, MemberRepository {
    Optional<Member> findName(String name);// 여기서 오류가나는이유는 findByName이 JpaRepository에 등록이 되어있어서 처음부터 이름을 잘못 설정함.
}