package hw7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestClass {
    public static void main(String[] args) {
        Class catTest = CatTest.class;
        try {
            start(catTest);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void start(Class cl) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        List<Method> methodList = new ArrayList<>();
        Method[] methods = cl.getDeclaredMethods();

        int beforeCount = 0;
        int afterCount = 0;
        for (Method m : methods) {
            if (m.isAnnotationPresent(Test.class)) {
                methodList.add(m);
            }
        }
        methodList.sort(Comparator.comparingInt((Method m) -> m.getAnnotation(Test.class).priority()).reversed());

        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                methodList.add(0, m);
                beforeCount++;
            } else if (m.isAnnotationPresent(AfterSuite.class)) {
                methodList.add(m);
                afterCount++;
            }
            if (afterCount > 1 || beforeCount > 1) {
                throw new RuntimeException();
            }
        }

        for (Method m : methodList) {
            m.invoke(cl.getDeclaredConstructor().newInstance());
        }
    }
}
