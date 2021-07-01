package hello.core.singleton;

public class SingletoneService {
    //딱 하나만 존재.
    private static final SingletoneService instance = new SingletoneService();

    public static SingletoneService getInstance() {
        return instance;
    }

    private SingletoneService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 호출");
    }
}
