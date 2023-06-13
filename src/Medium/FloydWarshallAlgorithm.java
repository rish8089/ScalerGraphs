package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FloydWarshallAlgorithm {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> mat=new ArrayList<>();
        for(int i=0;i<n;i++){
            String []str=br.readLine().split(" ");
            ArrayList<Integer> col=new ArrayList<>();
            for(int j=0;j<n;j++)
                col.add(Integer.parseInt(str[j]));
            mat.add(col);
        }
        FloydWarshallAlgorithm obj=new FloydWarshallAlgorithm();
        System.out.println(obj.solve(mat));
    }
    public ArrayList<ArrayList<Integer>> solve(ArrayList<ArrayList<Integer>> A) {
        int n=A.size();
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    //let's find the shortest path from i to j via k
                    if(A.get(i).get(k)!=-1 && A.get(k).get(j)!=-1){
                        int newDist=A.get(i).get(k)+A.get(k).get(j);
                        if(A.get(i).get(j)==-1 || A.get(i).get(j)>newDist)
                            A.get(i).set(j,newDist);
                    }
                }
            }
        }
        return A;
    }
}
