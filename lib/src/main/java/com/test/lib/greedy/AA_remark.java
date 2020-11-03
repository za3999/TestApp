package com.test.lib.greedy;

/**
 * 概念
 * <p>
 * 贪心的意思在于在作出选择时，每次都要选择对自身最为有利的结果，保证自身利益的最大化。贪心算法就是利用这种贪心思想而得出一种算法。
 * <p>
 * 贪心算法作为五大算法之一，在数据结构中的应用十分广泛。例如：在求最小生成树的 Prim 算法中，挑选的顶点是候选边中权值最小的边的一个端点。
 * 在 Kruskal 算法中，每次选取权值最小的边加入集合。在构造霍夫曼树的过程中也是每次选择最小权值的节点构造二叉树。
 * 这种每次在执行子问题的求解时，总是选择当前最优的情形，恰好符合贪心的含义。
 * <p>
 * 贪心算法可以简单描述为：大事化小，小事化了。对于一个较大的问题，通过找到与子问题的重叠，把复杂的问题划分为多个小问题。
 * 并且对于每个子问题的解进行选择，找出最优值，进行处理，再找出最优值，再处理。
 * 也就是说贪心算法是一种在每一步选择中都采取在当前状态下最好或最优的选择，从而希望得到结果是最好或最优的算法。
 * <p>
 * 贪心算法在对问题求解时，总是做出在当前看来是最好的选择。
 * 也就是说，不从整体最优上加以考虑，所做出的仅是在某种意义上的局部最优解。
 * 贪心算法不是对所有问题都能得到整体最优解，但对范围相当广泛的许多问题他能产生整体最优解或者是整体最优解的近似解。
 * <p>
 * <p>
 * 算法流程
 * <p>
 * （1）建立数学模型来描述问题。
 * （2）把求解的问题分成若干个子问题。
 * （3）对每一子问题求解，得到子问题的局部最优解。
 * （4）把子问题的局部最优解合成原来问题的一个解。
 * <p>
 * 伪代码
 * <p>
 * 从问题的某一初始解出发
 * while (能朝给定总目标前进一步)
 * do  选择当前最优解作为可行解的一个解元素；
 * 由所有解元素组合成问题的一个可行解。
 * <p>
 * 示例
 * <p>
 * 小明手中有 1，5，10，50，100 五种面额的纸币，每种纸币对应张数分别为 5，2，2，3，5 张。若小明需要支付 456 元，则需要多少张纸币？
 * <p>
 * 分析
 * <p>
 * (1）建立数学模型
 *   设小明每次选择纸币面额为 Xi ，需要的纸币张数为 n 张，剩余待支付金额为 V ，则有： X1 + X2 + … + Xn = 456.
 * <p>
 *（2）问题拆分为子问题
 *   小明选择纸币进行支付的过程，可以划分为n个子问题：即每个子问题对应为：在未超过456的前提下，在剩余的纸币中选择一张纸币。
 * <p>
 *（3）制定贪心策略，求解子问题
 * 制定的贪心策略为：在允许的条件下选择面额最大的纸币。则整个求解过程如下：
 * <p>
 * 选取面值为 100 的纸币，则 X1 = 100, V = 456 – 100 = 356；
 * <p>
 * 继续选取面值为 100 的纸币，则 X2 = 100, V = 356 – 100 = 256；
 * <p>
 * 继续选取面值为 100 的纸币，则 X3 = 100, V = 256 – 100 = 156；
 * <p>
 * 继续选取面值为 100 的纸币，则 X4 = 100, V = 156 – 100 = 56；
 * <p>
 * 选取面值为 50 的纸币，则 X5 = 50, V = 56 – 50 = 6；
 * <p>
 * 选取面值为 5 的纸币，则 X6 = 5, V = 6 – 5 = 1；
 * <p>
 * 选取面值为 1 的纸币，则 X7 = 1, V = 1 – 1 = 0；求解结束
 * <p>
 * （4）将所有解元素合并为原问题的解
 * <p>
 * 小明需要支付的纸币张数为 7 张，其中面值 100 元的 4 张，50 元 1 张，5 元 1 张，1 元 1 张。
 * <p>
 * 代码实现
 * <p>
 * <p>
 * <p>
 * https://www.cxyxiaowu.com/852.html
 */

public class AA_remark {

    public static void main(String[] args) {
        System.out.println("result:" + solve(450));
    }

    public static int solve(int money) {
        int N = 5;
        int[] count = new int[]{5, 2, 2, 3, 5};
        int[] value = new int[]{1, 5, 10, 50, 100};
        int num = 0;
        for (int i = N - 1; i >= 0; i--) {
            int c = Math.min(money / value[i], count[i]);//每一个所需要的张数
            money = money - c * value[i];
            num += c;//总张数
        }
        if (money > 0) num = -1;
        return num;
    }
}