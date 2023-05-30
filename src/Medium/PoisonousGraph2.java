package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class PoisonousGraph2 {
    int MOD=998244353;
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
        PoisonousGraph2 obj=new PoisonousGraph2();
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
        int []level=new int[A];
        Arrays.fill(level,-1);
        int ans=1;
        for(int i=0;i<A;i++) {
            if(level[i]==-1) {
                int ways=bfs(i, A, adjList, level);
                ans=(int)(((long)ans*ways)%MOD);
            }
        }
        return ans;
    }
    private int bfs(int s, int A, ArrayList<ArrayList<Integer>> adjList, int []level){
        Queue<Integer> q=new LinkedList<>();
        level[s]=0;
        q.add(s);
        int o=0,e=1;
        while(!q.isEmpty()){
            int u=q.poll();
            for(int i=0;i<adjList.get(u).size();i++){
                int v=adjList.get(u).get(i);
                if(level[v]==-1){
                    level[v]=level[u]+1;
                    q.add(v);
                    if(level[v]%2==0)
                        e++;
                    else
                        o++;
                }else if(level[v]==level[u])
                    return 0;
            }
        }
        return (mypow(2,o)+mypow(2,e))%MOD;
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
