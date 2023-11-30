package DSA.Graphs.ShortestPathDistFromSource;

import java.util.*;

// https://leetcode.com/problems/word-ladder/description/

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // return ladderLengthUtil(beginWord, endWord, wordList);
        return ladderLengthUtilApproach2(beginWord, endWord, wordList);
    }

    private int ladderLengthUtilApproach2(String beginWord, String endWord, List<String> wordList) {

        Set<String> set = new HashSet<>();

        for (int i =0; i < wordList.size(); i++) {
            set.add(wordList.get(i));
        }

        if (!set.contains(endWord)) return 0;

        set.remove(beginWord);

        Queue<String> queue = new LinkedList<>();

        queue.offer(beginWord);

        int seqLength = 1;

        while (!queue.isEmpty()) {

            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++){
                String currWord = queue.poll();

                for (int ind = 0; ind < currWord.length(); ind++) {
                    for (int j = 0; j < 26; j++) {
                        char charReplacement = (char) ('a' + j);
                        String newS = currWord.substring(0, ind) + charReplacement + currWord.substring(ind+1, currWord.length());



                        if (set.contains(newS)) {
                            if (newS.equals(endWord)) return seqLength+1;
                            queue.offer(newS);
                            set.remove(newS);
                        }
                    }
                }


            }

            seqLength++;


        }

        return 0;
    }



    private int ladderLengthUtil(String beginWord, String endWord, List<String> wordList) {

        Map<String, List<String>> adjMap = new HashMap<>();

        generateAdjMap(beginWord, wordList, adjMap);

        if (!adjMap.containsKey(endWord)) return 0;

        Map<String, Integer> dist = new HashMap<>();

        dist.put(beginWord, 0);

        for (int i = 0; i < wordList.size(); i++) {
            if (beginWord.equals(wordList.get(i))) continue;
            dist.put(wordList.get(i), (int) Math.pow(10, 9));
        }

        Queue<String> queue = new PriorityQueue<>();

        queue.offer(beginWord);

        while (!queue.isEmpty()) {
            String node = queue.poll();
            for (String neighbour: adjMap.get(node)) {
                if (dist.get(node)+ 1 < dist.get(neighbour)) {
                    dist.put(neighbour, dist.get(node) + 1);
                    queue.offer(neighbour);
                }
            }
        }

        if (dist.get(endWord) >= (int) Math.pow(10, 9)) {
            return 0;
        } else {
            return dist.get(endWord) + 1;
        }

    }


    private void generateAdjMap(String beginWord, List<String> wordList, Map<String, List<String>> adjMap) {
        adjMap.put(beginWord, new ArrayList<>());


        for (int i = 0; i < wordList.size(); i++) {
            adjMap.put(wordList.get(i), new ArrayList<>());
        }

        for (int i = 0; i < wordList.size(); i++) {
            if (beginWord.equals(wordList.get(i))) {
                continue;
            }
            boolean isAdj = isAdjWords(beginWord, wordList.get(i), 1);
            if (isAdj) {
                adjMap.get(beginWord).add(wordList.get(i));
                adjMap.get(wordList.get(i)).add(beginWord);
            }
        }

        for (int i = 0; i < wordList.size()-1; i++) {
            if (beginWord.equals(wordList.get(i))) continue;
            for (int j = i+1; j < wordList.size(); j++) {
                if (beginWord.equals(wordList.get(j))) continue;
                boolean isAdj = isAdjWords(wordList.get(i), wordList.get(j), 1);
                if (isAdj) {
                    adjMap.get(wordList.get(i)).add(wordList.get(j));
                    adjMap.get(wordList.get(j)).add(wordList.get(i));
                }
            }
        }
    }

    private boolean isAdjWords(String w1, String w2, int diffAllowed) {
        int i = 0;
        int diff = 0;
        while ( i < w1.length() ) {
            if (w1.charAt(i) != w2.charAt(i)) {
                diff++;
                if (diff > diffAllowed) return false;
            }
            i++;
        }
        return true;
    }
}
