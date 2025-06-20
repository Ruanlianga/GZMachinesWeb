package com.bonus.ma.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.core.qrcode.QRCodeUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class QRCodeControllerTest {

	@Test
	public void testProduceQRCode2() {
		List<String> list = new ArrayList<String>();
		list.add("201808-00780");

		for (String code : list) {
			try {
				produceQRCode2(code);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void produceQRCode2(String code) throws Exception {
		String url = "http://61.190.54.213:9988/imw/backstage/machine/qrCodePage?qrcode=" + code;
		// // 二维码的图片格式
		String format = "jpg";
		// // 生成二维码
		String realPath = "test/images/qr";
		File files = new File(realPath);
		if (!files.exists()) {
			files.mkdirs();
		}
		QRCodeUtil.generateQRImage(url, realPath, code + ".jpg", format);
	}
}
