package Stacks.dailyTemperatures;

import java.util.*;
import static org.junit.Assert.*;

public class Solution {
    public int[] dailyTemperatures(int[] T) {
        Map<Integer,Stack<Integer>> positions = new HashMap<>();

        for (int i = 0; i < T.length; i++){
            if (positions.containsKey(T[i])){
                positions.get(T[i]).add(i);
            }
            else{
                Stack<Integer> stack = new Stack<>();
                stack.add(i);
                positions.put(T[i], stack);
            }
        }

        Arrays.sort(T);

        int[] result = new int[T.length];

        for (int i = 0; i < T.length; i++){
            int currentTemperature = T[i];
            int origIndex = positions.get(currentTemperature).peek();
            int j = i + 1;

            outerloop:
            while (j < T.length && T[j] >= currentTemperature){
                Stack<Integer> tempStack = new Stack<>();
                // reverse the elements
                while(!positions.get(T[j]).isEmpty()){
                    tempStack.add(positions.get(T[j]).pop());
                }

                // check indexes for reversed elements
                while(!tempStack.isEmpty()){
                    int index = tempStack.pop();
                    positions.get(T[j]).add(index); // add back to original stack
                    if (index <= origIndex){
                        j++;
                        break;
                    } else {
                        break outerloop;
                    }
                }
                //if(tempStack.isEmpty()) j++;
            }

            if (j == T.length) j = T.length - 1;

            result[origIndex] = positions.get(T[j]).peek() - origIndex >= 0 ? positions.get(T[j]).peek() - origIndex : 0;
            if (positions.get(T[j]).size() > 1){
                positions.get(T[j]).pop();
            }
        }

        return result;
    }

    public static void main(String[] args){
        simpleTest1();
    }

    public static void simpleTest1(){
        Solution s = new Solution();
        int[] T = {89,62,70,58,47,47,46,76,100,70};
        int[] r = s.dailyTemperatures(T);
    }
}