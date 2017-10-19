package com.example.domain.orgtree;



import com.example.domain.org.Org;

import java.util.List;

/**
 * Created by dell on 2017/5/5.
 * Good Luck !
 * へ　　　　　／|
 * 　　/＼7　　　 ∠＿/
 * 　 /　│　　 ／　／
 * 　│　Z ＿,＜　／　　 /`ヽ
 * 　│　　　　　ヽ　　 /　　〉
 * 　 Y　　　　　`　 /　　/
 * 　ｲ●　､　●　　⊂⊃〈　　/
 * 　()　 へ　　　　|　＼〈
 * 　　>ｰ ､_　 ィ　 │ ／／
 * 　 / へ　　 /　ﾉ＜| ＼＼
 * 　 ヽ_ﾉ　　(_／　 │／／
 * 　　7　　　　　　　|／
 * 　　＞―r￣￣`ｰ―＿
 */
public class OrgTree extends Org {
    private List<OrgTree> children;

    public OrgTree() {
    }

    public OrgTree(Org org) {
        this.setCreateTime(org.getCreateTime());
        this.setOrgName(org.getOrgName());
        this.setParentId(org.getParentId());
        this.setParentName(org.getParentName());
        this.setLeader(org.getLeader());
        this.setUpdateTime(org.getUpdateTime());
        this.setIfuse(org.getIfuse());
        this.setPath(org.getPath());
        this.setId(org.getId());
        this.setOrderNum(org.getOrderNum());
    }

    public List<OrgTree> getChildren() {
        return children;
    }

    public void setChildren(List<OrgTree> children) {
        this.children = children;
    }
}
