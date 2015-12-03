package sjes.gateway.admin.module.base.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sjes.gateway.admin.module.base.domain.AclUser;
import sjes.gateway.admin.module.base.model.UserJson;
import sjes.gateway.admin.module.base.service.AclUserService;
import sjes.gateway.admin.utils.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jiangzhe on 15-11-16.
 */
@RestController
@RequestMapping("/user")
public class AclUserController {
    @Autowired
    private AclUserService aclUserService;

    /**
     * 保存
     */
    @RequestMapping(value="/save",method = RequestMethod.POST)
    public Object save(AclUser aclUser){
        Map map = new HashMap<>();

        try {

            aclUserService.save(aclUser);

            map.put("state","success");
        }catch (Exception e){
            map.put("state","error");
        }
        return map;
    }

    /**
     * 删除
     */
    @RequestMapping(value="/remove",method = RequestMethod.POST)
    public Object remove(String ids){
        Map map = new HashMap<>();
        for(String id : ids.split(",")){
            try {
                aclUserService.del(Long.valueOf(id));
                map.put("state","success");
            }catch(Exception e){
                map.put("state","error");
            }
        }
        return map;
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method = RequestMethod.POST)
    public Object update(AclUser aclUser){
        Map map = new HashMap<>();
        try {
            aclUserService.save(aclUser);
            map.put("state","success");
        }catch(Exception e){
            map.put("state","error");
        }
        return map;
    }

    /**
     * 分页列表
     */
    @RequestMapping("/list")
    public Object findAll(int page,int limit,String username){
        Map<Object,Object> filter = new HashMap<>();
        filter.put("username",username);
        Page<AclUser> userPage = aclUserService.selectPageList(page, limit, filter);
        List<UserJson> aclUserList = new ArrayList<>();

        for (AclUser aclUser : userPage) {
            UserJson userJson = new UserJson();
            BeanUtil.setBean2Bean(aclUser,userJson);
            userJson.setExpiredDate(aclUser.getExpiredDate().toString());
            userJson.setOrg(aclUser.getOrg() != null ? aclUser.getOrg().getOrgName() : "");
            userJson.setOrgId(aclUser.getOrg() != null ? aclUser.getOrg().getId() : 0L);
            aclUserList.add(userJson);
        }
        Map map = new HashMap<>();
        map.put("success",true);
        map.put("total",userPage.getTotalElements());
        map.put("items",aclUserList);
        return map;
    }

    /**
     * 该部门下，在此角色下包含和不包含的用户
     */
    @RequestMapping(value="/userInOrNotInOrg",method = RequestMethod.POST)
    public Object getAclUserByRoleAndOrg(Long roleId,Long orgId){
        return aclUserService.userInOrNotInOrg(roleId, orgId);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), true));
        binder.registerCustomEditor(int.class, new IntegerEditor());
        binder.registerCustomEditor(long.class, new LongEditor());
        binder.registerCustomEditor(double.class, new DoubleEditor());
        binder.registerCustomEditor(float.class, new FloatEditor());
    }
}
