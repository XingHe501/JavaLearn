package com.mine.algorithm.greedy;

import java.util.*;

/**
 * 划分字母区间
 * https://leetcode-cn.com/problems/partition-labels/
 */
public class PartitionLabels {
    /**
     * @param s String 字母
     * @return List<Integer>
     */
    public List<Integer> partitionLabels(String s) {
        List<Integer> ans;
        // todo
        //     要不要统计一下字母信息？
        //     尽量贪婪地选择包含第一个字母的最小分区。如果你有像“abaccbdeff”这样的东西，那么你可能需要添加b。你可以使用像“last['b'] = 5”这样的映射来帮助你扩展分区的宽度。
        // todo
        //  1,尽量划分多的片段
        //  2,同一字母最多出现在一个片段中
        Map<Character, int[]> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            var key = s.charAt(i);
            if (map.containsKey(key)) {
                var value = map.get(key);
                value[1] = i;
            } else {
                var pos = new int[]{i, i};
                map.put(key, pos);
            }
        }
        var pos = new int[map.size()][2];
        int index = 0;
        for (var v : map.values()) {
            pos[index] = v;
            index++;
        }
        // 获得字母开始结束区间，按照开始区间升序排列
        Arrays.sort(pos, Comparator.comparing(o -> o[0]));
        ans = new ArrayList<>();
        int firstPos = pos[0][0], lastPos = pos[0][1];
        for (int i = 1; i < pos.length; i++) {
            var temp = pos[i];
            if (lastPos > temp[0] && lastPos < temp[1]) {
                // [1,5], [2,8] => [1,8]
                lastPos = temp[1];
            } else if (lastPos < temp[0]) {
                // [1,8], [9,10] => add(8-1+1), [9,10]
                ans.add(lastPos - firstPos + 1);
                firstPos = temp[0];
                lastPos = temp[1];
            }
        }
        // for循环结束会有一次没被添加进去
        ans.add(lastPos - firstPos + 1);

        // Answer Two
        // todo
        //      记录每个字母最后一次出现的位置
        //      s只包含小写字母 ‘a' - 'z', 使用字母的ASCII编码做下标，'z'为122
        int[] edge = new int[123];
        for (int i = 0; i < s.length(); i++) {
            edge[s.charAt(i)] = i;
        }
        int left = 0;
        int right = 0;
        ans = new ArrayList<>();
        // todo 从头遍历字符，更新字符出现的最远下标，如果字符最远位置和当前下标相等，找到分割点
        for (int i = 0; i < s.length(); i++) {
            // 跟当前最末比较，选择最大的，等于当前下标是分割点；
            right = Math.max(right, edge[s.charAt(i)]);
            if (i == right) {
                ans.add(right - left + 1);
                left = i + 1;
            }
        }

        return ans;
    }
}

class PartitionLabelsRun{
    public static void main(String[] args) {

    }
}