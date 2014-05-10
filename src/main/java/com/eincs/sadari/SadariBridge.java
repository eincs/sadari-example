package com.eincs.sadari;

public final class SadariBridge {
    private final Point fromPoint;
    private final Point toPoint;

    public SadariBridge(Point fromPoint, Point toPoint) {
        this.fromPoint = fromPoint;
        this.toPoint = toPoint;
    }

    public Point getFromPoint() {
        return fromPoint;
    }

    public Point getToPoint() {
        return toPoint;
    }

    public boolean isToRight() {
        return fromPoint.getX() < toPoint.getX();
    }
}
