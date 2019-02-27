package com.theory.sorting;

import java.util.Arrays;
import java.util.Collection;

public class SortUtil {
    public static void quickSort(Integer[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(Integer[] array, int min, int max) {
        if(min >= max){
            return;
        }

        int pivot = array[(min + max) / 2];
        int partitionPoint = sortPartition(array, min, max, pivot);
        quickSort(array, min, partitionPoint -1);
        quickSort(array, partitionPoint , max);
    }

    private static void swap(Integer[] array, int first, int second) {
        int tmp = array[first];
        array[first] = array[second];
        array[second] = tmp;
    }

    private static int sortPartition(Integer[] array, int min, int max, Integer pivot) {
        while(min <= max) {
            while (array[min] < pivot) {
                min++;
            }

            while (array[max] > pivot) {
                max--;
            }
            if (max >= min) {
                swap(array, min, max);
                max--;
                min++;
            }
        }
        return min;
    }

    public static void mergeSort(Integer[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private static void mergeSort(Integer[] array, int left, int right) {
        if(left < right) {
            int m = left+(right-left)/2;
            mergeSort(array, left, m);
            mergeSort(array, m + 1 , right);
            merge(array, left, m, right);
        }
    }

    private static void merge(Integer[] array, int left, int mid, int right) {
            Integer[] tmp = new Integer[right - left + 1];
            int lI = left;
            int rI = mid + 1 ;

            int index = 0;
            while (lI <= mid && rI <= right) {
                if (array[lI] <= array[rI]) {
                    tmp[index] = array[lI];
                    lI++;
                }
                else {
                    tmp[index] = array[rI];
                    rI++;
                }
                index++;
            }
            while(lI <= mid) {
                tmp[index] = array[lI];
                lI++;
                index++;
            }
            while(rI <= right) {
                tmp[index] = array[rI];
                rI++;
                index++;
            }
            System.arraycopy(tmp, 0, array, left, tmp.length);
    }
}
