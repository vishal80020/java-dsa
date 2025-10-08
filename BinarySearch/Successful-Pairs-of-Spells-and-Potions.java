// Problem Link - https://leetcode.com/problems/successful-pairs-of-spells-and-potions/description/
class Solution {
    private int lowerBound(int[] potions,long x,long success) {
        int low = 0;
        int high = potions.length-1;
        int ans = -1;
        while(low<=high) {
            int mid = low + (high-low)/2;
            long product = x * potions[mid];
            if(product>=success) {
                ans = mid;
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return ans;
    }
    // TC - O(mlogm + nlogm)
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions); // O(mlogm)
        int n = spells.length;
        int[] ans = new int[n];
        // (nlogm)
        for(int i=0;i<n;i++) {
            int it = lowerBound(potions,spells[i],success);
            // System.out.println("it= "+it+" spells[i]= "+spells[i]);
            if(it==-1) {
                ans[i] = 0;
            } else {
                ans[i] = potions.length-it;
            }
        }
        return ans;
    }
}