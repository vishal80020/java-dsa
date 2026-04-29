// Problem Link - https://leetcode.com/problems/single-element-in-a-sorted-array/
// TC - O(logn)
// SC - O(1)
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        if(n==1) return nums[0];
        if(nums[0] != nums[1]) return nums[0];
        if(nums[n-1] != nums[n-2]) return nums[n-1];
        
        //(to remove additional check for out of index)
        // n+1 or 0-1 so reduce search space and handle explicitly(already handled above)
        int low = 1;
        int high = n-2; 
        while(low<=high) {
            int mid = low + (high-low)/2;
            if(nums[mid] != nums[mid-1] && nums[mid] != nums[mid+1]) return nums[mid];
            
            //(0,1) (2,3) (4,5) 6 (7,8) (8,9)
            if(mid%2==1 && nums[mid-1]==nums[mid] ||
                mid%2==0 && nums[mid]==nums[mid+1]) {
                    low = mid+1;
            } else {
                high = mid-1;
            }

        }

        return -1;
    }
}