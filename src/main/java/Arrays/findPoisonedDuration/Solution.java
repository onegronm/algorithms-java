package Arrays.findPoisonedDuration;

import java.util.*;
import static org.junit.Assert.*;

public class Solution {

    public int findPoisonedDuration(int[] timeSeries, int duration){
        if (duration == 0) return 0;

        Queue<Integer> poisonedState = new LinkedList<>();
        Map<Integer, Integer> durations = new HashMap<>();
        List<Integer> times = new ArrayList<>();

        for (int i : timeSeries){
            times.add(i);
        }

        int totalPoisonedTime = 0;
        for (int i = 0; i < times.size(); i++){
            /* UPDATE STATE START */
            for (int state : poisonedState){
                int howLongHasPassed = times.get(i) - state;
                int newDuration = duration - howLongHasPassed < 0 ? 0 : duration - howLongHasPassed;
                durations.put(state, newDuration);
                if (howLongHasPassed - totalPoisonedTime > 0){
                    int update = howLongHasPassed - totalPoisonedTime;
                    if (update > duration) update = duration;
                    totalPoisonedTime = totalPoisonedTime + update;
                }
            }
            /* UPDATE STATE END */
            poisonedState.add(times.get(i));
            durations.put(times.get(i), duration);
        }

        // update overlapping durations
        if (!poisonedState.isEmpty()) {
            int lastTime = times.get(times.size() - 1);
            for (int i = times.size() - 1; i > -1; i--) {
                int curTime = times.get(i);
                int howLongHasPassed = lastTime - curTime;
                if (howLongHasPassed > 0){
                    int newDuration = durations.get(lastTime) - durations.get(curTime);
                    if (newDuration < duration)
                        durations.put(curTime, 0);
                }
            }
        }

        // do one last check for remaining states
        while (!poisonedState.isEmpty()){
            int state = poisonedState.poll();
            totalPoisonedTime += durations.getOrDefault(state, 0);
        }

        return totalPoisonedTime;
    }

    public static void main(String[] args){
        simpleTest8();
        simpleTest4();
        simpleTest7();
        simpleTest6();
        simpleTest5();
        simpleTest3();
        simpleTest2();
        simpleTest1();
    }

    public static void simpleTest1(){
        Solution s = new Solution();
        int[] timeSeries = {1,4};
        assertEquals(4, s.findPoisonedDuration(timeSeries, 2));
    }

    public static void simpleTest2(){
        Solution s = new Solution();
        int[] timeSeries = {1,2};
        assertEquals(3, s.findPoisonedDuration(timeSeries, 2));
    }

    public static void simpleTest3(){
        Solution s = new Solution();
        int[] timeSeries = { };
        assertEquals(0, s.findPoisonedDuration(timeSeries, 2));
    }

    public static void simpleTest4(){
        Solution s = new Solution();
        int[] timeSeries = { 1,2,3,4,5 };
        assertEquals(6, s.findPoisonedDuration(timeSeries, 2));
    }

    public static void simpleTest5(){
        Solution s = new Solution();
        int[] timeSeries = { 1,2,3,4,5};
        assertEquals(0, s.findPoisonedDuration(timeSeries, 0));
    }

    public static void simpleTest6(){
        Solution s = new Solution();
        int[] timeSeries = { 1,4,7 };
        assertEquals(9, s.findPoisonedDuration(timeSeries, 3));
    }

    public static void simpleTest7(){
        Solution s = new Solution();
        int[] timeSeries = { 1,2 };
        assertEquals(6, s.findPoisonedDuration(timeSeries, 5));
    }

    public static void simpleTest8(){
        Solution s = new Solution();
        int[] timeSeries = { 1,2,3,4,5 };
        assertEquals(9, s.findPoisonedDuration(timeSeries, 5));
    }
}