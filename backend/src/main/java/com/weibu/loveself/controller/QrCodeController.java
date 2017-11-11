package com.weibu.loveself.controller;

import com.weibu.loveself.common.BaseController;
import com.weibu.loveself.common.ResponseMsg;
import com.weibu.loveself.dao.QrcodeDao;
import com.weibu.loveself.entity.Qrcode;
import com.weibu.loveself.service.IQrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/qrcode")
public class QrCodeController extends BaseController {

    @Autowired
    private QrcodeDao qrcodeDao;
    @Autowired
    private IQrCodeService iQrCodeService;

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseMsg index(@RequestParam(value="scene") String scene) {
        Long value = null;
        try {
            value = Long.parseLong(scene);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if(value == null)
            return error("scene_must_be_numbers");

        Qrcode qrcode = qrcodeDao.findByScene(value);
        return success(qrcode);
    }

    /**
     * 创建一个绑定指定场景值的永久小程序码
     * @param params POST参数
     * @return ResponseMsg
     */
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseMsg createQrCode(@RequestBody Map<String, String> params) {
        String scene = params.get("scene");

        Long value = null;
        try {
            value = Long.parseLong(scene);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if(value == null)
            return error("scene_must_be_numbers");

        Map<String, String> res = iQrCodeService.getWxaQrCode(value);
        if(res != null) {
            Qrcode qrcode = new Qrcode();
            qrcode.setScene(value);
            qrcode.setName(res.get("fileName"));
            qrcode.setPath(res.get("filePath"));
            qrcode.setType(0);
            qrcodeDao.create(qrcode);
            return success(qrcode);
        }
        return error("create_qrcode_failed");
    }
}
