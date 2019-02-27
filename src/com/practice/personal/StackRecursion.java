package com.practice.personal;

import com.theory.list.Stack;

public class StackRecursion {

    private class Frame{
        Integer one;
        Integer two;
        Frame(Integer one, Integer two) {
            this.one = one;
            this.two = two;
        }
    }

    private Integer fibonacci(Integer n) {
        final Stack<Frame> callStack = new Stack<>();
        callStack.push(new Frame(0, 1));

        for(int i = 0; i < n; i ++) {
            final Frame frame = callStack.pop();
            callStack.push(new Frame(frame.two, frame.two + frame.one));
        }
        return callStack.pop().two;
    }


    public static void main(String[] args) {
        final Integer fibonacci = new StackRecursion().fibonacci(5);
        System.out.println(fibonacci);
    }
}
