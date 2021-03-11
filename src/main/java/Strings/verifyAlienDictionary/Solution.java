package Strings.verifyAlienDictionary;

public class Solution {
    /**
     * Time complexity: O(26 * N) = O(N)
     * Space complexity: O(1)
     * @param words
     * @param order
     * @return
     */
    public boolean isAlienSorted(String[] words, String order){
        int[] index = new int[26];
        for(int i = 0; i < order.length(); i++){
            index[order.charAt(i) - 'a'] = i; // order.charAt(i) - 'a' since the character at 'i' converted to integer does fall in the range 0-25
        }

        // check if adjacent words are lexicographically ordered
        search: for(int i = 0; i < words.length - 1; i++){
            String word1 = words[i];
            String word2 = words[i+1];

            for(int j = 0; j < Math.min(word1.length(), word2.length()); j++){
                if(word1.charAt(j) != word2.charAt(j)) {
                    if(index[word1.charAt(j) - 'a'] > index[word2.charAt(j) - 'a']){
                        return false;
                    }
                    else {
                        continue search;
                    }
                }
            }

            // check for blank character (case apple > app because blank is less than any character)
            if(word1.length() > word2.length()) return false;
        }
        return true;
    }
}
