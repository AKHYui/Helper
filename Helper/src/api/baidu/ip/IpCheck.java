/*
 * doGetStr方法负责向百度地图API提交Get请求 得到JSON结果
 * GetData方法负责从JSON中取得重要数据
 */
package api.baidu.ip;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import net.sf.json.JSONObject;

public class IpCheck {

	public static JSONObject doGetStr(String url) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		JSONObject jsonObject = null;
		
		try {
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if(entity != null) {
				String result = EntityUtils.toString(entity,"UTF-8");
				jsonObject = JSONObject.fromObject(result);
			}
		} catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
		
	}

	public static String GetData(String json_s){
		com.alibaba.fastjson.JSONObject jsonObject = 
				com.alibaba.fastjson.JSONObject.parseObject(json_s);
		//从json_s中取status值 如果为0说明正常
		String r = jsonObject.getString("status");
		System.out.println("当前IP状态"+r);
		if(r.equals("0") == true){
			//取content值
			String content = jsonObject.getString("content");
			System.out.println("当前content"+content);
			com.alibaba.fastjson.JSONObject jsonObject_c = 
					com.alibaba.fastjson.JSONObject.parseObject(content);
			//取content中point值
			String point = jsonObject_c.getString("point");
			System.out.println("当前point"+point);
			com.alibaba.fastjson.JSONObject jsonObject_p = 
					com.alibaba.fastjson.JSONObject.parseObject(point);
			//取point中xy值
			String x = jsonObject_p.getString("x");
			String y = jsonObject_p.getString("y");
			System.out.println("x"+x+"y"+y);
			//获取ak值
			String ak = "";
			try {
				ak = UseApi.getak();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//解析出详细地址json
			String json_r = UseApi.doReverse(ak, x, y);
			return json_r;
		}else if(r.equals("1") == true){
			//status非0说明API故障
			System.out.println("API内部错误");
			String json_r = "0";
			return json_r;
		}else{
			System.out.println("其他错误");
			String json_r = "0";
			return json_r;
		}
	
	}

}
