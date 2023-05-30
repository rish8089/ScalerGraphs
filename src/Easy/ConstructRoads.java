package Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ConstructRoads {
    int MOD=(int)1e9+7;
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
        ConstructRoads obj=new ConstructRoads();
        System.out.println(obj.solve(A,B));
    }
    public int solve(int A, ArrayList<ArrayList<Integer>> B){
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
        return bfs(adjList,A);
    }
    private int bfs(ArrayList<ArrayList<Integer>> adjList, int A){
        Queue<Integer> q=new LinkedList<>();
        q.add(0);
        int []level=new int[A];
        Arrays.fill(level,-1);
        level[0]=0;
        while(!q.isEmpty()){
            int u=q.poll();
            for(int i=0;i<adjList.get(u).size();i++){
                int v=adjList.get(u).get(i);
                if(level[v]==-1){
                    level[v]=level[u]+1;
                    q.add(v);
                }
            }
        }
        int o=0;
        int e=0;
        for(int i=0;i<A;i++){
            if(level[i]%2==0)
                e++;
            else
                o++;
        }
        int ans=(int)(((long)o*e)%MOD);
        return (ans-(A-1)+MOD)%MOD;
    }
}
