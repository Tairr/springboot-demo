package com.example.domain.encoder;

/**
 * Created by Administrator on 2017/8/2 0002.
 */
public class Encoder {
    private int id;
    private String groups;
    private String encoder_name;
    private String encoder_address;
    private String encoder_port;
    private String encoder_channel;
    private String encoder_stream_number;
    private String decod_channel;
    private String lit_channel_number;
    private String loginname;
    private String password;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroups() {
        return groups;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getEncoder_name() {
        return encoder_name;
    }

    public void setEncoder_name(String encoder_name) {
        this.encoder_name = encoder_name;
    }

    public String getEncoder_address() {
        return encoder_address;
    }

    public void setEncoder_address(String encoder_address) {
        this.encoder_address = encoder_address;
    }

    public String getEncoder_port() {
        return encoder_port;
    }

    public void setEncoder_port(String encoder_port) {
        this.encoder_port = encoder_port;
    }

    public String getEncoder_channel() {
        return encoder_channel;
    }

    public void setEncoder_channel(String encoder_channel) {
        this.encoder_channel = encoder_channel;
    }

    public String getEncoder_stream_number() {
        return encoder_stream_number;
    }

    public void setEncoder_stream_number(String encoder_stream_number) {
        this.encoder_stream_number = encoder_stream_number;
    }

    public String getDecod_channel() {
        return decod_channel;
    }

    public void setDecod_channel(String decod_channel) {
        this.decod_channel = decod_channel;
    }

    public String getLit_channel_number() {
        return lit_channel_number;
    }

    public void setLit_channel_number(String lit_channel_number) {
        this.lit_channel_number = lit_channel_number;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }
}
