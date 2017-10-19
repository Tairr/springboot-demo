package com.example.service.role_authority.impl;


import com.example.dao.role_authority.IRoleAuthorityDao;
import com.example.domain.role_authority.RoleAuthority;
import com.example.service.role_authority.IRoleAuthorityService;
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
@Service("roleAuthorityService")
public class RoleAuthorityServiceImpl implements IRoleAuthorityService {
    @Autowired
    IRoleAuthorityDao roleAuthorityDao;

    @Override
    public List<RoleAuthority> queryForObject(RoleAuthority roleAuthority) {
        return this.roleAuthorityDao.queryForObject(roleAuthority);
    }

    @Override
    public void addForObject(RoleAuthority roleAuthority) {
        this.roleAuthorityDao.addForObject(roleAuthority);
    }
}
