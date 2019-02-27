package com.practice.codibility;

import java.util.*;

public class NestedBrackets {

    /**
     * This maps is used to keep track of matching opening and closing simbols
     */
    private Map<Character, Character> bracketPair = new HashMap<>();
    private Set<Character> validOpening = new HashSet<>();
    private Set<Character> validClosing = new HashSet<>();

    private Stack<Character> record = new Stack<>();

    /**
     * bracketPair is initialized with a set of matching simbols, this operation
     * could be done reading from conf file, from an API. For this example is fixed
     * but if I would have implemented a library this poart has to be generalized.
     */
    public NestedBrackets() {
        bracketPair.put('[', '}');
        bracketPair.put('(', ')');
        bracketPair.put('{', '}');
        bracketPair.forEach((k, v) -> {
            validOpening.add(k);
            validClosing.add(v);
        });
    }


    private boolean verifyTop(Character bracket) {
        try {
            Character pop = record.pop();
            return pop.equals(bracket);
        } catch (EmptyStackException ex) {
            // the stack is empty so no open brackets to match
            return false;
        }
    }

    /**
     *
     * @param bracket is the bracket we want to add to the record stack
     * @return return false if is not allowed to but the bracket on the stack
     */
    private boolean addRecord(Character bracket) {
        if(bracketPair.containsKey(bracket)) {
            // is an accepted character in a real world environment I should check
            // for allowed characters
            record.push(bracketPair.get(bracket));
            return true;
        }
        // ignore non brackets character
        return false;
    }

    private boolean isOpening(Character bracket) {
        return validOpening.contains(bracket);
    }

    private boolean isClosing(Character bracket) {
        return validClosing.contains(bracket);
    }

    public int solution(String S) {
        for (char bracket : S.toCharArray()) {
            if(isOpening(bracket)) {
                addRecord(bracket);
            } else if(isClosing(bracket)) {
                if(!verifyTop(bracket)) return 0;
            }
        }
        return record.isEmpty() ? 1 : 0;
    }

    public static void main(String[] args) {
        String par = "{(a})";
        int solution = new NestedBrackets().solution(par);
        System.out.println(solution);
    }
}
