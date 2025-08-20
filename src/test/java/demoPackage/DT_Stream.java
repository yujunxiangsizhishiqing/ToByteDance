package demoPackage;

import demoClass.Student;
import demoClass.User;
import org.assertj.core.util.DateUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DT_Stream {
    public static void main(String[] args) {
        //sumTest();
        DT_Stream dtStream = new DT_Stream();
//        dtStream.grpTest();
//
//        //System.out.println(DateUtil.parse("2024-01-01"));
//        System.out.println("\n\n");
//
//        dtStream.grpTest2();
//
//        System.out.println("\n");
//
//        dtStream.test03();


        dtStream.test04();
    }


    /**
     * @apiNote 利用stream对某个属性值求和
     * */
    private static void sumTest(){
        Student wx01 = new Student("wx01", 10, 60, BigDecimal.ZERO);
        System.out.println(wx01);
        Student wx02 = new Student("wx02", 20, 70, BigDecimal.ONE);
        System.out.println(wx02);
        Student wx03 = new Student("wx03", 30, 90, BigDecimal.TEN);
        System.out.println(wx03);
        List<Student> studentList = new ArrayList<>();
        studentList.add(wx01);
        studentList.add(wx02);
        studentList.add(wx03);

        //分数求和
        int sum = studentList.stream().mapToInt(Student::getScore).sum();
        System.out.println("所有人的分数总和为： "+sum);
        //code求和
        BigDecimal reduce = studentList.stream().map(Student::getCode).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("BigDecimal类型的code属性求和为："+ reduce);
    }

    /**list分组测试*/
    private void grpTest(){
        List<Item> items = Arrays.asList(
                new Item("Electronics", "Television", 1),
                new Item("Electronics", "Radio", 2),
                new Item("Kitchen", "Oven", 3),
                new Item("Kitchen", "Pan", 4),
                new Item("Electronics", "Television", 5)
        );

        Map<String, List<Item>> groupedItems = items.stream()
                .collect(Collectors.groupingBy(item ->
                        item.getCategory() + "-" + item.getSubCategory()
                ));

        groupedItems.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    /**list分组测试2*/
    private void grpTest2(){
        User user1 = new User("wx01","123");
        user1.setCreatedatetime(DateUtil.parse("2024-01-01"));
        User user2 = new User("wx01","123");
        user2.setCreatedatetime(DateUtil.parse("2024-01-01"));
        User user3 = new User("wx01","123");
        user3.setCreatedatetime(DateUtil.parse("2024-01-02"));

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        Map<String, List<User>> collect = users.stream().collect(Collectors.groupingBy(
                item -> String.format(item.getName()
                        + "-" + item.getPwd()
                        + "-" + new SimpleDateFormat("yyyyMMdd").format(item.getCreatedatetime()))));
        collect.forEach((key, value) -> System.out.println(key + ": " + value));


    }


    private void test03(){
        List<Item2> items = List.of(
                new Item2(1, "Item1"),
                new Item2(2, "Item2"),
                new Item2(1, "Item3"),
                new Item2(3, "Item4")
        );

        int targetAValue = 1;
        List<String> bProperties = items.stream()
                .filter(item -> item.getA() == targetAValue)
                .map(Item2::getB)
                .collect(Collectors.toList());

        System.out.println(bProperties);
    }

    private void test04(){
        List<Item> list = Arrays.asList(
                new Item("wx", "Television", 1),
                new Item("wx", "Radio", 2),
                new Item("wx", "Oven", 3),
                new Item("xx", "Pan", 4),
                new Item("xx", "Television", 5));
        List<Item> result = list.stream().filter(s -> !s.getCategory().equals("wx")).collect(Collectors.toList());
        System.out.println(list);
        result.forEach(s-> System.out.println(s.getCategory()));
    }

    class Item2 {
        private int a;
        private String b;

        public Item2(int a, String b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public String getB() {
            return b;
        }
    }
    class Item {
        private String category;
        private String subCategory;
        private int id;

        public Item(String category, String subCategory, int id) {
            this.category = category;
            this.subCategory = subCategory;
            this.id = id;
        }

        public String getCategory() {
            return category;
        }

        public String getSubCategory() {
            return subCategory;
        }

        public int getId() {
            return id;
        }
    }
}
