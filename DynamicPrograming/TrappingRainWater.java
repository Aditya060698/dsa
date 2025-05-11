package DynamicPrograming;

/**
 * Problem: - #Trapping Rainwater Problem states that given an array of n
 * non-negative integers arr[] representing an elevation map where the width of
 * each bar is 1,
 * compute how much water it can trap after rain.
 * Link: <https://www.geeksforgeeks.org/trapping-rain-water>
 *
 * Approach:
 * So basically what we have to find the left and right height of a particular
 * iteration and that minimum of them minus the elevation at that point will
 * give me the height
 * of water for that iteration
 * -Also there will no water on top of left most and right most elements
 * -Now for the approach2 we are checking for the limiting factor basically
 * checking leftMax basically the first elemt is less than or greater than the
 * rightMax last element
 * if leftMax is less then its the limiting factor we will calculate left side
 * first and update the left max
 * -Here like even if there are smaller walls it will hold water till leftMax or
 * if there are larger walls it will hold water at least to the level of left
 * max
 *
 *
 * Author: Aditya Mishra
 * Date: 2025-05-11
 */
public class TrappingRainWater {
    public static void main(String[] args) {

        int[] arr = { 2, 1, 5, 3, 1, 0, 4 };
        System.out.println(trappingRainWater(arr, arr.length)); // Approach 1
        System.out.println(trappingRainWater2(arr, arr.length)); // Approach 2

    }

    /**
     * 🔹 Approach: 1.Prefix and suffix max for each element
     *
     * 🕒 Time Complexity: O(n)
     * 🗂️ Space Complexity: O(n) - We will create two new array storing the left
     * maxes anf right maxes
     *
     * 🔁 Important Points:
     * 1. First decalring left max of first element will be height of that element
     * only same for right max for last element
     * 2. For finding max(left or right) of that iteration is max value between
     * previous left max or itself basically if there is a bigger wall towards the
     * left of that
     * element, same for rightmax array only difference we chck is towards the right
     * of the current iteration.
     *
     * 🔍 Example Trace:
     * Input: {2, 1, 5, 3, 1, 0, 4}
     * Output:
     * for 1st element
     * leftMax is 2 rightMax is 5 min is 2 subtreact 1 is 1
     * 
     * Similarly
     * 0 + 1 + 0 + 1 + 3 + 4 + 0 = 9
     */
    public static int trappingRainWater(int[] height, int n) {
        int[] leftArr = new int[n];
        int[] rightArr = new int[n];
        int res = 0;
        leftArr[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftArr[i] = Math.max(leftArr[i - 1], height[i]);
        }
        rightArr[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightArr[i] = Math.max(rightArr[i + 1], height[i]);
        }
        // Calculating total water stored by the logic in approach (main calculating
        // logic)
        for (int i = 0; i < n; i++) {
            res += Math.min(leftArr[i], rightArr[i]) - height[i];
        }
        return res;

    }

    /**
     * 🔹 Approach: 2. Two pointer Approach
     *
     * 🕒 Time Complexity: 0(n)
     * 🗂️ Space Complexity: 0(1)
     *
     * 🔁 Important Points:
     * 1. left and right are set as second and second last elemnts
     * 2. we run the loop from both sides hence we are check left<right and
     * incrementing left and decrementing right
     * 
     * 🔍 Example Trace:
     * Input:
     * Output:
     */
    public static int trappingRainWater2(int[] height, int n) {
        // int left = 1;
        // int right = height.length - 2;

        // // lMax : Maximum in subarray arr[0..left-1]
        // // rMax : Maximum in subarray arr[right+1..n-1]
        // int lMax = height[left - 1];
        // int rMax = height[right + 1];

        // int res = 0;
        // while (left <= right) {

        // // If rMax is smaller, then we can decide the amount of water for arr[right]
        // if (rMax <= lMax) {

        // // Add the water for arr[right]
        // res += Math.max(0, rMax - height[right]);

        // // Update right max
        // rMax = Math.max(rMax, height[right]);

        // // Update right pointer as we have decided the amount of water for this
        // right -= 1;
        // } else {

        // // Add the water for arr[left]
        // res += Math.max(0, lMax - height[left]);

        // // Update left max
        // lMax = Math.max(lMax, height[left]);

        // // Update left pointer as we have decided water for this
        // left += 1;
        // }
        // }
        int left = 1;
        int right = n - 2;

        int lmax = height[0];
        int rmax = height[n - 1];

        int res = 0;

        while (left <= right) {
            if (rmax < lmax) {
                res += Math.max(0, rmax - height[right]);
                rmax = Math.max(rmax, height[right]);
                right--;
            } else {
                res += Math.max(0, lmax - height[left]);
                lmax = Math.max(lmax, height[left]);
                left++;

            }
        }

        return res;
    }

}
