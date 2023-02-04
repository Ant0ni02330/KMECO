package com.example.kmeco;
//So we store the user information in the object. And then we send it to FIREBASE
public class User {
    public String fullName, age, email;

    //Creating two constructors
    //First one is an empty public constructor that doesn't accept or return anything
    public User(){

    }
    //Second constructor
    public User(String fullName, String age, String email){
        //initializing values
        this.fullName = fullName;
        this.age = age;
        this.email = email;
    }
}
