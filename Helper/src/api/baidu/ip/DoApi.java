package api.baidu.ip;

public class DoApi {
	public String doMapApi(String ak, String ip){
		//String userip = request.getRemoteAddr();//记得改回来
		//String userip = "118.74.119.220";
		String userip = ip;
		String json_s = UseApi.UseApiKey(ak, userip);
		String json_r = IpCheck.GetData(json_s);
		System.out.println(json_r);
		if(json_r.equals("0") == true){
			String addr = "API错误或管理员关闭了API";
			return addr;
		}else{
			String addr = Reverse.Getaddr(json_r);
			return addr;
		}
		
	}
}
