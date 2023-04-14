package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CheckBipartiteGraph {
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
        System.out.println(solve(A,B));
    }
    public static int solve(int A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<ArrayList<Integer>> adjList=new ArrayList<>();
        for(int i=0;i<A;i++)
            adjList.add(new ArrayList<>());
        for(int i=0;i<B.size();i++){
            int u=B.get(i).get(0);
            int v=B.get(i).get(1);
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        boolean flag=true;
        int []level=new int[A];
        Arrays.fill(level, -1);
        for(int i=0;i<A;i++){
            if(level[i]==-1){
                if(!isBipartite(i, adjList, level)){
                    flag=false;
                    break;
                }
            }
        }
        return flag?1:0;
    }
    private static boolean isBipartite(int s, ArrayList<ArrayList<Integer>> adjList, int []level) {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        level[s] = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int i = 0; i < adjList.get(u).size(); i++) {
                int v = adjList.get(u).get(i);
                if(level[v]==-1){
                    level[v]=level[u]+1;
                    q.add(v);
                }else{
                    if(level[u]==level[v])
                        return false;
                }
            }
        }
        return true;
    }
}
