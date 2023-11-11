import java.util.Arrays;

public class a {
    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 3, 10, 12, 11};
        Arrays.sort(arr, 4, 7);//배열의 4~6번째 인덱스만 오름차순으로 정렬

        for (int i = 0; i < 7; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
