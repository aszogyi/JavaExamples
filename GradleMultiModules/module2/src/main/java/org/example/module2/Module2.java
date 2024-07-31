package org.example.module2;

import org.example.module1.Module1;

public class Module2 {

    public static void main(String[] args) {
        Module1 module1 = new Module1();
        System.out.println(module1.getMessage());
        System.out.println("Hello from Module 2");
    }
}