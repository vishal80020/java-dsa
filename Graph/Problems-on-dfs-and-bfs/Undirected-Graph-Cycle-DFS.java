// Problem Link - https://www.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
// using DFS
class Pair {
    int node;
    int parent;
    public Pair(int node, int parent) {
        this.node = node;
        this.parent = parent;
    }
}

class Solution {
   
    private boolean dfs(int node,int parent,int[]vis,ArrayList<ArrayList<Integer>>adj) {
        vis[node] = 1;
        for(Integer adjNode: adj.get(node)) {
            if(vis[adjNode]==0) {
                if(dfs(adjNode,node,vis,adj)==true) return true;
            } 
            //already visited so check for where am I coming from
            else if(parent != adjNode) return true;
        }
        return false;
    }
    //TC - O(V+2E) (BFS) + O(V)(for loop)
    //SC - O(V) (recursion stack) + O(V) (visited array)
    
    public boolean isCycle(int V, int[][] edges) {
        // Code here
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0;i<V;i++) {
            adj.add(new ArrayList<Integer>());
        }
        for(int i=0;i<edges.length;i++) {
            adj.get(edges[i][0]).add(edges[i][1]);
            adj.get(edges[i][1]).add(edges[i][0]);
        }
        
        int[] vis = new int[V];
        for(int i=0;i<V;i++) {
            if(vis[i]==0 ) {
                // System.out.println("src = "+i);
                if (dfs(i,-1,vis,adj)==true){
                    return true;
                }
            }  
        }
        return false;
    }
}