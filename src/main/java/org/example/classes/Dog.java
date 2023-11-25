package org.example.classes;

public class Dog extends Animal{
    public Dog(String name, boolean isAlive){
        super(name,isAlive);
    }
    public void bark(String how){
        System.out.println(howIsNamed + " " + how);
    }
}
