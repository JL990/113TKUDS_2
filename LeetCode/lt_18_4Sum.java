package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lt_18_4Sum {

    /**
     * 求陣列中所有不重複且和為 target 的四元組
     * 時間複雜度：O(n^3)，n 為陣列長度
     * 先排序陣列（O(nlogn)），三層迴圈加雙指針遍歷所有可能組合（O(n^3)）
     */
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(nums); // 先將陣列排序

            // 第一層迴圈，固定第一個數
            for(int i=0; i<nums.length-3; i++){
                if(i>0 && nums[i] == nums[i-1]) continue; // 跳過重複元素

                // 第二層迴圈，固定第二個數
                for(int j=i+1; j<nums.length-2; j++){
                    if(j>i+1 && nums[j] == nums[j-1]) continue; // 跳過重複元素

                    int left = j+1; // 左指針
                    int right = nums.length - 1; // 右指針

                    // 雙指針尋找剩下兩個數
                    while(left < right){
                        long total = (long)nums[i] + (long)nums[j] + (long)nums[left] + (long)nums[right]; // 計算四數之和

                        if(total < target) left++; // 和太小，左指針右移
                        else if(total > target) right--; // 和太大，右指針左移
                        else{
                            res.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right])); // 加入結果
                            left++;
                            right--;

                            // 跳過重複元素
                            while(left < right && nums[left] == nums[left-1]){
                                left++;
                            }
                            while(left < right && nums[right] == nums[right+1]){
                                right--;
                            }
                        }
                    }
                }
            }
            return res;
        }
    }
    public static void main(String[] args) {
        Solution sol = new lt_18_4Sum().new Solution();

        // 測試案例
        System.out.println("Input: nums = [1,0,-1,0,-2,2], target = 0 → Output: " + sol.fourSum(new int[]{1,0,-1,0,-2,2}, 0)); // [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
        System.out.println("Input: nums = [2,2,2,2,2], target = 8 → Output: " + sol.fourSum(new int[]{2,2,2,2,2}, 8)); // [[2,2,2,2]]

        System.out.println("done");
    }
}
