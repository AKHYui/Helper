package api.baidu.ip;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc.support.UseJdbc;

import net.sf.json.JSONObject;

public class UseApi {
	public static String UseApi(String ak, String userip){
		String url = "http://api.map.baidu.com/location/"
				+ "ip?ak="+ak+"&ip="+userip+"&coor=bd09ll";
		JSONObject json = IpCheck.doGetStr(url);
		String json_s = json.toString();
		System.out.println(url);
		System.out.println(json_s);
		return json_s;
	}
	public static String getak() throws SQLException{
		int i = 0;
		i = UseJdbc.checkstatus();
		if(i == 1){
			ResultSet rs = null;
			rs = UseJdbc.getak();
			String ak = "";
			while(rs.next()){
				ak = rs.getString("ak");
			}
			return ak;
		}else if(i == 2){
			System.out.println("api处于关闭状态");
			String ak = "";
			return ak;
		}else{
			System.out.println("数据库出错");
			String ak = "";
			return ak;
		}
	}

}
