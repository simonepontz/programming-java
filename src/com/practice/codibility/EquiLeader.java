package com.practice.codibility;

import java.util.Optional;

public class EquiLeader {
    public static void main(String[] args) {
        int[] a = {4, 3, 4, 4, 4, 2};
        int solution = new EquiLeader().solution(a);
        System.out.println(solution);
    }

    private class Leader {
        Integer value;
        Integer amount;
        public Leader(Integer value, Integer amount) {
            this.value = value;
            this.amount = amount;
        }
    }

    private boolean isLeader(int count, int size) {
        return count > size / 2.0;
    }

    private Optional<Leader> globalLeader(int[] a) {
        int topOfStack = a[0];
        int stackSize = 1;
        for(int i = 1; i < a.length; i ++) {
            if(stackSize > 0) {
                if(topOfStack == a[i]) stackSize += 1;
                else                   stackSize -= 1;
            } else {
                stackSize = 1;
                topOfStack = a[i];
            }
        }
        if (stackSize == 0) return Optional.empty();
        int leaderCount = 0;
        for(int i = 0; i < a.length; i ++) {
            if(a[i] == topOfStack)
                leaderCount += 1;
        }
        if(isLeader(leaderCount, a.length)) {
            return Optional.of(new Leader(topOfStack, leaderCount));
        } else {
            return Optional.empty();
        }
    }

    private int equiliaderCount(int[] a, Leader gl) {
        int leftCount  = 0;
        int rightCount = gl.amount;
        int elCount = 0;
        for(int i = 0; i < a.length - 1; i++) {
            if(a[i] == gl.value) {
                leftCount += 1;
                rightCount -= 1;
            }
            if(isLeader(leftCount, i + 1) && isLeader(rightCount, a.length - i - 1))
                elCount += 1;
        }
        return elCount;
    }

    public int solution(int[] A) {
        Optional<Leader> leader = globalLeader(A);
        return leader.map(leader1 -> equiliaderCount(A, leader1)).orElse(0);
    }
}