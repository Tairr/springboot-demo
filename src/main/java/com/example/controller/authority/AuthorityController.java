package com.example.controller.authority;


import com.example.dao.resource.IResourceDao;
import com.example.dao.resource_authority.IResourceAuthorityDao;
import com.example.domain.authority.Authority;
import com.example.domain.resource.Resource;
import com.example.domain.resource_authority.ResourceAuthority;
import com.example.service.authority.IAuthorityService;
import com.example.service.resource.IResourceService;
import com.example.service.resource_authority.IResourceAuthorityService;
import com.example.util.TimeUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by dell on 2017/4/19.
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
@RequestMapping("/authority")
@RestController
public class AuthorityController {

    @Autowired
    private IAuthorityService authorityService;
    @Autowired
    private IResourceService iResourceService;
    @Autowired
    IResourceAuthorityDao resourceAuthorityDao;

    @Autowired
    IResourceDao ResourceDao;

    @Autowired
    private IResourceAuthorityService iResourceAuthorityService;
    @RequestMapping(method = RequestMethod.GET)
    public String queryForList() {
        List<Authority> authorityList = new ArrayList<>();
        List<Authority> authorityAll = new ArrayList<>();
        Map<String, Object> resultMap = new HashMap<>();
        authorityList = this.authorityService.queryForObject(new Authority());

        for (int i = 0; i < authorityList.size(); i++) {

            Authority authority = new Authority();
          /*  List<Resource> lists = new ArrayList<>();*/
            authority.setId(authorityList.get(i).getId());
            authority.setAuthorityName(authorityList.get(i).getAuthorityName());
            authority.setDescription(authorityList.get(i).getDescription());
            authority.setCreateTime(authorityList.get(i).getCreateTime());
            authority.setIfuse(authorityList.get(i).getIfuse());
            authority.setOrderNum(authorityList.get(i).getOrderNum());
            authority.setUpdateTime(authorityList.get(i).getUpdateTime());
            String aId = "";
            String ak = "";
            ResourceAuthority resourceAuthority = new ResourceAuthority();
            resourceAuthority.setAuthorityId(authorityList.get(i).getId());
            List<ResourceAuthority> authorityList1 = this.resourceAuthorityDao.queryForObject(resourceAuthority);
            for (int j = 0; j < authorityList1.size(); j++) {

                Resource resource = new Resource();
                resource.setId(authorityList1.get(j).getResourceId());
                Resource resource1 = this.ResourceDao.query1ForObject(resource);
                if (resource1 != null && resource1.getType().equals("MENU")) {
                   /* lists.add(resource1);*/
                    if (ak.equals("")) {
                        ak = resource1.getDescription();
                        aId = resource1.getId();
                    } else {
                        ak = ak + "," + resource1.getDescription();
                        aId = aId + "," + resource1.getId();
                    }
                }
            }
            authority.setAk(ak);
            authority.setaId(aId);
          /*  authority.setResourceList(lists);*/

            authorityAll.add(authority);

        }

        resultMap.put("code", "0000");
        resultMap.put("data", authorityAll);

        Gson gson = new Gson();
        return gson.toJson(resultMap);
    }
@RequestMapping(value="/queryAll", method= RequestMethod.GET)
public  String queryforAll(){
    List<Resource> authorityListALL = new ArrayList<>();
    authorityListALL = this.iResourceService.queryForObject(new Resource());
    for(int i=0;i<authorityListALL.size();i++){
        if(!authorityListALL.get(i).getType().equals("MENU")){
            authorityListALL.remove(authorityListALL.get(i));
        }
    }


    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put("code","0000");
    resultMap.put("data", authorityListALL);
    Gson gson=new Gson();
    return gson.toJson(resultMap);
}
    @RequestMapping(method = RequestMethod.POST)
    public String add(@RequestBody Authority authority) {
       // System.out.println("我是SELECTED"+authority.getSelected());
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        if (StringUtils.isEmpty(authority)) {
            resultMap.put("code", "0002");
            resultMap.put("data", "authority insert failed");
            return gson.toJson(resultMap);
        }
        authority.setId(UUID.randomUUID().toString());
        authority.setCreateTime(TimeUtils.getCurrentTime());
        authority.setUpdateTime(TimeUtils.getCurrentTime());
        authority.setIfuse("y");

        int flag = this.authorityService.addObject(authority);
        List<Authority>    queryid=this.authorityService.queryForObject(authority);
         //添加用户选择的资源
        if(authority.getSelected()!=null) {
            for (int i = 0; i < authority.getSelected().size(); i++) {
                ResourceAuthority resourceAuthority = new ResourceAuthority();
                resourceAuthority.setAuthorityId(queryid.get(0).getId());
                resourceAuthority.setResourceId(authority.getSelected().get(i));
                resourceAuthority.setId(UUID.randomUUID().toString());
                this.iResourceAuthorityService.add(resourceAuthority);
            }
            //添加用户登陆必用的资源，为获取菜单和获取所有用户。
            ResourceAuthority re1 = new ResourceAuthority();
            re1.setId(UUID.randomUUID().toString());
            re1.setResourceId("res9");
            re1.setAuthorityId(queryid.get(0).getId());
            this.iResourceAuthorityService.add(re1);
            ResourceAuthority re2 = new ResourceAuthority();
            re2.setId(UUID.randomUUID().toString());
            re2.setResourceId("res1");
            re2.setAuthorityId(queryid.get(0).getId());
            this.iResourceAuthorityService.add(re2);
        }
        if (flag == 1) {
            resultMap.put("code", "0000");
            resultMap.put("data", "success");
        } else {
            resultMap.put("code", "0042");
            resultMap.put("data", "authority insert failed");
        }

        return gson.toJson(resultMap);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") String id) {
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        if (StringUtils.isEmpty(id)) {
            resultMap.put("code", "0043");
            resultMap.put("data", "authority delete failed");
        } else {
            Authority authority = new Authority();
            authority.setId(id);
            this.authorityService.deleteObject(authority);
            resultMap.put("code", "0000");
            resultMap.put("data", "success");
        }
        return gson.toJson(resultMap);
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody Authority authority){
    System.out.println("我是Authority的更新："+authority.getSelected()+"==="+authority.getId());
    //删除所有现有的权限资源
    ResourceAuthority resourceAut1=new ResourceAuthority();
        resourceAut1.setAuthorityId(authority.getId());
    this.resourceAuthorityDao.deleteObject(resourceAut1);
    //重新添加一遍新选择的资源
        if(authority.getSelected()!=null) {
            for (int i = 0; i < authority.getSelected().size(); i++) {
                ResourceAuthority resourceAuthority1 = new ResourceAuthority();
                resourceAuthority1.setAuthorityId(authority.getId());
                resourceAuthority1.setResourceId(authority.getSelected().get(i));
                resourceAuthority1.setId(UUID.randomUUID().toString());
                this.iResourceAuthorityService.add(resourceAuthority1);
            }
            //添加用户登陆必用的资源，为获取菜单和获取所有用户。
            ResourceAuthority re1 = new ResourceAuthority();
            re1.setId(UUID.randomUUID().toString());
            re1.setResourceId("res9");
            re1.setAuthorityId(authority.getId());
            this.iResourceAuthorityService.add(re1);
            ResourceAuthority re2 = new ResourceAuthority();
            re2.setId(UUID.randomUUID().toString());
            re2.setResourceId("res1");
            re2.setAuthorityId(authority.getId());
            this.iResourceAuthorityService.add(re2);
        }
        //修改权限的普通信息
        this.authorityService.updateForObject(authority);
    }
}
