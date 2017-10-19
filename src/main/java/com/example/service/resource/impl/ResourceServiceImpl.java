package com.example.service.resource.impl;


import com.example.dao.account.IAccountDao;
import com.example.dao.authority.IAuthorityDao;
import com.example.dao.resource.IResourceDao;
import com.example.dao.resource_authority.IResourceAuthorityDao;
import com.example.domain.account.Account;
import com.example.domain.authority.Authority;
import com.example.domain.base.BaseModel;
import com.example.domain.resource.Resource;
import com.example.domain.resource_authority.ResourceAuthority;
import com.example.service.resource.IResourceService;
import com.example.util.BeanToMap;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
@Service("resourceService")
public class ResourceServiceImpl implements IResourceService {
    @Autowired
    private IResourceDao resourceDao;

    @Autowired
    private IAccountDao accountDao;

    @Autowired
    private IResourceAuthorityDao resourceAuthorityDao;

    @Autowired
    private IAuthorityDao authorityDao;

    @Override
    @Cacheable(value = "resourceCache")
    public List<Resource> queryForObject(BaseModel baseModel) {
        return this.resourceDao.queryForObject(baseModel);
    }

    @Override
    public List<Resource> queryForResourceByUsernameAndType(String username, String type) {
        Account account = new Account();
        account.setUsername(username);
        List<Authority> authorityList = new ArrayList<>();
        authorityList = this.accountDao.queryForAuthorities(account);
        List<String> resourceIdList = new ArrayList<>();
        for (Authority authority : authorityList) {
            ResourceAuthority resourceAuthority = new ResourceAuthority();
            resourceAuthority.setAuthorityId(authority.getId());
            List<ResourceAuthority> resourceAuthorityList = this.resourceAuthorityDao.queryForObject(resourceAuthority);
            for (ResourceAuthority resourceAuthority1 : resourceAuthorityList) {
                resourceIdList.add(resourceAuthority1.getResourceId());
            }
        }
        List<Resource> resourceList = new ArrayList<>();
        for (String id : resourceIdList) {
            Resource resource = new Resource();
            resource.setId(id);
            resource.setType(type);
            List<Resource> resources = this.queryForObject(resource);
            if (resources.size() != 0) {
                resourceList.add(resources.get(0));
            }
        }
        return resourceList;
    }

    @Override
    public List<Map<String, Object>> queryForList(BaseModel baseModel) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Resource> resourceList = new ArrayList<>();
        resourceList = this.resourceDao.queryForObject(baseModel);
        if (resourceList.size() > 0) {
            for (Resource resource : resourceList) {
                Map<String, Object> resultMap = new HashedMap();
                resultMap = BeanToMap.beanToMap(resource);
                ResourceAuthority resourceAuthority = new ResourceAuthority();
                resourceAuthority.setResourceId(resource.getId());
                List<ResourceAuthority> resourceAuthorityList = new ArrayList<>();
                resourceAuthorityList = this.resourceAuthorityDao.queryForObject(resourceAuthority);
                List<Authority> authorityList = new ArrayList<>();
                Map<String, Object> authorityMap = new HashedMap();
                for (ResourceAuthority ra : resourceAuthorityList) {
                    Authority authority = new Authority();
                    authority.setId(ra.getAuthorityId());
                    List<Authority> auList = new ArrayList<>();
                    auList = this.authorityDao.queryForObject(authority);
                    if (auList.size() != 0) {
                        if (authorityMap.get(auList.get(0).getId()) == null) {
                            authorityMap.put(auList.get(0).getId(), auList.get(0));
                        }
                    }
                }
                for (Map.Entry<String, Object> entry : authorityMap.entrySet()) {
                    authorityList.add((Authority) entry.getValue());
                }
                resultMap.put("authorities", authorityList);
                resultList.add(resultMap);
            }
            return resultList;
        } else {
            return null;
        }
    }
}
