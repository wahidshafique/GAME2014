package com.bug;

public class Ant extends Organism {
    Ant(Window world, int x, int y) {
        super(world, x, y);
        breedingAgeInterval = 3;
    }

    protected boolean canMove() {
        return true;
    }

    protected void birth(int newX, int newY) {
        world.set(newX, newY, new Ant(world, newX, newY));
    }
}