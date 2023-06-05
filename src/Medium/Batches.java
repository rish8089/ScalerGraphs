package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Batches {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A=Integer.parseInt(br.readLine());
        int m=Integer.parseInt(br.readLine());
        ArrayList<Integer> B=new ArrayList<>();
        String []str=br.readLine().split(" ");
        for(int i=0;i<A;i++)
            B.add(Integer.parseInt(str[i]));
        ArrayList<ArrayList<Integer>> C=new ArrayList<>();
        for(int i=0;i<m;i++){
            str=br.readLine().split(" ");
            int u=Integer.parseInt(str[0]);
            int v=Integer.parseInt(str[1]);
            ArrayList<Integer> list=new ArrayList<>();
            list.add(u);
            list.add(v);
            C.add(list);
        }
        Batches obj=new Batches();
        System.out.println(obj.solve(A,B,C,Integer.parseInt(br.readLine())));
    }
    public int solve(int A, ArrayList<Integer> B, ArrayList<ArrayList<Integer>> C, int D) {
        ArrayList<HashSet<Integer>> adjList=new ArrayList<>();
        for(int i=0;i<A;i++)
            adjList.add(new HashSet<>());
        for(int i=0;i<C.size();i++){
            int u=C.get(i).get(0);
            int v=C.get(i).get(1);
            u--;
            v--;
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        boolean []visited=new boolean[A];
        int cnt=0;
        for(int i=0;i<A;i++){
            if(!visited[i]) {
                int sum=bfs(adjList, i, B, visited);
                cnt+=sum>=D?1:0;
            }
        }
        return cnt;
    }
    private int bfs(ArrayList<HashSet<Integer>> adjList, int s, ArrayList<Integer> B, boolean []visited){
        Queue<Integer> q=new LinkedList<>();
        q.add(s);
        visited[s]=true;
        int sum=0;
        while(!q.isEmpty()){
            int u=q.poll();
            sum+=B.get(u);
            for(Integer v:adjList.get(u)){
                if(!visited[v]){
                    q.add(v);
                    visited[v]=true;
                }
            }
        }
        return sum;
    }
}
