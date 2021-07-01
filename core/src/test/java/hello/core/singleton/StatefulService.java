package hello.core.singleton;

import org.junit.jupiter.api.Test;

public class StatefulService {

    private int price;//상태유지 필드.문제가 된다.

    //void->int로 해서 return값을 주면 오류해결.
    public int order(String name, int price) {
        System.out.println("name = " + name + "price = " + price);
//        this.price = price;//여기가 문제.
        return price;
    }

//    public int getPrice() {
//        return price;
//    }


}
