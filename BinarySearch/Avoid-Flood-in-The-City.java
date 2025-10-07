// Problem Link - https://leetcode.com/problems/avoid-flood-in-the-city/description/

class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];
        Arrays.fill(ans,1);
        Map<Integer,Integer> map = new HashMap<>();
        TreeSet<Integer> trset = new TreeSet<>();
        for(int i=0;i<n;i++) {
            if(rains[i]==0) {
                trset.add(i);
            } else {
                ans[i] = -1;
                if(map.containsKey(rains[i])) {
                    Integer it = trset.ceiling(map.get(rains[i]));//smallest index greater or equal to ith day
                    if(it==null) return new int[0];
                    ans[it] = rains[i];
                    trset.remove(it);
                }
                map.put(rains[i],i);
            }

        }
        return ans;
    }
            
}