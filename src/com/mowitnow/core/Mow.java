package com.mowitnow.core;

import java.util.ArrayList;
import java.util.List;

public class Mow {
    private final int xMax;
    private final int yMax;
    private int x, y;
    private Direction direction;

    private List<Command> commands;

    public Mow(int xMax, int yMax, int x, int y, Direction direction) {
        this.xMax = xMax;
        this.yMax = yMax;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.commands = new ArrayList<>();
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    public void addCommand(Command command) {
        this.commands.add(command);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    /**
     * The mow executes all the commands added to it, and move accordingly
     */
    public void execute() {
        if(commands == null || commands.isEmpty()) return;
        for(Command command : commands) {
            switch (command) {
                case A: move();break;
                case D: turnRight();break;
                case G: turnLeft();break;
            }
        }
    }

    private void move() {
        switch (direction) {
            case N:
                y += (y < yMax) ? 1 : 0;
                break;
            case E:
                x += (x < xMax) ? 1 : 0;
                break;
            case S:
                y -= (y > 0) ? 1 : 0;
                break;
            case W:
                x -= (x > 0) ? 1 : 0;
        }
    }

    private void turnRight() {
        direction = direction.rotate(1);
    }

    private void turnLeft() {
        direction = direction.rotate(-1);
    }
}
