package com.need.common.core.dao.jdbc.kol;

import com.need.common.domain.po.api.kol.KolCategoryPO;

import java.util.List;

public interface KolCategoryPODAO {
   

    /**
     * 
     * @author peiboli 2015年8月11日 上午9:26:14
     * @Method: getKolcategoryList 
     * @Description: TODO获取分类列表
     * @param userId
     * @return 
     * @throws
     */
	List<KolCategoryPO> queryKolcategory();
}