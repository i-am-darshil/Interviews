package DSA.DP.Generic;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/soup-servings/description/
public class SoupServings {
    public double soupServings(int n) {
        Map<String, Double> serveCombToProbMap = new HashMap<>();
        return soupServingsUtil(n, n, serveCombToProbMap);
    }

    private double soupServingsUtil(int aRem, int bRem, Map<String, Double> serveCombToProbMap) {
        String key = aRem + "," + bRem;
        if (aRem == 0 && bRem == 0) {
            serveCombToProbMap.put(key, 0.5);
            return 0.5;
        } else if (aRem == 0) {
            serveCombToProbMap.put(key, 1.0);
            return 1;
        } else if (bRem == 0) {
            serveCombToProbMap.put(key, 0.0);
            return 0;
        }

        if (serveCombToProbMap.containsKey(key)) {
            return serveCombToProbMap.get(key);
        }

        // Serve 100 ml of soup A and 0 ml of soup B,
        double p1 = soupServingsUtil(Math.max(aRem - 100, 0), bRem, serveCombToProbMap);

        // Serve 75 ml of soup A and 25 ml of soup B,
        double p2 = soupServingsUtil(Math.max(aRem - 75, 0), Math.max(bRem - 25, 0), serveCombToProbMap);

        // Serve 50 ml of soup A and 50 ml of soup B, and
        double p3 = soupServingsUtil(Math.max(aRem - 50, 0), Math.max(bRem - 50, 0), serveCombToProbMap);

        // Serve 25 ml of soup A and 75 ml of soup B.
        double p4 = soupServingsUtil(Math.max(aRem - 25, 0), Math.max(bRem - 75, 0), serveCombToProbMap);

        double p = (p1 + p2 + p3 + p4) / 4.0;
        serveCombToProbMap.put(key, p);

        return p;

    }
}
