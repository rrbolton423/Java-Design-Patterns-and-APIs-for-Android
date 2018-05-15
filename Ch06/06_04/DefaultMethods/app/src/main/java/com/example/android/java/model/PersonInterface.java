package com.example.android.java.model;

public interface PersonInterface {

    // With the method having the keyword "default",
    // any class that implements this interface will inherit
    // the complete method and it's implementation, and not just the
    // method header. Using default methods you don't have to implement
    // the methods in the implementing classes.
    default int compareByAge(Person o1, Person o2) {
        Integer age_1 = o1.getAge();
        return age_1.compareTo(o2.getAge());
    }
}
