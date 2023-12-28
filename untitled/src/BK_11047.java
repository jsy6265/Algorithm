import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BK_11047 {
    static int coinNum, won, result;
    static int[] coin;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        coinNum = Integer.parseInt(st.nextToken());

        won = Integer.parseInt(st.nextToken());

        coin = new int[coinNum];

        for (int i = 0; i < coinNum; i++) {
            st = new StringTokenizer(bf.readLine());
            coin[i] = Integer.parseInt(st.nextToken());
        }

        result = 0;

        for (int i = coinNum - 1; i >= 0 ; i--) {
            result += won / coin[i];
            won = won % coin[i];
        }

//        dfs(0, 0);

        System.out.println(result);
    }

    static class Node{
        int won, cnt;

        public Node(int won, int cnt) {
            this.won = won;
            this.cnt = cnt;
        }
    }

    private static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0));

        while (!q.isEmpty()){
            Node now = q.poll();

            if(now.won > won){
                continue;
            }

            if(now.won == won){
                result = now.cnt;
                return;
            }

            for (int i = 0; i < coinNum; i++) {
                q.offer(new Node(now.won + coin[i], now.cnt + 1));
            }
        }
    }

    private static void dfs(int nWon, int cnt) {
        if(won == nWon){
            result = Math.min(cnt, result);
            return;
        }
        if(nWon > won || cnt > result){
            return;
        }
        for(int i = 0; i < coinNum; i++){
            dfs(nWon + coin[i], cnt + 1);
        }
    }
}
