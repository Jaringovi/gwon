import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int K = Integer.parseInt(st.nextToken());
        final int[] drinks = new int[N];
        int right = 0;
        for (int i = 0; i < N; i++) {
            drinks[i] = Integer.parseInt(br.readLine());
            right = Math.max(right, drinks[i]);
        }

        // 한 잔의 용량이 정해질 때마다 몇 잔이 나오는지 계산 == O(N) == 10000
        // 잔 수가 K에 근접해질 때까지, 최대 잔의 용량부터 시작해서 이진 탐색 == log(2^31)
        
        int answer = 0;

        int left = 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int k = 0;
            for (int drink : drinks) k += drink / mid;
            // System.out.printf("%d, %d = %d -> %d%n", left, right, mid, k);
            if (k >= K) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }
}