package com.xt.mp.bean;


import com.baomidou.mybatisplus.annotation.TableLogic;

public class User {
    private Integer id;
    private String username;

    // 逻辑删除属性
    @TableLogic
    private Integer logicFlag;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", logicFlag=" + logicFlag +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getLogicFlag() {
        return logicFlag;
    }

    public void setLogicFlag(Integer logicFlag) {
        this.logicFlag = logicFlag;
    }
}
