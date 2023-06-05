package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PossibilityOfFinishing {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A=Integer.parseInt(br.readLine());
        int m=Integer.parseInt(br.readLine());
        ArrayList<Integer> B=new ArrayList<>();
        ArrayList<Integer> C=new ArrayList<>();
        for(int i=0;i<m;i++){
            String []str=br.readLine().split(" ");
            int u=Integer.parseInt(str[0]);
            int v=Integer.parseInt(str[1]);
            B.add(u);
            C.add(v);
        }
        PossibilityOfFinishing obj=new PossibilityOfFinishing();
        System.out.println(obj.solve(A,B,C));
    }
    public int solve(int A, ArrayList<Integer> B, ArrayList<Integer> C) {
        ArrayList<HashSet<Integer>> adjList=new ArrayList<>();
        for(int i=0;i<A;i++)
            adjList.add(new HashSet<>());
        for(int i=0;i<B.size();i++){
            int u=B.get(i);
            int v=C.get(i);
            u--;
            v--;
            adjList.get(u).add(v);
        }
        return bfs(adjList,A);
    }
    private int bfs(ArrayList<HashSet<Integer>> adjList, int A){
        boolean []visited=new boolean[A];
        int []inwardEdges=new int[A];
        for(int i=0;i<adjList.size();i++){
            for(Integer elem:adjList.get(i))
                inwardEdges[elem]++;
        }
        Queue<Integer> q=new LinkedList<>();
        for(int i=0;i<A;i++){
            if(inwardEdges[i]==0) {
                q.add(i);
                visited[i]=true;
            }
        }
        while(!q.isEmpty()){
            int u=q.poll();
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
                return 0;
        }
        return 1;
    }
}
