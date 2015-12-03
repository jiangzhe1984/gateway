package sjes.gateway.admin.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sjes.gateway.admin.main.model.CategoryModel;
import sjes.gateway.admin.main.model.PayAmount;
import sjes.gateway.admin.module.base.domain.AclUser;
import sjes.gateway.admin.module.base.domain.Authority;
import sjes.gateway.admin.module.base.domain.UserAuthority;
import sjes.gateway.admin.module.base.service.AclUserService;
import sjes.gateway.admin.module.security.UserContext;
import sjes.gateway.admin.module.security.UserContextHelper;
import sjes.gateway.admin.utils.PasswordUtil;

import java.util.*;

/**
 * Created by jiangzhe on 15-11-3.
 */
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private AclUserService aclUserService;

    @RequestMapping("/index")
    public Object indexHtml(){
        return "index";
    }



    @RequestMapping("/leftTree")
    @ResponseBody
    public Object leftTreeLoad(){
        UserContext userContext = UserContextHelper.getUserContext();

        return userContext.getLeftTree();
    }

    @RequestMapping("/toolbar")
    @ResponseBody
    public Object topToolbar(){
        Set<String> authorities = new HashSet<>();

        for (UserAuthority userAuthority : UserContextHelper.getUserContext().getUserAuthorities()) {
            Authority authority = userAuthority.getAuthority();
            authorities.add(authority.getAuthority());
        }

        return authorities;

    }

    @RequestMapping("/currentUser")
    @ResponseBody
    public Object currentUser(){
        Map map = new HashMap<>();
        map.put("fullname",UserContextHelper.getUserContext().getUser().getFullName());
        return map;
    }

    @RequestMapping("/login")
    public Object login(){
        return "login";
    }

    @RequestMapping(value="/updatePwd",method = RequestMethod.POST)
    @ResponseBody
    public Object updatePwd(String oldPwd,String newPwd){
        Map map = new HashMap<>();

        AclUser currentUser = UserContextHelper.getUserContext().getUser();

        String currentPwd = currentUser.getPassword();

        if(!currentPwd.equals(PasswordUtil.generatePassword(oldPwd, currentUser.getUsername()))){
           map.put("state","atypism");
        }else{
           try{
               aclUserService.updatePwd(PasswordUtil.generatePassword(newPwd,currentUser.getUsername()),currentUser.getId());
               map.put("state","success");
           }catch(Exception e){
               e.getStackTrace();
               map.put("state","error");
           }
        }


        return map;
    }

    @RequestMapping("/category")
    @ResponseBody
    public Object category(){

        List<CategoryModel> list = new ArrayList<>();

        CategoryModel categoryModel0 = new CategoryModel();
        categoryModel0.setId("lysp");
        categoryModel0.setText("粮油食品");

        CategoryModel lyspChild0 = new CategoryModel();
        lyspChild0.setId("lyspC0");
        lyspChild0.setText("粮油");
        CategoryModel lyChild0 = new CategoryModel();
        lyChild0.setId("lyC0");
        lyChild0.setText("dami");
        CategoryModel lyChild1 = new CategoryModel();
        lyChild1.setId("lyC1");
        lyChild1.setText("jinlongyu");
        lyspChild0.getItems().add(lyChild0);
        lyspChild0.getItems().add(lyChild1);

        CategoryModel lyspChild1 = new CategoryModel();
        lyspChild1.setId("lyspC1");
        lyspChild1.setText("食品");
        CategoryModel spChild0 = new CategoryModel();
        spChild0.setId("spC0");
        spChild0.setText("xiaobaitu");
        CategoryModel spChild1 = new CategoryModel();
        spChild1.setId("spC1");
        spChild1.setText("dabaitu");
        lyspChild1.getItems().add(spChild0);
        lyspChild1.getItems().add(spChild1);

        categoryModel0.getItems().add(lyspChild0);
        categoryModel0.getItems().add(lyspChild1);


        CategoryModel categoryModel1 = new CategoryModel();
        categoryModel1.setId("jsyl");
        categoryModel1.setText("酒水饮料");

        CategoryModel jsylChild0 = new CategoryModel();
        jsylChild0.setId("jsylC0");
        jsylChild0.setText("酒水");
        CategoryModel jsChild0 = new CategoryModel();
        jsChild0.setId("jsC0");
        jsChild0.setText("白酒");
        CategoryModel jsChild1 = new CategoryModel();
        jsChild1.setId("jsC1");
        jsChild1.setText("葡萄酒");
        jsylChild0.getItems().add(jsChild0);
        jsylChild0.getItems().add(jsChild1);

        CategoryModel jsylChild1 = new CategoryModel();
        jsylChild1.setId("jsylC1");
        jsylChild1.setText("饮料");
        CategoryModel ylChild0 = new CategoryModel();
        ylChild0.setId("ylC0");
        ylChild0.setText("coca cole");
        CategoryModel ylChild1 = new CategoryModel();
        ylChild1.setId("ylC1");
        ylChild1.setText("baishi");
        jsylChild1.getItems().add(ylChild0);
        jsylChild1.getItems().add(ylChild1);

        categoryModel1.getItems().add(jsylChild0);
        categoryModel1.getItems().add(jsylChild1);

        list.add(categoryModel0);
        list.add(categoryModel1);

        return list;
    }


    @RequestMapping("/payAmount")
    @ResponseBody
    public Object payAmount(){
        List<PayAmount> payAmountList = new ArrayList<>();
        PayAmount payAmount0 = new PayAmount();
        payAmount0.setKey("支付类型");
        payAmount0.setValue("在线付款+现金券（3张）");
        payAmountList.add(payAmount0);

        PayAmount payAmount1 = new PayAmount();
        payAmount1.setKey("支付来源");
        payAmount1.setValue("web端");
        payAmountList.add(payAmount1);

        PayAmount payAmount2 = new PayAmount();
        payAmount2.setKey("在线支付平台");
        payAmount2.setValue("支付宝");
        payAmountList.add(payAmount2);

        PayAmount payAmount3 = new PayAmount();
        payAmount3.setKey("在线付款金额");
        payAmount3.setValue("￥33.6");
        payAmountList.add(payAmount3);

        PayAmount payAmount4 = new PayAmount();
        payAmount4.setKey("现金券1金额");
        payAmount4.setValue("￥5.0");
        payAmountList.add(payAmount4);

        PayAmount payAmount5 = new PayAmount();
        payAmount5.setKey("现金券1号");
        payAmount5.setValue("9556325");
        payAmountList.add(payAmount5);

        PayAmount payAmount7 = new PayAmount();
        payAmount7.setKey("现金券2金额");
        payAmount7.setValue("￥5.0");
        payAmountList.add(payAmount7);

        PayAmount payAmount6 = new PayAmount();
        payAmount6.setKey("现金券2号");
        payAmount6.setValue("9556325");
        payAmountList.add(payAmount6);

        PayAmount payAmount8 = new PayAmount();
        payAmount8.setKey("现金券3金额");
        payAmount8.setValue("￥5.0");
        payAmountList.add(payAmount8);

        PayAmount payAmount9 = new PayAmount();
        payAmount9.setKey("现金券3号");
        payAmount9.setValue("9556325");
        payAmountList.add(payAmount9);

        Map map = new HashMap<>();
        map.put("kids",payAmountList);

        return  map;
    }


}
