package com.eincs.sadari;

import java.util.List;

public final class SadariMain {

    public static void main(String[] args) {
        Sadari sadari = new SadariBuilder()
                .height(10)
                .lineCount(4)
                .generateBridgesRandomly()
                .build();

        System.out.println("Randomly Generated Ladder:");
        System.out.println(sadari.draw());

        System.out.println("Path of First Line:");
        List<Point> path = sadari.getPath(0);
        for (Point point : path) {
            System.out.println(point);
        }
    }
}
