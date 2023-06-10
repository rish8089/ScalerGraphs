package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class DamagedRoads2 {
    private int MOD=(int)1e9+7;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> A=new ArrayList<>();
        String []str=br.readLine().split(" ");
        for(int i=0;i<str.length;i++)
            A.add(Integer.parseInt(str[i]));
        ArrayList<Integer> B=new ArrayList<>();
        str=br.readLine().split(" ");
        for(int i=0;i<str.length;i++)
            B.add(Integer.parseInt(str[i]));
        DamagedRoads obj=new DamagedRoads();
        System.out.println(obj.solve(A,B));
    }
    public int solve(ArrayList<Integer> A, ArrayList<Integer> B) {
        int n=A.size();
        int m=B.size();
        int ans=0;
        for(int i=0;i<n;i++)
            ans=(ans+A.get(i))%MOD;
        for(int i=0;i<m;i++)
            ans=(ans+B.get(i))%MOD;
        Collections.sort(A);
        int []dp=new int[n];
        for(int i=0;i<n;i++)
            dp[i]=(A.get(i)+(i-1>=0?dp[i-1]:0))%MOD;
        for(int i=0;i<m;i++){
            int pos=binarySearch(A,B.get(i));
            ans=(ans+(pos>=0?dp[pos]:0))%MOD;
            ans=(ans+((n-pos-1)*B.get(i))%MOD)%MOD;
        }
        return ans;
    }
    private int binarySearch(ArrayList<Integer> A, int val){
        int u=0;
        int v=A.size()-1;
        int pos=-1;
        while(u<=v){
            int mid=(u+v)/2;
            if(A.get(mid)<val){
                pos=mid;
                u=mid+1;
            }else
                v=mid-1;
        }
        return pos;
    }

}
