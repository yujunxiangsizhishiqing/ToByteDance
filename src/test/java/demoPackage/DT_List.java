package demoPackage;

import demoClass.Student;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DT_List {

    public static void main(String[] args) {
        //test01();

/*
        Student wx = new Student("wx", 10, 10);
        System.out.println(wx.getName());
        classTest(wx);
        System.out.println(wx.getName());
        classTest2(wx);
        System.out.println(wx.getName());
*/

        //test0();

        List<String> list = new ArrayList<>();
        list.add("1");
        list.addAll(null);

/*        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        for (String s : list) {
            System.out.println(s);
        }

        System.out.println("---- ");

        List<String> collect = list.stream().filter(s -> "1".equals(s)).collect(Collectors.toList());
        for (String s : collect) {
            System.out.println(s);
        }*/

    }

    private static void test0(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        list.forEach(s-> System.out.println(s));
        removeList(list);
        list.forEach(s-> System.out.println(s));
    }

    private static List<String> removeList(List<String> list){
        List<String> rList = new ArrayList<>();
        rList.add("1");
        rList.add("2");
        list.removeAll(rList);
        //list.forEach(s-> System.out.println(s));
        System.out.println("------");

        return list;
    }

    private static void classTest(Student stu){
        Student student = new Student();
        BeanUtils.copyProperties(stu,student);
        student.setName("xx");
    }

    private static void classTest2(Student stu){
        Student student = stu;
        //BeanUtils.copyProperties(stu,student);
        student.setName("xx");
    }
    private static void test01(){

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add(null);
        list.add("2");
        list.add("3");
        list.add("4");

        System.out.println(list.size());

        for (String s : list) {
            System.out.println(s.length());
        }
    }
}
