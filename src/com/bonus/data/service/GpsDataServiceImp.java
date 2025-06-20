package com.bonus.data.service;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.core.DateTimeHelper;
import com.bonus.core.PushtoSingle;
import com.bonus.data.SignUtils;
import com.bonus.data.beans.GpsDataBean;
import com.bonus.data.dao.GpsDataDao;
import com.bonus.sys.BaseServiceImp;

@Service("gpsData")
public class GpsDataServiceImp extends BaseServiceImp<GpsDataBean> implements GpsDataService {

	@Autowired private GpsDataDao dao;
	
	PushtoSingle ps = new PushtoSingle();
	
	// GPS设备平台
		private static final String openapi_url = "http://open.aichezaixian.com/route/rest";
		// 申请来的appKey和appSecret
		private static final String app_key = "8FB345B8693CCD00C79139F362F8D346";
		private static final String app_secret = "802b06fd373447ad987978d49c693461";

		@Override
		public String getAccessToken(GpsDataBean bean, String token) {
			Map<String, String> headerMap = new HashMap<String, String>();
			headerMap.put("Content-Type", "application/x-www-form-urlencoded");
			Map<String, String> paramMap = new HashMap<String, String>();
			// 公共参数
			paramMap.put("app_key", app_key);
			paramMap.put("v", "1.0");
			paramMap.put("timestamp", getCurrentDate());
			paramMap.put("sign_method", "md5");
			paramMap.put("format", "json");

			// 获取token值
			paramMap.put("method", "jimi.oauth.token.get");
			// 私有参数
			paramMap.put("user_id", "安徽博诺思");
			paramMap.put("user_pwd_md5", DigestUtils.md5Hex("bonus@Admin123"));
			paramMap.put("expires_in", "7200");

			// 计算签名
			String sign = "";
			try {
				sign = SignUtils.signTopRequest(paramMap, app_secret, "md5");
				paramMap.put("sign", sign);
			} catch (IOException e) {
				System.err.println(e);
			}

			String res = sendPost(headerMap, paramMap);
			String accessToken = "";
			// 1、使用JSONObject
			JSONObject json;
			try {
				json = new JSONObject(res);
				JSONObject result = json.getJSONObject("result");
				String code = json.getString("code");
				if (!"0".equals(code)) {
					accessToken = "获取失败！";
				} else {
					accessToken = result.getString("accessToken");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return accessToken;
		}
	
	//获取定位信息
	public String getPosInfo(GpsDataBean bean,String token,String imeis){
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/x-www-form-urlencoded");
		Map<String, String> paramMap = new HashMap<String, String>();
		// 公共参数
		paramMap.put("app_key", app_key);
		paramMap.put("v", "1.0");
		paramMap.put("timestamp", getCurrentDate());
		paramMap.put("sign_method", "md5");
		paramMap.put("format", "json");
		
		//已有token值，获取定位信息
		paramMap.put("method", "jimi.device.location.get");
		// 私有参数
		paramMap.put("access_token", token);
		paramMap.put("imeis", imeis);
		paramMap.put("map_type", "BAIDU");
		
		// 计算签名
		String sign = "";
		try {
			sign = SignUtils.signTopRequest(paramMap, app_secret, "md5");
			paramMap.put("sign", sign);
		} catch (IOException e) {
			System.err.println(e);
		}
		String res = sendPost(headerMap, paramMap);
		JSONObject jsons;
		try {
			jsons = new JSONObject(res);
			String code = jsons.getString("code");
			logger.info("获取gps的code="+code);
			if(!"0".equals(code)){
				res = "获取失败！";
			}else{
				JSONArray results = jsons.getJSONArray("result");
				logger.info("获取gps的results="+results);
				for(int i=0;i<results.length();i++){
					JSONObject obj=results.getJSONObject(i);
					String gpsCode = obj.getString("imei");
					String gpsStatus = obj.getString("status");
					String posType = obj.getString("posType");
					String lat = obj.getString("lat");
					String lon = obj.getString("lng");
					String hbTime = obj.getString("hbTime");
					String accStatus = obj.getString("accStatus");
					String powerValue = obj.getString("powerValue");
					String speed = obj.getString("speed");
					String collectTime = obj.getString("gpsTime");
					String activationFlag = obj.getString("activationFlag");
					String expireFlag = obj.getString("expireFlag");
					String electQuantity = obj.getString("electQuantity");
				bean.setGpsCode(gpsCode);
				bean.setGpsStatus(gpsStatus);
				bean.setPosType(posType);
				bean.setLat(lat);
				bean.setLon(lon);
				bean.setHbTime(hbTime);
				bean.setAccStatus(accStatus);
				bean.setPowerValue(powerValue);
				bean.setSpeed(speed);
				bean.setCollectTime(collectTime);
				bean.setActivationFlag(activationFlag);
				bean.setExpireFlag(expireFlag);
				bean.setElectQuantity(electQuantity);
				bean.setUpTime(DateTimeHelper.getNowTime());
				if(gpsCode != "" || !"".equals(gpsCode)){
					String deviceCode = getDeviceCode(gpsCode);
					if(deviceCode != null || !"".equals(deviceCode)){
						bean.setDeviceCode(deviceCode);
					}
				}
				
				//更新实时记录表
				dao.insert(bean);
				//查询实时记录表数据并存入历史数据表
				addHisData();
				}
			}
		} catch (JSONException e) {
			logger.info("获取gps的异常="+e.toString());
			e.printStackTrace();
		}
		return res;
	}
	
	private String sendPost(Map<String, String> headerMap, Map<String, String> paramMap) {
		try {
			HttpPost post = new HttpPost(openapi_url);
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			for (String key : paramMap.keySet()) {
				list.add(new BasicNameValuePair(key, paramMap.get(key)));
			}
			post.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
			if (null != headerMap) {
				post.setHeaders(assemblyHeader(headerMap));
			}
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpResponse response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			String res = EntityUtils.toString(entity, "utf-8");
			System.err.println("res="+res);
			return res;
		} catch (IOException e) {
			System.err.println(e);
		}
		return "请求失败";
	}
	
	/**
	 * 组装头部信息
	 * 
	 * @param headers
	 * @return
	 */
	private static Header[] assemblyHeader(Map<String, String> headers) {
		Header[] allHeader = new BasicHeader[headers.size()];
		int i = 0;
		for (String str : headers.keySet()) {
			allHeader[i] = new BasicHeader(str, headers.get(str));
			i++;
		}
		return allHeader;
	}

	public static String getCurrentDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = formatter.format(new Date());
		return result;
	}
	
	public String getDeviceCode(String gpsCode){
		String deviceCode = dao.getDeviceCode(gpsCode);
		return deviceCode;
	}
	
	public int addHisData(){
		//1、查询实时表数据
		List<GpsDataBean> list = dao.findRealData();
		GpsDataBean beans = new GpsDataBean();
			if(list.size() >0){
				for(int j=0;j<list.size();j++){
					beans.setGpsCode(list.get(j).getGpsCode());
					beans.setDeviceCode(list.get(j).getDeviceCode());
					beans.setCollectTime(list.get(j).getCollectTime());
					beans.setLon(list.get(j).getLon());
					beans.setLat(list.get(j).getLat());
					beans.setGpsStatus(list.get(j).getGpsStatus());
					beans.setPosType(list.get(j).getPosType());
					beans.setHbTime(list.get(j).getHbTime());
					beans.setActivationFlag(list.get(j).getActivationFlag());  
					beans.setExpireFlag(list.get(j).getExpireFlag());
					beans.setElectQuantity(list.get(j).getElectQuantity());
					beans.setSpeed(list.get(j).getSpeed());
					beans.setAccStatus(list.get(j).getAccStatus());
					beans.setPowerValue(list.get(j).getPowerValue());
					beans.setUpTime(list.get(j).getUpTime());
					dao.addHisData(beans);
				}
				return 1;
			}else{
				return -1;
			}
		}
	
	/**
	 * 判断当前位置是否在多边形区域内
	 * @param orderLocation 当前点
	 * @param partitionLocation 区域顶点
	 * @return
	 */
	public static boolean isInPolygon(Map orderLocation, String partitionLocation) {

		double p_x = Double.parseDouble((String) orderLocation.get("X"));
		double p_y = Double.parseDouble((String) orderLocation.get("Y"));
		Point2D.Double point = new Point2D.Double(p_x, p_y);

		List<Point2D.Double> pointList = new ArrayList<Point2D.Double>();
		String[] strList = partitionLocation.split(",");

		for (String str : strList) {
			String[] points = str.split("_");
			double polygonPoint_x = Double.parseDouble(points[1]);
			double polygonPoint_y = Double.parseDouble(points[0]);
			Point2D.Double polygonPoint = new Point2D.Double(polygonPoint_x, polygonPoint_y);
			pointList.add(polygonPoint);
		}
		return IsPtInPoly(point, pointList);
	}

	/**
	 * 返回一个点是否在一个多边形区域内， 如果点位于多边形的顶点或边上，不算做点在多边形内，返回false
	 * 
	 * @param point
	 * @param polygon
	 * @return
	 */
	public static boolean checkWithJdkGeneralPath(Point2D.Double point, List<Point2D.Double> polygon) {
		java.awt.geom.GeneralPath p = new java.awt.geom.GeneralPath();
		Point2D.Double first = polygon.get(0);
		p.moveTo(first.x, first.y);
		polygon.remove(0);
		for (Point2D.Double d : polygon) {
			p.lineTo(d.x, d.y);
		}
		p.lineTo(first.x, first.y);
		p.closePath();
		return p.contains(point);
	}

	/**
	 * 判断点是否在多边形内，如果点位于多边形的顶点或边上，也算做点在多边形内，直接返回true
	 * 
	 * @param point
	 *            检测点
	 * @param pts
	 *            多边形的顶点
	 * @return 点在多边形内返回true,否则返回false
	 */
	public static boolean IsPtInPoly(Point2D.Double point, List<Point2D.Double> pts) {
		int N = pts.size();
		boolean boundOrVertex = true; // 如果点位于多边形的顶点或边上，也算做点在多边形内，直接返回true
		int intersectCount = 0;
		double precision = 2e-10; // 浮点类型计算时候与0比较时候的容差
		Point2D.Double p1, p2;
		Point2D.Double p = point; // 当前点

		p1 = pts.get(0);
		for (int i = 1; i <= N; ++i) {
			if (p.equals(p1)) {
				return boundOrVertex;
			}
			p2 = pts.get(i % N);
			if (p.x < Math.min(p1.x, p2.x) || p.x > Math.max(p1.x, p2.x)) {
				p1 = p2;
				continue;
			}
			if (p.x > Math.min(p1.x, p2.x) && p.x < Math.max(p1.x, p2.x)) {
				if (p.y <= Math.max(p1.y, p2.y)) {
					if (p1.x == p2.x && p.y >= Math.min(p1.y, p2.y)) {
						return boundOrVertex;
					}
					if (p1.y == p2.y) {
						if (p1.y == p.y) {
							return boundOrVertex;
						} else {
							++intersectCount;
						}
					} else {
						double xinters = (p.x - p1.x) * (p2.y - p1.y) / (p2.x - p1.x) + p1.y;
						if (Math.abs(p.y - xinters) < precision) {
							return boundOrVertex;
						}
						if (p.y < xinters) {
							++intersectCount;
						}
					}
				}
			} else {
				if (p.x == p2.x && p.y <= p2.y) {
					Point2D.Double p3 = pts.get((i + 1) % N);
					if (p.x >= Math.min(p1.x, p3.x) && p.x <= Math.max(p1.x, p3.x)) {
						++intersectCount;
					} else {
						intersectCount += 2;
					}
				}
			}
			p1 = p2;
		}
		if (intersectCount % 2 == 0) {// 偶数在多边形外
			return false;
		} else { // 奇数在多边形内
			return true;
		}
	}
	
}
