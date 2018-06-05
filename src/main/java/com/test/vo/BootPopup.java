package com.test.vo;

import java.util.Date;

/**
 * 启动页弹窗
 *
 * @author luoyong
 * @date 2018/5/14
 */
public class BootPopup {

    /** 主键ID */
    private Integer id;

    /** 状态：0:删除，1:启用，2:下线 */
    private Byte status;

    /** 启动页名称 */
    private String popupName;

    /** 开始时间 */
    private Date startTime;

    /** 结束时间 */
    private Date endTime;

    /** 展现时间，单位秒 */
    private Byte showTime;

    /** 图片地址 */
    private String picUrl;

    /** 活动链接 */
    private String activityUrl;

    /** 编辑人 */
    private String editPerson;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getPopupName() {
        return popupName;
    }

    public void setPopupName(String popupName) {
        this.popupName = popupName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Byte getShowTime() {
        return showTime;
    }

    public void setShowTime(Byte showTime) {
        this.showTime = showTime;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getActivityUrl() {
        return activityUrl;
    }

    public void setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl;
    }

    public String getEditPerson() {
        return editPerson;
    }

    public void setEditPerson(String editPerson) {
        this.editPerson = editPerson;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getSimpleName());
        builder.append(" [");
        builder.append("Hash = ").append(hashCode());
        builder.append(", id=").append(id);
        builder.append(", status=").append(status);
        builder.append(", popupName=").append(popupName);
        builder.append(", startTime=").append(startTime);
        builder.append(", endTime=").append(endTime);
        builder.append(", showTime=").append(showTime);
        builder.append(", picUrl=").append(picUrl);
        builder.append(", activityUrl=").append(activityUrl);
        builder.append(", editPerson=").append(editPerson);
        builder.append(", createTime=").append(createTime);
        builder.append(", updateTime=").append(updateTime);
        builder.append("]");
        return builder.toString();
    }
}
