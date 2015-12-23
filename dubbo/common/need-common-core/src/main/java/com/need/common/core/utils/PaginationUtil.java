/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.common.core.utils;


import com.need.common.domain.enums.ListStatusEnum;
import com.need.common.domain.enums.RefreshTypeEnum;
import com.need.common.domain.pub.Message;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author YAN
 */
public class PaginationUtil {

    public static <T> List<T> getPaginationList(List<T> list, T lastId, Integer pageSize, String refreshType, Message message) {
        List<T> neededIds = new ArrayList<T>();
        if (list == null || list.isEmpty()) {
            message.addData("listStatus", ListStatusEnum.BOTTOM.status);
            return neededIds;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }
        message.addData("listStatus", ListStatusEnum.MIDDILE.status);
        int index = -1;
        int listSize = list.size();
        if (lastId != null && !lastId.equals("0") && !lastId.equals("") && !lastId.equals(0)) {
            index = list.indexOf(lastId);
            if (index < 0) {
                message.addData("listStatus", ListStatusEnum.REFRESH.status);
                neededIds = list.subList(0, Math.min(pageSize, listSize));
                return neededIds;
            }
        }
        if (index == -1) {
            if (pageSize < listSize) {
                neededIds = list.subList(0, pageSize);
            } else {
                neededIds = list.subList(0, listSize);
                message.addData("listStatus", ListStatusEnum.BOTTOM.status);
            }
        } else if (RefreshTypeEnum.DOWN.status.equals(refreshType)) {
            neededIds = list.subList(Math.max(0, index - pageSize), index);
            if(pageSize >= index) {
                message.addData("listStatus", ListStatusEnum.TOP.status);
            }
        } else if (pageSize + index + 1 >= listSize) {
            message.addData("listStatus", ListStatusEnum.BOTTOM.status);
            neededIds = list.subList(index + 1, listSize);
        } else {
            neededIds = list.subList(index + 1, pageSize + index + 1);
        }
        return neededIds;
    }
}
