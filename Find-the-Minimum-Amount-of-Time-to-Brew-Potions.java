// Problem Link - https://leetcode.com/problems/find-the-minimum-amount-of-time-to-brew-potions/description/

//  TC - O(M*N)
//  SC - O(N)
class Solution {
    public long minTime(int[] skill, int[] mana) {
        int m = mana.length;
        int n = skill.length;
        long [] finishTime = new long[n];
        
        for(int j=0;j<m;j++) {
            finishTime[0] += (long)mana[j]*skill[0];

            //calculate
            for(int i=1;i<n;i++) {
                finishTime[i] = Math.max(finishTime[i],finishTime[i-1]) + (long)skill[i]*mana[j];
            }

            //synchronize the time
            for(int i=n-1;i>0;i--) {
                finishTime[i-1] = finishTime[i] - (long)skill[i]*mana[j];
            }
        }
        return finishTime[n-1];
    }
}