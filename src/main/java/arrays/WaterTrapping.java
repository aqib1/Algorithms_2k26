package arrays;

/**
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
 * Example 2:
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 *
 * */
public class WaterTrapping {
    public int trap(int[] height) {
        if(height == null || height.length == 0) {
            return 0;
        }
        int trap = 0;
        int[] maxFromLeft = new int[height.length];
        int[] maxFromRight = new int[height.length];

        maxFromLeft[0] = height[0];
        for(int i = 1; i < height.length; i++) {
            maxFromLeft[i] = Math.max(maxFromLeft[i - 1], height[i]);
        }

        maxFromRight[maxFromRight.length - 1] = height[height.length - 1];
        for(int i = height.length - 2; i >= 0; i--) {
            maxFromRight[i] = Math.max(maxFromRight[i + 1], height[i]);
        }

        for(int i = 0; i < height.length; i++) {
            trap += Math.min(maxFromLeft[i], maxFromRight[i]) - height[i];
        }
        return trap;
    }

    static void main() {
        var trap = new WaterTrapping();
        System.out.println(trap.trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
