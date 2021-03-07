package Arrays.twoSum;

import org.junit.Assert;

import java.util.*;
import static org.junit.Assert.*;

public class Solution {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            int complement = target - nums[i];
            if(map.containsKey(complement))
                return new int[] { map.get(complement), i };
            map.put(nums[i], i);
        }

        return new int[] { };
    }

    public static void main(String[] args){
        test1();
        test2();
        test3();
    }

    public static void test1() {
        int[] result = twoSum(new int[] {2,7,11,15}, 9);
        assertTrue(Arrays.equals(result, new int[]{ 0,1 }));
    }

    public static void test2() {
        int[] result = twoSum(new int[] {3,2,4}, 6);
        assertTrue(Arrays.equals(result, new int[]{ 1,2 }));
    }

    public static void test3() {
        int[] result = twoSum(new int[] {3,3}, 6);
        assertTrue(Arrays.equals(result, new int[]{ 0,1 }));
    }
}
