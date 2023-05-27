package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ValidPath2 {
    int []X={-1,-1,-1,1,1,1,0,0};
    int []Y={-1,1,0,-1,1,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x=Integer.parseInt(br.readLine());
        int y=Integer.parseInt(br.readLine());
        int n=Integer.parseInt(br.readLine());
        int r=Integer.parseInt(br.readLine());
        ArrayList<Integer> E=new ArrayList<>();
        String []str=br.readLine().split(" ");
        for(int i=0;i<n;i++)
            E.add(Integer.parseInt(str[i]));
        ArrayList<Integer> F=new ArrayList<>();
        str=br.readLine().split(" ");
        for(int i=0;i<n;i++)
            F.add(Integer.parseInt(str[i]));
        ValidPath2 obj=new ValidPath2();
        System.out.println(obj.solve(x,y,n,r,E,F));
    }
    public String solve(int A, int B, int C, int D, ArrayList<Integer> E, ArrayList<Integer> F){
        boolean [][]visited=new boolean[A+1][B+1];
        return dfs(A,B,0,0,C,D,E,F,visited)?"YES":"NO";
    }

    private boolean dfs(int destx, int desty, int srcx, int srcy, int n, int r, ArrayList<Integer> E, ArrayList<Integer> F, boolean[][] visited) {
        if (!isValid(srcx, srcy, visited, n, r, E, F, destx + 1, desty + 1))
            return false;
        visited[srcx][srcy] = true;
        if (srcx == destx && srcy == desty)
            return true;
        for (int i = 0; i < 8; i++) {
            boolean res = dfs(destx, desty, srcx + X[i], srcy + Y[i], n, r, E, F, visited);
            if (res)
                return true;
        }
        return false;
    }
    private boolean isValid(int x,int y,boolean [][]visited, int nc, int r, ArrayList<Integer> E, ArrayList<Integer> F, int n, int m){
        if(x<0 || x>=n || y<0 || y>=m || visited[x][y])
            return false;
        for(int i=0;i<nc;i++){
            int cx=E.get(i);
            int cy=F.get(i);
            if((x-cx)*(x-cx)+(y-cy)*(y-cy)-r*r<=0)
                return false;
        }
        return true;
    }
}
