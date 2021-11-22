package com.mine.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created: 2021/05/17 14:24
 *
 * https://leetcode-cn.com/problems/cousins-in-binary-tree/
 */


//  Definition for a binary tree node.

public class CousinsInBinaryTree {
    /**
     * DFS 深度搜索
     * 时间复杂度：O(n)
     * 空间复杂度：忽略递归开销为 O(1)，否则为 O(n)
     *
     * @author 宫水三叶
     * @param root TreeNode
     * @param x int
     * @param y int
     * @return boolean
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        int[] xi = dfs(root, null, 0, x);
        int[] yi = dfs(root, null, 0, y);
        return xi[1] == yi[1] && xi[0] != yi[0];
    }

    /**
     * 查找 t 的「父节点值」&「所在深度」
     * @apiNote 需要注意的时，我们需要区分出「搜索不到」和「搜索对象为 root（没有 fa 父节点）」两种情况。
     *      我们约定使用 -1 代指没有找到目标值 t，使用 0 代表找到了目标值 t，但其不存在父节点。
     * @param root 当前搜索到的节点
     * @param fa root 的父节点
     * @param depth 当前深度
     * @param t 搜索目标值
     * @return [fa.val, depth]
     */
    int[] dfs(TreeNode root, TreeNode fa, int depth, int t){
        if (root == null) return new int[]{-1, -1}; // 使用 -1 代表为搜索不到 t
        if (root.val == t) {
            return new int[]{fa != null ? fa.val : 1, depth}; // 使用 1 代表搜索值 t 为 root
        }
        int[] l = dfs(root.left, root, depth + 1, t);
        if (l[0] != -1) return l;
        return dfs(root.right, root, depth + 1, t);
    }

    /**
     * BFS
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @author 宫水三叶
     * @param root TreeNode
     * @param x int
     * @param y int
     * @return boolean
     */
    public boolean isCousinsAnswerTwo(TreeNode root, int x, int y) {
        int[] xi = bfs(root, x);
        int[] yi = bfs(root, y);
        return xi[1] == yi[1] && xi[0] != yi[0];
    }


    int[] bfs(TreeNode root, int t) {
        Deque<Object[]> d = new ArrayDeque<>(); // 存储值为 [cur, fa, depth]
        d.addLast(new Object[]{root, null, 0});
        while (!d.isEmpty()) {
            int size = d.size();
            while (size-- > 0) {
                Object[] poll = d.pollFirst();
                TreeNode cur = (TreeNode)poll[0], fa = (TreeNode)poll[1];
                int depth = (Integer)poll[2];

                if (cur.val == t) return new int[]{fa != null ? fa.val : 0, depth};
                if (cur.left != null) d.addLast(new Object[]{cur.left, cur, depth + 1});
                if (cur.right != null) d.addLast(new Object[]{cur.right, cur, depth + 1});
            }
        }
        return new int[]{-1, -1};
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class CousinsInBinaryTreeRun{
    public static void main(String[] args) {
    }
}
