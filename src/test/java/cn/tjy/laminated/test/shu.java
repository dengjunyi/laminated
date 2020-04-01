package cn.tjy.laminated.test;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class shu {

    public static void main(String[] args) {
                int[] nums = { 2, 1, 4, 100, 88, 66, 123, 5, 74, 69 };
                for (int i = 0; i <= nums.length - 1; i++) {
                    for (int j = i + 1; j <= nums.length - 1; j++) {
                        if (nums[i] > nums[j]) {
                            int temp = nums[i];
                            nums[i] = nums[j];
                            nums[j] = temp;
                        }
                    }
                }
                System.out.println("最小值为:" + nums[0] + " , 最大值为:"
                        + nums[nums.length - 1]);
            }
    }
