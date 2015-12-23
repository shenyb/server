package com.need.api.web.controller.user;


import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.service.follow.FollowCacheService;
import com.need.common.core.service.system.SystemSettingService;
import com.need.common.core.service.user.UserService;
import com.need.common.domain.enums.SystemSettingEnum;
import com.need.common.domain.enums.UserTypeEnum;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.user.KolInfoVo;
import com.need.common.domain.vo.user.UserFollowVO;
import com.need.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping(value = ControllerMappings.USERINFO_GET_USER_SELF_INFO)
public class GetUserSelfInfoController {

	private static final Logger logger = Logger.getLogger(GetUserSelfInfoController.class);

	@Autowired
	private UserService userService;

    @Autowired
    private FollowCacheService followCacheService;
    
    @Autowired
    private SystemSettingService SystemSettingService;

    @ResponseBody
    @RequestMapping(params = "apiVersion=2.0", method = RequestMethod.GET)
    public Message getUserInfo(@RequestParam(required = true) String userId) {
        Message success = Message.success();
        logger.info(String.format("getUserInfo...in...param:userId %s", userId));
        if(StringUtil.isBlank(userId)) {
            return Message.error(BizReturnCode.USERID_NOT_EXIST);
        }
        //Addy liyongran 20151208 新增用户sut参数
/*        if(userId != null && userId.equals(targetId)){
        	String[] replaceUserId =ShortUrlUtil.shortUrl(userId);
        	String sut=replaceUserId[new Random().nextInt(replaceUserId.length-1)];
        	success.addData("sut",sut);
        	JedisSentinelClient.setObject(RedisKeyConstant.REPLACE_USER_ID.concat(sut), userId, -1);
        }*/
        String isSupportShare= SystemSettingService.getSystemSettingByName(SystemSettingEnum.USERINFO_IS_SUPPORT_SHARE.code);
        //success.addData("isSupportShare", isSupportShare);  
        String userType = userService.getUserType(userId);
        if (UserTypeEnum.COMMON.name().equals(userType)) {
            UserFollowVO user = userService.getuserFollowInfo(userId, userId);
            user.setUserType(UserTypeEnum.getCode(user.getUserType()));
            user.setIsSupportShare(isSupportShare);
            success.addData("user", user);
        } else if (UserTypeEnum.KOL.name().equals(userType)) {
            KolInfoVo kol = userService.getKolInfo(userId);
            kol.setFollowCount(followCacheService.getFollowCount(userId));
            kol.setFollowedCount(followCacheService.getFollowedCount(userId));
            kol.setIsSupportShare(isSupportShare);
            Boolean isFollow = false;
            if (!StringUtil.isBlank(userId)) {
                isFollow = followCacheService.isFollow(userId, userId);
            }
            kol.setIsFollow(isFollow.toString().toUpperCase());
            String kolCategories = userService.getcategoryNameList(kol.getUserId());
            if (kolCategories != null) {
                kol.setKolCategories(kolCategories);
            }
            kol.setUserType(UserTypeEnum.getCode(kol.getUserType()));

            success.addData("user", kol);
        } else {
            return Message.error(BizReturnCode.GET_USERINFO_FAIL);
        }
        return success;
    }

}
