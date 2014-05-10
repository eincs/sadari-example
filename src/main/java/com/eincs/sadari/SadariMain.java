package com.eincs.sadari;

import java.util.List;

public final class SadariMain {

    public static void main(String[] args) {
        Sadari sadari = new SadariBuilder()
                .height(10)
                .lineCount(4)
                .addRandomBridges().build();
        System.out.println(sadari.draw());

        List<Point> path = sadari.getPath(1);
        for (Point point : path) {
            System.out.println(point);
        }
    }
}
