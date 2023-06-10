package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class AnotherBFS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A=Integer.parseInt(br.readLine());
        int m=Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> B=new ArrayList<>();
        for(int i=0;i<m;i++){
            String []str=br.readLine().split(" ");
            int u=Integer.parseInt(str[0]);
            int v=Integer.parseInt(str[1]);
            int w=Integer.parseInt(str[2]);
            ArrayList<Integer> list=new ArrayList<>();
            list.add(u);
            list.add(v);
            list.add(w);
            B.add(list);
        }
        AnotherBFS obj=new AnotherBFS();
        System.out.println(obj.solve(A,B,Integer.parseInt(br.readLine()),Integer.parseInt(br.readLine())));
    }
    public int solve(int A, ArrayList<ArrayList<Integer>> B, int C, int D) {
        ArrayList<ArrayList<Integer>> adjList=new ArrayList<>();
        for(int i=0;i<A;i++)
            adjList.add(new ArrayList<>());
        for(int i=0;i<B.size();i++){
            int u=B.get(i).get(0);
            int v=B.get(i).get(1);
            int w=B.get(i).get(2);
            if(w==2) {
                adjList.add(new ArrayList<>());
                adjList.get(u).add(A);
                adjList.get(A).add(u);
                adjList.get(A).add(v);
                adjList.get(v).add(A);
                A++;
            }else{
                adjList.get(u).add(v);
                adjList.get(v).add(u);
            }
        }
        return bfs(adjList,A,C,D);
    }
    private int bfs(ArrayList<ArrayList<Integer>> adjList, int A, int C, int D){
        int []level=new int[A];
        Arrays.fill(level,-1);
        Queue<Integer> q=new LinkedList<>();
        q.add(C);
        level[C]=0;
        while(!q.isEmpty()){
            int u=q.poll();
            if(u==D)
                return level[u];
            for(int i=0;i<adjList.get(u).size();i++){
                int v=adjList.get(u).get(i);
                if(level[v]==-1){
                    level[v]=level[u]+1;
                    q.add(v);
                }
            }
        }
        return -1;
    }
}
