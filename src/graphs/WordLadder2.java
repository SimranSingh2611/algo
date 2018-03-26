package graphs;

import java.util.*;

/*
* https://leetcode.com/problems/word-ladder-ii/
* https://www.interviewbit.com/problems/word-ladder-ii/
 */
public class WordLadder2 {
    public static class Node {
        int dist;
        String word;
        List<Node> neigh;

        public Node(String word) {
            this.word = word;
            dist = 0;
            neigh = new ArrayList<>();
        }
    }

    private boolean isOneLetterApart(String s1, String s2) {
        if(s1.length() != s2.length()) return false;
        int count = 0;
        for(int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i)) count++;
            if(count > 1) return false;
        }
        return count == 1;
    }

    // Get all the words that are exactly one letter apart
    private List<String> getOneLetterApart(String s) {
        List<String> result = new ArrayList<>();
        char[] word = s.toCharArray();
        for(int i = 0; i < s.length(); i++) {
            for(int j = 0; j < 26; j++) {
                char ch = (char) ('a' + j);
                if(ch == s.charAt(i)) continue;
                word[i] = ch;
                result.add(String.valueOf(word));
                word[i] = s.charAt(i);
            }
        }
        return result;
    }

    // Backtracking
    private void findPaths(Node cur, Node finish, List<String> path, List<List<String>> result) {
        path.add(cur.word);
        if(cur == finish) {
            result.add(new ArrayList<>(path));
        } else {
            for(Node neigh : cur.neigh) {
                if(neigh.dist < cur.dist) findPaths(neigh, finish, path, result);
            }
        }
        path.remove(path.size() - 1);
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();

        // Part 1: form a graph
        Map<String, Node> nodes = new HashMap<>();
        for(String word : wordList) {
            nodes.put(word, new Node(word));
        }

        // Note that when building edges we don't check every pair
        // O(N^2) is much worse than O(N) even with a bad constant factor
        for(String word : nodes.keySet()) {
            Node cur = nodes.get(word);
            List<String> candidates = getOneLetterApart(word);
            for(String candidate : candidates) {
                if(nodes.containsKey(candidate)) cur.neigh.add(nodes.get(candidate));
            }
        }

        // Part 2: set distances
        Node start = nodes.get(endWord);
        if(start == null) return result;
        Deque<Node> queue = new ArrayDeque<>();
        Set<Node> visited = new HashSet<>();
        Deque<Integer> dist = new ArrayDeque<>();

        queue.addLast(start);
        visited.add(start);
        dist.addLast(0);

        while(!queue.isEmpty()) {
            Node cur = queue.removeFirst();
            int d = dist.removeFirst();
            // Set the min distance
            cur.dist = d;
            for(Node neigh : cur.neigh) {
                if(!visited.contains(neigh)) {
                    visited.add(neigh);
                    dist.addLast(d + 1);
                    queue.addLast(neigh);
                }
            }
        }

        // Part 3: Efficient search with backtracking
        List<Node> bestNodes = new ArrayList<>();
        int bestDist = Integer.MAX_VALUE;
        for(String word : nodes.keySet()) {
            if(isOneLetterApart(beginWord, word)) {
                Node cur = nodes.get(word);
                if(cur.dist < bestDist) {
                    bestDist = cur.dist;
                    bestNodes = new ArrayList<>();
                    bestNodes.add(cur);
                } else if(cur.dist == bestDist) bestNodes.add(cur);
            }
        }

        for(Node node : bestNodes) {
            List<String> path = new ArrayList<>();
            path.add(beginWord);
            findPaths(node, nodes.get(endWord), path, result);
        }

        return result;
    }
}