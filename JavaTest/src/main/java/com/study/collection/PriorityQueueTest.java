package com.study.collection;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueTest {
    public static void main(String[] args) {
//        PriorityQueue<String> queue = new PriorityQueue<>();
//        queue.offer("1");
//        queue.offer("2");
//        queue.offer("5");
//        queue.offer("4");
//        queue.offer("3");
//
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
        PriorityQueue<Student> studentQueue = new PriorityQueue<>((o1, o2) -> {
            if (o1.getScore() == o2.getScore()) {
                return o1.getName().compareTo(o2.getName());
            }
            return o1.getScore() - o2.getScore();
        });
        //入列
        studentQueue.offer(new Student("dafei", 20));
        studentQueue.offer(new Student("will", 17));
        studentQueue.offer(new Student("setf", 30));
        studentQueue.offer(new Student("bunny", 20));

        System.out.println(studentQueue.poll().toString());
        System.out.println(studentQueue.poll().toString());
        System.out.println(studentQueue.poll().toString());
        System.out.println(studentQueue.poll().toString());

    }

    static class Student {
        private String name;
        private int score;

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", score=" + score +
                    '}';
        }

        public Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }
}
