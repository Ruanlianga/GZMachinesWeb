package com.bonus.index.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bonus.bm.beans.CompanyBean;
import com.bonus.index.beans.PositionBean;
import com.bonus.index.dao.PositionDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.Page;
import com.bonus.sys.beans.ZNode;

@Service("Position")
public class PositionServiceImp extends BaseServiceImp<PositionBean> implements PositionService{

	@Autowired
	private PositionDao dao;
	
	@Override
	public String gpsCode(PositionBean bean) {
		// TODO Auto-generated method stub
		return dao.gpsCode(bean);
	}
	
	@Override
	public List<PositionBean> findBindPosition(PositionBean o) {
		List<PositionBean> list = dao.findBindPosition(o);
		for(int i= 0;i<list.size();i++) {
			String address = getAddressInfoByLngAndLat(list.get(i).getLon(),list.get(i).getLat());
			list.get(i).setAddress(address);
		}
		return list;
	}

	@Override
	public String gpsCodeBind(PositionBean bean) {
		// TODO Auto-generated method stub
		return dao.gpsCodeBind(bean);
	}

	@Override
	public List<PositionBean> findAll() {
		return dao.findAll();
	}
	
    private static double getDistance(double lat_a, double lng_a, double lat_b, double lng_b){
        double pk = 180 / 3.14169;
        double a1 = lat_a / pk;
        double a2 = lng_a / pk;
        double b1 = lat_b / pk;
        double b2 = lng_b / pk;
        double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
        double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
        double t3 = Math.sin(a1) * Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);
        double ar = 6371000 * tt;
        return ar;
    }
    public static void main(String[] args) {
    	System.err.println(getDistance(31.8467368107190,121.07746555755528,31.8467338107156,121.07746555745528));
	}

	@Override
	public Page<PositionBean> findDeviceDetail(PositionBean o, Page<PositionBean> page) {
		page.setResults(dao.findDeviceDetail(o, page));
		return page;
	}

	@Override
	public List<PositionBean> findNoPage(PositionBean o) {
		// TODO Auto-generated method stub
		return dao.findNoPage(o);
	}

	@Override
	public Page<PositionBean> findInLibDevice(PositionBean o, Page<PositionBean> page) {
		page.setResults(dao.findInLibDevice(o, page));
		return page;
	}

	@Override
	public List<ZNode> getMainTree(PositionBean o) {
		// TODO Auto-generated method stub
		return dao.getMainTree(o);
	}

	
	/**
     * 根据经纬度调用百度API获取 地理位置信息，根据经纬度
     * @param longitude 经度
     * @param latitude 纬度
     * @return
     */
	 public final static String BAIDU_MAP_AK = "S3b9buXfNthnsH1GOF2gI2HIOufDTWhs";
    public static String getAddressInfoByLngAndLat(String longitude,String latitude){
        JSONObject obj = new JSONObject();
        String addressComponent = null;
        String location=latitude+","+longitude;
        //百度url  coordtype :bd09ll（百度经纬度坐标）、bd09mc（百度米制坐标）、gcj02ll（国测局经纬度坐标，仅限中国）、wgs84ll（ GPS经纬度）
        String url ="http://api.map.baidu.com/reverse_geocoding/v3/?ak="+BAIDU_MAP_AK+"&output=json&coordtype=wgs84ll&location="+location;
        try {
            String json = loadJSON(url);
            obj = JSONObject.parseObject(json);
            System.out.println(obj.toString());
            // status:0 成功
            String success="0";
            String status = String.valueOf(obj.get("status"));
            if(success.equals(status)){
                String result = String.valueOf(obj.get("result"));
                JSONObject resultObj = JSONObject.parseObject(result);
                addressComponent = String.valueOf(resultObj.get("formatted_address"));
                //JSON字符串转换成Java对象
                // AddressComponent addressComponentInfo = JSONObject.parseObject(addressComponent, AddressComponent.class);
            }
        } catch (Exception e) {
            System.out.println("未找到相匹配的经纬度，请检查地址！");
        }
        return addressComponent;
    }

    public static String loadJSON(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {} catch (IOException e) {}
        return json.toString();
    }

	@Override
	public List<PositionBean> findOldGpsData(PositionBean o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PositionBean> findOldGpsDatas(PositionBean o, Page<PositionBean> page) {
		List<PositionBean> list = dao.findOldGpsData(o,page);
		for(int i= 0;i<list.size();i++) {
			//String address = getAddressInfoByLngAndLat(list.get(i).getLon(),list.get(i).getLat());
			String address = "";
			list.get(i).setAddress(address);
		}
		page.setResults(list);
		return page;
	}

	@Override
	public List<CompanyBean> getTypeName() {
		// TODO Auto-generated method stub
		return dao.getTypeName();
	}

	@Override
	public List<CompanyBean> getDeviceModel() {
		// TODO Auto-generated method stub
		return dao.getDeviceModel();
	}

	public List<CompanyBean> getDeviceCode() {
		// TODO Auto-generated method stub
		return dao.getDeviceCode();
	}
	
	public List<CompanyBean> getCode() {
		// TODO Auto-generated method stub
		return dao.getCode();
	}

	@Override
	public List<ZNode> getMainSpecialTree(PositionBean o) {
		// TODO Auto-generated method stub
		return dao.getMainSpecialTree(o);
	}
}

