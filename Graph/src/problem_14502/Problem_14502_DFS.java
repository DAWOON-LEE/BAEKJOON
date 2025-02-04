package problem_14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem_14502_DFS {

    private static class Point {
        private int row;
        private int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static int N, M;
    private static int[][] lab;
    private static List<Point> virusList;
    private static int maxSafety = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        lab = new int[N][M];
        virusList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(input.readLine());

            for (int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(tokenizer.nextToken());

                if (lab[i][j] == 2) {
                    virusList.add(new Point(i, j));
                }
            }
        }

        int[] selected = new int[3];
        selectWall(selected, 0, 0);
        System.out.println(maxSafety);
    }

    private static void selectWall(int[] selected, int start, int cnt) {
        if (cnt == 3) {
            installWall(selected);
            maxSafety = Math.max(maxSafety, getEmptySpace());
            uninstallWall(selected);
            return;
        }

        for (int size = N * M, i = start; i < size; i++) {
            if (lab[i / M][i % M] != 0) {
                continue;
            }

            selected[cnt] = i;
            selectWall(selected, i + 1, cnt + 1);
        }
    }

    private static void installWall(int[] selected) {
        for (int pos : selected) {
            int row = pos / M;
            int col = pos % M;

            lab[row][col] = 1;
        }
    }

    private static void uninstallWall(int[] selected) {
        for (int pos : selected) {
            int row = pos / M;
            int col = pos % M;

            lab[row][col] = 0;
        }
    }

    private static int getEmptySpace() {
        for (Point virus : virusList) {
            spreadVirus(virus.row, virus.col);
        }

        int emptySpace = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (lab[i][j] == 0) {
                    emptySpace++;
                    continue;
                }

                if (lab[i][j] == 3) {
                    lab[i][j] = 0;
                }
            }
        }

        return emptySpace;
    }

    private static void spreadVirus(int row, int col) {
        for (int[] move : DIRECTIONS) {
            int nextRow = row + move[0];
            int nextCol = col + move[1];

            if (isMovable(nextRow, nextCol) && lab[nextRow][nextCol] == 0) {
                lab[nextRow][nextCol] = 3;
                spreadVirus(nextRow, nextCol);
            }
        }
    }

    private static boolean isMovable(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < M;
    }

}
