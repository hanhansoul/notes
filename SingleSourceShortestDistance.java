package contest;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by Administrator on 2020/11/1 0001.
 */
public class SingleSourceShortestDistance {

    class Position {
        public int row;
        public int col;
        public int distance;

        public Position(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }

    public final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int[][] dis;
    public char[][] map;
    public int row = 10;
    public int col = 8;
    public int sRow = 4;
    public int sCol = 4;

    public void input() {
        Scanner scanner = new Scanner(System.in);
        map = new char[row][col];
        int r = 0;
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            if (s.equals("")) {
                break;
            }
            for (int i = 0; i < s.length(); i++) {
                map[r][i] = s.charAt(i);
            }
            r++;
        }
    }

    public void output() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (dis[i][j] == Integer.MAX_VALUE) {
                    dis[i][j] = -1;
                }
                if (map[i][j] == 'B') {
                    dis[i][j] = -2;
                }
                System.out.printf("%3d", dis[i][j]);
            }
            System.out.println();
        }
    }

    public void solve() {
        dis = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dis[i][j] = Integer.MAX_VALUE;
            }
        }
        dis[sRow][sCol] = 0;
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(sRow, sCol, 0));
        while (!queue.isEmpty()) {
            Position position = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nRow = position.row + DIRECTIONS[i][0];
                int nCol = position.col + DIRECTIONS[i][1];
                if (isInBoard(nRow, nCol) && isPositionValid(nRow, nCol)
                        && dis[nRow][nCol] > position.distance + 1) {
                    dis[nRow][nCol] = position.distance + 1;
                    queue.add(new Position(nRow, nCol, dis[nRow][nCol]));
                }
            }
        }
    }

    private boolean isPositionValid(int nRow, int nCol) {
        return map[nRow][nCol] == 'O';
    }

    private boolean isInBoard(int nRow, int nCol) {
        return nRow >= 0 && nRow < row && nCol >= 0 && nCol < col;
    }

    public static void main(String[] args) {
        SingleSourceShortestDistance solver = new SingleSourceShortestDistance();
        solver.input();
        for (int i = 0; i < solver.row; i++) {
            for (int j = 0; j < solver.col; j++) {
                System.out.print(solver.map[i][j]);
            }
            System.out.println();
        }
        solver.solve();
        solver.output();
    }
}
