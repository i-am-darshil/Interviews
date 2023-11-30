package DSA.String;

// https://takeuforward.org/data-structure/check-if-a-string-is-a-subsequence-of-other/
public class CheckIfSubSequence {
    public static void main(String[] args) {
        String s1 = "tuf";
        String s2 = "takeuforward";

        int i=0;
        int j=0;

        while (i <= s1.length()-1 && j <=s2.length()-1) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }

        System.out.println(i==s1.length());
        System.out.println(CheckIfSubSequenceUtility(s1, s2, s1.length()-1, s2.length()-1));
    }

    public static boolean CheckIfSubSequenceUtility(String s1, String s2, int i, int j) {
        if (i < 0) {
            return true;
        }

        if (j < 0) return false;

        if (s1.charAt(i) == s2.charAt(j)) {
            return CheckIfSubSequenceUtility(s1, s2, i-1, j-1);
        }

        return CheckIfSubSequenceUtility(s1, s2, i, j-1);
    }


}
