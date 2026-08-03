package org.example.service;

import org.example.model.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentService {

    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student student){
        students.add(student);
        System.out.println("Student Added Successfully.");
    }

    public void viewStudents(){

        if(students.isEmpty()){
            System.out.println("No Students Found.");
            return;
        }

        students.forEach(System.out::println);
    }

    public void searchById(int id){

        students.stream()
                .filter(s->s.getId()==id)
                .findFirst()
                .ifPresentOrElse(
                        System.out::println,
                        ()-> System.out.println("Student Not Found.")
                );
    }

    public void searchByName(String name){

        List<Student> list=students.stream()
                .filter(s->s.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());

        if(list.isEmpty())
            System.out.println("No Student Found");
        else
            list.forEach(System.out::println);

    }

    public void updateStudent(int id,String name,int age,String branch,double marks,char grade){

        for(Student s:students){

            if(s.getId()==id){

                s.setName(name);
                s.setAge(age);
                s.setBranch(branch);
                s.setMarks(marks);
                s.setGrade(grade);

                System.out.println("Student Updated.");
                return;
            }
        }

        System.out.println("Student Not Found.");
    }

    public void deleteStudent(int id){

        Iterator<Student> iterator=students.iterator();

        while(iterator.hasNext()){

            Student s=iterator.next();

            if(s.getId()==id){

                iterator.remove();
                System.out.println("Deleted Successfully.");
                return;
            }
        }

        System.out.println("Student Not Found.");
    }

    public void searchByBranch(String branch){

        students.stream()
                .filter(s->s.getBranch().equalsIgnoreCase(branch))
                .forEach(System.out::println);
    }

    public void searchByGrade(char grade){

        students.stream()
                .filter(s->s.getGrade()==grade)
                .forEach(System.out::println);
    }

    public void countStudents(){

        System.out.println("Total Students = "+students.size());
    }

    public void sortByMarks(){

        students.stream()
                .sorted(Comparator.comparing(Student::getMarks).reversed())
                .forEach(System.out::println);
    }
}