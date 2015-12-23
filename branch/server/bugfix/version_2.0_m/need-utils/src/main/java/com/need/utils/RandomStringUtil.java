package com.need.utils;

import java.util.Random;

/**
 * 
 * @author YAN 2015-12-14 18:41:37
 * @ClassName RandomCharsUtil
 * @Description TODO
 * @version V2.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: YAN 2015-12-14
 * @modify by reason:{方法名}:{原因}
 */
public class RandomStringUtil {
    
    private static final String[] CHARS = new String[]{"a", "b", "c", "d", "e", "f", "g", "h",
            "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
    };
    
    private static final Random RANDOM = new Random();
    
    public static String getRandom(int length) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < length; i++) {
            result.append(CHARS[RANDOM.nextInt(CHARS.length)]);
        }
        return result.toString();
    }
    
    public static void main(String args[]) {
        for(int i = 0; i < 10; i++) {
            System.out.println(getRandom(6));
        }
    }

}