package DSA.Recursion;

import java.util.LinkedList;
import java.util.List;

/*
https://leetcode.com/problems/strobogrammatic-number-ii/description/
Given an integer n, return all the strobogrammatic numbers that are of length n. You may return the answer in any order.

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Example 1:

Input: n = 2
Output: ["11","69","88","96"]
Example 2:

Input: n = 1
Output: ["0","1","8"]
 */
public class StrobogrammaticNumberII {
    public List<String> findStrobogrammatic(int n) {
        // 0, 1, 8
        // 11, 88, 69, 96
        // 101, 111, 181, 808, 818, 888, 609, 619, 689, 906, 916, 986

        List<String> combs = new LinkedList<>();

        getCombinations(0, n-1, new char[n], combs, new char[] {'0', '1', '8'});

        return combs;
    }

    private void getCombinations(int start, int end, char[] comb, List<String> combs, char[] options) {
        if (start > end) {
            combs.add(new String(comb));
            return;
        }

        if (end - start >= 1) {
            comb[start] = '6';
            comb[end] = '9';
            getCombinations(start+1, end-1, comb, combs, options);

            comb[start] = '9';
            comb[end] = '6';
            getCombinations(start+1, end-1, comb, combs, options);

            for (int i = start == 0 ? 1 : 0; i < options.length; i++) {
                comb[start] = options[i];
                comb[end] = options[i];
                getCombinations(start+1, end-1, comb, combs, options);
            }
        } else {
            for (int i = 0; i < options.length; i++) {
                comb[start] = options[i];
                getCombinations(start+1, end, comb, combs, options);
            }
        }
    }
}
