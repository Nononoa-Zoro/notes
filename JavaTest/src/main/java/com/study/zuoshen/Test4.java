package com.study.zuoshen;

import java.util.PriorityQueue;

/**
 * PriorityQueue就是堆
 */
public class Test4 {
    public static void main(String[] args) {
        PriorityQueue<Student> heap = new PriorityQueue<>(new IdDescendComparator());
        Student s1 = new Student("A", 1, 10);
        Student s2 = new Student("B", 2, 9);
        Student s3 = new Student("C", 3, 8);
        heap.add(s1);
        heap.add(s2);
        heap.add(s3);
        while (!heap.isEmpty()){
            Student student = heap.poll();
            System.out.println(student.toString());
        }
    }
}
