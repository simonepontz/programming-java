package test.aws;

import java.util.Arrays;

public class e1 {
    public static void main(String[] args) {
        // int[] A = {9,4,2,10,7,8,8,1,9};

        int[] A = {9,9,9};

        int solution = new e1().solution(A);
        System.out.println(solution);
    }

    /**
     * Is requested to calcculate rurbolence period (the max one)
     *
     * A turbolence period is where there is a tiple A[i] < A[i+1] > A[i+2]
     *
     *
     * With some special cases
     * - A.length = 1 is a turbolence return 1
     * - A.length = 2 is a turbolece if they have opposite sign.
     *
     * I will calculate the sign of the turbolence and count consecutive change in sign
     *
     * When I have the same sign I reset the counter
     *
     * @param A
     * @return
     */
    public int solution(int[] A) {
        // First handle the empy array that is a turbolence instance
        if(A.length == 1) return 1;
        int count = 1;
        int maxTurbolence = 1;

        int predSign = 0;

        for(int i = 0; i < A.length -1 ; i++) {
            int cSign = (int)Math.signum(A[i + 1] - A[i]);
            if(cSign != predSign || A[i + 1] == A[i]) {
                count += 1;
                predSign = cSign;
            } else {
                count = 1;
            }
        }
        maxTurbolence = Integer.max(maxTurbolence, count);

        return maxTurbolence;
    }

}
