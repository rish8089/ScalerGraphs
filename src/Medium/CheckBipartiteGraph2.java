package Medium;

import Easy.PathInDirectedGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CheckBipartiteGraph2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A=Integer.parseInt(br.readLine());
        int m=Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> B=new ArrayList<>();
        for(int i=0;i<m;i++){
            String []str=br.readLine().split(" ");
            int u=Integer.parseInt(str[0]);
            int v=Integer.parseInt(str[1]);
            ArrayList<Integer> list=new ArrayList<>();
            list.add(u);
            list.add(v);
            B.add(list);
        }
        CheckBipartiteGraph2 obj=new CheckBipartiteGraph2();
        System.out.println(obj.solve(A,B));
    }
    public int solve(int A, ArrayList<ArrayList<Integer>> B){
        ArrayList<ArrayList<Integer>> adjList=new ArrayList<>();
        for(int i=0;i<A;i++)
            adjList.add(new ArrayList<>());
        for(int i=0;i<B.size();i++){
            int u=B.get(i).get(0);
            int v=B.get(i).get(1);
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        int []visited=new int[A];
        Arrays.fill(visited,-1);
        for(int i=0;i<A;i++){
            if(visited[i]==-1){
                if(!bfs(adjList,A,visited,i))
                    return 0;
            }

        }
        return 1;
    }
    private boolean bfs(ArrayList<ArrayList<Integer>> adjList, int A, int []visited, int s){
        Queue<Integer> q=new LinkedList<>();
        visited[s]=0;
        q.add(s);
        while(!q.isEmpty()){
            int u=q.poll();
            for(int i=0;i<adjList.get(u).size();i++){
                int v=adjList.get(u).get(i);
                if(visited[v]==-1){
                    visited[v]=visited[u]+1;
                    q.add(v);
                }else if(visited[v]==visited[u])
                    return false;
            }
        }
        return true;
    }
}
