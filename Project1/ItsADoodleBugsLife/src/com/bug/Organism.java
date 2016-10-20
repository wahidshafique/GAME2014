package com.bug;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Organism {
    Window world;
    int xPos;
    int yPos;
    int age = 0;
    boolean hasMoved;
    int breedingAgeInterval = 100;

    Organism(Window world, int x, int y) {
        this.world = world;
        this.xPos = x;
        this.yPos = y;
        hasMoved = true;
    }

    protected abstract void birth(int newX, int newY);
    protected abstract boolean canMove();

    protected void breed() {
        if(world.inGrid(xPos + 1, yPos) && world.get(xPos + 1, yPos) == null) {
            birth(xPos + 1, yPos);
            return;
        }
        else if(world.inGrid(xPos - 1, yPos) && world.get(xPos - 1, yPos) == null) {
            birth(xPos - 1, yPos);
            return;
        }
        else if(world.inGrid(xPos, yPos + 1) && world.get(xPos, yPos + 1) == null) {
            birth(xPos, yPos + 1);
            return;
        }
        else if(world.inGrid(xPos, yPos - 1) && world.get(xPos, yPos - 1) == null) {
            birth(xPos, yPos - 1);
            return;
        }
        return;
    }

    void run() {
        if (hasMoved) return;
        hasMoved = true;
        age++;
        if (age % breedingAgeInterval == 0 && age != 0) {
            breed();
            return;
        }
        if (canMove()){
        move();
        }
    }
    public void stop() {
        hasMoved = false;
    }

    void move() {//basically random, the basis for any simple organism
        int dirX = setRandom(xPos);//old positions are set to new ones
        int dirY = setRandom(yPos);
        if(world.inGrid(dirX, dirY) && world.get(dirX, dirY) == null) {
            world.set(dirX,dirY, this);//set pos
            world.set(xPos,yPos,null);//remove old pos
            xPos = dirX;//set the old one for current one
            yPos = dirY;
        }
        return;
    }

    int setRandom(int origPos){
        int val = ThreadLocalRandom.current().nextInt(-1, 2);
        return val + origPos;
    }
}
