package com.example.domain.encoder;

/**
 * Created by Administrator on 2017/8/2 0002.
 */
public class Encoder1 {
    private Integer id;
    private String groups;

    private Integer ChannelID;
    private String ProtocolType;
    private Integer DecoderID;
    private Integer DecoderChannelID;
    private Integer CamID;
    private Integer MatrixID;
    private Integer MatrixInChannelNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public Integer getChannelID() {
        return ChannelID;
    }

    public void setChannelID(Integer channelID) {
        ChannelID = channelID;
    }

    public String getProtocolType() {
        return ProtocolType;
    }

    public void setProtocolType(String protocolType) {
        ProtocolType = protocolType;
    }

    public Integer getDecoderID() {
        return DecoderID;
    }

    public void setDecoderID(Integer decoderID) {
        DecoderID = decoderID;
    }

    public Integer getDecoderChannelID() {
        return DecoderChannelID;
    }

    public void setDecoderChannelID(Integer decoderChannelID) {
        DecoderChannelID = decoderChannelID;
    }

    public Integer getCamID() {
        return CamID;
    }

    public void setCamID(Integer camID) {
        CamID = camID;
    }

    public Integer getMatrixID() {
        return MatrixID;
    }

    public void setMatrixID(Integer matrixID) {
        MatrixID = matrixID;
    }

    public Integer getMatrixInChannelNo() {
        return MatrixInChannelNo;
    }

    public void setMatrixInChannelNo(Integer matrixInChannelNo) {
        MatrixInChannelNo = matrixInChannelNo;
    }
}
