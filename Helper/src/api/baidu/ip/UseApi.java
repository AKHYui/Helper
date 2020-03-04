package api.baidu.ip;

import net.sf.json.JSONObject;

public class UseApi {
	public static String UseApi(String userip){
		String ak = "LowbbhGX04PYPpSObHVZoCdTjzaHScjm";
		String url = "http://api.map.baidu.com/location/"
				+ "ip?ak="+ak+"&ip="+userip+"&coor=bd09ll";
		JSONObject json = IpCheck.doGetStr(url);
		String json_s = json.toString();
		System.out.println(json_s);
		return json_s;
	}

}
