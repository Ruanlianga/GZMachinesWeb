package com.bonus.sys;

import java.net.URLEncoder;

public class SmsClientSend {

	private static String url = "http://sms.kingtto.com:9999/sms.aspx";
	private static String userid = "6030";
	private static String account = "shizongjin";
	private static String password = "xiao394001";
	private static String action = "send";
	private static String sendType = "get";
	private static String codingType = "UTF-8";
	private static String backEncodType = "UTF-8";
//	private String mobile = "15656751631";
//	private String content = "【车辆管理】用车单位：对对对，用车时间：2018-02-02，出发地：合肥，目的地：蚌埠，公里数：110";

	/*
	 * @param url ：必填--发送连接地址URL——http://sms.kingtto.com:9999/sms.aspx
	 * 
	 * @param userid ：必填--用户ID，为数字
	 * 
	 * @param account ：必填--用户帐号
	 * 
	 * @param password ：必填--用户密码
	 * 
	 * @param mobile ：必填--发送的手机号码，多个可以用逗号隔比如>130xxxxxxxx,131xxxxxxxx
	 * 
	 * @param content ：必填--实际发送内容，
	 * 
	 * @param action ：选填--访问的事件，默认为send
	 * 
	 * @param sendType ：选填--发送方式，默认为POST
	 * 
	 * @param codingType ：选填--发送内容编码方式，默认为UTF-8
	 * 
	 * @param backEncodType ：选填--返回内容编码方式，默认为UTF-8
	 * 
	 * @return 返回发送之后收到的信息
	 */
	public String sendSms(String mobile, String content) {

		try {
			StringBuffer send = new StringBuffer();
			if (action != null && !action.equals("")) {
				send.append("action=").append(action);
			} else {
				send.append("action=send");
			}
			send.append("&userid=").append(userid);
			send.append("&account=").append(URLEncoder.encode(account, codingType));
			send.append("&password=").append(URLEncoder.encode(password, codingType));
			send.append("&mobile=").append(mobile);
			send.append("&content=").append(URLEncoder.encode(content, codingType));
			if (sendType != null && (sendType.toLowerCase()).equals("get")) {
				return SmsClientAccessTool.getInstance().doAccessHTTPGet(url + "?" + send.toString(), backEncodType);
			} else {
				return SmsClientAccessTool.getInstance().doAccessHTTPPost(url, send.toString(), backEncodType);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "未发送，编码异常";
		}
	}

}
