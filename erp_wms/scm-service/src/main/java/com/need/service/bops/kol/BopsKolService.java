package com.need.service.bops.kol;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.api.kol.UserKol;
import com.need.domain.po.bops.basedata.BopsKolCategory;
import com.need.domain.vo.kol.BopsKolParamVO;
import com.need.domain.vo.kol.BopsKolResultVO;
import com.need.domain.vo.kol.BopsKolUpdateParamVO;

/**
 * @author Rylan 2015年8月5日 下午8:37:53
 * @ClassName BopsKolService
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月5日
 * @modify by reason:{方法名}:{原因}
 */
public interface BopsKolService {

	/**
	 * @author Rylan 2015年8月5日 下午10:29:30
	 * @Method: addBopsKol 
	 * @Description: TODO
	 * @param bopsKolVO
	 * @return 
	 * @throws
	 */
	public int addBopsKol(BopsKolParamVO bopsKolVO) ;
	
	/**
	 * @author Rylan 2015年8月5日 下午10:29:34
	 * @Method: updateBopsKol 
	 * @Description: TODO
	 * @param bopsKolVO
	 * @return 
	 * @throws
	 */
	public int updateBopsKol(BopsKolParamVO bopsKolVO);
	/**
	 * @author Rylan 2015年8月5日 下午10:29:37
	 * @Method: getBopsKolById 
	 * @Description: TODO
	 * @param kolId
	 * @return 
	 * @throws
	 */
	public BopsKolResultVO getBopsKolById(String kolId);
	/**
	 * @author Rylan 2015年8月5日 下午10:29:44
	 * @Method: getListBopsKol 
	 * @Description: TODO
	 * @return 
	 * @throws
	 */
	public List<BopsKolResultVO> getListBopsKol(BopsKolParamVO bopsKolParamVO);
	/**
	 * @author Rylan 2015年8月5日 下午10:29:48
	 * @Method: deleteBopsKol 
	 * @Description: TODO
	 * @param kolId
	 * @return 
	 * @throws
	 */
	public int deleteBopsKol(String kolId);
	
	/**
	 * @author Rylan 2015年8月7日 下午2:43:16
	 * @Method: auditKolService 
	 * @Description: TODO
	 * @param bopsKolVO
	 * @return 
	 * @throws
	 */
	public void auditKolService(BopsKolParamVO bopsKolVO);
	
	/**
	 * @author Rylan 2015年8月7日 下午2:43:07
	 * @Method: insertUserKol 
	 * @Description: TODO
	 * @param bopsKolVO
	 * @return 
	 * @throws
	 */
	public int insertUserKol(UserKol bopsKolVO);
	
	/**
	 * @author Rylan 2015年8月7日 下午2:43:11
	 * @Method: deleteUserKol 
	 * @Description: TODO
	 * @param kolId
	 * @return 
	 * @throws
	 */
	public int deleteUserKol(String kolId);
	/**
	 * @author Rylan 2015年8月9日 下午9:31:47
	 * @Method: selectByIds 
	 * @Description: TODO
	 * @param categoryIds
	 * @return 
	 * @throws
	 */
	List<BopsKolCategory>  selectByIds(@Param("categoryIds") String categoryIds);

	public void addApiKol(BopsKolUpdateParamVO bopsKolVO);
	
}
