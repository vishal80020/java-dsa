//Problem Link - https://www.geeksforgeeks.org/problems/minimum-multiplications-to-reach-end/1
// User function Template for Java
class Pair {
    int dis;
    int node;
    Pair(int dis, int node) {
        this.dis = dis;
        this.node = node;
    }
}
class Solution {
    int minimumMultiplications(int[] arr, int start, int end) {
       if(start == end ) return 0;
       int mod = 100000;
       int[] dist = new int[mod];
       for(int i=0;i<mod;i++) dist[i] = (int) 1e9;
       dist[start] = 0;
       Queue<Pair> q = new LinkedList<>();
       q.add(new Pair(0,start));
       // TC - O(100000 * arr.length)
       while(!q.isEmpty()) {
           int steps = q.peek().dis;
           int node = q.peek().node;
           q.remove();
           for(int i : arr) {
               int num =  (node*i)%mod;
               if(steps+1 < dist[num]) {
                   if(num == end) return steps+1;
                   dist[num] = steps+1;
                   q.add(new Pair(steps+1,num));
               }
           }
           
           
       }
       return -1;
       
    }
}
