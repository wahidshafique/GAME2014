package com.bug;

public class DoodleBug extends Organism {
    int hunger = 3;
    DoodleBug(Window world, int x, int y) {
        super(world,x,y);
        breedingAgeInterval = 8;
    }

    protected boolean canMove() {//if you can eat an ant, you eat and do not move
        if(eatAnt(xPos + 1, yPos)) return false;
        else if(eatAnt(xPos - 1, yPos)) return false;
        else if(eatAnt(xPos, yPos + 1)) return false;
        else if(eatAnt(xPos, yPos - 1)) return false;
        hunger--;
        if(hunger == 0) {
            world.set(xPos,yPos,null);//doodle dies
            return false;
        }
        return true;
    }
    private boolean eatAnt(int tempX, int tempY) {
        Organism tempOrg = world.get(tempX,tempY);
        if(world.inGrid(tempX, tempY) && tempOrg != null && tempOrg instanceof Ant)
        {
            hunger = 3;
            world.set(xPos, yPos, null);//remove ant first
            world.set(tempX, tempY, this);//set yourself
            this.xPos = tempX;
            this.yPos = tempY;
            return true;
        }
        return false;
    }

    protected void birth(int newX, int newY) {
        world.set(newX, newY, new DoodleBug(world, newX, newY));
    }
}
