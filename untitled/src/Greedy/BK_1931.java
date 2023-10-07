package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//백준 회의실 배정
public class BK_1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int num = Integer.parseInt(st.nextToken());
        //회의 별 시작시간 종료시간
        int[][] arr = new int[num][2];

        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(bf.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            arr[i][0] = s;
            arr[i][1] = e;
        }
        
        //회의 종료 시간으로 정렬
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });

       for (int[] temp : arr){
           System.out.println("[ " + temp[0] + " , " + temp[1] + " ]");
       }

        int result = 0;
        //최종 스케줄표에 추가된 회의의 종료시간
        int endTime = 0;

        for (int i = 0; i < num; i++) {
            //현재 일정의 시작 시간이 최종 스케줄 표의 종료시간 보다 늦으면 스케줄 추가
            if (endTime <= arr[i][0]) {
                endTime = arr[i][1];
                result++;
            }
        }
        System.out.println(result);
    }

}