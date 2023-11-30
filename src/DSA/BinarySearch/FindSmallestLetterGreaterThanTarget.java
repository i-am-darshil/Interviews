package DSA.BinarySearch;

// https://leetcode.com/problems/find-smallest-letter-greater-than-target/description/
public class FindSmallestLetterGreaterThanTarget {
    public char nextGreatestLetter(char[] letters, char target) {
        return nextGreatestLetterUtil2(letters, target);
    }

    private char nextGreatestLetterUtil(char[] letters, char target) {
        char ans = letters[0];

        int start = 0;
        int end = letters.length - 1;
        int mid;

        while (start <= end) {
            mid = start + (end-start)/2;

            if (letters[mid] > target) {
                ans = letters[mid];
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return ans;
    }

    private char nextGreatestLetterUtil2(char[] letters, char target) {

        if (target >= letters[letters.length-1]) return letters[0];

        int start = 0;
        int end = letters.length - 1;
        int mid;

        while (start <= end) {
            mid = start + (end-start)/2;

            if (letters[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return letters[start];
    }
}
