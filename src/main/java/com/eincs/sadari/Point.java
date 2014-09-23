package com.eincs.sadari;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

public class Point implements Comparable<Point> {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point other = (Point) obj;
            return Objects.equal(x, other.x)
                    && Objects.equal(y, other.y);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(x, y);
    }

    @Override
    public int compareTo(Point o) {
        return ComparisonChain.start().compare(x, o.x).compare(y, o.y).result();
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(getClass()).add("x", x).add("y", y).toString();
    }
}
