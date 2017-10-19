package com.example.util;


import com.example.domain.base.BaseModel;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * 为BaseModel实体设置属性
 */
public class BaseModelFieldSetUtils {
    //新增用
    public static BaseModel FieldSet(BaseModel baseModel){
        if (StringUtils.isEmpty(baseModel.getId())) {
            baseModel.setId(UUID.randomUUID().toString());
        }
        if (StringUtils.isEmpty(baseModel.getCreateTime())) {
            baseModel.setCreateTime(TimeUtils.getCurrentTime());
        }
        baseModel.setUpdateTime(TimeUtils.getCurrentTime());
        if (StringUtils.isEmpty(baseModel.getIfuse())) {
            baseModel.setIfuse("y");
        }
        return baseModel;
    }
    //更新用
    public static BaseModel FieldSetByOldField(BaseModel newBaseModel,BaseModel oldBaseModel){
        if (StringUtils.isEmpty(newBaseModel.getCreateTime())) {
            String createTime = oldBaseModel.getCreateTime();
            newBaseModel.setCreateTime(createTime);
        }
        if(StringUtils.isEmpty(newBaseModel.getOrderNum())){
            String ordernum = oldBaseModel.getOrderNum();
            newBaseModel.setOrderNum(ordernum);
        }
        if (StringUtils.isEmpty(newBaseModel.getIfuse())) {
            newBaseModel.setIfuse("y");
        }
        newBaseModel.setUpdateTime(TimeUtils.getCurrentTime());
        return newBaseModel;
    }
}
