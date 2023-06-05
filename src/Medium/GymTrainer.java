package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class GymTrainer {
    private int MOD=(int)1e9+7;
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
        int m2=Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> C=new ArrayList<>();
        for(int i=0;i<m2;i++){
            String []str=br.readLine().split(" ");
            int u=Integer.parseInt(str[0]);
            int v=Integer.parseInt(str[1]);
            ArrayList<Integer> list=new ArrayList<>();
            list.add(u);
            list.add(v);
            C.add(list);
        }
        GymTrainer obj=new GymTrainer();
        System.out.println(obj.solve(A,B,C));
    }
    public int solve(int A, ArrayList<ArrayList<Integer>> B, ArrayList<ArrayList<Integer>> C) {
        ArrayList<HashSet<Integer>> adjList=new ArrayList<>();
        for(int i=0;i<A;i++)
            adjList.add(new HashSet<>());
        HashSet<Integer> talk=new HashSet<>();
        //talk
        for(int i=0;i<B.size();i++){
            int u=B.get(i).get(0);
            int v=B.get(i).get(1);
            u--;
            v--;
            adjList.get(u).add(v);
            adjList.get(v).add(u);
            talk.add(u);
            talk.add(v);
        }
        //walk
        for(int i=0;i<C.size();i++){
            int u=C.get(i).get(0);
            int v=C.get(i).get(1);
            u--;
            v--;
            if(talk.contains(u) || talk.contains(v))
                return 0;
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        boolean []visited=new boolean[A];
        int cnt=0;
        for(int i=0;i<A;i++){
            if(!visited[i]){
                bfs(adjList,visited,i);
                cnt++;
            }
        }
        return mypow(2,cnt);
    }
    private void bfs(ArrayList<HashSet<Integer>> adjList, boolean []visited, int s){
        Queue<Integer> q=new LinkedList<>();
        q.add(s);
        visited[s]=true;
        while(!q.isEmpty()){
            int u=q.poll();
            for(Integer v:adjList.get(u)){
                if(!visited[v]){
                    q.add(v);
                    visited[v]=true;
                }
            }
        }
    }
    private int mypow(int a, int b){
        int res=1;
        while(b>0){
            if(b%2!=0)
                res=(int)(((long)res*a)%MOD);
            b/=2;
            a=(int)(((long)a*a)%MOD);
        }
        return res;
    }
}
