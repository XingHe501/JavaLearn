package com.mine.algorithm.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 用最少数量的箭引爆气球（Mid)
 * https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons/
 */
public class MinimumNumberOfArrowsToBurstBalloons {
    /**
     * @param points int[][]
     * @return ans int 最少数量
     */
    public int findMinArrowShots(int[][] points) {
        // 升序排列
        Arrays.sort(points, Comparator.comparing(o -> o[1]));
        // 假设四个都不重叠，最小数量就是所给数组长度
        int ans = points.length, prev = points[0][1];
        for (int i = 1; i < points.length; i++) {
            // 上一个气球结束位置大于当前气球开始位置表示重叠
            if (prev >= points[i][0]) {
                // 重叠的话，数量-1
                ans--;
            } else {
                prev = points[i][1];
            }
        }

//        int ans = 1, prev = points[0][1];
//        for (var point: points){
//            if(point[0] > prev ){
//                ans++;
//                prev = point[1];
//            }
//        }
        return ans;
    }
}

class MinimumNumberOfArrowsToBurstBalloonsRun{
    public static void main(String[] args) {

    }
}