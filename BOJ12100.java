import java.io.*;
import java.util.*;

public class BOJ12100 {
    static int answer = 0;

    private static void dfs(int depth, int N, int[][] board) {
        // System.out.println("depth = " + depth);
        // for (int r = 0; r < N; r++) {
        //     System.out.println(Arrays.toString(board[r]));
        // }
        
        if (depth == 5) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    answer = Math.max(answer, board[r][c]);
                }
            }

            return;
        }

        int[][] newBoard;

        // 좌
        newBoard = new int[N][N];
        for (int r = 0; r < N; r++) {
            int prev = board[r][0];
            int idx = 0;
            for (int c = 1; c < N; c++) {
                int cur = board[r][c];
                if (cur == 0) continue;
                if (prev == cur) {
                    newBoard[r][idx++] = prev << 1;
                    prev = 0;
                } else {
                    if (prev != 0)
                        newBoard[r][idx++] = prev;
                    prev = cur;
                }
            }
            newBoard[r][idx] = prev;
        }
        dfs(depth + 1, N, newBoard);

        // 우
        newBoard = new int[N][N];
        for (int r = 0; r < N; r++) {
            int prev = board[r][N - 1];
            int idx = N - 1;
            for (int c = N - 2; c >= 0; c--) {
                int cur = board[r][c];
                if (cur == 0) continue;
                if (prev == cur) {
                    newBoard[r][idx--] = prev << 1;
                    prev = 0;
                } else {
                    if (prev != 0)
                        newBoard[r][idx--] = prev;
                    prev = cur;
                }
            }
            newBoard[r][idx] = prev;
        }
        dfs(depth + 1, N, newBoard);

        // 상
        newBoard = new int[N][N];
        for (int c = 0; c < N; c++) {
            int prev = board[0][c];
            int idx = 0;
            for (int r = 1; r < N; r++) {
                int cur = board[r][c];
                if (cur == 0) continue;
                if (prev == cur) {
                    newBoard[idx++][c] = prev << 1;
                    prev = 0;
                } else {
                    if (prev != 0)
                        newBoard[idx++][c] = prev;
                    prev = cur;
                }
            }
            newBoard[idx][c] = prev;
        }
        dfs(depth + 1, N, newBoard);

        // 하
        newBoard = new int[N][N];
        for (int c = 0; c < N; c++) {
            int prev = board[N - 1][c];
            int idx = N - 1;
            for (int r = N - 2; r >= 0; r--) {
                int cur = board[r][c];
                if (cur == 0) continue;
                if (prev == cur) {
                    newBoard[idx--][c] = prev << 1;
                    prev = 0;
                } else {
                    if (prev != 0)
                        newBoard[idx--][c] = prev;
                    prev = cur;
                }
            }
            newBoard[idx][c] = prev;
        }
        dfs(depth + 1, N, newBoard);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        final int N = Integer.parseInt(br.readLine());
        final int[][] board = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, N, board);

        System.out.println(answer);
    }
}