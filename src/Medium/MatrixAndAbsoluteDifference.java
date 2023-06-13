package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MatrixAndAbsoluteDifference {
    int []X={-1,0,0,1};
    int []Y={0,-1,1,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int A=Integer.parseInt(br.readLine());
        int B=Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> C=new ArrayList<>();
        for(int i=0;i<A;i++){
            String []str=br.readLine().split(" ");
            ArrayList<Integer> list=new ArrayList<>();
            for(int j=0;j<B;j++)
                list.add(Integer.parseInt(str[j]));
            C.add(list);
        }
        MatrixAndAbsoluteDifference obj=new MatrixAndAbsoluteDifference();
        System.out.println(obj.solve(A,B,C));
    }
    public int solve(int A, int B, ArrayList<ArrayList<Integer>> C) {
        int ans=-1;
        int u=0;
        int v=(int)1e9;
        while(u<=v){
            int mid=(u+v)/2;
            if(isPossible(mid,A,B,C)){
                ans=mid;
                v=mid-1;
            }else{
                u=mid+1;
            }
        }
        return ans;
    }
    private boolean isPossible(int k, int A, int B, ArrayList<ArrayList<Integer>> C){
        Queue<Coordinate> q=new LinkedList<>();
        Coordinate s=new Coordinate(0,0);
        HashSet<Coordinate> set=new HashSet<>();
        q.add(s);
        set.add(s);
        while(!q.isEmpty()){
            Coordinate u=q.poll();
            for(int i=0;i<4;i++){
                if(isValid(u.i,u.j,X[i],Y[i],k,A,B,set,C)){
                    Coordinate v=new Coordinate(u.i+X[i],u.j+Y[i]);
                    q.add(v);
                    set.add(v);
                }
            }
        }
        return set.size()==A*B;
    }
    private boolean isValid(int x, int y, int dx, int dy, int k, int A, int B, HashSet<Coordinate> set, ArrayList<ArrayList<Integer>> C){
        return x+dx>=0 && x+dx<A && y+dy>=0 && y+dy<B && !set.contains(new Coordinate(x+dx,y+dy)) && Math.abs(C.get(x+dx).get(y+dy)-C.get(x).get(y))<=k;
    }

    class Coordinate{
        int i;
        int j;
        Coordinate(int i, int j){
            this.i=i;
            this.j=j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return i == that.i && j == that.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }

}
