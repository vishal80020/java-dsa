// Problem Link - https://leetcode.com/problems/search-in-rotated-sorted-array/
// TC - O(logn)
// SC - O(1)
class Solution {
    public int search(int[] nums, int target) {
        int n =  nums.length;
        int low = 0;
        int high = n-1;
        while(low<=high) {
            int mid = low + (high-low)/2;
            if(nums[mid] == target) return mid;
            //left part is sorted
            if(nums[low] <= nums[mid]) {
                // target lies within left part
                if( nums[low] <= target && target < nums[mid]) {
                    high = mid-1;
                } 
                // else search in right half
                else {
                    low = mid+1;
                }

            } 
            // right half is sorted
            else {
                // target lies within right sorted part
                if(nums[mid] < target && target <= nums[high]) {
                    low = mid+1;
                } 
                // search in  left half
                else {
                    high = mid-1;
                }
            }
           
        }
        return -1;
    }
}