package com.study.zuoshen;

import java.util.Arrays;
import java.util.Comparator;

class Student {
    public String name;
    public int id;
    public int age;

    public Student(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    @Override
    public String toString() {
        return "name: " + this.name + " id: " + this.id + " age: " + this.age;
    }
}

class IdDescendComparator implements Comparator<Student>{
    @Override
    public int compare(Student o1, Student o2) {
        return o2.id-o1.id;
    }
}
class IdAscendComparator implements Comparator<Student>{
    @Override
    public int compare(Student o1, Student o2) {
        return o1.id-o2.id;
    }
}

public class Main {
    public static void main(String[] args) {

        Student s1 = new Student("A", 1, 10);
        Student s2 = new Student("B", 2, 9);
        Student s3 = new Student("C", 3, 8);
        Student[] arr = new Student[]{s1, s2, s3};

        Arrays.sort(arr, new IdDescendComparator());
        print(arr);

        Arrays.sort(arr,new IdAscendComparator());
        print(arr);

    }
    public static void print(Student[] arr){
        for (Student student : arr) {
            System.out.println(student.toString());
        }
        System.out.println("===============");
    }
}
