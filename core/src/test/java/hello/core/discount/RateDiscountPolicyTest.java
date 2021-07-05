package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@Component
class RateDiscountPolicyTest {
   RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

   @Test
   @DisplayName("VIP OK")
   void vip_o() {
       //given
       Member member = new Member(1L, "memberVIP", Grade.VIP);
       //when
       int discount = rateDiscountPolicy.discount(member,10000);
       //then
      assertThat(discount).isEqualTo(1000);
   }
   @Test
   @DisplayName("VIP X")
   void vip_no() {
      //given
      Member member = new Member(2L, "memberBASIC", Grade.BASIC);
      //when
      int discount = rateDiscountPolicy.discount(member,10000);
      //then
      assertThat(discount).isEqualTo(0);
   }
}