package com.need.domain.vo.feed;

/**
 * 
 * @author YAN 2015-12-14 19:08:59
 * @ClassName FeedReportVO
 * @Description TODO
 * @version V2.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: YAN 2015-12-14
 * @modify by reason:{方法名}:{原因}
 */
public class FeedReportVO extends FeedVO {

    private static final long serialVersionUID = 6690029384690693649L;
    
    private String reportContent;
    private String reportStatus;

    /**
     * @return the reportContent
     */
    public String getReportContent() {
        return reportContent;
    }

    /**
     * @param reportContent the reportContent to set
     */
    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    /**
     * @return the reportStatus
     */
    public String getReportStatus() {
        return reportStatus;
    }

    /**
     * @param reportStatus the reportStatus to set
     */
    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

}