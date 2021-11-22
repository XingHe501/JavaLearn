package com.mine.algorithm.greedy;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/queue-reconstruction-by-height/
 */
public class QueueReconstructionByHeight {
    public int[][] reconstructQueue(int[][] people){
        Arrays.stream(people).sorted(

        );
        return null;
    }
}

class QueueReconstructionByHeightRun{
    public static void main(String[] args) {
        QueueReconstructionByHeight queueReconstructionByHeight = new QueueReconstructionByHeight();
        int[][] people = new int[][]{{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        var result = queueReconstructionByHeight.reconstructQueue(people);
        System.out.println(Arrays.deepToString(result));
    }
}