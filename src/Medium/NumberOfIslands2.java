package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands2 {
    int []X={-1,-1,-1,1,1,1,0,0};
    int []Y={-1,1,0,-1,1,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
            String []str=br.readLine().split(" ");
            for(int j=0;j<m;j++)
                adj.get(i).add(Integer.parseInt(str[j]));
        }
        NumberOfIslands2 obj=new NumberOfIslands2();
        System.out.println(obj.solve(adj));
    }
    public int solve(ArrayList<ArrayList<Integer>> A){
        int n=A.size();
        int m=A.get(0).size();
        boolean [][]visited=new boolean[n][m];
        int cnt=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!visited[i][j] && A.get(i).get(j)==1){
                    bfs(i,j,A,visited);
                    cnt++;
                }
            }
        }
        return cnt;
    }
    private void bfs(int sx, int sy, ArrayList<ArrayList<Integer>> A, boolean [][]visited){
        int n=A.size();
        int m=A.get(0).size();
        visited[sx][sy]=true;
        Queue<Node> q=new LinkedList<>();
        q.add(new Node(sx,sy));
        while(!q.isEmpty()){
            Node nd=q.poll();
            for(int i=0;i<8;i++){
                if(isValid(nd.i,nd.j,X[i],Y[i],visited,n,m,A)){
                    visited[nd.i+X[i]][nd.j+Y[i]]=true;
                    q.add(new Node(nd.i+X[i],nd.j+Y[i]));
                }
            }
        }
    }
    private boolean isValid(int x, int y, int dx, int dy, boolean [][]visited, int n, int m, ArrayList<ArrayList<Integer>> A){
        return x+dx>=0 && x+dx<n && y+dy>=0 && y+dy<m && !visited[x+dx][y+dy] && A.get(x+dx).get(y+dy)==1;
    }
    class Node{
        int i;
        int j;
        Node(int i,int j){
            this.i=i;
            this.j=j;
        }
    }
}
