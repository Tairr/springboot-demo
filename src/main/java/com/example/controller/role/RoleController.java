package com.example.controller.role;


import com.example.domain.role.Role;
import com.example.domain.role.RoleTmp;
import com.example.domain.role_authority.RoleAuthority;
import com.example.service.role.IRoleService;
import com.example.service.role_authority.IRoleAuthorityService;
import com.example.util.TimeUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/role")
@RestController
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IRoleAuthorityService roleAuthorityService;

    @RequestMapping(method = RequestMethod.GET)
    public String queryForList() {
        List<Map<String, Object>> roleList = new ArrayList<>();
        Map<String, Object> resultMap = new HashMap<>();
        roleList = this.roleService.queryForObject(new Role());

        resultMap.put("code", "0000");
        resultMap.put("data", roleList);

        Gson gson = new Gson();
        return gson.toJson(resultMap);
    }

    @RequestMapping(method = RequestMethod.POST)
   /* public String add(@RequestBody Map<String, Object> paramMap) {*/
    public String add(@RequestBody Role role) {
       /* Role role = new Role();*/
        role.setId(UUID.randomUUID().toString());
        role.setIfuse("y");
        List<Map<String,String>> test = new ArrayList<>();
        role.setCreateTime(TimeUtils.getCurrentTime());
        role.setUpdateTime(TimeUtils.getCurrentTime());
        int flag = this.roleService.addObject(role);
        List<Role> roleList = this.roleService.query1ForObject(role);
        String roleId = roleList.get(0).getId();

        for (int i = 0; i < role.getAuthorities().size(); i++) {

            Map<String, String> listObject = (Map<String, String>) role.getAuthorities().get(i);

            RoleAuthority roleAuthority = new RoleAuthority();
            roleAuthority.setId(UUID.randomUUID().toString());
            roleAuthority.setRoleId(roleId);
            roleAuthority.setAuthorityId(listObject.get("id"));
            this.roleAuthorityService.addForObject(roleAuthority);
        }
        Map<String, Object> resultMap = new HashMap<>();
        if (flag == 1) {
            resultMap.put("code", "0000");
            resultMap.put("data", "success");
        } else {
            resultMap.put("code", "0002");
            resultMap.put("data", "role insert failed");
        }
        Gson gson = new Gson();
        return gson.toJson(resultMap);
    }

    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    public void updateRole(@RequestBody RoleTmp role) {
        String realName = role.getRoleName();
        String description = role.getDescription();
        String orderNum = role.getOrderNum();
        String id = role.getId();
        this.roleService.updateRole(role);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") String id) {
        Role role = new Role();
        role.setId(id);
        int flag = this.roleService.deleteObject(role);
        Map<String, Object> resultMap = new HashMap<>();
        if (flag == 1) {
            resultMap.put("code", "0000");
            resultMap.put("data", "success");
        } else {
            resultMap.put("code", "0021");
            resultMap.put("data", "role delete failed");
        }
        Gson gson = new Gson();
        return gson.toJson(resultMap);
    }
}
