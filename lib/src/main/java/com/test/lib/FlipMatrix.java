package com.test.lib;

/**
 *  矩阵翻转
 */
public class FlipMatrix {

    public static int[][] transposeMatrix(int[][] A) {
        int iL = A.length;
        int jL = A[0].length;
        int[][] B = new int[jL][iL];
        for (int j = 0; j < jL; j++) {
            for (int i = 0; i < iL; i++) {
                B[j][i] = A[i][j];
            }
        }
        return B;
    }

}
