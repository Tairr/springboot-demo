package com.example.service.authority.impl;


import com.example.dao.authority.IAuthorityDao;
import com.example.dao.resource.IResourceDao;
import com.example.dao.resource_authority.IResourceAuthorityDao;
import com.example.dao.role_authority.IRoleAuthorityDao;
import com.example.domain.authority.Authority;
import com.example.domain.base.BaseModel;
import com.example.domain.resource_authority.ResourceAuthority;
import com.example.domain.role_authority.RoleAuthority;
import com.example.service.authority.IAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
@Service("authorityService")
public class AuthorityServiceImpl implements IAuthorityService {
    @Autowired
    private IAuthorityDao authorityDao;
    @Autowired
    IResourceAuthorityDao resourceAuthorityDao;
    @Autowired
    IRoleAuthorityDao roleAuthorityDao;
    @Autowired
    IResourceDao ResourceDao;


    @Override
    public List<Authority> queryForObject(BaseModel baseModel) {

        return this.authorityDao.queryForObject(baseModel);
    }

    @Override
    public int addObject(BaseModel baseModel) {
        return this.authorityDao.addObject(baseModel);
    }

    @Override
    public void deleteObject(BaseModel baseModel) {
        RoleAuthority roleAuthority = new RoleAuthority();
        ResourceAuthority resourceAuthority = new ResourceAuthority();
        roleAuthority.setAuthorityId(baseModel.getId());
        resourceAuthority.setAuthorityId(baseModel.getId());
        this.authorityDao.deleteObject(baseModel);
        this.resourceAuthorityDao.deleteObject(resourceAuthority);
        this.roleAuthorityDao.deleteObject(roleAuthority);
    }

    @Override
    public void updateForObject(BaseModel baseModel) {
        this.authorityDao.updateForObject(baseModel);
    }
}
