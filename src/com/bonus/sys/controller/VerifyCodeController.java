package com.bonus.sys.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bonus.core.VerifyCodeHelper;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;

/** 
 * 生成验证码
 * @version
 */
@Controller
@RequestMapping("/verifyCode")
public class VerifyCodeController extends BaseController<Object>{
	/**
	 * 生成登录验证码
	 * @return
	 */
	@RequestMapping(value="/slogin")
	public void slogin(HttpServletResponse response){
	    //设置页面不缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        //为了手机客户端方便使用数字验证码
        String verifyCode = VerifyCodeHelper.generateTextCode(VerifyCodeHelper.TYPE_NUM_ONLY, 4, null);		
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		session.setAttribute(GlobalConst.SESSION_SECURITY_CODE, verifyCode);	
		try {
	        //设置输出的内容的类型为JPEG图像
	        response.setContentType("image/jpeg");
	        BufferedImage bufferedImage = VerifyCodeHelper.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null,null);
			 //写给浏览器
	        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
		} catch (IOException e) {
			logger.error("generate Verify Code Image exception",e);
	       	}
    }
} 
