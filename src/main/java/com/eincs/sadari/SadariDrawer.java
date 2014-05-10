package com.eincs.sadari;

import java.util.TreeMap;

public class SadariDrawer {

    public String draw(Sadari sadari) {
        int height = sadari.getHeight();
        int lineCount = sadari.getLineCount();
        TreeMap<Point, SadariBridge> bridges = sadari.getBridges();
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height + 1; y++) {
            StringBuilder line = new StringBuilder();
            for (int lineNumber = 0; lineNumber < lineCount; lineNumber++) {
                line.append("|");
                boolean hasBridge = hasToRightBridge(new Point(lineNumber, y), bridges);
                if (hasBridge) {
                    line.append("--------");
                } else {
                    line.append("        ");
                }
            }
            sb.append(line.toString().trim());
            sb.append("\n");
        }
        return sb.toString();
    }

    private boolean hasToRightBridge(Point startPoint, TreeMap<Point, SadariBridge> bridges) {
        boolean hasCandidateBridge = bridges.containsKey(startPoint);
        if (!hasCandidateBridge) {
            return false;
        }
        SadariBridge bridge = bridges.get(startPoint);
        return bridge.isToRight();
    }
}
