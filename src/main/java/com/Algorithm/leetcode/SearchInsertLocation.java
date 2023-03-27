package com.Algorithm.leetcode;

/**
 * https://leetcode.cn/leetbook/read/array-and-string/cxqdh/
 *
 * 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 排序数组为无重复元素的升序排列数组,请必须使用时间复杂度为 O(log n) 的算法。
 *
 * 示例 1:
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 *
 * 示例 2:
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 *
 * 示例 3:
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 *
 * 提示:
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 为 无重复元素 的 升序 排列数组
 * -104 <= target <= 104
 *
 * */
public class SearchInsertLocation {

    public static void main(String[] args) {
        int[] arr = new int[]{1,3,5,6};
        System.out.println(searchInsert1(arr,1));
        System.out.println(searchInsert2(arr,1));
    }

    //暴力遍历
    private static int searchInsert1(int[] nums, int target) {
        if (null==nums||nums.length<0){
            return -1;
        }
        if (target>nums[nums.length-1]){
            return nums.length;
        }
        for (int index=0 ;index<nums.length ; index++){
            if (target==nums[index]){
                return index;
            }else if (target>nums[nums.length-1]){
                return nums.length;
            }else if (target<nums[0]){
                return 0;
            }else {
                if (target>nums[index]&&target<nums[index+1]){
                    return index+1;
                }
            }
        }
       return -1;
    }

    //二分法
    private static int searchInsert2(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = n;
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (target <= nums[mid]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
