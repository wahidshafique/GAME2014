package com.bug;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    Bug bugsy = new Bug (10);
        bugsy.move();
        bugsy.turn();
        bugsy.move();
        bugsy.move();
        bugsy.move();
        bugsy.move();

        System.out.println(bugsy.getPosition());

        Square a = new Square();

        Square b = a;

        a.area = 25;

        System.out.println(a.area);

        System.out.println(b.area);
    }
}

class Bug {
    int position;
    int dir;
    Bug(int initialPosition){
        this.position = initialPosition;
        dir = 1;
    }
    void turn (){
        dir = -dir;
    }
    void move(){
        position += 1 * dir;
    }
    int getPosition (){
        return position;
    }
}

class Square {
        int area;
        }


