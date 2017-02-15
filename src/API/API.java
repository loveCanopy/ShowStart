package API;
import org.apache.log4j.chainsaw.Main;
import org.json.JSONArray;
import org.json.JSONObject;
import com.mysql.jdbc.PreparedStatement;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import javax.crypto.Cipher;
import AES.*;
import Model.Tool;
//API
public class API {
	
//	static String sql = null;  
//    static Tool tool = null;  
//    static  ResultSet ret = null;  
    //MD5加密函数
	static File file=null;
	static BufferedWriter out=null;
	
 public static String getMD5(String str) throws Exception{
	    try {
	        // 生成一个MD5加密计算摘要
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        // 计算md5函数
	        md.update(str.getBytes());
	        // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
	        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
	        return new BigInteger(1, md.digest()).toString(16);
	    } catch (Exception e) {
	        throw new Exception("MD5加密出现错误");
	    }
	}
 
 //获取JSON
 public static String getJsonString(String urlPath) throws Exception {  
	 //配置代理服务器
	 Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.16.87.254", 8080));
     URL url = new URL(urlPath);  
     HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
     //HttpURLConnection connection = (HttpURLConnection) url.openConnection();
     connection.setConnectTimeout(30000000);  
     connection.setReadTimeout(30000000); 
     connection.connect();  
     InputStream inputStream = connection.getInputStream();  
     //对应的字符编码转换  
     Reader reader = new InputStreamReader(inputStream, "UTF-8");  
     BufferedReader bufferedReader = new BufferedReader(reader);  
     String str = null;  
     StringBuffer sb = new StringBuffer();  
     while ((str = bufferedReader.readLine()) != null) {  
         sb.append(str);  
     }  
     reader.close();  
     connection.disconnect();  
     return sb.toString();  
 } 
 
 //得到查询的页数
 public static int getPageNum(String jsonStr ) throws Exception {
	 JSONObject jsonObject = new JSONObject(jsonStr);  
     String fatherName = jsonObject.getString("result");  
     String result= AES.Aes.decrypt(fatherName);
     JSONObject resultObject = new JSONObject(result);
     //得到总数
     String totalCount=resultObject.getString("totalCount");
	 return Integer.parseInt(totalCount);
 }
 
 //解析JSON
 public static void jsonToObj_Order(String jsonStr) throws Exception {   
     JSONObject jsonObject = new JSONObject(jsonStr);  
     String fatherName = jsonObject.getString("result");  
     String result= AES.Aes.decrypt(fatherName);
     JSONObject resultObject = new JSONObject(result);
     //得到总数
     String totalCount=resultObject.getString("totalCount");
     //System.out.println(totalCount);
     // resultObject {"orderList":[{"showId":10,"orderTime":1413288369000,"orderSn":"4287001025","payTime":1413288533000,"totalPrice":150,"price":150,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3634},{"showId":4,"orderTime":1413296319000,"orderSn":"4287001026","payTime":1413296354000,"totalPrice":200,"price":100,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3635},{"showId":10,"orderTime":1413296410000,"orderSn":"4287001027","payTime":1413296462000,"totalPrice":300,"price":150,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3635},{"showId":5,"orderTime":1413296556000,"orderSn":"4287001028","payTime":1413296592000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3635},{"showId":5,"orderTime":1413296687000,"orderSn":"4287001029","payTime":1413296843000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3634},{"showId":4,"orderTime":1413362493000,"orderSn":"4288000006","payTime":1413362886000,"totalPrice":200,"price":100,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3650},{"showId":4,"orderTime":1413384551000,"orderSn":"4288000008","payTime":1413384640000,"totalPrice":100,"price":100,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3657},{"showId":4,"orderTime":1413442589000,"orderSn":"4289000003","payTime":1413442767000,"totalPrice":200,"price":100,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3660},{"showId":4,"orderTime":1413447731000,"orderSn":"4289000005","payTime":1413447880000,"totalPrice":200,"price":100,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3658},{"showId":10,"orderTime":1413449251000,"orderSn":"4289000006","payTime":1413449418000,"totalPrice":300,"price":150,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3658},{"showId":5,"orderTime":1413449331000,"orderSn":"4289000007","payTime":1413449544000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3658},{"showId":4,"orderTime":1413478958000,"orderSn":"4290000001","payTime":1413479183000,"totalPrice":100,"price":100,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3664},{"showId":10,"orderTime":1413510536000,"orderSn":"4290000002","payTime":1413511773000,"totalPrice":150,"price":150,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3665},{"showId":4,"orderTime":1413531554000,"orderSn":"4290000007","payTime":1413531606000,"totalPrice":100,"price":100,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3669},{"showId":5,"orderTime":1413531903000,"orderSn":"4290000008","payTime":1413531947000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3669},{"showId":5,"orderTime":1413545635000,"orderSn":"4290001006","payTime":1413545734000,"totalPrice":800,"price":200,"payMethod":"支付宝","ticketNum":4,"payStatus":"完成","userId":3679},{"showId":5,"orderTime":1413555876000,"orderSn":"4290001008","payTime":1413555941000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3666},{"showId":5,"orderTime":1413556128000,"orderSn":"4290001009","payTime":1413556192000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3684},{"showId":5,"orderTime":1413569415000,"orderSn":"4291000004","payTime":1413569505000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3687},{"showId":4,"orderTime":1413618131000,"orderSn":"4291000008","payTime":1413618472000,"totalPrice":200,"price":100,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3693},{"showId":4,"orderTime":1413624434000,"orderSn":"4291000010","payTime":1413624524000,"totalPrice":100,"price":100,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3695},{"showId":10,"orderTime":1413681519000,"orderSn":"4292000001","payTime":1413681672000,"totalPrice":300,"price":150,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3705},{"showId":5,"orderTime":1413684442000,"orderSn":"4292000002","payTime":1413685274000,"totalPrice":600,"price":200,"payMethod":"支付宝","ticketNum":3,"payStatus":"完成","userId":3706},{"showId":5,"orderTime":1413700531000,"orderSn":"4292000005","payTime":1413700724000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3709},{"showId":5,"orderTime":1413700886000,"orderSn":"4292000006","payTime":1413700958000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3709},{"showId":5,"orderTime":1413707648000,"orderSn":"4292000010","payTime":1413708176000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3711},{"showId":10,"orderTime":1413709241000,"orderSn":"4292000011","payTime":1413709848000,"totalPrice":300,"price":150,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3710},{"showId":5,"orderTime":1413716097000,"orderSn":"4292000017","payTime":1413716222000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3712},{"showId":5,"orderTime":1413726091000,"orderSn":"4292000018","payTime":1413726442000,"totalPrice":800,"price":200,"payMethod":"支付宝","ticketNum":4,"payStatus":"完成","userId":3745},{"showId":5,"orderTime":1413735256000,"orderSn":"4293000003","payTime":1413735426000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3709},{"showId":5,"orderTime":1413777571000,"orderSn":"4293000007","payTime":1413777649000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3788},{"showId":5,"orderTime":1413785010000,"orderSn":"4293000009","payTime":1413785250000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3792},{"showId":5,"orderTime":1413785290000,"orderSn":"4293000010","payTime":1413785410000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3793},{"showId":5,"orderTime":1413785750000,"orderSn":"4293000011","payTime":1413785750000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3795},{"showId":5,"orderTime":1413785750000,"orderSn":"4293000011","payTime":1413786165000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3795},{"showId":5,"orderTime":1413786785000,"orderSn":"4293000012","payTime":1413787048000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3797},{"showId":5,"orderTime":1413787421000,"orderSn":"4293000013","payTime":1413787658000,"totalPrice":200,"price":200,"payMethod":"快钱","ticketNum":1,"payStatus":"完成","userId":3798},{"showId":5,"orderTime":1413787972000,"orderSn":"4293000014","payTime":1413788405000,"totalPrice":400,"price":200,"payMethod":"快钱","ticketNum":2,"payStatus":"完成","userId":3799},{"showId":5,"orderTime":1413789922000,"orderSn":"4293000015","payTime":1413789993000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3804},{"showId":5,"orderTime":1413789990000,"orderSn":"4293000016","payTime":1413790190000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3803},{"showId":5,"orderTime":1413790299000,"orderSn":"4293000018","payTime":1413790616000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3806},{"showId":5,"orderTime":1413790757000,"orderSn":"4293000019","payTime":1413790831000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3809},{"showId":5,"orderTime":1413794146000,"orderSn":"4293000021","payTime":1413794468000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3813},{"showId":5,"orderTime":1413794266000,"orderSn":"4293000022","payTime":1413794480000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3809},{"showId":5,"orderTime":1413795667000,"orderSn":"4293000023","payTime":1413795777000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3814},{"showId":5,"orderTime":1413796940000,"orderSn":"4293000025","payTime":1413797059000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3815},{"showId":5,"orderTime":1413801512000,"orderSn":"4293000027","payTime":1413801589000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3824},{"showId":5,"orderTime":1413801725000,"orderSn":"4293000028","payTime":1413801788000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3822},{"showId":5,"orderTime":1413810021000,"orderSn":"4293000029","payTime":1413810428000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3820},{"showId":5,"orderTime":1413810514000,"orderSn":"4293000030","payTime":1413810666000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3843},{"showId":5,"orderTime":1413811106000,"orderSn":"4293000031","payTime":1413811765000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3846},{"showId":5,"orderTime":1413812058000,"orderSn":"4293000033","payTime":1413812154000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3848},{"showId":5,"orderTime":1413814357000,"orderSn":"4293000036","payTime":1413814505000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3858},{"showId":5,"orderTime":1413814489000,"orderSn":"4293000037","payTime":1413814608000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3859},{"showId":5,"orderTime":1413814716000,"orderSn":"4293000038","payTime":1413814893000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3796},{"showId":5,"orderTime":1413815003000,"orderSn":"4293000039","payTime":1413815048000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3858},{"showId":5,"orderTime":1413815157000,"orderSn":"4293000040","payTime":1413815190000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3796},{"showId":5,"orderTime":1413815257000,"orderSn":"4293000041","payTime":1413815361000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3860},{"showId":5,"orderTime":1413817536000,"orderSn":"4293000042","payTime":1413817566000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3861},{"showId":5,"orderTime":1413818222000,"orderSn":"4293000044","payTime":1413818552000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3862},{"showId":5,"orderTime":1413818268000,"orderSn":"4293000045","payTime":1413818408000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3865},{"showId":5,"orderTime":1413818755000,"orderSn":"4293000047","payTime":1413818901000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3867},{"showId":5,"orderTime":1413819049000,"orderSn":"4293000048","payTime":1413821529000,"totalPrice":800,"price":200,"payMethod":"支付宝","ticketNum":4,"payStatus":"完成","userId":3869},{"showId":5,"orderTime":1413819794000,"orderSn":"4293000051","payTime":1413819894000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3868},{"showId":5,"orderTime":1413820344000,"orderSn":"4293000055","payTime":1413820430000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3870}],"totalCount":105899}
     JSONArray jsonArray = resultObject.getJSONArray("orderList");
    // System.out.println(totalCount);
     for(int i=0;i<jsonArray.length();i++){
    	 JSONObject order = jsonArray.getJSONObject(i);
    	 String showId=order.optString("showId");
    	 String orderTime=stampToDate(order.getString("orderTime"));
    	 String  orderSource=order.optString("orderSource");
    	 String orderSn=order.getString("orderSn");
    	 String payTime=stampToDate(order.getString("payTime"));
    	 Float totalPrice=Float.parseFloat(order.getString("totalPrice"));
    	 Float price=Float.parseFloat(order.getString("price"));
    	 String payMethod=order.optString("payMethod");
    	 int ticketNum=Integer.parseInt(order.getString("ticketNum"));
    	 String payStatus=order.optString("payStatus");
    	 String userId=order.optString("userId");
	     out.write(showId+","+orderTime+","+orderSource+","+orderSn+","+payTime+","+totalPrice+","+price+","+payMethod+","+ticketNum+","+payStatus+","+userId+'\n');
	     
     }
    
 }
 
 /* 
  * 将时间戳转换为时间
  */
 public static String stampToDate(String s){
     String res;
     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     long lt = new Long(s);
     Date date = new Date(lt);
     res = simpleDateFormat.format(date);
     return res;
 }
 


	     
	     
	     
	 
	 
}
 

