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
		//��json_s��ȡstatusֵ ���Ϊ0˵������
		String r = jsonObject.getString("status");
		System.out.println("��ǰIP״̬"+r);
		if(r.equals("0") == true){
			//ȡcontentֵ
			String content = jsonObject.getString("content");
			System.out.println("��ǰcontent"+content);
			com.alibaba.fastjson.JSONObject jsonObject_c = 
					com.alibaba.fastjson.JSONObject.parseObject(content);
			//ȡcontent�е�address_detailֵ
			String address_detail = jsonObject_c.getString("address_detail");
			System.out.println("��ǰaddress_detail"+address_detail);
			com.alibaba.fastjson.JSONObject jsonObject_a = 
					com.alibaba.fastjson.JSONObject.parseObject(address_detail);
			//ȡaddress_detail�е�ʡ�ݺͳ���
			String province = jsonObject_a.getString("province");
			String city = jsonObject_a.getString("city");
			String addr = province+city;
			return addr;
		}else if(r.equals("1") == true){
			//status��0˵��API����
			System.out.println("API�ڲ�����");
			String addr = "API�����ѯʧ��";
			return addr;
		}else{
			System.out.println("��������");
			String addr = "API�����ѯʧ��";
			return addr;
		}
	
	}

}
