package com.company;

import java.util.Scanner;

public class InverseMatrix {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of matrix >> ");
        int size = sc.nextInt();

        float[][] matrixA = new float[size][size];
//        float[][] m={{2,3,4,1},
//                {3,2,7,3},
//                {9,1,3,0},
//                {3,4,1,8}};
        float[][] inv = new float[size][size];

        getData(matrixA);
        System.out.println("Inverse of matrix is >> ");

        if (inverse(matrixA, inv, size)) prt(inv);
    }

    public static void getData(float n[][]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("----Enter Matrix----");
        for (int i = 0; i < n.length; i++) {
            for (int j = 0; j < n[0].length; j++) {
                System.out.print("X[" + i + "][" + j + "] >> ");
                n[i][j] = sc.nextInt();
            }
        }
    }

    public static void prt(float[][] result) {
        //System.out.println("----ANSWER----");
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.printf("%.2f %s", result[i][j], "\t");
            }
            System.out.println();
        }
    }

    public static void cofactor(float[][] matrix, float[][] temp, int x, int y, int n) {
        int i = 0, j = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row != x && col != y) {
                    temp[i][j++] = matrix[row][col];
                    if (j == n - 1) {
                        j = 0;
                        i += 1;
                    }
                }
            }
        }
    }

    public static float determinant(float matrix[][], int size) {

        float detAns = 0, k = 1;
        float temp[][] = new float[size][size];
        int m, n;

        if (size == 1) {
            return (matrix[0][0]);
        } else {
            for (int column = 0; column < size; column++) {
                cofactor(matrix, temp, 0, column, size);
                detAns = detAns + k * (matrix[0][column] * determinant(temp, size - 1));
                k *= -1;
            }
        }
        return (detAns);
    }

    public static void adjoint(float[][] matrix, float[][] adj, int n) {
        if (n == 1) {
            adj[0][0] = 1;
            return;
        }

        int k = 1;
        float[][] temp = new float[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cofactor(matrix, temp, i, j, n);

                k = ((i + j) % 2 == 0) ? 1 : -1;
                adj[j][i] = k * (determinant(temp, n - 1)); //transpose
            }
        }
    }

    public static boolean inverse(float matrix[][], float[][] inverse, int size) {
        float det = determinant(matrix, size);
        if (det == 0) {
            System.out.print("Singular matrix, can't find its inverse");
            return false;
        }

        float[][] adj = new float[size][size];
        adjoint(matrix, adj, size);

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                inverse[i][j] = adj[i][j] / det;

        return true;
    }
}
