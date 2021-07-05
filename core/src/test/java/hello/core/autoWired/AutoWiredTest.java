package hello.core.autoWired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import javax.lang.model.SourceVersion;
import java.util.Optional;

public class AutoWiredTest {

    @Test
    void AutoWiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        @Autowired(required = false) //Bean등록이 안되도 에러가 안난다. 호출 자체가 안됨
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }
        @Autowired //null로 값이 들어감 noBean2 = null
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }
        @Autowired // noBean3 = Optional.empty
        public void setNoBean2(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }



    }
}
