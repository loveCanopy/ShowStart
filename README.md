# ShowStart
秀动网内部数据接入
## 解析代码：
1.访问提供的接口
2.用规定的解密函数解密
3.解析json字符串
4.写入文件
5.脚本调用，天级或周级执行
## 公用方法
### 1.AES加密解密方法及通过时间戳来获取tp_sign
```
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
static String tp_source="XXXX";
static String tp_token="XXXX";
String begin_tp_sign=API.getMD5(tp_token+Long.toString(begin_t1)+"XXXX");
```
### 2.通过服务器访问接口，得到jsonString
```
public static String getJsonString(String urlPath) throws Exception {  
//配置代理服务器（集群无法访问外网，需配置代理服务器）
Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("XXXX", XXXX));
 URL url = new URL(urlPath);  
 HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
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
 ```
### 3.得到查询的页数
```
public static int getPageNum(String jsonStr ) throws Exception {
	 JSONObject jsonObject = new JSONObject(jsonStr);  
     String fatherName = jsonObject.getString("result");  
     String result= AES.Aes.decrypt(fatherName);
     JSONObject resultObject = new JSONObject(result);
     //得到总数
     String totalCount=resultObject.getString("totalCount");
	 return Integer.parseInt(totalCount);
 }
```
### 4.解析对应的字段，并写入文件
1)解析showlist
```
public static void jsonToObj_ShowList(String jsonStr,BufferedWriter out) throws Exception {   
	     JSONObject jsonObject = new JSONObject(jsonStr);  
	     String fatherName = jsonObject.getString("result");  
	     String result= AES.Aes.decrypt(fatherName);
	     JSONObject resultObject = new JSONObject(result);
	     //得到总数
	     String totalCount=resultObject.getString("totalCount");
	     JSONArray jsonArray = resultObject.getJSONArray("showList");
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

	    	 JSONArray tourList=showList.getJSONArray("tourList"); 

	    	 String showEndTime=API.stampToDate(showList.optString("showEndTime"));
	    	 String tourId=showList.optString("tourId");
	    	 String tourName=showList.optString("tourName");
	    	 
 	    	 out.write(showPoster+"\001"+showName+"\001"+showStyleId+"\001"+showStyleName+"\001"+showId+"\001"+performerId+"\001"+showStartTime+"\001"+siteName+"\001"+cityId+"\001"+cityName+"\001"
 	    			 +ticketList.toString()+"\001"+tourList.toString()+"\001"+showEndTime+"\001"+tourId+"\001"+tourName+'\n');
 	    	 
	     } 
	 }
```  
