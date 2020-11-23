package com.mex.mazeescape.model;

import java.util.Objects;

public class MazeIndex {

    private int x;
    private int y;

    public MazeIndex(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MazeIndex mazeIndex = (MazeIndex) o;
        return x == mazeIndex.x &&
                y == mazeIndex.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "MazeIndex{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
