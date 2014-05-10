package com.eincs.sadari;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.TreeMap;

import com.google.common.collect.Lists;

public class Sadari {
    private final int height;
    private final int lineCount;
    private final TreeMap<Point, SadariBridge> bridges;

    Sadari(int height, int lineCount, TreeMap<Point, SadariBridge> bridges) {
        this.height = height;
        this.lineCount = lineCount;
        this.bridges = bridges;
    }

    public int getHeight() {
        return height;
    }

    public int getLineCount() {
        return lineCount;
    }

    public TreeMap<Point, SadariBridge> getBridges() {
        return bridges;
    }

    public List<Point> getPath(int startingLine) {
        List<Point> path = Lists.newArrayList();

        Point startingPoint = new Point(startingLine, 0);
        path.add(startingPoint);

        Point currentPoint = startingPoint;
        SadariBridge currentBridge = null;
        while ((currentBridge = nextBridge(currentPoint)) != null) {
            path.add(currentBridge.getFromPoint());
            path.add(currentBridge.getToPoint());
            currentPoint = currentBridge.getToPoint();
        }
        return path;
    }

    public SadariBridge nextBridge(Point point) {
        try {
            Point startPoint = point;
            Point endPoint = new Point(point.getX(), Integer.MAX_VALUE);
            Point nextBridgeFromPoint = bridges.subMap(startPoint, false, endPoint, false).firstKey();
            return bridges.get(nextBridgeFromPoint);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public String draw() {
        return new SadariDrawer().draw(this);
    }
}
