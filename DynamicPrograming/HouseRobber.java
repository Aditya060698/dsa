package DynamicPrograming;

import java.util.Arrays;

/**
 * Problem:  -  #You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you 
 * from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on 
 * the same night.
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 * 
 * Link: <https://leetcode.com/problems/house-robber/description/?envType=company&envId=google&favoriteSlug=google-all>
 *
 * Approach:
 * - Recurrance Relation:-So basically in order find the max  loot till the point what will be rquired is tow option
 *   1.Either include the current house and calculate the max loot till n-2 house and add them up skipping n-1
 *   2. Or don't include the current house and calculate till the house before it maxLoot till n-1
 *  and max of them will give the max loot
 * 
 *
 *
 * Author: Aditya Mishra
 * Date: 2025-05-11
 */
public class HouseRobber {
    public static void main(String[] args) {
        
    }
    /**
     * 🔹 Approach: 1.Memoization 
     *
     * 🕒 Time Complexity:O(n). Every house is computed only once.
     * 🗂️ Space Complexity: O(n). For recursion stack space and memo array.
     *
     * 🔁 Important Points:
     * 1. Using the recurrance relation calulating the max loot and storing the values in memo array
     *
     * 🔍 Example Trace:
     * Input: 
     * Output: 
     */
    static int maxLoot(int[] hval) {
        int n = hval.length;
      
        // Initialize memo array with -1
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);

        return maxLootRec(hval, n, memo);
    }
    static int maxLootRec(int[] hval, int n, int[] memo) {
        if (n <= 0) return 0;
        if (n == 1) return hval[0];

        // Check if the result is already computed
        if (memo[n] != -1) return memo[n];

        int pick = hval[n - 1] + maxLootRec(hval, n - 2, memo);
        int notPick = maxLootRec(hval, n - 1, memo);

        // Store the max of two choices in the memo array and return it
        memo[n] = Math.max(pick, notPick);
        return memo[n];
    }
    
    public static int houseRobber(int[] hval){

    }
}
