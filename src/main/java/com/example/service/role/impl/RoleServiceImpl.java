package com.example.service.role.impl;



import com.example.dao.authority.IAuthorityDao;
import com.example.dao.role.IRoleDao;
import com.example.dao.role_authority.IRoleAuthorityDao;
import com.example.domain.authority.Authority;
import com.example.domain.base.BaseModel;
import com.example.domain.role.Role;
import com.example.domain.role_authority.RoleAuthority;
import com.example.service.role.IRoleService;
import com.example.util.BeanToMap;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/1/12.
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
@Service("roleService")
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private IRoleAuthorityDao roleAuthorityDao;
    @Autowired
    private IAuthorityDao authorityDao;

    @Override
    public List<Map<String, Object>> queryForObject(BaseModel baseModel) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Role> roleList = this.roleDao.queryForObject(baseModel);
        String authorityIds = "";
        String authorityNames = "";
        for (Role role : roleList) {
            Map<String, Object> resultMap = new HashedMap();
            resultMap = BeanToMap.beanToMap(role);
            RoleAuthority roleAuthority = new RoleAuthority();
            roleAuthority.setRoleId(role.getId());
            List<RoleAuthority> roleAuthorityList = this.roleAuthorityDao.queryForObject(roleAuthority);
            String testId = "";
            String testName = "";
            for(int i=0;i<roleAuthorityList.size();i++) {

                Authority authority = new Authority();
                List<Authority> authorityList = new ArrayList<>();
                authority.setId(roleAuthorityList.get(i).getAuthorityId());
                authorityList = this.authorityDao.queryForObject(authority);

                if(i!=0) {
                    testName = testName + "," + authorityList.get(0).getAuthorityName();
                    testId += testId + "," + authorityList.get(0).getId();
                }else{
                    testName =  authorityList.get(0).getAuthorityName();
                    testId =  authorityList.get(0).getId();
                }
            }

            authorityNames=testName;
            authorityIds=testId;
         /*   List<Authority> authorityList = new ArrayList<>();
            for (RoleAuthority ra : roleAuthorityList) {
                Authority authority = new Authority();
                authority.setId(ra.getAuthorityId());
                authorityList = this.authorityDao.queryForObject(authority);
            }
            if (authorityList.size() > 0) {
                for (int i = 0; i < authorityList.size(); i++) {
                    if (i != (authorityList.size() - 1)) {
                        authorityIds += authorityList.get(i).getId() + ",";
                        authorityNames += authorityList.get(i).getAuthorityName() + ",";
                    } else {
                        authorityIds += authorityList.get(i).getId();
                        authorityNames += authorityList.get(i).getAuthorityName();
                    }
                }*/
                resultMap.put("authorityIds", authorityIds);
                resultMap.put("authorityNames", authorityNames);

            resultList.add(resultMap);
        }
        return resultList;
    }

    @Override
    public int addObject(BaseModel baseModel) {
        return this.roleDao.addObject(baseModel);
    }

    @Override
    public int deleteObject(BaseModel baseModel) {
        return this.roleDao.deleteObject(baseModel);
    }

    @Override
    public void updateRole(BaseModel baseModel) {
        this.roleDao.updateRole(baseModel);
    }

    @Override
    public List<Role> query1ForObject(BaseModel baseModel) {
        return this.roleDao.queryForObject(baseModel);
    }
}
