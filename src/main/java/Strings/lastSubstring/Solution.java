package Strings.lastSubstring;

import java.util.*;
import static org.junit.Assert.*;
/**
 * Difficulty: Hard
 * Companies: Salesforce, Goldman Sachs
 * https://leetcode.com/problems/last-substring-in-lexicographical-order/
 */
public class Solution {
    public String lastSubstring(String s){
        Queue<String> substrings = new LinkedList<>();

        char max = Character.MIN_VALUE;
        for (int i = 0; i < s.length(); i++){
            char cur = s.charAt(i);
            if (cur > max)
            {
                max = cur;
            }
        }

        int i = 0;
        char last = '$';
        while (i < s.length()){
            char cur = s.charAt(i);
            if (cur == max && cur != last){
                String sub = s.substring(i);
                substrings.add(sub);
            }
            last = cur;
            i++;
        }

        String maxString = substrings.poll();
        while (!substrings.isEmpty()){
            String sub = substrings.poll();
            int j = 0;
            int k = 0;
            while (sub.charAt(k) == maxString.charAt(j) && j == k && j < maxString.length()){
                if (j + 1 < maxString.length()) j++;
                if (k + 1 < sub.length()) k++;
            }

            if (k > j) maxString = sub;
            else if (k == j && sub.charAt(k) > maxString.charAt(j)) maxString = sub;
        }

        return maxString;
    }

    public static void main(String[] args){
        test0();
        test1();
        test2();
    }

    public static void test0(){
        Solution s = new Solution();
        assertEquals("bab", s.lastSubstring("abab"));
    }

    public static void test1(){
        Solution s = new Solution();
        assertEquals("tcode", s.lastSubstring("leetcode"));
    }

    public static void test2(){
        Solution s = new Solution();
        assertEquals("cb", s.lastSubstring("cacacb"));
    }
}