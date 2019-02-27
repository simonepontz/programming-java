package com.practice.codibility;

import java.util.Arrays;
import java.util.LinkedList;

public class SieveOfErastosthenes {

    /**
     *
     * @param n
     * @return
     */
    public Integer[] solution(int n) {
        boolean[] canceled = new boolean[n + 1];
        Arrays.fill(canceled, true);

        // initialization
        canceled[0] = canceled[1] = false;

        int elitionCounter = 2;
        while (elitionCounter * elitionCounter <= n) {
            if(canceled[elitionCounter]) {
                int j = elitionCounter * elitionCounter;
                while (j <= n) {
                    canceled[j] = false;
                    j += elitionCounter;
                }
            }
            elitionCounter += 1;
        }

        LinkedList<Integer> primeNumbers = new LinkedList<>();
        for(int i = 0; i < canceled.length; i++) {
            if(canceled[i])
                primeNumbers.add(i);
        }
        primeNumbers.addFirst(1);
        return primeNumbers.toArray(new Integer[0]);
    }

    public int[] makeFactor(int x) {
        int[] factors = new int[x +1];
        int factor = 2;
        factors[0] = factors[1] = 0;
        while (factor * factor <= x) {
            if(factors[factor] == 0) {
                int j = factor * factor;
                while (j <= x) {
                    if(factors[j] == 0) {
                        factors[j] = factor;
                    }
                    j += factor;
                }
            }
            factor += 1;
        }
        return factors;
    }

    public Integer[] factorization(int x) {
        int[] F = makeFactor(x);
        LinkedList<Integer> primeFactor = new LinkedList<>();
        while(F[x] > 0) {
            primeFactor.add(F[x]);
            x /= F[x];
        }
        primeFactor.add(x);
        return primeFactor.toArray(new Integer[0]);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SieveOfErastosthenes().solution(13)));

        System.out.println(Arrays.toString(new SieveOfErastosthenes().factorization(12)));
    }
}
