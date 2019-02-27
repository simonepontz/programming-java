package com.theory.hashing;

public class RabinKarb {
    private final static long R = 128;

    public static long makeHash(String s) {
        char[] token = s.toCharArray();
        long hash = 0;
        for(int i = 0 ; i < token.length; i++) {
            hash = ((int)token[i] + hash * R) % 31;
        }
        return hash;
    }

    public static Long updateHash(long hash, char tail, char head, int n) {
        hash = hash - head * (long)Math.pow(R, n);
        hash = tail + hash * R;
        return hash;
    }

    public static boolean check(String pattern, String text, int position) {
        return text.substring(position, position + pattern.length()).equals(pattern);
    }

    public static int search(String substring, String text) {
        if(substring.length() > text.length())
            return -1;

        long subHash = makeHash(substring);
        long rollingHash = makeHash(text.substring(0,substring.length()));

        if(subHash == rollingHash && check(substring, text, 0))
            return 0;

        for(int i = 0; i < (text.length() - substring.length()); i++) {
            rollingHash = updateHash(rollingHash, text.charAt(i + substring.length()), text.charAt(i), substring.length() - 1);
            if(subHash == rollingHash && check(substring, text, i + 1))
                return i + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(search("L", "ciao"));
    }
}
