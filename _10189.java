/*
Problem: Minesweeper
Description: https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&category=0&problem=1130&mosmsg=Submission+received+with+ID+26395645
Names: James Brocklebank
Date: 2021-05-12

Topic: Arrays
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Grid {
  private int rows;
  private int cols;
  private char[][] grid;

  public int getRows() {
    return this.rows;
  }

  public int getCols() {
    return this.cols;
  }

  public Grid(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    this.grid = new char[this.rows][this.cols];
  }

  public boolean isBomb(int row, int col) {
    return this.grid[row][col] == '*';
  }

  public void addRow(int row, String input) {
    for (int i = 0; i < input.length(); i++) {
      this.grid[row][i] = input.charAt(i);
    }
  }
}

class Solver {
  private static int[] X = new int[] {1, 1, 1, 0, 0, -1, -1, -1 };
  private static int[] Y = new int[] { 0, 1, -1, 1, -1, -1, 1, 0 };

  public static void solve(Grid grid) {
    for (int x = 0; x < grid.getRows(); x++) {
      for (int y = 0; y < grid.getCols(); y++) {
        if (grid.isBomb(x, y)) {
          System.out.print("*");
          continue;
        }

        int count = _countBombs(x, y, grid);

        System.out.print(count);
      }
      System.out.println();
    }
  }

  private static int _countBombs(int x, int y, Grid _grid)
  {
    int count = 0;
    for (int dir = 0; dir < 8; dir++)
    {
        int dX = x + X[dir];
        int dY = y + Y[dir];

        if (dX < 0 || dX >= _grid.getRows() || dY < 0 || dY >= _grid.getCols())
        {
            continue;
        }

        if (_grid.isBomb(dX, dY))
        {
            count++;
        }
    }

    return count;
  }

}

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int testCase = 1;

    while (true) {
      String[] dimensions = reader.readLine().split(" ");
      int rows = Integer.parseInt(dimensions[0]);
      int cols = Integer.parseInt(dimensions[1]);

      if (rows == 0 && cols == 0) {
        break;
      }

      if (testCase != 1) {
        System.out.println();
      }

      System.out.println("Field #" + testCase + ":");
      Grid grid = new Grid(rows, cols);

      for (int i = 0; i < rows; i++) {
        String line = reader.readLine();
        grid.addRow(i, line);
      }

      Solver.solve(grid);

      testCase++;   
    }
  }
}
