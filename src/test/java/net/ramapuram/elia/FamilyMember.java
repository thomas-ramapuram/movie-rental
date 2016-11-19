package net.ramapuram.elia;

/**
 * Created by Elia Thomas Ramapuram on 04/10/16.
 * as part of the Computer Science Project 2016-17
 */
public class FamilyMember {
    String name;
    int age;

    public FamilyMember(String name, int age) {
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
    public String toString() {
        return "FamilyMember{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
