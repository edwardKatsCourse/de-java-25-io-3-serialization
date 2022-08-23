package com.company;

import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // serialization:
        //      object -> byte code (File System)

        // deserialization:
        //      byte code -> object

        serialize();
        deserialize();
    }

    private static void deserialize() throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream("john.employee");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            Employee john = (Employee) ois.readObject();
            System.out.println(john);
        }
    }

    private static void serialize() throws IOException {
        try (FileOutputStream fos = new FileOutputStream("john.employee");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            Employee john = new Employee("John", 35, "qwerty123456");

            oos.writeObject(john);
            oos.flush();

        }
    }
}

class Employee implements Serializable {
    private static final long serialVersionUID = 2L;

    private final String name;
    private final int age;

    private final transient String password;

    public Employee(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }
}