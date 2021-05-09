package com.lc.daily.may;

/**
 * 1482. 制作 m 束花所需的最少天数
 * https://leetcode-cn.com/problems/minimum-number-of-days-to-make-m-bouquets/
 * 解题思路：
 * 如果花朵的数量够的话，是肯定可以制作成目标花束的（最坏的情况就是所有花都开完了，再制作花束）
 * 最快是花最早开放的时间，最迟是花最晚开放的时间，将数组的最小值设置为left ，最大值设置为right，然后设置一个 mid 值
 * 遍历数组，判断在 mid 天的情况下，能有多少花束
 */
public class Main09 {
    public int minDays(int[] bloomDay, int m, int k) {
        if (m * k > bloomDay.length) {
            return -1;
        }
        int low = 0, high = 0;
        for (int i = 0; i < bloomDay.length; i++) {
            low = Math.min(low, bloomDay[i]);
            high = Math.max(high, bloomDay[i]);
        }
        while (low < high) {
            int mid = (low + high) / 2;
            if (canMake(bloomDay, m, k, mid)) {//能制作，在左侧查找
                high = mid;// <- 二分查找的边界问题
            }else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean canMake(int[] bloomDay, int m, int k, int day) {
        int bouquets = 0;//花束
        int flowers = 0;//单个花
        for (int i = 0; i < bloomDay.length && bouquets < m; i++) {
            if (bloomDay[i] <= day) {
                flowers++;
                if (flowers == k) {
                    bouquets++;
                    flowers = 0;
                }
            }else {
                flowers = 0;
            }
        }
        return bouquets >= m;
    }
}
