package hw7;

public class CatTest {
    @AfterSuite
    public void sleep() {
        System.out.println("Кот: спит");
    }

    @Test(priority = 7)
    public void eat() {
        System.out.println("Кот: ест");
    }

    @BeforeSuite
    public void wakeup() {
        System.out.println("Кот: пробудился");
    }

    @Test
    public void voice() {
        System.out.println("Кот: Мяуууу");
    }

}
