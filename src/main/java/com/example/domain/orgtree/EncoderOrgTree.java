package com.example.domain.orgtree;



import com.example.domain.camera.Camera1;
import com.example.domain.res_attr.Res_Attr;

import java.util.List;

/**
 * Created on 2017/10/18.
 */
public class EncoderOrgTree extends Res_Attr {
     private List<Camera1> children;
     private String groups;

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public List<Camera1> getChildren() {
        return children;
    }

    public void setChildren(List<Camera1> children) {
        this.children = children;
    }
}
