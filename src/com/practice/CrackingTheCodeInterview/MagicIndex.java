package com.practice.CrackingTheCodeInterview;

public class MagicIndex {

    public static int magicIndexUtil(int[] array, int low, int high) {
        if(low > high)
            return -1;
        int mid = (high - low) / 2 + low;

        if(array[mid] != mid) {
            int left = magicIndexUtil(array, Math.max(array[mid], mid + 1), high);
            int right = magicIndexUtil(array, low, Math.min(array[mid], mid - 1));
            return left != -1 ? left : right;
        }

        return mid;
    }

    public static int magicIndex(int[] array) {
        return magicIndexUtil(array, 0, array.length - 1);
    }

    public static void main(String[] args) {
        int[] array = {5, 5, 5, 5, 5, 5};
        System.out.println(magicIndex(array));
    }
}
