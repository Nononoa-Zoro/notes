package com.study.collection;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetTest {
    public static void main(String[] args) {
        HashSet<Person> hs = new HashSet<>();
        hs.add(new Person("lisi4", 24));
        hs.add(new Person("lisi7", 27));
        hs.add(new Person("lisi1", 21));
        hs.add(new Person("lisi9", 29));
        hs.add(new Person("lisi7", 27));

        Iterator<Person> iterator = hs.iterator();
        while (iterator.hasNext()) {
            Person p = (Person)iterator.next();
            System.out.println(p.toString());
        }
    }

    static class Person implements Comparable {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
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
        public String   toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        @Override
        public int compareTo(Object o) {
            Person person = (Person) o;
            if (this.getAge() == person.getAge()) {
                return this.getName().compareTo(person.getName());
            }
            return this.getAge() - person.getAge();
        }

        @Override
        public int hashCode() {
            System.out.println("this hashcode");
            return this.name.hashCode()+age*27;
        }

        @Override
        public boolean equals(Object obj) {
            if(this==obj)return true;
            if(!(obj instanceof  Person))throw new ClassCastException("类型不匹配");
            Person p = (Person)obj;
            return this.name.equals(p.name)&&this.age==p.age;
        }
    }
}
