package API;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;
//演出列表
public class Showlist {
	
	public static String begin_url="xxxxx";
	 //解析JSON
	 public static void jsonToObj_ShowList(String jsonStr,BufferedWriter out) throws Exception {   
	     JSONObject jsonObject = new JSONObject(jsonStr);  
	     String fatherName = jsonObject.getString("result");  
	     String result= AES.Aes.decrypt(fatherName);
	     JSONObject resultObject = new JSONObject(result);
	     //得到总数
	     String totalCount=resultObject.getString("totalCount");
	     //System.out.println(totalCount);
	     // resultObject {"orderList":[{"showId":10,"orderTime":1413288369000,"orderSn":"4287001025","payTime":1413288533000,"totalPrice":150,"price":150,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3634},{"showId":4,"orderTime":1413296319000,"orderSn":"4287001026","payTime":1413296354000,"totalPrice":200,"price":100,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3635},{"showId":10,"orderTime":1413296410000,"orderSn":"4287001027","payTime":1413296462000,"totalPrice":300,"price":150,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3635},{"showId":5,"orderTime":1413296556000,"orderSn":"4287001028","payTime":1413296592000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3635},{"showId":5,"orderTime":1413296687000,"orderSn":"4287001029","payTime":1413296843000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3634},{"showId":4,"orderTime":1413362493000,"orderSn":"4288000006","payTime":1413362886000,"totalPrice":200,"price":100,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3650},{"showId":4,"orderTime":1413384551000,"orderSn":"4288000008","payTime":1413384640000,"totalPrice":100,"price":100,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3657},{"showId":4,"orderTime":1413442589000,"orderSn":"4289000003","payTime":1413442767000,"totalPrice":200,"price":100,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3660},{"showId":4,"orderTime":1413447731000,"orderSn":"4289000005","payTime":1413447880000,"totalPrice":200,"price":100,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3658},{"showId":10,"orderTime":1413449251000,"orderSn":"4289000006","payTime":1413449418000,"totalPrice":300,"price":150,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3658},{"showId":5,"orderTime":1413449331000,"orderSn":"4289000007","payTime":1413449544000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3658},{"showId":4,"orderTime":1413478958000,"orderSn":"4290000001","payTime":1413479183000,"totalPrice":100,"price":100,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3664},{"showId":10,"orderTime":1413510536000,"orderSn":"4290000002","payTime":1413511773000,"totalPrice":150,"price":150,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3665},{"showId":4,"orderTime":1413531554000,"orderSn":"4290000007","payTime":1413531606000,"totalPrice":100,"price":100,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3669},{"showId":5,"orderTime":1413531903000,"orderSn":"4290000008","payTime":1413531947000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3669},{"showId":5,"orderTime":1413545635000,"orderSn":"4290001006","payTime":1413545734000,"totalPrice":800,"price":200,"payMethod":"支付宝","ticketNum":4,"payStatus":"完成","userId":3679},{"showId":5,"orderTime":1413555876000,"orderSn":"4290001008","payTime":1413555941000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3666},{"showId":5,"orderTime":1413556128000,"orderSn":"4290001009","payTime":1413556192000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3684},{"showId":5,"orderTime":1413569415000,"orderSn":"4291000004","payTime":1413569505000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3687},{"showId":4,"orderTime":1413618131000,"orderSn":"4291000008","payTime":1413618472000,"totalPrice":200,"price":100,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3693},{"showId":4,"orderTime":1413624434000,"orderSn":"4291000010","payTime":1413624524000,"totalPrice":100,"price":100,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3695},{"showId":10,"orderTime":1413681519000,"orderSn":"4292000001","payTime":1413681672000,"totalPrice":300,"price":150,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3705},{"showId":5,"orderTime":1413684442000,"orderSn":"4292000002","payTime":1413685274000,"totalPrice":600,"price":200,"payMethod":"支付宝","ticketNum":3,"payStatus":"完成","userId":3706},{"showId":5,"orderTime":1413700531000,"orderSn":"4292000005","payTime":1413700724000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3709},{"showId":5,"orderTime":1413700886000,"orderSn":"4292000006","payTime":1413700958000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3709},{"showId":5,"orderTime":1413707648000,"orderSn":"4292000010","payTime":1413708176000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3711},{"showId":10,"orderTime":1413709241000,"orderSn":"4292000011","payTime":1413709848000,"totalPrice":300,"price":150,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3710},{"showId":5,"orderTime":1413716097000,"orderSn":"4292000017","payTime":1413716222000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3712},{"showId":5,"orderTime":1413726091000,"orderSn":"4292000018","payTime":1413726442000,"totalPrice":800,"price":200,"payMethod":"支付宝","ticketNum":4,"payStatus":"完成","userId":3745},{"showId":5,"orderTime":1413735256000,"orderSn":"4293000003","payTime":1413735426000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3709},{"showId":5,"orderTime":1413777571000,"orderSn":"4293000007","payTime":1413777649000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3788},{"showId":5,"orderTime":1413785010000,"orderSn":"4293000009","payTime":1413785250000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3792},{"showId":5,"orderTime":1413785290000,"orderSn":"4293000010","payTime":1413785410000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3793},{"showId":5,"orderTime":1413785750000,"orderSn":"4293000011","payTime":1413785750000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3795},{"showId":5,"orderTime":1413785750000,"orderSn":"4293000011","payTime":1413786165000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3795},{"showId":5,"orderTime":1413786785000,"orderSn":"4293000012","payTime":1413787048000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3797},{"showId":5,"orderTime":1413787421000,"orderSn":"4293000013","payTime":1413787658000,"totalPrice":200,"price":200,"payMethod":"快钱","ticketNum":1,"payStatus":"完成","userId":3798},{"showId":5,"orderTime":1413787972000,"orderSn":"4293000014","payTime":1413788405000,"totalPrice":400,"price":200,"payMethod":"快钱","ticketNum":2,"payStatus":"完成","userId":3799},{"showId":5,"orderTime":1413789922000,"orderSn":"4293000015","payTime":1413789993000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3804},{"showId":5,"orderTime":1413789990000,"orderSn":"4293000016","payTime":1413790190000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3803},{"showId":5,"orderTime":1413790299000,"orderSn":"4293000018","payTime":1413790616000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3806},{"showId":5,"orderTime":1413790757000,"orderSn":"4293000019","payTime":1413790831000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3809},{"showId":5,"orderTime":1413794146000,"orderSn":"4293000021","payTime":1413794468000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3813},{"showId":5,"orderTime":1413794266000,"orderSn":"4293000022","payTime":1413794480000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3809},{"showId":5,"orderTime":1413795667000,"orderSn":"4293000023","payTime":1413795777000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3814},{"showId":5,"orderTime":1413796940000,"orderSn":"4293000025","payTime":1413797059000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3815},{"showId":5,"orderTime":1413801512000,"orderSn":"4293000027","payTime":1413801589000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3824},{"showId":5,"orderTime":1413801725000,"orderSn":"4293000028","payTime":1413801788000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3822},{"showId":5,"orderTime":1413810021000,"orderSn":"4293000029","payTime":1413810428000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3820},{"showId":5,"orderTime":1413810514000,"orderSn":"4293000030","payTime":1413810666000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3843},{"showId":5,"orderTime":1413811106000,"orderSn":"4293000031","payTime":1413811765000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3846},{"showId":5,"orderTime":1413812058000,"orderSn":"4293000033","payTime":1413812154000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3848},{"showId":5,"orderTime":1413814357000,"orderSn":"4293000036","payTime":1413814505000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3858},{"showId":5,"orderTime":1413814489000,"orderSn":"4293000037","payTime":1413814608000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3859},{"showId":5,"orderTime":1413814716000,"orderSn":"4293000038","payTime":1413814893000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3796},{"showId":5,"orderTime":1413815003000,"orderSn":"4293000039","payTime":1413815048000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3858},{"showId":5,"orderTime":1413815157000,"orderSn":"4293000040","payTime":1413815190000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3796},{"showId":5,"orderTime":1413815257000,"orderSn":"4293000041","payTime":1413815361000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3860},{"showId":5,"orderTime":1413817536000,"orderSn":"4293000042","payTime":1413817566000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3861},{"showId":5,"orderTime":1413818222000,"orderSn":"4293000044","payTime":1413818552000,"totalPrice":400,"price":200,"payMethod":"支付宝","ticketNum":2,"payStatus":"完成","userId":3862},{"showId":5,"orderTime":1413818268000,"orderSn":"4293000045","payTime":1413818408000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3865},{"showId":5,"orderTime":1413818755000,"orderSn":"4293000047","payTime":1413818901000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3867},{"showId":5,"orderTime":1413819049000,"orderSn":"4293000048","payTime":1413821529000,"totalPrice":800,"price":200,"payMethod":"支付宝","ticketNum":4,"payStatus":"完成","userId":3869},{"showId":5,"orderTime":1413819794000,"orderSn":"4293000051","payTime":1413819894000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3868},{"showId":5,"orderTime":1413820344000,"orderSn":"4293000055","payTime":1413820430000,"totalPrice":200,"price":200,"payMethod":"支付宝","ticketNum":1,"payStatus":"完成","userId":3870}],"totalCount":105899}
	     JSONArray jsonArray = resultObject.getJSONArray("showList");
	    // System.out.println(totalCount);
	     for(int i=0;i<jsonArray.length();i++){
	    	 JSONObject showList = jsonArray.getJSONObject(i);
	    	 String showPoster=showList.optString("showPoster");
	    	 String  showName=showList.optString("showName");
	    	 String showStyleId=showList.optString("showStyleId");
	    	 String showStyleName=showList.optString("showStyleName");
	    	 String  showId=showList.optString("showId");
	    	 String performerId=showList.optString("performerId");
	    	 String showStartTime=API.stampToDate(showList.optString("showStartTime"));
	    	 String  siteName=showList.optString("siteName");
	    	 String cityId=showList.optString("cityId");
	    	 String cityName=showList.optString("cityName");
	    	 JSONArray ticketList=showList.getJSONArray("ticketList");
//	    	 System.out.println(ticketList);
//	    	 Map<Integer,Object> mapTicket=new HashMap<Integer,Object>();
//	    	 for(int j=0;j<ticketList.length();j++){
//	    		 mapTicket.put(j, ticketList.get(j));
//	    	 }
//	    	 
//	    	 for(int j=0;j<ticketList.length();j++){
//	    		// System.out.print(ticketList.get(j));
//	    	 }
	    	 
	    	 JSONArray tourList=showList.getJSONArray("tourList"); 
	    	 //System.out.println(tourList);
//	    	 Map<Integer,Object> maptour=new HashMap<Integer,Object>();
//	    	 for(int j=0;j<tourList.length();j++){
//	    		 maptour.put(j, tourList.get(j));
//	    	 }
	    	 String showEndTime=API.stampToDate(showList.optString("showEndTime"));
	    	 String tourId=showList.optString("tourId");
	    	 String tourName=showList.optString("tourName");
	    	 
 	    	 out.write(showPoster+"|"+showName+"|"+showStyleId+"|"+showStyleName+"|"+showId+"|"+performerId+"|"+showStartTime+"|"+siteName+"|"+cityId+"|"+cityName+"|"
 	    			 +ticketList.toString()+"|"+tourList.toString()+"|"+showEndTime+"|"+tourId+"|"+tourName+'\n');
 	    	 
	     } 
	 }
	
	 public static void writetoFile(String Filename,int PageNum,Showlist showlist,String time,String sign) throws Exception{
    	 
    	 File file_showlist=new File(Filename);
		 BufferedWriter out_showlist=new BufferedWriter(new FileWriter(file_showlist,true));
		 for(int i=1;i<=PageNum;i++){
			 //long t1=System.currentTimeMillis();
			 //String tp_sign=getMD5(tp_token+Long.toString(t1)+"taihemusic");
		     String url="xxxxx";
		     System.out.println(url);
		     String jsonObj=API.getJsonString(url);
		     showlist.jsonToObj_ShowList(jsonObj, out_showlist);
		 }  
		 out_showlist.close();
    	 
     }
	

}
