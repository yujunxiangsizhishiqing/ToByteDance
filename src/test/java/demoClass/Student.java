package demoClass;

import java.math.BigDecimal;

public class Student {

    private String name;

    private int age;

    private int score;

    private BigDecimal code;

    public Student(){

    }

    public Student(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public Student(String name, int age, int score, BigDecimal code) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.code = code;
    }

    public BigDecimal getCode() {
        return code;
    }

    public void setCode(BigDecimal code) {
        this.code = code;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", code=" + code +
                '}';
    }
}
