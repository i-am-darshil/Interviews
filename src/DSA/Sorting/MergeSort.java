package DSA.Sorting;

import java.util.*;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[] {2, 5, 1, 8, 2, -7};
        mergeSort(arr);
        for (int el: arr) {
            System.out.print(el + " ");
        }
    }

    static void mergeSort(int[] arr) {
        mergeSortUtility(arr, 0, arr.length);
    }

    static void mergeSortUtility(int[] arr, int start, int end) {

        if (start >= end - 1) return;

        int mid = (start + end) / 2;

        mergeSortUtility(arr, start, mid);
        mergeSortUtility(arr, mid, end);

        merge(arr, start, mid, end);
    }

    static void merge(int[] arr, int start, int mid, int end) {

        int[] temp = new int[end-start];

        int i = start; // 3 -> 6
        int j = mid;
        int k = 0;

        while (i < mid && j < end) {
            if (arr[i] < arr[j]) {
                temp[k] = arr[i];
                i++;
                k++;
            } else {
                temp[k] = arr[j];
                j++;
                k++;
            }
        }

        while (i < mid) {
            temp[k] = arr[i];
            i++;
            k++;
        }

        while (j < end) {
            temp[k] = arr[j];
            j++;
            k++;
        }

        for (int ind=start; ind<end; ind++) {
            arr[ind] = temp[ind - start];
        }
    }
}
