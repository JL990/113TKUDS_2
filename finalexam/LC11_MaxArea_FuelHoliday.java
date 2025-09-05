package finalexam;

public class LC11_MaxArea_FuelHoliday {
    
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 測試案例
        System.out.println("Input: height = [1,8,6,2,5,4,8,3,7] → Output: " + sol.maxArea(new int[]{1,8,6,2,5,4,8,3,7})); // 49
        System.out.println("Input: height = [1,1] → Output: " + sol.maxArea(new int[]{1,1})); // 1

        System.out.println("done");
    }

    static class Solution {
        /**
         * 計算最大可盛水的面積
         * @param height 每個位置的高度
         * @return 最大面積
         */
        public int maxArea(int[] height) {
            int maxArea = 0; // 最大面積
            int left = 0; // 左指針
            int right = height.length - 1; // 右指針

            // 使用雙指針法，從兩端往中間移動
            while (left < right) {
                // 計算當前左右指針所形成的容器面積
                maxArea = Math.max(maxArea, (right - left) * Math.min(height[left], height[right]));

                // 移動較低的指針，以期找到更高的邊界
                if (height[left] < height[right]) {
                    left++;
                } else {
                    right--;
                }
            }

            return maxArea;        
        }
    }
}
