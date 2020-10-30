package com.test.lib.recursion;

import java.util.ArrayList;
import java.util.Collections;

//背包问题
public class Backpack {
    ArrayList<Goods> goods;
    int capacity;
    int goodsSize;
    int maxValue = 0;
    int tempValue;
    int tempWeight;
    int[] way;
    int[] bestWay;

    public Backpack(int capacity, ArrayList<Goods> goods) {
        this.capacity = capacity;
        this.goods = goods;
        this.goodsSize = goods.size();
        way = new int[goods.size()];
        bestWay = new int[goods.size()];
    }

    public void backTrack(int t) {
        //已经搜索到根节点
        if (t == goodsSize) {
            if (tempValue > maxValue) {
                maxValue = tempValue;
                for (int i = 0; i < goodsSize; i++)
                    bestWay[i] = way[i];
            }
            return;
        }
        Goods good = goods.get(t);
        //搜索左边节点
        if (tempWeight + good.weight <= capacity) {
            tempWeight += good.weight;
            tempValue += good.value;
            way[t] = 1;
            backTrack(t + 1);
            tempWeight -= good.weight;
            tempValue -= good.value;
            way[t] = 0;
        }

        if (bound(t + 1) >= maxValue) {   //不装入这个物品，直接搜索右边的节点
            backTrack(t + 1);
        }
    }

    //用于计算剩余物品的最高价值上界
    public double bound(int k) {
        double maxLeft = tempValue;
        int leftWeight = capacity - tempWeight;
        //尽力依照单位重量价值次序装剩余的物品
        while (k <= goodsSize - 1 && leftWeight > goods.get(k).weight) {
            leftWeight -= goods.get(k).weight;
            maxLeft += goods.get(k).value;
            k++;
        }
        //不能装时，用下一个物品的单位重量价值折算到剩余空间。
        if (k <= goodsSize - 1) {
            maxLeft += goods.get(k).value / goods.get(k).weight * leftWeight;
        }
        return maxLeft;
    }

    public static void main(String[] args) {
        ArrayList<Goods> goods = new ArrayList<>();
        Collections.addAll(goods,
                new Goods(2, 6),
                new Goods(6, 9),
                new Goods(4, 6),
                new Goods(1, 1),
                new Goods(5, 4)
        );
        Backpack b = new Backpack(10, goods);
        b.backTrack(0);
        System.out.println("该背包能够取到的最大价值为:" + b.maxValue);
        System.out.println("取出的方法为:");
        for (int i : b.bestWay)
            System.out.print(i + "  ");
    }

    private static class Goods {
        int weight;
        double value;

        public Goods(int weight, double value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
