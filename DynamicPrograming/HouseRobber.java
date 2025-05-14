package DynamicPrograming;

import java.util.Arrays;

/**
 * Problem: - #You are a professional robber planning to rob houses along a
 * street. Each house has a certain amount of money stashed, the only constraint
 * stopping you
 * from robbing each of them is that adjacent houses have security systems
 * connected and it will automatically contact the police if two adjacent houses
 * were broken into on
 * the same night.
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the
 * police.
 * 
 * Link:
 * <https://leetcode.com/problems/house-robber/description/?envType=company&envId=google&favoriteSlug=google-all>
 *
 * Approach:
 * - Recurrance Relation:-So basically in order find the max loot till the point
 * what will be rquired is tow option
 * 1.Either include the current house and calculate the max loot till n-2 house
 * and add them up skipping n-1
 * 2. Or don't include the current house and calculate till the house before it
 * maxLoot till n-1
 * and max of them will give the max loot
 * 
 *
 *
 * Author: Aditya Mishra
 * Date: 2025-05-11
 */
public class HouseRobber {
    public static void main(String[] args) {
        int[] hval = { 6, 7, 1, 3, 8, 2, 4 };
        System.out.println(maxLoot(hval));
        System.out.println(maxLoot2(hval));
        System.out.println(maxLoot3(hval));
    }

    /**
     * 🔹 Approach: 1.Memoization
     *
     * 🕒 Time Complexity:O(n). Every house is computed only once.
     * 🗂️ Space Complexity: O(n). For recursion stack space and memo array.
     *
     * 🔁 Important Points:
     * 1. Using the recurrance relation calulating the max loot and storing the
     * values in memo array
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
        if (n <= 0)
            return 0;
        if (n == 1)
            return hval[0];

        // Check if the result is already computed
        if (memo[n] != -1)
            return memo[n];

        int pick = hval[n - 1] + maxLootRec(hval, n - 2, memo);
        int notPick = maxLootRec(hval, n - 1, memo);

        // Store the max of two choices in the memo array and return it
        memo[n] = Math.max(pick, notPick);
        return memo[n];
    }

    /**
     * 🔹 Approach: 2.Tabulation with dp array
     *
     * 🕒 Time Complexity: O(n), Every house is computed only once.
     * 🗂️ Space Complexity: O(n), We are using a dp array of size n.
     *
     * 🔁 Important Points:
     * 1. In this it important to understand that we are using dp array to store the
     * values till the point.
     * 2. intialising the dp array with one extra space and the first element as 0
     * and the 2nd as first house val (It can also be both zero but till 1 element
     * any way the max value will be the value of the 1 house thats why intialising
     * second elemnt as house val1)
     *
     * 🔍 Example Trace:
     * Input:
     * Output:
     */
    static int maxLoot2(int[] hval) {
        int n = hval.length;

        // Create a dp array to store the maximum loot at each house
        int[] dp = new int[n + 1];

        // Base cases
        dp[0] = 0;
        dp[1] = hval[0];

        // Fill the dp array using the bottom-up approach
        // Dp[i] stores hval [i-1] highest loot
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(hval[i - 1] + dp[i - 2], dp[i - 1]);
        }
        return dp[n];
    }

    /**
     * 🔹 Approach: 3.Tabulation but withot a dp array
     *
     * 🕒 Time Complexity: O(n), Every value is computed only once.
     * 🗂️ Space Complexity: O(1), as we are using only two variables.
     *
     * 🔁 Important Points:
     * 1. as we only need the last value in the dp array replacing with variables
     * and executing the logic
     *
     * 🔍 Example Trace:
     * Input:
     * Output:
     */
    static int maxLoot3(int[] hval) {
        int n = hval.length;

        if (n == 0)
        return 0;
        if (n == 1)
        return hval[0];

        // Set previous 2 values
        int secondLast = 0, last = hval[0];

        // Compute current value using previous
        // two values. The final current value
        // would be our result
        int res = 0;
        for (int i = 1; i < n; i++) {
        res = Math.max(hval[i] + secondLast, last);
        secondLast = last;
        last = res;
        }

        return res;
        // int n = hval.length;

        // if (n == 0) {
        //     return 0;
        // }
        // if (n == 1) {
        //     return hval[0];
        // }

        // int secondlast = 0, last = hval[0];

        // for (int i = 1; i < n; i++) {
        //     int max = Math.max(hval[i] + secondlast, last);
        //     secondlast = last;
        //     last = max;
        // }
        // return last;

    }
}
