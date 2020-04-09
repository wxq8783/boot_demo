package com.wu.threadpool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ScheduledThreadPoolExecutorTest {
    public static int longestPalindrome(String s) {
        int[] c = new int[26+26];
        for(char ss : s.toCharArray()) {
            if(ss >= 'a') {
                c[ss-'a'] += 1;
            } else {
                c[ss-'A'+26] += 1;
            }
        }
        int res = 0;
        int odd = 0;
        for(int cc : c) {
            res += cc;
            if(cc % 2 == 1) {
                odd += 1;
            }
        }
        return odd == 0 ? res : res - odd + 1;
    }

    //无重复字符的最长子串
    public static int lengthOfLongestSubstring03(String s) {
        int n = s.length(), ans = 0;
        //创建map窗口,i为左区间，j为右区间，右边界移动
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0, i = 0; j < n; j++) {
            // 如果窗口中包含当前字符，
            if (map.containsKey(s.charAt(j))) {
                //左边界移动到 相同字符的下一个位置和i当前位置中更靠右的位置，这样是为了防止i向左移动
                i = Math.max(map.get(s.charAt(j)), i);
            }
            //比对当前无重复字段长度和储存的长度，选最大值并替换
            //j-i+1是因为此时i,j索引仍处于不重复的位置，j还没有向后移动，取的[i,j]长度
            ans = Math.max(ans, j - i + 1);
            // 将当前字符为key，下一个索引为value放入map中
            // value为j+1是为了当出现重复字符时，i直接跳到上个相同字符的下一个位置，if中取值就不用+1了
            map.put(s.charAt(j), j+1);
        }
        return ans;
    }
    public static void main(String[] args){
        int i = longestPalindrome("ddcabcddcba");
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(3);



//        executor.scheduleWithFixedDelay(()->{
//            System.out.println("---->"+new Date());
//            try {
//                Thread.sleep(11000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },0,1000,TimeUnit.SECONDS);

//        executor.scheduleAtFixedRate(()->{
//            System.out.println("=====>"+new Date());
//            try {
//                Thread.sleep(11000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },0,10,TimeUnit.SECONDS);
//
//        executor.schedule(()->{
//            System.out.println("++++++++>"+new Date());
//        },10,TimeUnit.SECONDS);
    }
}
