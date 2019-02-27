package com.practice.codibility;

import java.util.Stack;

public class Fish {
    private final int UP_FLOW = 0;
    private final int DOWN_FLOW = 1;

    public int solution(int[] A, int[] B) {
        class Frame {
            int flow;
            int size;

            public Frame(int flow, int size){
                this.flow = flow;
                this.size = size;
            }
        }
        Stack<Frame> upFlow = new Stack<>();
        Stack<Frame> downFlow = new Stack<>();
        for(int i = 0; i < A.length; i++) {
            upFlow.push(new Frame(B[i], A[i]));
        }

        int aliveFish = 0;

        while(upFlow.peek().flow == DOWN_FLOW)
        {
            upFlow.pop();
            aliveFish++;
        }

        Frame flowingFish = upFlow.pop();
        while(true) {

            if((flowingFish.flow == UP_FLOW && upFlow.isEmpty())  || (flowingFish.flow == DOWN_FLOW && downFlow.isEmpty())){
                aliveFish++;
                break;
            }

            Frame currentFish = upFlow.pop();
            if(currentFish.flow != flowingFish.flow && flowingFish.size < currentFish.size) {
                flowingFish = currentFish;
            }

        }

        return 0;
    }

    /**
     *   A[0] = 4    B[0] = 0
     *   A[1] = 3    B[1] = 1
     *   A[2] = 2    B[2] = 0
     *   A[3] = 1    B[3] = 0
     *   A[4] = 5    B[4] = 0
     * @param args
     */
    public static void main(String[] args) {
        int A[] = new int[5];
        A[0] = 4;
        A[1] = 3;
        A[2] = 2;
        A[3] = 1;
        A[4] = 5;

        int B[] = new int[5];
        B[0] = 0;
        B[1] = 1;
        B[2] = 0;
        B[3] = 0;
        B[4] = 0;
    }
}
