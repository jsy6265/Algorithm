package DivideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 색종이 만들기
public class BK_2630 {
    static int red;
    static int blue;
    static int[][] paper;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());

        paper = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        red = 0;
        blue = 0;

        cheakRange(0, 0, N);
        System.out.println(red);
        System.out.println(blue);
    }
    
    //범위를 입력 받아 범위 안에 모든 색이 같은지 체크 후 같지 않으면 4등분 후 재귀 호출
    private static void cheakRange(int row, int col, int size) {

        boolean isSame = false;
        int first = paper[row][col];
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (paper[i][j] != first) {
                    isSame = true;
                    break;
                }
            }
        }
        if (!isSame) {
            cheakColor(row, col);
        } else {
            //size를 2로 나누어 다음 호출되는 범위의 크기를 1/4으로 바꿈
            int newSize = size / 2;
            //1사분면
            cheakRange(row, col, newSize);
            //3사분면
            cheakRange(row, col + newSize, newSize);
            //2사분면
            cheakRange(row + newSize, col, newSize);
            //4사분면
            cheakRange(row + newSize, col + newSize, newSize);
        }

    }
    
    //어떤 색인지 체크
    private static void cheakColor(int x, int y) {
        if (paper[x][y] == 1) {
            blue++;
        } else {
            red++;
        }
    }
}