package com.example.domain.camera;

/**
 * Created by Administrator on 2017/8/11 0011.
 */
public class Camera {
    private int id;
    private String camId ;   //摄像机ID
    private String name ;   //摄像机名称
    private String domain ;  //摄像机所在地点
    private String groups ;     //摄像机所属组域
    private String online ;      //是否在线
    private String resSbuType;		//可控类型
    private String dvr ;			//所在DVR
    private String encChPort ;    //通道号
   /* private Long streamSrvId ;		//所属转发服务器id
    private Long storageSrvID ;		//存储服务器id
    private String streamSrvName ;	//转发服务器名称
    private String storageSrvName ;	//存储服务器名称*/
   /* private Long resId;// 资源点Id，关联tbl_res_attr表autoid，保持数据唯一性，没有物理意义
    private Long camId;// 摄像机数字ID，系统内部对摄像机的标识，可以用来控制Cam。与tbl_res_attr中的resId相等
    private String userCamID;// 字符ID，供用户自定义使用
    private int connectType;// 1：连编码器 2：连矩阵 3：数字摄像机
   // private Codec codec;// 关联的编码器
   // private EncChannel encCh;// 关联编码器通道
   // private MatrixInfo matrix;// 连接矩阵
    private Integer matrixInportId;// 矩阵输入端口号
    private Integer ptzCtrlProtocol;// 云台控制协议类型
  //  private Integer ptzAddress;// 云台控制地址码
   // private String ptzurl;// 云台控制URL，通过url解析控制的传输协议：utp、tcp、com、485、422等，定义比较灵活
    private Boolean ifCustom;// 是否自定义
    // private Service service;//所属转发服务器
    private Long streamSrvId;
    // private Integer storageType;//视频存储类型
    private Integer storageSrvId;*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCamId() {
        return camId;
    }

    public void setCamId(String camId) {
        this.camId = camId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getResSbuType() {
        return resSbuType;
    }

    public void setResSbuType(String resSbuType) {
        this.resSbuType = resSbuType;
    }

    public String getDvr() {
        return dvr;
    }

    public void setDvr(String dvr) {
        this.dvr = dvr;
    }

    public String getEncChPort() {
        return encChPort;
    }

    public void setEncChPort(String encChPort) {
        this.encChPort = encChPort;
    }
}
