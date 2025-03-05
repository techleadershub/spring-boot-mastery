package com.example.springdi.repository;

public class MyRepository {
    public MyRepository() {
        System.out.println("MyRepository constructor called");
    }

    public void doQuery() {
        System.out.println("MyRepository is doing query!");
    }
}
