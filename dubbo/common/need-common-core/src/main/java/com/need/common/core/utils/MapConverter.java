/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.common.core.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author YAN
 */
public class MapConverter {
    
    public static Map<String, String> convert(Map<String, Object> input) {
        Map<String, String> result = new HashMap<String, String>();
        for(Map.Entry<String, Object> entry : input.entrySet()) {
            if(entry.getValue() instanceof String) {
                result.put(entry.getKey(), (String) entry.getValue());
            } else if(entry.getValue() instanceof Number) {
                result.put(entry.getKey(), ((Number) entry.getValue()).toString());
            } else if(entry.getValue() instanceof Date) {
                result.put(entry.getKey(), "" + ((Date)entry.getValue()).getTime());
            } else if(entry.getValue() instanceof Boolean) {
                result.put(entry.getKey(), ((Boolean) entry.getValue()).toString());
            }
        }
        return result;
    }
}
