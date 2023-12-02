package DSA.ImplementationSpecific;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/cinema-seat-allocation/description/
public class CinemaSeatAllocation {
    public int maxNumberOfFamiliesTLE(int n, int[][] reservedSeats) {

        int[] positionsToCheck = new int[] {2, 4, 6};

        Arrays.sort(reservedSeats, Comparator.comparing(e -> e, (e1, e2) -> {
            if (e1[0] == e2[0]) return e1[1] - e2[1];
            else return e1[0] - e2[0];
        }));

        int rowPointer = 0;
        int maxPossible = 0;

        for (int i = 1; i <= n; i++) {
            boolean possibleFrom2 = true;
            boolean possibleFrom4 = true;
            boolean possibleFrom6 = true;

            while (rowPointer < reservedSeats.length && reservedSeats[rowPointer][0] == i) {
                int label = reservedSeats[rowPointer][1];

                if (label >= 2 && label <= 5) {
                    possibleFrom2 = false;
                }
                if (label >= 4 && label <= 7) {
                    possibleFrom4 = false;
                }
                if (label >= 6 && label <= 9) {
                    possibleFrom6 = false;
                }
                rowPointer++;
            }

            if (possibleFrom2 && possibleFrom6) maxPossible += 2;
            else if (possibleFrom2 || possibleFrom4 || possibleFrom6) maxPossible += 1;
            // System.out.println(maxPossible);


        }

        return maxPossible;

    }

    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        Map<Integer, List<Integer>> rowToLabelListMap = new HashMap<>();

        for (int[] reservedSeat: reservedSeats) {
            int row = reservedSeat[0];
            int label = reservedSeat[1];

            rowToLabelListMap.computeIfAbsent(row, key -> new ArrayList<Integer>()).add(label);
        }

        int maxPossible = 2 * (n - rowToLabelListMap.size());

        for (int row: rowToLabelListMap.keySet()) {
            boolean possibleFrom2 = true;
            boolean possibleFrom4 = true;
            boolean possibleFrom6 = true;

            List<Integer> labels = rowToLabelListMap.getOrDefault(row, new ArrayList<Integer>());

            for (Integer label: labels) {
                if (label >= 2 && label <= 5) {
                    possibleFrom2 = false;
                }
                if (label >= 4 && label <= 7) {
                    possibleFrom4 = false;
                }
                if (label >= 6 && label <= 9) {
                    possibleFrom6 = false;
                }
            }

            if (possibleFrom2 && possibleFrom6) maxPossible += 2;
            else if (possibleFrom2 || possibleFrom4 || possibleFrom6) maxPossible += 1;
        }

        return maxPossible;

    }
}
