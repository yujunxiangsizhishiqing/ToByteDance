package demoPackage;

import demoClass.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DT_Lambda {

    public static void main(String[] args) {

//        List<String> list = Arrays.asList("wangxu", "chenjie", "123");
//        System.out.println(list);


//        ArrayList<Student> students = new ArrayList<>();
//        students.add(new Student("a", 25, 85));
//        students.add(new Student("b", 26, 86));
//        students.add(new Student("c", 27, 87));
//        students.add(new Student("e", 29, 89));
//        students.add(new Student("d", 28, 88));
//        //System.out.println(students +" "+ students.size());
//        students.forEach(s->{
//            System.out.println(s);
//        });

        int maxAge = 130;
        int minAge = 0;
        int maxScore = 100;
        int minScore = 0;

        List<Student> students = genrateStudentList(maxAge,maxScore,10000);

        List<Student> lowStudent = getStudentByScore(students, 60, 0);
        System.out.println("不及格的学生有"+lowStudent.size()+"个");
//        lowStudent.forEach(s->{
//            System.out.println(s);
//        });

        List<Student> perStudent = getStudentByScore(students, 100, 95);
        System.out.println("牛逼的学生有"+perStudent.size()+"个");
        boolean result02 = perStudent.stream().anyMatch(m -> m.getScore()>95);
        System.out.println("有没有100分的学生？"+result02);

//        perStudent.forEach(s->{
//            System.out.println(s);
//        });

        List<Student> otherStudent = getStudentByScore(students, 94, 61);
        System.out.println("其他的学生有"+otherStudent.size()+"个");

//        System.out.println("年龄大于" + minAge + "小于" + maxAge + "并且"+"成绩大于" + minScore + "小于" + maxScore + "的学生有"+retList.size()+"个");
//        retList.forEach(s->{
//            System.out.println(s);
//        });
//        System.out.println("年龄大于" + minAge + "小于" + maxAge + "并且"+"成绩大于" + minScore + "小于" + maxScore + "的学生有"+retList.size()+"个");


    }

    public static List<Student> genrateStudentList(int maxAge,int maxSCORE,int listSize){
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0;i < listSize ; i++){
            students.add(new Student("a",(int) (maxAge * Math.random()),(int) (maxSCORE * Math.random())));
        }

        return students;
    }

    public static List<Student> getStudentByAge(List<Student> sourceList,int maxAge,int minAge){
        System.out.println("查询年龄大于" + minAge + "小于" + maxAge + "的学生");
        List<Student> collect = sourceList.stream().filter(student -> (student.getAge() <= maxAge) && (student.getAge() >= minAge)).collect(Collectors.toList());
        return collect;
    }

    public static List<Student> getStudentByScore(List<Student> sourceList,int maxScore,int minScore){
        System.out.println("查询成绩大于" + minScore + "小于" + maxScore + "的学生");
        List<Student> collect = sourceList.stream().filter(student -> (student.getScore() <= maxScore) && (student.getScore() >= minScore)).collect(Collectors.toList());
        return collect;
    }
}
