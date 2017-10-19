package com.example.domain.role;


import com.example.domain.base.BaseModel;

/**
 * Created by Administrator on 2017/8/23 0023.
 */
public class RoleTmp extends BaseModel {

    private String roleName;
    private String description;
    private String orderNum;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getOrderNum() {
        return orderNum;
    }

    @Override
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
}
