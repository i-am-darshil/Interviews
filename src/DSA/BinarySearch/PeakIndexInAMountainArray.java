package DSA.BinarySearch;

public class PeakIndexInAMountainArray {
    public int peakIndexInMountainArray(int[] arr) {
        return peakIndexInMountainArrayUtil(arr);
    }

    private int peakIndexInMountainArrayUtil(int[] a) {
        int start = 0;
        int end = a.length - 1;
        int ans = -1;

        while (start <= end) {
            int mid = start + (end-start)/2;

            if (a[mid] > a[mid+1]) {
                ans = mid;
                end = mid-1;
            }
            else if (a[mid] < a[mid+1]) {
                start = mid + 1;
            }

        }

        return ans;
    }
}
