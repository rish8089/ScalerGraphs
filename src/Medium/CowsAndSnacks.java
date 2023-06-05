package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class CowsAndSnacks {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A=Integer.parseInt(br.readLine());
        int m1=Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> B=new ArrayList<>();
        for(int i=0;i<m1;i++){
            String []str=br.readLine().split(" ");
            int u=Integer.parseInt(str[0]);
            int v=Integer.parseInt(str[1]);
            ArrayList<Integer> list=new ArrayList<>();
            list.add(u);
            list.add(v);
            B.add(list);
        }
        CowsAndSnacks obj=new CowsAndSnacks();
        System.out.println(obj.solve(A,B));
    }
    public int solve(int A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<HashSet<Integer>> adjList=new ArrayList<>();
        for(int i=0;i<A;i++)
            adjList.add(new HashSet<>());
        for(int i=0;i<B.size();i++){
            int u=B.get(i).get(0);
            int v=B.get(i).get(1);
            u--;
            v--;
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        boolean []visited=new boolean[A];
        int ans=0;
        for(int i=0;i<A;i++){
            if(!visited[i]){
                ans+=bfs(adjList,i,visited)-1;
            }
        }
        return B.size()-ans;
    }
    private int bfs(ArrayList<HashSet<Integer>> adjList, int s, boolean []visited){
        Queue<Integer> q=new LinkedList<>();
        q.add(s);
        visited[s]=true;
        int cnt=0;
        while(!q.isEmpty()){
            int u=q.poll();
            cnt++;
            for(Integer v:adjList.get(u)){
                if(!visited[v]){
                    q.add(v);
                    visited[v]=true;
                }
            }
        }
        return cnt;
    }
}
