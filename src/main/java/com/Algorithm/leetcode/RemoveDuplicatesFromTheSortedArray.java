package com.Algorithm.leetcode;

/**
 *
 * https://leetcode.cn/leetbook/read/top-interview-questions-easy/x2gy9m/
 *
 * 删除排序数组中的重复项
 *
 * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
 * 由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 k 个元素，那么 nums 的前 k 个元素应该保存最终结果。
 * 将最终结果插入 nums 的前 k 个位置后返回 k 。
 * 不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2,_]
 * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 2：
 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 * 输出：5, nums = [0,1,2,3,4]
 * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
 *
 * 提示：
 * 1 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * nums 已按 升序 排列
 *
 * */
public class RemoveDuplicatesFromTheSortedArray {

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,3,3,3};
        System.out.println("old "+arr.length);
        System.out.println("new1 "+removeDuplicates(arr));
        System.out.println("new2 "+removeDuplicates2(arr)+"is error!!!");
        System.out.println("new3 "+removeDuplicates3(arr));
    }

    private static int removeDuplicates(int[] nums) {
        //校验
        if (null == nums || nums.length<=0){
            return 0;
        }

        int left = 0;
        int right = left+1;
        //如果左指针和右指针指向的值一样，说明有重复的，
        //这个时候，左指针不动，右指针继续往右移。如果他俩
        //指向的值不一样就把右指针指向的值往前挪
        for (;right<nums.length;right++){
            if (nums[left] != nums[right]){
                left++;
                nums[left] = nums[right];
            }
        }
        return ++left;
    }

    private static int removeDuplicates2(int[] nums) {
        //校验
        if (null == nums || nums.length<=0){
            return 0;
        }

        int left = 0;
        int right = left+1;
        //如果左指针和右指针指向的值一样，说明有重复的，
        //这个时候，左指针不动，右指针继续往右移。如果他俩
        //指向的值不一样就把右指针指向的值往前挪
        for (;right<nums.length;right++){
            if (nums[left] != nums[right]){
                left++;
                nums[left] = nums[right];
            }
        }
        return left++;//使用left++返回是不对的！！！
    }

    private static int removeDuplicates3(int[] nums) {
        //边界条件判断
        if (nums == null || nums.length == 0)
            return 0;
        int left = 0;
        for (int right = 1; right < nums.length; right++)
            //如果左指针和右指针指向的值一样，说明有重复的，
            //这个时候，左指针不动，右指针继续往右移。如果他俩
            //指向的值不一样就把右指针指向的值往前挪
            if (nums[left] != nums[right])
                nums[++left] = nums[right];
        return ++left;
    }
}
