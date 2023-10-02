package com.sijo.Dossiercompback;

import com.sijo.Dossiercompback.model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;

@SpringBootApplication
public class DossierCompBackApplication {

	public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(DossierCompBackApplication.class, args);

        TreeSet<City> tree = new TreeSet<>();
        tree.add(new City("test1", true));
        tree.add(new City("test2", false));
        tree.add(new City("test3", true));
        tree.add(new City("test4", true));
        StringBuilder stringBuilder = new StringBuilder();
        for (City city : tree) {
            stringBuilder.append(city + ",");

        }
        System.out.println(stringBuilder.toString());


        Map<Animal, String> m = new HashMap<Animal, String>();
        Animal animal = new Animal("Monday");
        Animal animal2 = new Animal("Monday");
        Animal animal3 = new Animal("Tuesday");
        m.put(animal, "qds");
        m.put(animal2, "qdss");
        m.put(animal3, "qdsq");
        m.put(animal, "qds");

        System.out.println("hashmap"+m.size() +"sqddq"+animal.hashCode()+" 2 "+animal2.hashCode());
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("One");
        list.add("Two");
        list.add("Three");
        Iterator<String> i = list.iterator();

        list.remove("Two");


        while (i.hasNext()) {
            System.out.println(i.next());
        }


        HashMap<String, String> map = new HashMap<>();
        map.put("10", "Ten");
        map.put("20", "twenty");
        map.put("30", "Thirty");

        String result = map.putIfAbsent("20", "newTwenty");
        System.out.println(result);
        System.out.println(map.get("20"));
        String result1 = map.putIfAbsent("40", "Forty");
        System.out.println(result1);
        System.out.println(map.get("40"));


        ///////////////////////


        final int maxSize = 25;
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        final ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap<>();

        ExecutorService executorService = Executors.newCachedThreadPool();

        Runnable task1 = () -> {
            for (int j = 0; j < maxSize; j++) {
                concurrentMap.put("key" + j, "value" + j);

            }
            System.out.println("Task1");
            countDownLatch.countDown();
        };

        Runnable task2 = () -> {
            for (int j = 0; j < maxSize; j++) {
                concurrentMap.put("key" + j, "SecondUpdate");

            }
            System.out.println("Task2");
            countDownLatch.countDown();
        };

        executorService.execute(task2);
        executorService.execute(task1);
        System.out.println("Start");
        countDownLatch.await();
        System.out.println("End");
        executorService.shutdown();


        /////

        Function<List<Integer>, Integer> function = x -> x.stream()
                .map(k -> k * 2)
                .mapToInt(k -> k).distinct().sum();
        Function<Integer, Integer> function2 = x -> x * 10;
        Function<Integer, Integer> function3 = x -> x * 100;
        int len = function.andThen(function2).andThen(function3).apply(Arrays.asList(1, 2, 2));
        System.out.println(len);


        ///////
        Person person = new Person();
        person.sayHow(getHow()) ;
        Person employee = new Employe();
        employee.sayHow(getHow()) ;
        User user = employee;
        user.sayHow(getHow()) ;



    }
    private static String getHow() {
        return null;

    }
}



