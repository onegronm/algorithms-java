package Dynamic.Programming.numMusicPlaylist;

import static org.junit.Assert.*;
import java.util.*;
/**
 * Difficulty: hard
 * Companies: Salesforce, Facebook
 * https://leetcode.com/problems/number-of-music-playlists/
 * Started: 2/20/21 @ 5:55PM
 */
public class Solution {
    private int helper(int[] songs, HashMap<Integer,Integer> songMap, int k){

        return -1;
    }

    public int numMusicPlaylists(int N, int L, int K) {
        int[] songs = new int[N];
        for (int i = 0; i < N; i++)
            songs[i] = i + 1;


        return -1;
    }

    public static void main(String[] args){
        test0();
    }

    public static void test0(){
        int n = 3;
        int l = 3;
        int k = 1;
        Solution s = new Solution();
        assertEquals(6, s.numMusicPlaylists(n, l, k));
    }
}