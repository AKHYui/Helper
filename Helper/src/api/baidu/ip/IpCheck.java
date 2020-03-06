package api.baidu.ip;

import java.io.IOException;

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
			//取content中的address_detail值
			String address_detail = jsonObject_c.getString("address_detail");
			System.out.println("当前address_detail"+address_detail);
			com.alibaba.fastjson.JSONObject jsonObject_a = 
					com.alibaba.fastjson.JSONObject.parseObject(address_detail);
			//取address_detail中的省份和城市
			String province = jsonObject_a.getString("province");
			String city = jsonObject_a.getString("city");
			String addr = province+city;
			return addr;
		}else if(r.equals("1") == true){
			//status非0说明API故障
			System.out.println("API内部错误");
			String addr = "管理员关闭了API或者API错误";
			return addr;
		}else{
			System.out.println("其他错误");
			String addr = "管理员关闭了API或者API错误";
			return addr;
		}
	
	}

}
