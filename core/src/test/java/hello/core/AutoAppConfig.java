package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core.member", //어디서부터 찾을지 위치지정.
        basePackageClasses = AutoAppConfig.class, //hello.core에서부터 찾음.
        //Default값은? hello.core부터 시작.@ComponentScan이 붙은 곳부터 사용.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) //이것을 빼고 스캔한다.
)

public class AutoAppConfig {

//    @Bean("memoryMemberRepository") //중복으로 등록되도 수동으로 하는 것이 우선적으로 실행해준다.Overridng해줌.
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
