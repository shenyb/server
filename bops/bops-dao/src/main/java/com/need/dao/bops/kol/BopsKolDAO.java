package com.need.dao.bops.kol;

import java.util.List;

import com.need.domain.po.bops.kol.BopsKol;
import com.need.domain.vo.kol.BopsKolParamVO;
/**
 * <p></p>
 * @author Rylan 2015年8月5日 下午8:16:34
 * @ClassName BopsKolDAO
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月5日
 * @modify by reason:{方法名}:{原因}
 */
public interface BopsKolDAO {
	/**
	 * @author Rylan 2015年8月5日 下午8:16:39
	 * @Method: deleteByPrimaryKey 
	 * @Description: TODO
	 * @param kolId
	 * @return 
	 * @throws
	 */
    int deleteByPrimaryKey(String kolId);

    /**
     * @author Rylan 2015年8月5日 下午8:16:42
     * @Method: insert 
     * @Description: TODO
     * @param record
     * @return 
     * @throws
     */
    int insert(BopsKol record);

    /**
     * @author Rylan 2015年8月5日 下午8:16:46
     * @Method: insertSelective 
     * @Description: TODO
     * @param record
     * @return 
     * @throws
     */
    int insertSelective(BopsKol record);

    /**
     * @author Rylan 2015年8月5日 下午8:16:50
     * @Method: selectByPrimaryKey 
     * @Description: TODO
     * @param kolId
     * @return 
     * @throws
     */
    BopsKol selectByPrimaryKey(String kolId);

    /**
     * @author Rylan 2015年8月5日 下午8:16:54
     * @Method: updateByPrimaryKeySelective 
     * @Description: TODO
     * @param record
     * @return 
     * @throws
     */
    
    int updateKol(BopsKol record);

    /**
     * @author Rylan 2015年8月5日 下午8:16:57
     * @Method: updateByPrimaryKey 
     * @Description: TODO
     * @param record
     * @return 
     * @throws
     */
    int updateByPrimaryKey(BopsKol record);
    
    /**
     * @author Rylan 2015年8月5日 下午11:08:12
     * @Method: selectAll 
     * @Description: TODO
     * @param bopsKolParamVO
     * @return 
     * @throws
     */
    List<BopsKol> selectAll( BopsKolParamVO bopsKolParamVO);
   
    /**
     * @author Rylan 2015年8月5日 下午11:09:25
     * @Method: selectTotalCount 
     * @Description: TODO
     * @return 
     * @throws
     */
    int selectTotalCount(BopsKolParamVO bopsKolParamVO);
    
    
    BopsKol selectKOL(String kolId);
}