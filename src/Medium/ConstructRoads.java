package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ConstructRoads {
    static int MOD=1000000007;
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
            u--;
            v--;
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        return bfs(0,A,B.size(),adjList);
    }
    private static int bfs(int s, int A, int M, ArrayList<ArrayList<Integer>> adjList){
        boolean []visited=new boolean[A];
        visited[s]=true;
        int odd=0;
        int even=1;
        Queue<Node> q=new LinkedList<>();
        q.add(new Node(s,0));
        while(!q.isEmpty()){
            Node nd=q.poll();
            int u=nd.val;
            for(int i=0;i<adjList.get(u).size();i++){
                int v=adjList.get(u).get(i);
                if(!visited[v]){
                    visited[v]=true;
                    q.add(new Node(v, nd.l+1));
                    if((nd.l+1)%2==0)
                        even++;
                    else
                        odd++;
                }
            }
        }
        long ans=((long)odd*even)%MOD;
        ans=(ans-M+MOD)%MOD;
        return (int)ans;
    }
    static class Node{
        int val;
        int l;
        Node(int val, int l){
            this.val=val;
            this.l=l;
        }
    }
}
