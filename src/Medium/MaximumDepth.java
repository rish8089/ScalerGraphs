package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MaximumDepth {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A=Integer.parseInt(br.readLine());
        ArrayList<Integer> B=new ArrayList<>();
        String []str=br.readLine().split(" ");
        for(int i=0;i<str.length;i++)
            B.add(Integer.parseInt(str[i]));
        ArrayList<Integer> C=new ArrayList<>();
        str=br.readLine().split(" ");
        for(int i=0;i<str.length;i++)
            C.add(Integer.parseInt(str[i]));
        ArrayList<Integer> D=new ArrayList<>();
        str=br.readLine().split(" ");
        for(int i=0;i<str.length;i++)
            D.add(Integer.parseInt(str[i]));
        ArrayList<Integer> E=new ArrayList<>();
        str=br.readLine().split(" ");
        for(int i=0;i<str.length;i++)
            E.add(Integer.parseInt(str[i]));
        ArrayList<Integer> F=new ArrayList<>();
        str=br.readLine().split(" ");
        for(int i=0;i<str.length;i++)
            F.add(Integer.parseInt(str[i]));
        MaximumDepth obj=new MaximumDepth();
        System.out.println(obj.solve(A,B,C,D,E,F));
    }
    public ArrayList<Integer> solve(int A, ArrayList<Integer> B, ArrayList<Integer> C, ArrayList<Integer> D, ArrayList<Integer> E, ArrayList<Integer> F) {
        ArrayList<HashSet<Integer>> adjList=new ArrayList<>();
        for(int i=0;i<A;i++)
            adjList.add(new HashSet<>());
        for(int i=0;i<B.size();i++){
            int u=B.get(i);
            int v=C.get(i);
            u--;
            v--;
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        return bfs(adjList,A,D,E,F);
    }
    private ArrayList<Integer> bfs(ArrayList<HashSet<Integer>> adjList, int A, ArrayList<Integer> D, ArrayList<Integer> E, ArrayList<Integer> F){
        int []level=new int[A];
        Arrays.fill(level,-1);
        Queue<Integer> q=new LinkedList<>();
        q.add(0);
        level[0]=0;
        ArrayList<ArrayList<Integer>> levels=new ArrayList<>();
        while(!q.isEmpty()){
            int u=q.poll();
            if(level[u]>=levels.size())
                levels.add(new ArrayList<>());
            levels.get(level[u]).add(D.get(u));
            for(Integer v:adjList.get(u)){
                if(level[v]==-1) {
                    q.add(v);
                    level[v]=level[u]+1;
                }
            }
        }
        for(int i=0;i<levels.size();i++)
            Collections.sort(levels.get(i));
        ArrayList<Integer> res=new ArrayList<>();
        for(int i=0;i<E.size();i++){
            int levelIdx=E.get(i)%levels.size();
            res.add(binarySearch(levels.get(levelIdx),F.get(i)));
        }
        return res;
    }
    private int binarySearch(ArrayList<Integer> list, int X){
        int u=0;
        int v=list.size()-1;
        int ans=-1;
        while(u<=v){
            int mid=(u+v)/2;
            if(list.get(mid)>=X){
                ans=list.get(mid);
                v=mid-1;
            }else
                u=mid+1;
        }
        return ans;
    }
}
