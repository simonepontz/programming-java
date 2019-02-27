package com.practice.codibility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GenomicRangeQuery {
    public static void main(String[] args) {
        int[] p = {2,5,0};
        int[] q = {4,5,6};

        //int[] solution = new GenomicRangeQuery().solution("CAGCCTA", p, q);

        int[] pp = {0};
        int[] qq = {1};
        int[] solution1 = new GenomicRangeQuery().solution("TT", pp, qq);
        System.out.println(Arrays.toString(solution1));
    }

    private int[][] buildIndex(String s, char[] genomeImpactFactor) {
        int[][] genomeIndex = new int[genomeImpactFactor.length][s.length() + 1];
        Map<Character, Integer> evaluator = new HashMap<>();
        for(int i = 0; i < genomeImpactFactor.length; i++) {
            evaluator.put(genomeImpactFactor[i], i);
        }

        char[] genomes = s.toCharArray();

        for(int i = 0; i < genomes.length; i++) {
            int gi = evaluator.get(genomes[i]);

            for(int g = 0; g < genomeImpactFactor.length; g ++)
                if(g != gi) genomeIndex[g][i + 1] = genomeIndex[g][i];
            genomeIndex[gi][i + 1] = genomeIndex[gi][i] + 1;
        }
        return genomeIndex;
    }

    public int[] solution(String S, int[] P, int[] Q) {
        int[] factorInQuery = new int[P.length];

        char[] genomes = {'A', 'C', 'G', 'T'};

        int[][] genomeIdx = buildIndex(S, genomes);
        for(int i = 0; i < factorInQuery.length; i++) {
            int p = P[i];
            int q = Q[i] + 1;
            int minimalImpact = Integer.MAX_VALUE;
            for (int g = 0; g < genomes.length; g++) {

                if (genomeIdx[g][q] - genomeIdx[g][p] > 0) {
                    minimalImpact = Integer.min(minimalImpact, g + 1);
                }

            }
            factorInQuery[i] = minimalImpact;
        }

        return factorInQuery;
    }
}
