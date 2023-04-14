package Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CycleInDirectedGraph {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        ArrayList<ArrayList<Integer>> B = new ArrayList<>();
        for(int i=0;i<m;i++){
            String []str=br.readLine().split(" ");
            int u=Integer.parseInt(str[0]);
            int v=Integer.parseInt(str[1]);
            ArrayList<Integer> list=new ArrayList<>();
            list.add(u);
            list.add(v);
            B.add(list);
        }
        System.out.println(solve(n,B));
    }
    private static int solve(int A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<ArrayList<Integer>> adjList=new ArrayList<>();
        for(int i=0;i<A;i++)
            adjList.add(new ArrayList<>());
        for(int i=0;i<B.size();i++){
            int u=B.get(i).get(0)-1;
            int v=B.get(i).get(1)-1;
            adjList.get(u).add(v);
        }
        boolean []visited=new boolean[A];
        boolean flag=false;
        for(int i=0;i<A;i++){
            if(!visited[i]){
                visited[i]=true;
                if(isCycle(i, visited, adjList)) {
                    flag = true;
                    break;
                }
            }
        }
        return flag?1:0;
    }
    private static boolean isCycle(int u, boolean []visited, ArrayList<ArrayList<Integer>> adjList){
        for(int i=0;i<adjList.get(u).size();i++){
            int v=adjList.get(u).get(i);
            if(!visited[v]){
                visited[v]=true;
            }else
                return true;
        }
        return false;
    }
}
