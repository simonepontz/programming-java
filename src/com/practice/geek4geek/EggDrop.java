package com.practice.geek4geek;

public class EggDrop {

    public int minTry(int egg, int floors) {
        if(egg == 1) {
            return floors;
        }
        if(floors <= 0 || floors == 1) {
            return floors;
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < floors; i++) {
            min = Math.min(Math.max(minTry(egg - 1 , i - 1), minTry(egg, floors - i)), min);
        }
        return min + 1;
    }

    public static void main(String[] args) {
        int attempt = new EggDrop().minTry(2, 10);
        System.out.println(String.format(" Attempt number: %d", attempt));
    }
}
