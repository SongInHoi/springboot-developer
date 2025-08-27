import org.junit.jupiter.api.*;

public class JUnitCycleTest {
    // JUnit은 각 테스트에 대해 객체를 만들어 독립적으로 실행한다.
    // 테스트는 애너테이션에 따라 실행 순서가 정해진다.
    @BeforeAll // 전체 테스트를 시작하기 전에 1회 실행하므로 메서드는 static으로 선언
    static void beforeAll(){
        System.out.println("@BeforeAll");
    }

    @BeforeEach // 테스트 케이스를 시작하기 전마다 실행
    public void beforeEach(){
        System.out.println("@BeforeEach");
    }

    @Test
    public void test1(){
        System.out.println("test1");
    }

    @Test
    public void test2(){
        System.out.println("test2");
    }

    @Test
    public void test3(){
        System.out.println("test3");
    }

    @AfterAll // 전체 테스트를 마치고 종료하기 전에 1회 실행하므로 메서드는 static으로 선언
    static void afterAll(){
        System.out.println("@AfterAll");
    }

    @AfterEach // 테스트 케이스를 종료하기 전마다 실행
    public void afterEach(){
        System.out.println("@AfterEach");
    }
}
