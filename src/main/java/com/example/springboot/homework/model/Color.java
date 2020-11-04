package com.example.springboot.homework.model;



public enum Color {
    RED("red"),
    BLUE("blue"),
    WHITE("white"),
    BLACK("black"),
    GREEN("green"),
    UNIDENTIFIED("unidentified");

    public final String color;

    private Color(String color) {
        this.color = color;
    }
}
