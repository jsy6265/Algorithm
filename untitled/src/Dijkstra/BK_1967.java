package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BK_1967 {
   static class Node{
        int to, weigth;

        public Node(int to, int weigth) {
            this.to = to;
            this.weigth = weigth;
        }
    }
    static int N, maxWeigth;
    static List<List<Node>> list;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(bf.readLine());
            int from, to, weigth;
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            weigth = Integer.parseInt(st.nextToken());


            list.get(from - 1).add(new Node(to - 1, weigth));
            list.get(to - 1).add(new Node(from - 1, weigth));
        }

        maxWeigth = 0;
        for (int i = 0; i < N; i++) {
            boolean[] visited = new boolean[N];
            visited[i] = true;
            dfs(i, 0, visited);
        }

        System.out.println(maxWeigth);
    }

    private static void dfs(int i, int length, boolean[] visited) {
        maxWeigth = Math.max(maxWeigth, length);

        for (int j = 0; j < list.get(i).size(); j++) {

            Node temp =  list.get(i).get(j);
            if(!visited[temp.to]){
                visited[temp.to] = true;
                dfs(temp.to, length + temp.weigth, visited);
                visited[temp.to] = false;
            }


        }
    }

}
