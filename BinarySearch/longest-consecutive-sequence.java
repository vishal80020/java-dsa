// Problem Link - https://leetcode.com/problems/longest-consecutive-sequence/
// TC - O(n)
// SC - O(n)
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums) {
            set.add(num);
        }
        int ans = 0;
        for(int el : set) {
            int x = el;
            int count = 1;
            if(set.contains(x-1)) continue;
            while(set.contains(x+1)) {
                count++;
                x++;
            }
            ans = Math.max(ans,count);
        }
        return ans;
    }
}