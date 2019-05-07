package com.example.poultrygeist;

public class House {
    private static int houseCounter;
    private int houseNumber;
    private int numberOfDeadChickens;

    public House() {

    }

    public House(int numberOfDeadChickens) {
        this.houseNumber = houseCounter;
        this.numberOfDeadChickens = numberOfDeadChickens;
        houseCounter++;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public int getNumberOfDeadChickens() {
        return numberOfDeadChickens;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setNumberOfDeadChickens(int numberOfDeadChickens) {
        this.numberOfDeadChickens = numberOfDeadChickens;
    }
}