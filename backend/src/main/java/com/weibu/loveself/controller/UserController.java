package com.weibu.loveself.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.weibu.loveself.common.BaseController;
import com.weibu.loveself.common.ResponseMsg;
import com.weibu.loveself.dao.UserDao;
import com.weibu.loveself.entity.User;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private WxMaService wxMaService;

    /**
     * 登录换取openid接口
     * @param params POST参数
     * @return ResponseMsg
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseMsg login(@RequestBody Map<String, String> params) {
        String code = params.get("code");
        if(code == null)
            return error("illigal_param_code");

        WxMaJscode2SessionResult session = null;
        try {
            session = wxMaService.getUserService().getSessionInfo(code);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        if(session != null) {
            Map<String, Object> result = new HashMap<>();
            result.put("openid", session.getOpenid());
            return success(result);
        }
        return error("login_fail");
    }

    /**
     * 查询用户信息
     * @param openid 用户openid
     * @return ResponseMsg
     */
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseMsg index(@RequestParam(value="openid") String openid) {
        User user = userDao.findByOpenid(openid);
        if(user != null) {
            return success(user);
        }
        return error("user_not_found");
    }
}