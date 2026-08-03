package org.example.model;

public class Student {

    private int id;
    private String name;
    private int age;
    private String branch;
    private double marks;
    private char grade;

    public Student() {
    }

    public Student(int id, String name, int age, String branch, double marks, char grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.branch = branch;
        this.marks = marks;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "ID=" + id +
                ", Name='" + name + '\'' +
                ", Age=" + age +
                ", Branch='" + branch + '\'' +
                ", Marks=" + marks +
                ", Grade=" + grade;
    }
}