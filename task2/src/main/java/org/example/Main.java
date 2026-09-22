//package org.example;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner ellipse = new Scanner(new File(args[0]));
        double x0 = ellipse.nextDouble();
        double y0 = ellipse.nextDouble();
        double a = ellipse.nextDouble();
        double b = ellipse.nextDouble();
        ellipse.close();

        Scanner points = new Scanner(new File(args[1]));
        while (points.hasNextDouble()) {
            double x = points.nextDouble();
            double y = points.nextDouble();

            double dx = (x - x0) / a;
            double dy = (y - y0) / b;
            double value = dx * dx + dy * dy;

            if (value == 1.0) {
                System.out.println("0");
            } else if (value < 1.0) {
                System.out.println("1");
            } else {
                System.out.println("2");
            }
        }
        points.close();
    }
}