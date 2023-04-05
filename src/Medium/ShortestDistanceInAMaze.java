//package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ShortestDistanceInAMaze {
    static int[] X = {-1, 1, 0, 0};
    static int[] Y = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j < m; j++)
                list.add(Integer.parseInt(str[j]));
            adj.add(list);
        }
        ArrayList<Integer> source = new ArrayList<>();
        String[] str = br.readLine().split(" ");
        source.add(Integer.parseInt(str[0]));
        source.add(Integer.parseInt(str[1]));
        ArrayList<Integer> destination = new ArrayList<>();
        str = br.readLine().split(" ");
        destination.add(Integer.parseInt(str[0]));
        destination.add(Integer.parseInt(str[1]));
        System.out.println(solve(adj, source, destination));
    }

    public static int solve(ArrayList<ArrayList<Integer>> A, ArrayList<Integer> B, ArrayList<Integer> C) {
        Queue<Node> q = new LinkedList<>();
        //source
        int sx = B.get(0);
        int sy = B.get(1);
        //destination
        int dx = C.get(0);
        int dy = C.get(1);
        int n = A.size();
        int m = A.get(0).size();
        boolean[][][] visited = new boolean[n][m][4];
        //starting position itself is the wall
        if (A.get(sx).get(sy) == 1)
            return -1;
        q.add(new Node(sx, sy, 0));
        Arrays.fill(visited[sx][sy], true);
        int ans = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Node nd = q.poll();
            if (nd.i == dx && nd.j == dy) {
                ans = Math.min(ans, nd.l);
            }
            for (int k = 0; k < 4; k++) {
                int adjX = nd.i;
                int adjY = nd.j;
                while (isValid(adjX + X[k], adjY + Y[k], k, visited, A)) {
                    adjX += X[k];
                    adjY += Y[k];
                    visited[adjX][adjY][k] = true;
                }
                if ((adjX != nd.i || adjY != nd.j) && isWall(adjX, adjY, k, A)) {
                    q.add(new Node(adjX, adjY, nd.l + Math.abs(adjX - nd.i) + Math.abs(adjY - nd.j)));
                }
            }

        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


    private static boolean isValid(int x, int y, int k, boolean[][][] visited, ArrayList<ArrayList<Integer>> A) {
        int n = A.size();
        int m = A.get(0).size();
        return x >= 0 && x < n && y >= 0 && y < m && !visited[x][y][k] && A.get(x).get(y) == 0;
    }

    private static boolean isWall(int x, int y, int k, ArrayList<ArrayList<Integer>> A) {
        int n = A.size();
        int m = A.get(0).size();
        return x + X[k] < 0 || x + X[k] >= n || y + Y[k] < 0 || y + Y[k] >= m || A.get(x + X[k]).get(y + Y[k]) == 1;
    }

    static class Node {
        int i;
        int j;
        int l;

        Node(int i, int j, int l) {
            this.i = i;
            this.j = j;
            this.l = l;
        }
    }
}
