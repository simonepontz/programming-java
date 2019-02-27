package test.aws;

import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class e2 {
    public static void main(String[] args) {
        String s = "ACCAABBBCAACBA";
        String solution = new e2().solution(s);
        System.out.println(solution);
    }

    private List<Character> scan(List<Character> characters) {
        if(characters.size() < 2) return characters;

        if(characters.get(0) == characters.get(1)) {
            return scan(characters.subList(2, characters.size()));
        }

        List<Character> sliced = scan(characters.subList(1, characters.size()));

        // check for collapse if not collapsed first two
        if(sliced.size() > 0 && sliced.get(0) == characters.get(0)) {
            return sliced.subList(1, sliced.size());
        }
        if(sliced.isEmpty()) {
            return Collections.emptyList();
        } else {
            // is character on the pile and start of string are not equals return them.
            List<Character> concatenatedList = new ArrayList<>(sliced.size() + 1);
            // this is needed for the first part
            concatenatedList.add(characters.get(0));
            // "ACBBCB"; this is needed for string that have a look ahead that can be collapsed to return.
            concatenatedList.addAll(sliced);
            return concatenatedList;
        }
    }

    // probably I will try to use a better library then implementing myself
    String asString(List<Character> list)
    {
        StringBuilder builder = new StringBuilder(list.size());
        for(Character ch: list)
        {
            builder.append(ch);
        }
        return builder.toString();
    }

    /**
     *
     * Solution 1:
     * The first solution is a brute forcing one and require to code
     * exactly what is written in the task02. Scan the string for dupplicates
     * and remove them. Then restart from the beginning.
     * The problem is that for a string of N this can require to scan it N times in case of a
     * dyck language string (parentesis). ex: "ABCAB..BACBA".
     *
     * A better solution could be found with a consideration:
     * deletion happens when I delete a pair and other pair touch.
     *
     * I must not start from the beginning but I can keep on removing the next part of the string and then turn back
     * on the point I've deleted to check if there is a deletion.
     *
     * I can implement the elition in place, but will require a lot of rewriting of the array and lot of corner case
     *
     * I will implement a recursive ones with some base case.
     *
     * @param S
     * @return
     */
    public String solution(String S) {
        char[] chars = S.toCharArray();
        List<Character> charString = new ArrayList<>(chars.length);
        for (char c : chars) {
            charString.add(c);
        }

        // I will use null since won't collide with any character
        return asString(scan(charString));
    }
}
