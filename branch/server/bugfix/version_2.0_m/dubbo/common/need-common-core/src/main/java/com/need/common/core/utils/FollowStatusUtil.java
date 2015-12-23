/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.common.core.utils;

import com.need.common.domain.enums.FollowStatusEnum;

/**
 *
 * @author 庆凯
 */
public class FollowStatusUtil {
    
    public static String reverseStatus(String followStatus) {
        String result = followStatus;
        if (FollowStatusEnum.FOLLOW.status.equals(followStatus)) {
            result = FollowStatusEnum.FOLLOWED.status;
        } else if (FollowStatusEnum.FOLLOWED.status.equals(followStatus)) {
            result = FollowStatusEnum.FOLLOW.status;
        }
        return result;
    }
    
    public static boolean needReverse(String userId, String followerId) {
        return userId.compareTo(followerId) < 0;
    }
    
    public static String addStatus(String oldStatus, boolean needReverse) {
        if(FollowStatusEnum.BOTH.status.equals(oldStatus)) {
            return FollowStatusEnum.BOTH.status;
        }
        String newStatus = oldStatus;
        if(FollowStatusEnum.NEITHER.status.equals(oldStatus)) {
            if(needReverse) {
                newStatus = FollowStatusEnum.FOLLOWED.status;
            } else {
                newStatus = FollowStatusEnum.FOLLOW.status;
            }
        } else if((FollowStatusEnum.FOLLOW.status.equals(oldStatus) && needReverse) || 
                (FollowStatusEnum.FOLLOWED.status.equals(oldStatus) && !needReverse)) {
            newStatus = FollowStatusEnum.BOTH.status;
        }
        return newStatus;
    }
    
    public static String delStatus(String oldStatus, boolean needReverse) {
        if(FollowStatusEnum.NEITHER.status.equals(oldStatus)) {
            return FollowStatusEnum.NEITHER.status;
        }
        String newStatus = oldStatus;
        if(FollowStatusEnum.BOTH.status.equals(oldStatus)) {
            if(needReverse) {
                newStatus = FollowStatusEnum.FOLLOW.status;
            } else {
                newStatus = FollowStatusEnum.FOLLOWED.status;
            }
        } else if((FollowStatusEnum.FOLLOWED.status.equals(oldStatus) && needReverse) || 
                (FollowStatusEnum.FOLLOW.status.equals(oldStatus) && !needReverse)) {
            newStatus = FollowStatusEnum.NEITHER.status;
        }
        return newStatus;
    }
    
}
