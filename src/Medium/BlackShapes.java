package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BlackShapes {
    private static int []X={-1,1,0,0};
    private static int []Y={0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String []str=br.readLine().split(" ");
        int n=Integer.parseInt(str[0]);
        int m=Integer.parseInt(str[1]);
        ArrayList<String> A=new ArrayList<>();
        for(int i=0;i<n;i++){
            String s=br.readLine();
            A.add(s);
        }
        System.out.println(black(A));
    }
    public static int black(ArrayList<String> A) {
        int n=A.size();
        int m=A.get(0).length();
        boolean [][]visited=new boolean[n][m];
        int cnt=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!visited[i][j] && A.get(i).charAt(j)=='X') {
                    dfs(i, j, n,m,A, visited);
                    cnt++;
                }
            }
        }
        return cnt;
    }
    private static void dfs(int i, int j, int n, int m, ArrayList<String> A, boolean [][]visited){
        if(isValid(i,j,n,m,A,visited)){
            visited[i][j]=true;
            for(int k=0;k<4;k++){
                dfs(i+X[k],j+Y[k],n,m,A,visited);
            }
        }
    }
    private static boolean isValid(int i, int j, int n, int m, ArrayList<String> A, boolean [][]visited){
        return i >= 0 && i < n && j >= 0 && j < m && A.get(i).charAt(j) == 'X' && !visited[i][j];
    }
}
