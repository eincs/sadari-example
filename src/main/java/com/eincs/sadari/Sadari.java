package com.eincs.sadari;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.TreeMap;

import com.google.common.collect.Lists;

/**
 * 사다리 자료구조. {@link SadariBuilder}를 이용하여 만들어낼 수 있다.
 */
public final class Sadari {
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

    /**
     * 특정 라인에서 끝 점까지 가기 위한 길에 대한 정보를 리턴한다.
     *
     * @param startingLine 시작할 라인
     * @return 주어진 라인에서 시작 부터 끝까지 사다리타고 내려가는 길
     */
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

    /**
     * 특정 사다리의 포인트에서 내려가다가 만나는 브릿지 정보
     *
     * @param point 사다리 상의 특정 포인트
     * @return 주어진 포인트에서 내려가다가 처음으로 만나는 브릿지. 만나는 브릿지가 없다면 null을 리턴한다.
     */
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

    /**
     * 사다리를 문자열로 그려낸다.
     *
     * @return 사다리를 표현하는 문자열
     */
    public String draw() {
        return SadariHelper.draw(this);
    }
}
