package com.bonus.core;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UploadFileHelper {

	private static final Logger log = LoggerFactory.getLogger(UploadFileHelper.class);
	// private static final String TAG = "uploadFile";
	private static final int TIME_OUT = 100 * 1000; // 超时时间
	private static final String CHARSET = "utf-8"; // 设置编码

	/**
	 * 上传文件到服务器
	 *
	 * @param file
	 *            需要上传的文件
	 * @param RequestURL
	 *            文件服务器的rul
	 * @return 返回响应的内容
	 *
	 */
	public static String uploadFile(File file, String RequestURL) throws IOException {
		String result = null;
		String BOUNDARY = "letv"; // 边界标识 随机生成
		String PREFIX = "--", LINE_END = "\r\n";
		String CONTENT_TYPE = "multipart/form-data"; // 内容类型

		URL url = new URL(RequestURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setReadTimeout(TIME_OUT);
		conn.setConnectTimeout(TIME_OUT);
		conn.setDoInput(true); // 允许输入流
		conn.setDoOutput(true); // 允许输出流
		conn.setUseCaches(false); // 不允许使用缓存
		conn.setRequestMethod("POST"); // 请求方式
		conn.setRequestProperty("Charset", CHARSET); // 设置编码
		conn.setRequestProperty("connection", "keep-alive");
		conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);
		if (file != null) {
			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			StringBuffer sb = new StringBuffer();
			sb.append(PREFIX);
			sb.append(BOUNDARY);
			sb.append(LINE_END);
			sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"" + LINE_END);
			sb.append("Content-Type: application/ctet-stream" + LINE_END);
			sb.append(LINE_END);
			dos.write(sb.toString().getBytes());
			@SuppressWarnings("resource")
			InputStream is = new FileInputStream(file);
			byte[] bytes = new byte[1024 * 1024];
			int len = 0;
			while ((len = is.read(bytes)) != -1) {
				dos.write(bytes, 0, len);
			}
			dos.write(LINE_END.getBytes());
			byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
			dos.write(end_data);
			dos.flush();
			System.setProperty("http.keepAlive", "false");
			int res = conn.getResponseCode();
			log.info("response code:" + res);
			// if(res==200)
			// {
			log.info("request success");
			InputStream input = conn.getInputStream();
			StringBuffer sb1 = new StringBuffer();
			int ss;
			while ((ss = input.read()) != -1) {
				sb1.append((char) ss);
			}
			result = sb1.toString();
			result = new String(result.getBytes("iso8859-1"), "utf-8");
			log.info("result : " + result);
		}

		return result;
	}

}
