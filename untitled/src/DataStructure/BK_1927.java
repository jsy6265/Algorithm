package DataStructure;

import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.Collections;
        import java.util.PriorityQueue;
        import java.util.StringTokenizer;

//백준 최소 힙

public class BK_1927 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int num = Integer.parseInt(st.nextToken());
        
        //우선 순위 큐 사용하여 0을 입력받으면 큐에 가장 작은 숫자 출력
        PriorityQueue<Integer> qp = new PriorityQueue<>();

        for (int i = 0; i < num; i++){
            st = new StringTokenizer(bf.readLine());
            int temp = Integer.parseInt(st.nextToken());
            if (temp == 0){
                if (qp.isEmpty()){
                    System.out.println(0);
                }else{
                    System.out.println(qp.poll());
                }
            }else {
                qp.add(temp);
            }
        }
    }
}