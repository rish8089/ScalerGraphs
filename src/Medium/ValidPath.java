package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ValidPath {
    static int []X={-1,-1,-1,1,1,1,0,0};
    static int []Y={0,-1,1,0,-1,1,-1,1};
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
        System.out.println(solve(x,y,n,r,E,F));
    }
    public static String solve(int A, int B, int C, int D, ArrayList<Integer> E, ArrayList<Integer> F) {
        boolean [][]visited=new boolean[A+1][B+1];
        return isReachable(-1,-1,A,B,C,D,E,F,visited)?"YES":"NO";
    }
    private static boolean isReachable(int sx, int sy, int dx, int dy, int n, int r, ArrayList<Integer> E, ArrayList<Integer> F, boolean [][]visited){
        if(sx==dx && sy==dy)
            return true;
        for(int i=0;i<8;i++){
            if(isValid(sx,sy,dx,dy,i,n,r,E,F,visited)){
                visited[sx+X[i]][sy+Y[i]]=true;
                boolean res=isReachable(sx+X[i],sy+Y[i],dx,dy,n,r,E,F,visited);
                if(res)
                    return true;
            }
        }
        return false;
    }
    private static boolean isValid(int sx, int sy, int dx, int dy, int k, int n, int r, ArrayList<Integer> E, ArrayList<Integer> F, boolean [][]visited){
        int x=sx+X[k];
        int y=sy+Y[k];
        //boundary and visited conditions
        if(x<0 || x>dx || y<0 || y>dy || visited[x][y])
            return false;
        //crossing any circle
        for(int i=0;i<n;i++){
            int cx=E.get(i);
            int cy=F.get(i);
            if((x-cx)*(x-cx)+(y-cy)*(y-cy)-(r*r)<=0)
                return false;
        }
        return true;
    }
}
