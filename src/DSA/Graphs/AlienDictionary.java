package DSA.Graphs;

import java.util.*;

public class AlienDictionary {
    public String alienOrder(String[] words) {
        return alienOrderUtil(words);
    }

    private String alienOrderUtil(String[] words) {
        Map<Character, Set<Character>> adjMap = new HashMap<>();
        Map<Character, Integer> indegrees = new HashMap<>();

        boolean isValid = createAdjMap(words, adjMap, indegrees);
        if (!isValid) return "";

        int numLetters = adjMap.size();

        Queue<Character> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for (Character letter: indegrees.keySet()) {
            if (indegrees.get(letter) == 0) {
                queue.offer(letter);
            }
        }


        while (!queue.isEmpty()) {
            char letter = queue.poll();
            sb.append(letter);

            for (char neighbour: adjMap.get(letter)) {
                indegrees.compute(neighbour, (k,v) -> {
                    return v-1;
                });

                if (indegrees.get(neighbour) == 0) {
                    queue.offer(neighbour);
                }
            }
        }

        if (sb.length() == numLetters) {
            return sb.toString();
        } else {
            return "";
        }
    }

    private boolean createAdjMap(String[] words, Map<Character, Set<Character>> adjMap, Map<Character, Integer>  indegrees) {
        int W = words.length;
        boolean isValid = true;

        for (int i = 0; i < W; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                adjMap.put(words[i].charAt(j), new HashSet<>());
                indegrees.put(words[i].charAt(j), 0);
            }
        }

        for (int i = 0; i < W-1; i++) {
            isValid = compare(words[i], words[i+1], adjMap, indegrees);
            if (!isValid) return false;
        }

        return true;
    }

    private boolean compare(String w1, String w2, Map<Character, Set<Character>> adjMap, Map<Character, Integer> indegrees) {

        // abcd should not come before abc. Violates rule of lexicographic sort.
        if (w1.length() > w2.length() && w1.startsWith(w2)) {
            return false;
        }

        int i = 0;
        int j = 0;

        while (i < w1.length() && j < w2.length() && w1.charAt(i) == w2.charAt(j)) {
            i++;
            j++;
        }

        if (i >= w1.length() || j >= w2.length()) return true;

        Set<Character> neighbours = adjMap.get(w1.charAt(i));
        if (!neighbours.contains(w2.charAt(j))) {
            adjMap.get(w1.charAt(i)).add(w2.charAt(j));
            indegrees.compute(w2.charAt(j), (k,v) -> {
                if (v == null) return 1;
                else return v + 1;
            });
        }

        return true;

    }
}
