package com.practice.personal;

import java.util.HashMap;
import java.util.Map;

public class BitMasking {

    public static void main(String[] args) {
        Integer value = -9;
        System.out.println(value);
        Integer valuePair = (value & ~(1<<0));
        Map a = new HashMap();
        System.out.println(valuePair);
    }
}