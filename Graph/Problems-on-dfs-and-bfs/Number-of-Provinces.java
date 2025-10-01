//Problem Link - https://leetcode.com/problems/number-of-provinces/
class Solution {

    private void dfs(int node,ArrayList<ArrayList<Integer>> adj,int[] vis) {
        vis[node] = 1;
        for(int it: adj.get(node)) {
            if(vis[it]!=1) dfs(it,adj,vis);
        }
    }
    // TC - O(V) + O(V+2E)
    // SC - O(V) + O(V) => vis array + recursion stack
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<=n; i++) adj.add(new ArrayList<>());
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(i!=j && isConnected[i][j]==1) {
                    adj.get(i+1).add(j+1);
                    adj.get(j+1).add(i+1);
                }
            }
        }
        int ans = 0;
        int vis[] = new int[n+1];
        for(int i=1;i<=n;i++) {
            if(vis[i]!=1) {
                ans++;
                dfs(i,adj,vis);
            }
        }
        return ans;
    }
}