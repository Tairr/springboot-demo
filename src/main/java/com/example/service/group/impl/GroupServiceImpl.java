package com.example.service.group.impl;


import com.example.dao.group.IGroupDao;
import com.example.domain.group.GroupInfo;
import com.example.service.group.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2017/10/13.
 */
@Service("groupsService")
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private IGroupDao groupDao;
    @Override
    public List<GroupInfo> list() {
        return this.groupDao.queryForObject();
    }

    @Override
    public void delete(GroupInfo groupInfo) {
        this.groupDao.deletForObject(groupInfo);
    }

    @Override
    public void adds(GroupInfo groupInfo) {
        this.groupDao.addForObject(groupInfo);
    }

    @Override
    public List<Integer> list1() {
        return this.groupDao.queryForObject1();
    }


    @Override
    public List<Integer> list2() {
        return this.groupDao.queryForObject2();
    }

    @Override
    public void update(GroupInfo groupInfo) {
        this.groupDao.updateForObject(groupInfo);
    }

    @Override
    public void add(GroupInfo groupInfo) {
        this.groupDao.addForObject(groupInfo);
    }
}
