package com.mine.algorithm;

/**
 * Created: 2021/06/07 19:54
 *
 * https://leetcode-cn.com/problems/delete-duplicates-in-sort-array
 */
public class DeleteDuplicatesInSortArray {
    /**
     * 使用两个指针，右指针始终往右移动，
     * 如果右指针指向的值等于左指针指向的值，左指针不动。
     * 如果右指针指向的值不等于左指针指向的值，那么左指针往右移一步，然后再把右指针指向的值赋给左指针。
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0 || nums == null)
           return 0;
        int left = 0;
        for (int right = 1; right < nums.length; right++) {
            if (nums[left] != nums[right]) nums[++left] = nums[right];
        }
        return ++left;
    }
}
class DeleteDuplicatesInSortArrayRun {
    public static void main(String[] args) {
        DeleteDuplicatesInSortArray delete = new DeleteDuplicatesInSortArray();
        System.out.println(delete.removeDuplicates(new int[]{1, 1, 2, 3, 4, 5, 6, 7, 7, 8}));
    }
}