package DSA.Sorting;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[] {3, 2, 4, 1, 7};
        quickSort(arr);
        for (int el: arr) {
            System.out.print(el + " ");
        }
    }

    static void quickSort(int[] arr) {
        if (arr.length == 1) return;
        quickSortUtility(arr, 0, arr.length);
    }

    static void quickSortUtility(int[] arr, int start, int end) {
        if (start >= end - 1) return;
        int pivot = getPivot(arr, start, end);
        quickSortUtility(arr, start, pivot);
        quickSortUtility(arr, pivot + 1, end);
    }

    static int getPivot(int[] arr, int start, int end) {
        int pivotPos = start;
        int lastInd = end - 1;

        while (start <= lastInd) {
            if (arr[start] <= arr[pivotPos]) {
                start++;
            } else {
                swap(arr, start, lastInd);
                lastInd--;
            }
        }

        swap(arr, pivotPos, start-1);

        return start-1;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

