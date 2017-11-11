package com.weibu.loveself.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaQrcodeService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import com.weibu.loveself.constant.QrCodePathConst;
import com.weibu.loveself.service.IQrCodeService;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Service
public class IQrCodeServiceImpl implements IQrCodeService {

    @Autowired
    private WxMaService wxService;

    @Value("${upload.path}")
    private String uploadPath;

    private FileSystem fileSystem = FileSystems.getDefault();

    @Override
    public Map<String, String> getWxaQrCode(Long scene) {
        Map<String, String> result;
        try {
            result = new HashMap<>();

            String value = String.format("%d", scene);
            File file = wxService.getQrcodeService().createWxCodeLimit(value, QrCodePathConst.HOME_PATH, 600, false, new WxMaQrcodeService.LineColor("0", "0", "0"));
            if(file != null) {
                String path = uploadPath + scene + ".jpg";
                Path filePath = fileSystem.getPath(path);
                File dir = filePath.getParent().toFile();
                if(!dir.exists()) dir.mkdirs();
                if(file.renameTo(new File(path))) {
                    result.put("scene", value);
                    result.put("filePath", path);
                    result.put("fileName", scene + ".jpg");
                }
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }
}
