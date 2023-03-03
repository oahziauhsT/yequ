import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: zhaost
 * @create: 2023-01-20
 **/
public class Train {
    @Test
    void test1(){
        Student s1 = new Student();
        s1.a = 1;
        Student s2 = new Student();
        s2.a = 1;
        HashSet<Student> set = new HashSet();
        set.add(s1);
        set.add(s2);
        for (Student student : set) {
            System.out.println(student);
        }
    }

    public class Student {
        public int a;
        public int b;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return a == student.a && b == student.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }

    @Test
    void test2(){
        ThreadLocal threadLocal1 = new ThreadLocal();
        ThreadLocal threadLocal2 = new ThreadLocal();
        Thread thread1 = new Thread(() -> {
            threadLocal1.set(1);
            threadLocal2.set(1);
            System.out.println(threadLocal1.get());
            System.out.println(threadLocal2.get());
        });
        Thread thread2 = new Thread(() -> {
            threadLocal1.set(2);
            threadLocal2.set(2);
            System.out.println(threadLocal1.get());
            System.out.println(threadLocal2.get());
        });
        Thread thread3 = new Thread(() -> {
            threadLocal1.set(3);
            threadLocal2.set(3);
            System.out.println(threadLocal1.get());
            System.out.println(threadLocal2.get());
        });
        thread1.start();
        thread2.start();
        thread3.start();
    }

    private static volatile boolean stop = false;
    @Test
    void test3(){

        new Thread("Thread A") {
            @Override
            public void run() {
                while (!stop) {
                }
                System.out.println(Thread.currentThread() + " stopped");
            }
        }.start();

        // Thread-main
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread() + " after 1 seconds");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stop = true;
    }



}
