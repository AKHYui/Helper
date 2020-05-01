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
		//��json_s��ȡstatusֵ ���Ϊ0˵������
		String r = jsonObject.getString("status");
		System.out.println("��ǰIP״̬"+r);
		if(r.equals("0") == true){
			//ȡresultֵ
			String result = jsonObject.getString("result");
			System.out.println("��ǰresult"+result);
			com.alibaba.fastjson.JSONObject jsonObject_re = 
					com.alibaba.fastjson.JSONObject.parseObject(result);
			//ȡresult�е�formatted_addressֵ
			String formatted_address =jsonObject_re.getString("formatted_address");
			System.out.println("��ǰformatted_address"+formatted_address);
			String addr = formatted_address;
			return addr;
		}else if(r.equals("1") == true){
			//status��0˵��API����
			System.out.println("API�ڲ�����");
			String addr = "����Ա�ر���API����API����";
			return addr;
		}else{
			System.out.println("��������");
			String addr = "����Ա�ر���API����API����";
			return addr;
		}

		
	}
}
