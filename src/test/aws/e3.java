package test.aws;

public class e3 {
    public static void main(String[] args) {
        int[] table = {0,0,0,0};
        System.out.println(new e3().solution(table));
    }

    /**
     * Using constant will boost the readability of the code and lets you focus on semantics.
     * It also protect from typos. Seeing the difference between HEAD and TAIL is more obvious then 0, 1
     */
    private static final int TAIL   = 1;
    private static final int HEAD = 0;

    private int countSymbol(int[] array, int desiredSymbol) {
        int symbols = 0;
        for (int symbol : array) {
            if(desiredSymbol == symbol)
                symbols += 1;
        }
        return symbols;
    }

    /**
     * Count the minimum number of swap to be performed to have an array with all the coin in the same direction
     * the solution is about counting for an arbitrary HEAD/TAIL the coins.
     * If there is less coin with TAIL swap them to HEAD, otherwise swap the TAIL.
     *
     * Is necessary to count only HEAD since the other can be obtained with the length of the array.
     * The min between #HEAD and LEN - #HEAD is the desired output
     * Corner case is the empy array, which i suppose that is 0 swap.
     *
     * note: Since the array size is [1..100] no need to care about overflow and performance.
     * This solution is O(N) where N is the number of coins
     *
     * @param A an array len [1..10] containing "coins" zero is face down and one is faceup
     * @return the minimum number of swap required.
     */
    public int solution(int[] A) {
        // the empty array means zero swap, and having one coin is zero swap
        if(A.length < 2) return 0;

        int numberOfHead = countSymbol(A, HEAD);
        // this also cover the case of all HEAD or all TAIL.
        int numberOfTail = A.length - numberOfHead;

        return Integer.min(numberOfHead, numberOfTail);
    }

}
