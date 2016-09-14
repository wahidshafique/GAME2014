package com.company;


/**
 * Created by Wahid on 9/12/2016.
 */
public class Employee {
    String name;
    double hourlyPay;
    int yearOfHire;
    double hoursPerWeek;

    Employee(){
        this.name = "";
        this.yearOfHire = 0;
        this.hourlyPay = 0;
        this.hoursPerWeek = 0;
    }

    Employee(String name, double hourlyPay, int yearOfHire, double hoursPerWeek) {
        this.name = name;
        this.yearOfHire = yearOfHire;
        this.hourlyPay = hourlyPay;
        this.hoursPerWeek = hoursPerWeek;
    }

    double getYearlySalary() {
        return (this.hoursPerWeek * 52) * this.hourlyPay;
    }
    int getYearsOfEmployment(int currYear){
        return currYear - yearOfHire;
    }
}
