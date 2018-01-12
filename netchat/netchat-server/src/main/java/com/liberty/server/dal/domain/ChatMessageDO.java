package com.liberty.server.dal.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Binbin Wang
 * @date 2018/1/12
 */
public class ChatMessageDO implements Serializable{

    private String userId;

    private String content;

    private Date createTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ChatMessageDO{" +
                "userId='" + userId + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
