// Problem Link - https://www.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
// using BFS
class Pair {
    int node;
    int parent;
    public Pair(int node, int parent) {
        this.node = node;
        this.parent = parent;
    }
}

class Solution {
    private boolean bfs(int src,int[] vis,ArrayList<ArrayList<Integer>> adj) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(src,-1));
        vis[src]=1;
        while(!q.isEmpty()) {
            Pair p = q.peek();
            int node = p.node;
            int parent = p.parent;
            // System.out.println("node = "+node+ " parent= "+parent);
            q.remove();
            for(Integer adjNode : adj.get(node)) {
                if(vis[adjNode] ==0) {
                    q.add(new Pair(adjNode,node));
                    vis[adjNode] = 1;
                } else if(parent != adjNode) {
                    return true;
                }
            }
        }
        return false;
    }
    
    //TC - O(V+2E) (BFS) + O(V)(for loop)
    //SC - O(V) (all nodes in queue) + O(V) (visited array)
    
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
            if(vis[i]==0){
                // System.out.println("source node = "+i);
                if(bfs(i,vis,adj)==true){
                    return true;
                }
            }  
        }
        return false;
    }
}