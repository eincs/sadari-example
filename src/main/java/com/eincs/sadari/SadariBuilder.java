package com.eincs.sadari;

import java.util.List;
import java.util.Random;
import java.util.TreeMap;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 사다리를 만들어내기 위한 빌더
 */
public class SadariBuilder {
    private int height = 0;
    private int lineCount = 0;
    private List<BridgeInfo> bridgeInfos = Lists.newArrayList();

    /**
     * 사다리의 높이를 세팅한다.
     *
     * @param height 사다리의 높이
     * @return 본 {@link SadariBuilder} 인스턴스
     */
    public SadariBuilder height(int height) {
        Preconditions.checkArgument(height > 0, "height should be positive.");
        this.height = height;
        return this;
    }

    /**
     * 사다리의 라인 갯수를 설정한다.
     *
     * @param lineCount 사다리의 라인 갯수
     * @return 본 {@link SadariBuilder} 인스턴스
     */
    public SadariBuilder lineCount(int lineCount) {
        Preconditions.checkArgument(lineCount > 0, "line count should be positive.");
        this.lineCount = lineCount;
        return this;
    }

    /**
     * 새로운 브릿지를 사다리에 추가한다.
     * 사다리의 높이나 라인 수가 결정된 후에 이 메서드를 호출할 수 있다.
     *
     * @param fromLine 브릿지의 한쪽 한쪽 끝이 붙어있는 라인의 인덱스
     * @param toLine 브릿지의 한쪽 한쪽 끝이 붙어있는 라인의 인덱스
     * @param positionY 브릿지의 사다리 최상단으로부터의 거리
     * @throws IllegalArgumentException 새로운 브릿지가 추가할 수 있는 조건에 맞지 않는 경우
     * @return 본 {@link SadariBuilder} 인스턴스
     */
    public SadariBuilder addBridge(int fromLine, int toLine, int positionY) {
        Preconditions.checkArgument(lineCount > 0, "You should specify line count of ladder.");
        Preconditions.checkArgument(height > 0, "You should specify height of ladder.");
        Preconditions.checkArgument(positionY > 0, "positionY should be positive");
        Preconditions.checkArgument(positionY < height, "positionY should smaller than height");
        Preconditions.checkArgument(0 <= fromLine && fromLine < lineCount, "fromLine is out of range.");
        Preconditions.checkArgument(0 <= toLine && toLine < lineCount, "toLine is out of range.");
        Preconditions.checkArgument(fromLine != toLine, "fromLine and toLine should be different.");
        Preconditions.checkArgument(Math.abs(fromLine - toLine) == 1, "fromLine and toLine should be adjacent.");
        Preconditions.checkArgument(!hasConflictBridge(fromLine, toLine, positionY), "new bridge is conflict with existing bridge.");
        BridgeInfo bridgeInfo = new BridgeInfo();
        bridgeInfo.fromLine = fromLine;
        bridgeInfo.toLine = toLine;
        bridgeInfo.positionY = positionY;
        this.bridgeInfos.add(bridgeInfo);
        return this;
    }

    /**
     * 겹치는 브릿지가 생기면 안되므로 체크한다.
     *
     * <pre>
     * |        |        |        |
     * |--------|--------|        | <- 이런 경우가 있으면 안됨
     * |        |        |        |
     * |        |--------|        |
     * |        |        |--------|
     * |--------|        |        |
     * |        |--------|        |
     * |--------|        |        |
     * |--------|        |--------|
     * |        |--------|        |
     * |        |        |        |
     * </pre>
     */
    private boolean hasConflictBridge(int fromLine, int toLine, int positionY) {
        for (BridgeInfo bridgeInfo : bridgeInfos) {
            if (bridgeInfo.positionY == positionY) {
                if (bridgeInfo.fromLine == fromLine
                 || bridgeInfo.fromLine == toLine
                 || bridgeInfo.toLine == toLine
                 || bridgeInfo.toLine == fromLine) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 적당히 랜덤하게 사다리 브릿지들을 만들어 낸다.
     */
    public SadariBuilder generateBridgesRandomly() {
        final Random rand = new Random();
        int minBridgeCount = lineCount * 2;
        int creatinCount = rand.nextInt(lineCount * (height - 2)) + minBridgeCount;
        for (int i = 0; i < creatinCount; i++) {
            int retryCount = 10;
            while (retryCount-- > 0) {
                try {
                    int fromLine = rand.nextInt(lineCount);
                    int toLine = rand.nextInt(lineCount);
                    int positionY = rand.nextInt(height);
                    addBridge(fromLine, toLine, positionY);
                    // 만드는데 성공하면, 끝!
                    break;
                } catch (IllegalArgumentException e) {
                    // Precondition에 실패하면 재시도 한다.
                }
            }
        }
        return this;
    }

    /**
     * 사다리를 만든다.
     *
     * @return 설정 값으로 만들어낸 사다리의 인스턴스
     */
    public Sadari build() {
        TreeMap<Point, SadariBridge> bridges = Maps.newTreeMap();
        for (BridgeInfo bridgeInfo : bridgeInfos) {
            Point fromPoint = new Point(bridgeInfo.fromLine, bridgeInfo.positionY);
            Point toPoint = new Point(bridgeInfo.toLine, bridgeInfo.positionY);
            bridges.put(fromPoint, new SadariBridge(fromPoint, toPoint));
            bridges.put(toPoint, new SadariBridge(toPoint, fromPoint));
        }
        return new Sadari(height, lineCount, bridges);
    }

    private class BridgeInfo {
        int fromLine;
        int toLine;
        int positionY;
    }
}
