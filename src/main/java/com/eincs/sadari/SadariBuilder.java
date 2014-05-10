package com.eincs.sadari;

import java.util.List;
import java.util.Random;
import java.util.TreeMap;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class SadariBuilder {
    private int height = 0;
    private int lineCount = 0;
    private List<BridgeInfo> bridgeInfos = Lists.newArrayList();

    public SadariBuilder height(int height) {
        Preconditions.checkArgument(height > 0);
        this.height = height;
        return this;
    }

    public SadariBuilder lineCount(int lineCount) {
        Preconditions.checkArgument(lineCount > 0);
        this.lineCount = lineCount;
        return this;
    }

    public SadariBuilder addBridge(int fromLine, int toLine, int positionY) {
        Preconditions.checkArgument(positionY > 0);
        Preconditions.checkArgument(positionY < height);
        Preconditions.checkArgument(0 <= fromLine && fromLine < lineCount);
        Preconditions.checkArgument(0 <= toLine && toLine < lineCount);
        Preconditions.checkArgument(fromLine != toLine);
        Preconditions.checkArgument(Math.abs(fromLine - toLine) == 1);
        Preconditions.checkArgument(!hasConflictBridge(fromLine, toLine, positionY));
        BridgeInfo bridgeInfo = new BridgeInfo();
        bridgeInfo.fromLine = fromLine;
        bridgeInfo.toLine = toLine;
        bridgeInfo.positionY = positionY;
        this.bridgeInfos.add(bridgeInfo);
        return this;
    }

    /**
     * ???????????? ????????? ????????? ?????? ????????? ???????????? ?????? ????????????.
     *
     * <pre>
     * |        |        |        |
     * |--------|--------|        | <- ?????? ?????? ????????? ?????????!
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

    public SadariBuilder addRandomBridges(int bridgeCount) {
        final Random rand = new Random();
        for (int i = 0; i < bridgeCount; i++) {
            while (true) {
                try {
                    int fromLine = rand.nextInt(lineCount);
                    int toLine = rand.nextInt(lineCount);
                    int positionY = rand.nextInt(height);
                    addBridge(fromLine, toLine, positionY);
                    // ???????????? ??????????????? ???!
                    break;
                } catch (IllegalArgumentException e) {
                    // Precondition??? ?????? ????????? ?????? ????????????.
                }
            }
        }
        return this;
    }

    public Sadari build() {
        TreeMap<Point, SadariBridge> bridges = Maps.newTreeMap();
        for(BridgeInfo bridgeInfo : bridgeInfos) {
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
