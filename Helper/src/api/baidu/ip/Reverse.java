package api.baidu.ip;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import net.sf.json.JSONObject;
public class Reverse {
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
	public static String Getaddr(String json_r){
		com.alibaba.fastjson.JSONObject jsonObject = 
				com.alibaba.fastjson.JSONObject.parseObject(json_r);
		//从json_s中取status值 如果为0说明正常
		String r = jsonObject.getString("status");
		System.out.println("当前IP状态"+r);
		if(r.equals("0") == true){
			//取result值
			String result = jsonObject.getString("result");
			System.out.println("当前result"+result);
			com.alibaba.fastjson.JSONObject jsonObject_re = 
					com.alibaba.fastjson.JSONObject.parseObject(result);
			//取result中的formatted_address值
			String formatted_address =jsonObject_re.getString("formatted_address");
			System.out.println("当前formatted_address"+formatted_address);
			String addr = formatted_address;
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
