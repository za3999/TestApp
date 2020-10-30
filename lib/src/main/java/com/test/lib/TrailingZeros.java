package com.test.lib;

/**
 * 阶乘最后的0的个数
 */
public class TrailingZeros {

    public static long trailingZeros(long n) {
        long count = 0;
        long i = 5;
        while (i <= n) {
            count += i / 5;
            i = i * 5;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("result:" + trailingZeros(1000));
    }
}
