package com.nanfenggongxiang.Controller.Front;

import com.qiniu.util.Auth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "七牛上传文件的接口")
@RestController
@RequestMapping(value = "/", method = {RequestMethod.GET,RequestMethod.POST})
public class qiniuController {

    @Value("${qiniu.accessKey}")
    private String accessKey;
    @Value("${qiniu.secretKey}")
    private String secretKey;
    @Value("${qiniu.bucket}")
    private String bucket;

    @ApiOperation("获取一个可以自定义文件名的token")
    @RequestMapping("/qiniu/upload-with-pic-name")
    public String getToken(String picName){
        if (picName==null) return "{\"code\":400,\"dsc\":\"图片名称为空\"}";
        // doAuth();
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket, picName);
        return upToken;
    }

    @ApiOperation("获取一个由七牛自动生成文件名的token")
    @RequestMapping("/qiniu/upload-with-no-pic-name")
    public String simpleToken(){
        //  doAuth();
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        return upToken;
    }

}
