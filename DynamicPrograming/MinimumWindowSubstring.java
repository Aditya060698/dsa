package DynamicPrograming;

import java.util.HashMap;
import java.util.Map;

/**
 * Not a DP problem
 * Problem:  -  #Given two strings s (length m) and p (length n), the task is to find the smallest substring in s that contains all characters of p, including duplicates. 
 *                  If no such substring exists, return “-1”. If multiple substrings of the same length are found, return the one with the smallest starting index.
 * Link: <https://leetcode.com/problems/minimum-window-substring/description/?envType=company&envId=google&favoriteSlug=google-all>
 *
 * Approach:
 * - the approach for this is that first have a map for all chars in p with its frequency
 * - then start the with one pointer in the string s and check the character in the pmap and add it to the smap and increase its frequency
 * - next check if the smap character freq <= pmap character frequency then its a match and kept track of
 * - once we find the window try shirking it and updating the result
 * - for shrinking it check if the character in pmap and removing it will decrement matched value making the min window wrong so dont remove til we make it correct again  
 *
 *
 * Author: Aditya Mishra
 * Date: 2025-05-11
 */
public class MinimumWindowSubstring {
    public static void main(String[] args) {
        String s = "timetopractice";
        String p = "toc";

        String result = smallestWindow(s, p);
        System.out.println(result);  // Output: "toprac"
    }
    /**
     * 🔹 Approach: Sliding Window approach
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
    public static String smallestWindow(String s, String p) {
        if (s.length() < p.length()) return "-1";

        // Frequency map for characters in p
        Map<Character, Integer> freqP = new HashMap<>();
        for (char c : p.toCharArray()) {
            freqP.put(c, freqP.getOrDefault(c, 0) + 1);
        }

        int start = 0, matched = 0, minLen = Integer.MAX_VALUE, startIdx = 0;
        Map<Character, Integer> windowFreq = new HashMap<>();

        for (int end = 0; end < s.length(); end++) {
            char endChar = s.charAt(end);
            windowFreq.put(endChar, windowFreq.getOrDefault(endChar, 0) + 1);

            // Count matching characters
            if (freqP.containsKey(endChar) &&
                windowFreq.get(endChar) <= freqP.get(endChar)) {
                matched++;
            }

            // Try to shrink the window
            while (matched == p.length()) {
                char startChar = s.charAt(start);

                // Update min window
                if ((end - start + 1) < minLen) {
                    minLen = end - start + 1;
                    startIdx = start;
                }

                // Remove from window
                windowFreq.put(startChar, windowFreq.get(startChar) - 1);
                if (freqP.containsKey(startChar) &&
                    windowFreq.get(startChar) < freqP.get(startChar)) {
                    matched--;
                }
                start++;
            }
        }

        return (minLen == Integer.MAX_VALUE) ? "-1" : s.substring(startIdx, startIdx + minLen);
    }
}

