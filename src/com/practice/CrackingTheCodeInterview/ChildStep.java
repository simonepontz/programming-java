package com.practice.CrackingTheCodeInterview;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

public class ChildStep {

    public static BigInteger[] step;

    public BigInteger countStep(int n) {

        if(n == 0) return BigInteger.valueOf(1);
        if(n < 0) return BigInteger.valueOf(0);

        if(!Objects.equals(step[n], BigInteger.valueOf(-1))) return step[n];

        step[n] = countStep(n - 3).add(countStep(n-2)).add(countStep(n-1));
        return step[n];
    }

    public static void main(String[] args) {
        ChildStep.step = new BigInteger[51];
        Arrays.fill(ChildStep.step, BigInteger.valueOf(-1));
        System.out.println(new ChildStep().countStep(50));
    }
}
