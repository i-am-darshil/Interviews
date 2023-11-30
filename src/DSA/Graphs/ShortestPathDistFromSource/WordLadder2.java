package DSA.Graphs.ShortestPathDistFromSource;

import java.util.*;

// https://leetcode.com/problems/word-ladder-ii/description/

public class WordLadder2 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        // My approach
        return findLaddersUtil(beginWord, endWord, wordList);

        // return ladderLengthUtilApproach2(beginWord, endWord, wordList);

    }

    // Gives TTL, this is striver's solution (https://www.youtube.com/watch?v=DREutrv2XD0&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=30)
    private List<List<String>> ladderLengthUtilApproach2(String beginWord, String endWord, List<String> wordList) {

        Set<String> set = new HashSet<>();

        for (int i =0; i < wordList.size(); i++) {
            set.add(wordList.get(i));
        }

        List<List<String>> ans = new LinkedList<>();

        if (!set.contains(endWord)) return ans;

        set.remove(beginWord);

        Queue<List<String>> queue = new LinkedList<>();

        queue.offer(new ArrayList<>(Arrays.asList(beginWord)));

        while (!queue.isEmpty()) {

            int levelSize = queue.size();

            List<String> wordsVisited = new ArrayList<>();

            boolean endWordFoundAtThisLevel = false;
            for (int i = 0; i < levelSize; i++){
                List<String> currSeq = queue.poll();
                String currWord = currSeq.get(currSeq.size() - 1);

                if (currWord.equals(endWord)) {
                    ans.add(currSeq);
                    endWordFoundAtThisLevel = true;
                    continue;
                }

                for (int ind = 0; ind < currWord.length(); ind++) {
                    for (int j = 0; j < 26; j++) {
                        char charReplacement = (char) ('a' + j);
                        String newS = currWord.substring(0, ind) + charReplacement + currWord.substring(ind+1, currWord.length());


                        if (set.contains(newS)) {
                            List<String> newSeq = new ArrayList<>(currSeq);
                            newSeq.add(newS);
                            queue.offer(newSeq);
                            wordsVisited.add(newS);
                            // set.remove(newS);
                        }
                    }
                }
            }

            if (endWordFoundAtThisLevel) return ans;

            for (String wordVisited: wordsVisited) {
                set.remove(wordVisited);
            }

        }

        return new LinkedList<>();
    }

    private List<List<String>> findLaddersUtil(String beginWord, String endWord, List<String> wordList) {


        if (!wordList.contains(endWord)) {
            return new ArrayList<>();
        }

        Map<String, List<String>> adjMap = new HashMap<>();

        adjMap.put(beginWord, new ArrayList<>());

        wordList.remove(beginWord);

        for (int i = 0; i < wordList.size(); i++) {
            adjMap.put(wordList.get(i), new ArrayList<>());
        }

        generateAdjMap(beginWord, wordList, adjMap);
        // System.out.println(adjMap);

        List<List<String>> ans = new LinkedList<>();

        Queue<String> queue = new LinkedList<>();

        queue.offer(beginWord);

        Map<String, Integer> distMap = new HashMap<>();
        Map<String, List<String>> parentsMap = new HashMap<>();

        for (String key: adjMap.keySet()) {
            distMap.put(key, (int) Math.pow(10, 9));
        }

        distMap.put(beginWord, 0);

        while (!queue.isEmpty()) {
            String currWord = queue.poll();

            for (String neighbour: adjMap.get(currWord)) {
                int newDist = distMap.get(currWord) + 1;
                if (newDist < distMap.get(neighbour)) {
                    distMap.put(neighbour, newDist);
                    List<String> parents = new ArrayList<>();
                    parents.add(currWord);
                    parentsMap.put(neighbour, parents);
                    queue.offer(neighbour);
                } else if (newDist == distMap.get(neighbour)) {
                    parentsMap.get(neighbour).add(currWord);
                }
            }
        }

        // System.out.println(distMap);
        // System.out.println(parentsMap);

        if (distMap.get(endWord) >= (int) Math.pow(10, 9)) {
            return new ArrayList<>();
        }

        Queue<Pair> parentQueue = new LinkedList<>();

        parentQueue.offer(new Pair(endWord, new ArrayList<>()));

        while (!parentQueue.isEmpty()) {
            Pair wordProp = parentQueue.poll();
            String word = wordProp.word;
            List<String> parentsSoFar = wordProp.parents;
            parentsSoFar.add(word);

            if (word.equals(beginWord)) {
                reverse(parentsSoFar);
                ans.add(parentsSoFar);
                continue;
            }

            for (String parent: parentsMap.get(word)) {
                List<String> parentChain = new ArrayList<>(parentsSoFar);
                Pair p = new Pair(parent, parentChain);
                parentQueue.offer(p);
            }
        }

        return ans;

    }

    private void reverse(List<String> l) {
        for (int i = 0; i < l.size()/2; i++) {
            String temp = l.get(i);
            l.set(i, l.get(l.size() - 1 - i));
            l.set(l.size() - 1 - i, temp);
        }
    }

    private class Pair {
        String word;
        List<String> parents;

        public Pair(String word, List<String> parents) {
            this.word = word;
            this.parents = parents;
        }
    }

    private void generateAdjMap(String beginWord, List<String> wordList, Map<String, List<String>> adjMap) {

        for (int i = 0; i < wordList.size(); i++) {
            boolean isAdj = checkIfAdj(beginWord, wordList.get(i), 1);
            if (isAdj) {
                adjMap.get(beginWord).add(wordList.get(i));
                adjMap.get(wordList.get(i)).add(beginWord);
            }
        }

        for (int i = 0; i < wordList.size() - 1; i++) {
            for (int j = i+1; j < wordList.size(); j++) {
                boolean isAdj = checkIfAdj(wordList.get(i), wordList.get(j), 1);
                if (isAdj) {
                    adjMap.get(wordList.get(i)).add(wordList.get(j));
                    adjMap.get(wordList.get(j)).add(wordList.get(i));
                }
            }

        }
    }

    private boolean checkIfAdj(String w1, String w2, int diffAllowed) {
        int i = 0;
        int diff = 0;

        while (i < w1.length()) {
            if (w1.charAt(i) != w2.charAt(i)) {
                diff++;
                if (diff > diffAllowed) return false;
            }
            i++;
        }

        return true;
    }
}
