package DynamicPrograming;

/**
 * Problem:  -  #Given n items where each item has some weight and profit associated with it and also given a bag with capacity W, 
 * [i.e., the bag can hold at most W weight in it]. The task is to put the items into the bag such that the sum of profits associated with them is the maximum possible. 
    Note: The constraint here is we can either put an item completely into the bag or cannot put it at all [It is not possible to put a part of an item into the bag].
 * Link: <https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/#space-optimized-approach-on-x-w-time-and-ow-space>
 *
 * Approach:
 * - Need a dp matrix to calculate it but the choice and approach are
 *  | i (items)       | w=0 | w=1 | w=2 | w=3 | w=4 | w=5 |
    |-----------------|-----|-----|-----|-----|-----|-----|
    | 0               |  0  |  0  |  0  |  0  |  0  |  0  |
    | 1 (wt=1, val=1) | 0   | 1   | 1   | 1   | 1   | 1   |
    | 2 (wt=3, val=4) | 0   | 1   | 1   | 4   | 5   | 5   |
    | 3 (wt=4, val=5) | 0   | 1   | 1   | 4   | 5   | 6   |

 * - first when there are i elements available and W is target weight dp[i-1][W] is the max total value
 * - So now for the recurrence relation as we now it is f(n)=f(n-1)+f(n-2)
 * - dp[i-1][W] has two options pick the item or don't pick the item
 * - pick scenario val[i-1](item value) + dp[i-1][W-wt[i-1]] will the max value only if wt[i-1]<W
 * - not pick scenario dp[i-1][W]
 * - hence  dp[i-1][W] is Max(pick and not pick scenario)
 *
 * Author: Aditya Mishra
 * Date: 2025-05-11
 */
public class Knapsack {
    public static void main(String[] args) {
        int[] val = {1, 2, 3};
        int[] wt = {4, 5, 1};
        int W = 4;

        System.out.println(knapsack(W, val, wt));
    }
    /**
     * 🔹 Approach: 1.Tabulation space complexity
     *
     * 🕒 Time Complexity:  O(n x W)
     * 🗂️ Space Complexity:  O(n x W)
     *
     * 🔁 Important Points:
     * 1. If any one items or target is zero dp is zero
     * 2. logically it dp[i-1] beacause we are dp[i] 
     * 3. 
     *
     * 🔍 Example Trace:
     * Input: 
     * Output: 
     */
    static int knapsack(int W, int[] val, int[] wt) {
        int n = wt.length;
        int[][] dp = new int[n + 1][W + 1];
    
        // Build table dp[][] in bottom-up manner
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
    
                // If there is no item or the knapsack's capacity is 0
                if (i == 0 || j == 0)
                    dp[i][j] = 0;
                else {
                    int pick = 0;
    
                    // Pick ith item if it does not exceed the capacity of knapsack
                    //1 is actually zero 2 is one beacause added first element as zer0
                    if (wt[i - 1] <= j)
                        pick = val[i - 1] + dp[i - 1][j - wt[i - 1]];
    
                    // Don't pick the ith item
                    int notPick = dp[i - 1][j];
    
                    dp[i][j] = Math.max(pick, notPick);
                }
            }
        }
        return dp[n][W];
    }
    /**
     * 🔹 Approach: 
     *
     * 🕒 Time Complexity: 
     * 🗂️ Space Complexity: 
     *
     * 🔁 Important Points:
     * 1. 
     * 2. 
     * 3. 
     *
     * 🔍 Example Trace:
     * Input: 
     * Output: 
     */
    static int knapsack2(int W, int[] val, int[] wt) {
        
        // Initializing dp array
        int[] dp = new int[W + 1];
        
        // Taking first i elements
        for (int i = 1; i <= wt.length; i++) {
            
            // Starting from back, so that we also have data of
            // previous computation of i-1 items
            for (int j = W; j >= wt[i - 1]; j--) {
                dp[j] = Math.max(dp[j], dp[j - wt[i - 1]] + val[i - 1]);
            }
        }
        return dp[W];
    }
}

