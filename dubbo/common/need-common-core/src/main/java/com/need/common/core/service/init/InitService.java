package com.need.common.core.service.init;

import com.need.common.core.service.feed.FeedCacheService;
import com.need.common.core.service.follow.FollowCacheService;
import com.need.common.core.service.system.SystemSettingService;
import com.need.common.core.service.user.UserCacheService;
import javax.annotation.PostConstruct;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author 庆凯 2015-12-5 14:42:49
 * @ClassName InitService
 * @Description 项目启动时调用的初始化服务
 * @version V2.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-12-5
 * @modify by reason:{方法名}:{原因}
 */
public class InitService {

    @Autowired
    private FeedCacheService feedCacheService;

    @Autowired
    private FollowCacheService followCacheService;

    @Autowired
    private UserCacheService userCacheService;

    @Autowired
    private SystemSettingService systemSettingService;

    @PostConstruct
    private void init() {
        try {
            systemSettingService.init();
            userCacheService.init();
            followCacheService.init();
            feedCacheService.init();
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error("-+-+-+-+-+-+-+-+-+-+-+-+-+-+init fail-+-+-+-+-+-+-+-+-+-+-+-+-+-+", e);
            System.exit(0);
        }
    }

}
