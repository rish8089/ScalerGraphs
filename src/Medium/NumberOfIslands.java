package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NumberOfIslands {
    static int[] X = {-1, -1, -1, 1, 1, 1, 0, 0};
    static int[] Y = {0, 1, -1, 0, -1, 1, -1, 1};
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
        System.out.println(solve(adj));
    }
    public static int solve(ArrayList<ArrayList<Integer>> A) {
        int n=A.size();
        int m=A.get(0).size();
        boolean [][]visited=new boolean[n][m];
        int cnt=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(A.get(i).get(j)==1 && !visited[i][j]){
                    visited[i][j]=true;
                    dfs(i,j,A,n,m,visited);
                    cnt++;
                }
            }
        }
        return cnt;
    }
    private static void dfs(int i, int j, ArrayList<ArrayList<Integer>> A, int n, int m, boolean [][]visited){
        for(int k=0;k<8;k++){
            if(i+X[k]>=0 && i+X[k]<n && j+Y[k]>=0 && j+Y[k]<m && A.get(i+X[k]).get(j+Y[k])==1 && !visited[i+X[k]][j+Y[k]]){
                visited[i+X[k]][j+Y[k]]=true;
                dfs(i+X[k], j+Y[k], A, n, m, visited);
            }
        }
    }
}
