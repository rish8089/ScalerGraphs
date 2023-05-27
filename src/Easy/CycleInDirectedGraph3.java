package Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CycleInDirectedGraph3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A=Integer.parseInt(br.readLine());
        int M=Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> B=new ArrayList<>();
        for(int i=0;i<M;i++){
            String []str=br.readLine().split(" ");
            int u=Integer.parseInt(str[0]);
            int v=Integer.parseInt(str[1]);
            ArrayList<Integer> list=new ArrayList<>();
            list.add(u);
            list.add(v);
            B.add(list);
        }
        CycleInDirectedGraph3 obj=new CycleInDirectedGraph3();
        System.out.println(obj.solve(A,B));
    }
    public int solve(int A, ArrayList<ArrayList<Integer>> B){
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        for(int i=0;i<A;i++)
            adj.add(new ArrayList<>());
        for(int i=0;i<B.size();i++){
            int u=B.get(i).get(0);
            int v=B.get(i).get(1);
            u--;
            v--;
            adj.get(u).add(v);
        }
        int []visited=new int[A];
        Arrays.fill(visited,-1);
        for(int i=0;i<A;i++) {
            if(visited[i]==-1){
                if(isCycle(i,adj,visited))
                    return 1;
            }
        }
        return 0;
    }
    private boolean isCycle(int s,ArrayList<ArrayList<Integer>> adj, int []visited){
        Queue<Integer> q=new LinkedList<>();
        q.add(s);
        visited[s]=0;
        while(!q.isEmpty()){
            int u=q.poll();
            for(int i=0;i<adj.get(u).size();i++){
                int v=adj.get(u).get(i);
                if(visited[v]==-1){
                    visited[v]=visited[u]+1;
                    q.add(v);
                }else if(visited[v]<visited[u])
                    return true;
            }
        }
        return false;
    }
}
