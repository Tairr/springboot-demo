package com.example.domain.group;



import com.example.domain.orgtree.EncoderOrgTree;

import java.util.List;

/**
 * Created on 2017/10/13.
 */
public class GroupInfo {
    private Integer GroupID;
    private Integer Type;
    private String VirtualOrgID;
    private String Name;
    private Integer ParentID;
    private String BusinessGroupID;
    private String ParentOrgID;
    private String groups;
    private List<EncoderOrgTree> children;

    public List<EncoderOrgTree> getChildren() {
        return children;
    }

    public void setChildren(List<EncoderOrgTree> children) {
        this.children = children;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public Integer getGroupID() {
        return GroupID;
    }

    public void setGroupID(Integer groupID) {
        GroupID = groupID;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }

    public String getVirtualOrgID() {
        return VirtualOrgID;
    }

    public void setVirtualOrgID(String virtualOrgID) {
        VirtualOrgID = virtualOrgID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getParentID() {
        return ParentID;
    }

    public void setParentID(Integer parentID) {
        ParentID = parentID;
    }

    public String getBusinessGroupID() {
        return BusinessGroupID;
    }

    public void setBusinessGroupID(String businessGroupID) {
        BusinessGroupID = businessGroupID;
    }

    public String getParentOrgID() {
        return ParentOrgID;
    }

    public void setParentOrgID(String parentOrgID) {
        ParentOrgID = parentOrgID;
    }
}
