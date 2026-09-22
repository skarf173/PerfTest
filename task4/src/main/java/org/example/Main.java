//package org.example;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File(args[0]));

        ArrayList<Integer> nums = new ArrayList<>();
        while (sc.hasNextInt()) {
            nums.add(sc.nextInt());
        }
        sc.close();

        Collections.sort(nums);

        int mid = nums.get(nums.size() / 2);

        int moves = 0;
        for (int n : nums) {
            moves += Math.abs(n - mid);
        }

        if (moves > 20) {
            System.out.println("20 ходов недостаточно для приведения всех элементов массива к одному числу");
        } else {
            System.out.println(moves);
        }
    }
}