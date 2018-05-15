package com.example.android.java.model;

@SuppressWarnings("unused")
public class Person {

    private String firstName;
    private int age;

    public Person(String firstName, int age) {
        this.firstName = firstName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // compareByAge() compares the age field of multiple instances of
    // the Person class and returns them in the order of smallest to largest
    // age
    public static int compareByAge(Person o1, Person o2) {

        // Get the age of the first person
        Integer age_1 = o1.getAge();

        // Compare the ages of person1 and person2
        return age_1.compareTo(o2.getAge());
    }

    @Override
    public String toString() {
        return firstName + " (" + age + ")";
    }
}
