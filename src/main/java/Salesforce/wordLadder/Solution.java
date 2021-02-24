package Salesforce.wordLadder;

import java.util.*;

/* TOPIC: breadth-first search */
public class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        Set<String> words = new HashSet<>(); // constant lookup for words in list

        queue.add(beginWord);
        words.remove(beginWord);

        int level = 0;
        // meat of bfs is a while loop
        while (!queue.isEmpty()){
            level++;
            for (int i = 0; i < queue.size(); i++){
                String currentWord = queue.poll();
                if (currentWord.equals(endWord)) return level;
                else {
                    List<String> neighbors = findNeighbors(currentWord);
                    for(String n : neighbors){
                        if (words.contains(n)){
                            // we only care neighbors that are in word list
                            queue.add(n);
                            words.remove(n);
                        }
                    }
                }
            }
        }

        return 0;
    }

    public List<String> findNeighbors(String word){
        List<String> result = new ArrayList<>();
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++){
            char temp = chars[i];

            // avoid expensive concatenations
            for(char j = 'a'; j <= 'z'; j++){
                chars[i] = j;
                String s = new String(chars);
                result.add(s);
            }

            chars[i] = temp;
        }

        return result;
    }
}
