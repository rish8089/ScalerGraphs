package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TopologicalSort {
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
        TopologicalSort obj=new TopologicalSort();
        System.out.println(obj.solve(A,B));
    }
    public ArrayList<Integer> solve(int A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<HashSet<Integer>> adjList=new ArrayList<>();
        for(int i=0;i<A;i++)
            adjList.add(new HashSet<>());
        for(int i=0;i<B.size();i++){
            int u=B.get(i).get(0);
            int v=B.get(i).get(1);
            u--;
            v--;
            adjList.get(u).add(v);
        }
        return bfs(adjList,A);
    }
    private ArrayList<Integer> bfs(ArrayList<HashSet<Integer>> adjList, int A){
        boolean []visited=new boolean[A];
        int []inwardEdges=new int[A];
        for(int i=0;i<adjList.size();i++){
            for(Integer elem:adjList.get(i))
                inwardEdges[elem]++;
        }
        Queue<Integer> q=new PriorityQueue<>();
        for(int i=0;i<A;i++){
            if(inwardEdges[i]==0) {
                q.add(i);
                visited[i]=true;
            }
        }
        ArrayList<Integer> res=new ArrayList<>();
        while(!q.isEmpty()){
            int u=q.poll();
            res.add(u+1);
            for(Integer v:adjList.get(u)){
                if(!visited[v]) {
                    inwardEdges[v]--;
                    if (inwardEdges[v] == 0) {
                        q.add(v);
                        visited[v]=true;
                    }
                }
            }
        }
        for(int i=0;i<A;i++){
            if(!visited[i])
                return new ArrayList<>();
        }
        return res;
    }
}
