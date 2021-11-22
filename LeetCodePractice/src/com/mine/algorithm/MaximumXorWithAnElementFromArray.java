package com.mine.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created: 2021/05/23 20:51
 *
 * https://leetcode-cn.com/problems/maximum-xor-with-an-element-from-array/
 */

public class MaximumXorWithAnElementFromArray {
    /**
     * 超时。
     *
     * @param nums    int[]
     * @param queries int[][]
     * @return ans int[queries.length]
     */
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int max = -1, conditionVal = queries[i][1];
            if (Arrays.stream(nums).min().getAsInt() <= conditionVal) {
                List<Integer> meetConditions = Arrays.stream(nums).filter(v -> v <= conditionVal).boxed().collect(Collectors.toList());
                for (int meetCondition : meetConditions) max = Math.max(max, queries[i][0] ^ meetCondition);
            }
            ans[i] = max;
        }
        return ans;
    }
    // start
    static int N = (int) 1e5 * 32;
    static int[][] trie = new int[N][2];
    static int idx = 0;

    public MaximumXorWithAnElementFromArray() {
        for (int i = 0; i <= idx; i++) {
            Arrays.fill(trie[i], 0);
        }
        idx = 0;
    }

    void add(int x) {
        int p = 0;
        for (int i = 31; i >= 0; i--) {
            int u = (x >> i) & 1;
            if (trie[p][u] == 0) trie[p][u] = ++idx;
            p = trie[p][u];
        }
    }

    int getVal(int x) {
        int ans = 0;
        int p = 0;
        for (int i = 31; i >= 0; i--) {
            int a = (x >> i) & 1, b = 1 - a;
            if (trie[p][b] != 0) {
                p = trie[p][b];
                ans = ans | (b << i);
            } else {
                p = trie[p][a];
                ans = ans | (a << i);
            }
        }
        return ans ^ x;
    }

    /**
     * 普通Trie
     * @author 宫水三叶
     *
     * 时间复杂度：令 nums 的长度为 m，qs 的长度为 n。两者排序的复杂度为 O(m log m) 和 O(n log n)；将所有数插入 TrieTrie 和从 TrieTrie 中查找的复杂度均为 O(Len)，Len 为 S2。
     * 整体复杂度为 O(m log m+n log n+(m+n)∗Len) = O(m ∗ max(log m,Len) + n ∗ max(log n,Len))。
     * 空间复杂度：O(C)。其中 C 为常数，固定为 1e5 * 32 * 2。
     */
    public int[] maximizeXorSecond(int[] nums, int[][] qs) {
        int m = nums.length, n = qs.length;

        // 使用哈希表将原本的顺序保存下来
        Map<int[], Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) map.put(qs[i], i);

        // 将 nums 与 queries[x][1] 进行「从小到大」进行排序
        Arrays.sort(nums);
        Arrays.sort(qs, (a, b) -> a[1] - b[1]);

        int[] ans = new int[n];
        int loc = 0; // 记录 nums 中哪些位置之前的数已经放入 Trie
        for (int[] q : qs) {
            int x = q[0], limit = q[1];
            // 将小于等于 limit 的数存入 Trie
            while (loc < m && nums[loc] <= limit) add(nums[loc++]);
            if (loc == 0) {
                ans[map.get(q)] = -1;
            } else {
                ans[map.get(q)] = getVal(x);
            }
        }
        return ans;
    }
    // end
}
class MaximumXorWithAnElementFromArrayRun{
    public static void main(String[] args) {
        int[] nums = new int[]{0,1,2,3,4};
        int[][] queries = new int[][]{{3,1},{1,3},{5,6}};
        MaximumXorWithAnElementFromArray maximumXorWithAnElementFromArray = new MaximumXorWithAnElementFromArray();
        System.out.printf("Value: " + Arrays.toString(maximumXorWithAnElementFromArray.maximizeXor(nums, queries)));
    }
}