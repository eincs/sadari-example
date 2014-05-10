package com.eincs.sadari;

import java.util.TreeMap;

public final class SadariHelper {
    private SadariHelper() {}

    /**
     * 주어진 사다리를 다음과 같은 형태의 문자열 표현으로 만들어내는 메서드.
     * 주어진 사다리의 각 라인을 따라가면서 각 좌표에서 오른쪽으로 브릿지를 뻗어야하는지 검사하여 문자열을 만들어낸다.
     * 별로 효율적이진 않다.
     *
     * <pre>
     * |        |        |        |
     * |        |        |--------|
     * |--------|        |--------|
     * |        |        |--------|
     * |        |--------|        |
     * |--------|        |--------|
     * |        |        |        |
     * |--------|        |--------|
     * |        |--------|        |
     * |--------|        |--------|
     * |        |        |        |
     * </pre>
     */
    public static String draw(Sadari sadari) {
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

    private static boolean hasToRightBridge(Point startPoint, TreeMap<Point, SadariBridge> bridges) {
        boolean hasCandidateBridge = bridges.containsKey(startPoint);
        if (!hasCandidateBridge) {
            return false;
        }
        SadariBridge bridge = bridges.get(startPoint);
        return bridge.isToRight();
    }
}
