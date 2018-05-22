package Java.Algorithms.Week6;

import java.util.Arrays;

/**
 * Created by Andrei on 20.05.2018.
 */
public class LogicSolver {
  private static boolean satisfies(int[] assignment, int[] clause) {
    for (int i = 0; i < clause.length; i++){
      if (assignment[i] != 0 && clause[i] == assignment[i]){
        return true;
      }
    }
    return false;
  }
  private static boolean satisfies(int[] assignment, int[][] formula) {
    for (int[] a : formula) {
      if (!satisfies(assignment, a)) {
        return false;
      }
    }
    return true;
  }

  public static void main (String[] args){
    int[][] formula = new int[][] {
        { 0, 1, 0, 0, 0},
        { 0, 0, 1, },
        { 1, -1},
        { 1,  1},
    };

    System.out.println(satisfiable(new int[]{1,0}, new int[]{0,-1}));
    System.out.println("Greedy         " + Arrays.toString(solveGreedy(formula)));
    System.out.println("Backtracking 1 " + Arrays.toString(solveBacktracking1(formula)));
    System.out.println("Backtracking 2 " + Arrays.toString(solveBacktracking2(formula)));
  }

  private static boolean satisfiable(int[] assignment, int[] clause) {
    for (int i = 0; i < clause.length; i++){
      if (clause[i] != 0 && (assignment[i] == 0 || assignment[i] == clause[i])) {
        return true;
      }
    }
    return false;
  }
  private static boolean satisfiable(int[] assignment, int[][] formula) {
    for (int[] a : formula) {
      if (!satisfiable(assignment, a)) {
        return false;
      }
    }
    return true;
  }

  private static int[] solveGreedy(int[][] formula) {
    int[] output = new int[formula[0].length];
    for (int[] a : formula) {
      for (int j = 0; j < a.length; j++) {
        if (a[j] != 0) {
          if (a[j] == 1 && output[j] != -1) {
            output[j] = a[j];
            break;
          }
          if (a[j] == -1 && output[j] != 1) {
            output[j] = a[j];
            break;
          }
        }
      }
    }
    return satisfiable(output, formula) ? output : new int[formula[0].length];
  }


  private static int[] solveBacktracking1(int[][] formula) {
    int[] output = new int[formula[0].length];

    if (satisfies(output, formula)){
      return output;
    }

    if (!solve1(output, formula, 0)){
      output = new int[formula[0].length];
    }
    return output;
  }

  private static boolean solve1(int[] output, int[][] formula, int i) {
    if (satisfies(output, formula)){
      return true;
    }

    for ( ; i < output.length; i++){
      if (solve1(output, formula, i + 1)){
        return true;
      }

      output[i] = 1;
      if (solve1(output, formula, i + 1)){
        return true;
      }

      output[i] = -1;
    }
    return false;
  }

  private static int[] solveBacktracking2(int[][] formula) {
    int[] output = new int[formula[0].length];

    if (satisfies(output, formula)){
      return output;
    }

    for (int i = 0; i < formula.length; i++){
      if (!solve2(output, formula, i , 0)){
        output = new int[formula[0].length];
      }
    }

    if (!satisfies(output, formula)){
      output = new int[formula[0].length];
    }

    return output;
  }

  private static boolean solve2(int[] output, int[][] formula, int i, int j) {
    if (satisfies(output, formula)) {
      return true;
    }

    for ( ; j < formula[i].length; j++) {
      if (satisfies(output, formula[i])){
        return true;
      }

      if (formula[i][j] == 0) {
        continue;
      }

      if (solve2(output, formula, i, j + 1)) {
        return true;
      }

      output[j] = 1;
      if (solve2(output, formula, i, j + 1)) {
        return true;
      }

      output[j] = -1;
    }

    return false;
  }
}