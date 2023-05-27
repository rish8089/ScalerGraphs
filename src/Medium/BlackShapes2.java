package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BlackShapes2 {
    int []X={-1,1,0,0};
    int []Y={0,0,1,-1};
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
        BlackShapes2 obj=new BlackShapes2();
        System.out.println(obj.black(A));
    }
    public int black(ArrayList<String> A){
        int n=A.size();
        int m=A.get(0).length();
        int cnt=0;
        boolean [][]visited=new boolean[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!visited[i][j] && A.get(i).charAt(j)=='X'){
                    cnt++;
                    dfs(i,j,visited,A);
                }
            }
        }
        return cnt;
    }
    private void dfs(int ux, int uy, boolean [][]visited, ArrayList<String> A){
        int n=A.size();
        int m=A.get(0).length();
        visited[ux][uy]=true;
        for(int i=0;i<4;i++){
            if(isValid(ux,uy,X[i],Y[i],visited,A,n,m)){
                dfs(ux+X[i],uy+Y[i],visited,A);
            }
        }
    }
    private boolean isValid(int x, int y, int dx,int dy, boolean [][]visited,ArrayList<String> A, int n, int m){
        return x+dx>=0 && x+dx<n && y+dy>=0 && y+dy<m && !visited[x+dx][y+dy] && A.get(x+dx).charAt(y+dy)=='X';
    }
}
