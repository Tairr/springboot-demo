package com.example.service.group;



import com.example.domain.group.GroupInfo;

import java.util.List;

/**
 * Created on 2017/10/13.
 */
public interface IGroupService {
    List<GroupInfo> list();
    void add(GroupInfo groupInfo);
    void update(GroupInfo groupInfo);
    void delete(GroupInfo groupInfo);
    void adds(GroupInfo groupInfo);
    List<Integer> list1();
    List<Integer> list2();

}
