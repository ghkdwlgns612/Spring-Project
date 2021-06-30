package hello.core.oreder;

import hello.core.AppConfig;
import hello.core.member.*;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void BeforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }
    @Test
    void createOrder() {
        //given
        Long memberId = 1L;
        Member member = new Member(memberId,"memberA",Grade.VIP);
        memberService.join(member);
        //when
        Order order = orderService.creatOrder(memberId,"itemA",10000);
        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
