package com.example.domain.encoder;


import com.example.domain.camera.Camera1;

import java.util.List;

/**
 * Created by Administrator on 2017/8/21 0021.
 */
public class EncoderTree extends Encoder1 {
    private List<Camera1> children;

    public List<Camera1> getChildren() {
        return children;
    }

    public void setChildren(List<Camera1> children) {
        this.children = children;
    }
}
