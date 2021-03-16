package Stacks.validParentheses;

import java.util.*;
/**
 * Type of problem: stack, string
 */
public class Solution {
    /**
     * determines if the input string is valid
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){ // "()"
            char c = s.charAt(i); // c:)
            if(c == '(' || c == '{' || c == '[')
                stack.push(c); // stack:
            else{
                if(stack.isEmpty()) return false;
                char compare = stack.pop(); // compare: (
                switch(compare){
                    case '(':
                        if(c != ')') return false;
                        break;
                    case '{':
                        if(c != '}') return false;
                        break;
                    case '[':
                        if(c != ']') return false;
                        break;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args){
        Solution s = new Solution();
        System.out.println(s.isValid("()"));
    }
}
