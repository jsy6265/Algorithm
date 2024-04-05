package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BK_1504 {
    static class Node {
        int to, dist;

        public Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    static int N, E, v1, v2;
    static List<List<Node>> list;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list.get(from).add(new Node(to, w));
            list.get(to).add(new Node(from, w));
        }

        st = new StringTokenizer(bf.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        int[] arr1 = dijk(1);
        // 1 -> v1 가능 비용
        int stv1 = arr1[v1];
        // 1 -> v2 가는 비용
        int stv2 = arr1[v2];

        int[] arr2 = dijk(v1);
        // v1 -> v2 가는 비용
        int v1tv2 = arr2[v2];
        // v1 -> N 가는 비용
        int v1tn = arr2[N];

        int[] arr3 = dijk(v2);
        // v2 -> v1 가는 비용
        int v2tv1 = arr2[v2];
        // v2 -> N 가는 비용
        int v2tn = arr3[N];

        // 1 -> v1 -> v2 -> N
        int v1v2 = stv1 + v1tv2 + v2tn;
        //1 -> v2 -> v1 -> N
        int v2v1 = stv2 + v2tv1 + v1tn;
//
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println("v1v2 = " + stv1 + " + " + v1tv2 + " + " + v2tn + " = " + v1v2);
//        System.out.println("v2v1 = " + stv2 + " + " + v2tv1 + " + " + v1tn + " = " + v2v1);

        if(v1v2 > Integer.MAX_VALUE - 3 || v2v1 > Integer.MAX_VALUE - 3 || v2v1 < 0 || v1v2 < 0){
            System.out.println(-1);
        }else{
            System.out.println(Math.min(v1v2, v2v1));
        }
    }

    private static int[] dijk(int start) {
        boolean[] visited = new boolean[N + 1];
        int[] dist = new int[N + 1];

        //거리 배열 무한대로 초기화
        for (int i = 0; i < N + 1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        //시작점 0으로 초기화
        dist[start] = 0;

        for (int i = 0; i < N; i++) {
            //비용 초기화
            int nodeVal = Integer.MAX_VALUE;
            //현재 노드 초기화
            int nodeIdx = 0;

            //방문하지 않은 노드 중 가장 작은 값을 가진 노드 찾기
            for (int j = 1; j < N + 1; j++) {
                if (!visited[j] && dist[j] < nodeVal) {
                    nodeIdx = j;
                    nodeVal = dist[j];
                }
            }

            //위에 for문에서 찾은 nodeIdx 방문처리
            visited[nodeIdx] = true;

            //현재 찾은 정점에서 연결된 간선들의 비용을 dist 배열의 nodeIdx값보다 작으면 최신호
            for (int j = 0; j < list.get(nodeIdx).size(); j++) {
                Node adj = list.get(nodeIdx).get(j);

                //nodeIdx에서 adj로 연결된 adj.to라는 정점의 dist값이 nodeVal + adj.w의 값보다 크면 최단거리를 수정한다
                if (dist[adj.to] > dist[nodeIdx] + adj.dist) {
                    dist[adj.to] = dist[nodeIdx] + adj.dist;
                }
            }
        }

        return dist;
    }
}
