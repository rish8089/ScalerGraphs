package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FirstDepthFirstSearch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        ArrayList<Integer> A=new ArrayList<>();
        String []str=br.readLine().split(" ");
        for(int i=0;i<n;i++)
            A.add(Integer.parseInt(str[i]));
        int B=Integer.parseInt(br.readLine());
        int C=Integer.parseInt(br.readLine());
        System.out.println(solve(A,B,C));
    }
    public static int solve(ArrayList<Integer> A, final int B, final int C) {
        int n=A.size();
        ArrayList<ArrayList<Integer>> adjList=new ArrayList<>();
        for(int i=0;i<n;i++)
            adjList.add(new ArrayList<>());
        for(int i=1;i<n;i++)
            adjList.get(A.get(i)-1).add(i);
        return isReachable(adjList, B-1, C-1)?1:0;
    }
    private static boolean isReachable(ArrayList<ArrayList<Integer>> adjList, int B, int C){
        if(C==B)
            return true;
        for(int i=0;i<adjList.get(C).size();i++){
            boolean res=isReachable(adjList, B, adjList.get(C).get(i));
            if(res)
                return true;
        }
        return false;
    }
}
