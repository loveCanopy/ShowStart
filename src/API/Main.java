package API;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Set;

//执行类
public class Main {

	 static String tp_source="xxx";
	 static String tp_token="xxxx";
	 //static String tp_token='123456789';
	 static long begin_t1=System.currentTimeMillis();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		 
		 String begin_tp_sign=API.getMD5(tp_token+Long.toString(begin_t1)+"xxx");
		 
		//Order(args, begin_tp_sign);
		 
		//User(args, begin_tp_sign);
		 
		//Artist(args, begin_tp_sign);
		 
		Showlist(args, begin_tp_sign);
		 //粉丝数据
		// Fans(args,begin_tp_sign);
	}

	public static void Showlist(String[] args, String begin_tp_sign) throws Exception {
		//演出列表数据
		 Showlist showlist=new Showlist();
	     String begin_showlist_url=showlist.begin_url+Long.toString(begin_t1)+"&tp_sign="+begin_tp_sign;
	     String begin_showlist_jsonObj=API.getJsonString(begin_showlist_url);
	     int Showlist_pageNum=API.getPageNum(begin_showlist_jsonObj)/200+1;
	     System.out.println(Showlist_pageNum);
	     showlist.writetoFile("showstart_"+"showlist"+"_"+args[0], Showlist_pageNum, showlist, Long.toString(begin_t1), begin_tp_sign);
	}

	public static void Artist(String[] args, String begin_tp_sign) throws Exception {
		//艺人数据
		 Artist artist=new Artist();
		 String begin_artist_url=artist.begin_url+Long.toString(begin_t1)+"&tp_sign="+begin_tp_sign;
		 String begin_artist_jsonObj=API.getJsonString(begin_artist_url);
		 int Artist_pageNum=API.getPageNum(begin_artist_jsonObj)/200+1;
		 System.out.println(Artist_pageNum);
		 artist.writetoFile("showstart_"+"artist"+"_"+args[0], Artist_pageNum, artist, Long.toString(begin_t1), begin_tp_sign);
	}

	public static void User(String[] args, String begin_tp_sign) throws Exception {
		//用户数据
		 User user=new User();
		 String begin_user_url=user.begin_url+Long.toString(begin_t1)+"&tp_sign="+begin_tp_sign;
		 System.out.println(begin_user_url);
		 String begin_user_jsonObj=API.getJsonString(begin_user_url);
		 int User_pageNum=API.getPageNum(begin_user_jsonObj)/200+1;
		 System.out.println(User_pageNum);
		 user.writetoFile("showstart_"+"user"+"_"+args[0], User_pageNum, user, Long.toString(begin_t1), begin_tp_sign);
	}
	
	public static void Order(String[] args, String begin_tp_sign) throws Exception {
		//订单数据
		 Order order=new Order();
		 String begin_order_url=order.begin_url+Long.toString(begin_t1)+"&tp_sign="+begin_tp_sign;
		 String begin_order_jsonObj=API.getJsonString(begin_order_url);
		 int Order_pageNum=API.getPageNum(begin_order_jsonObj)/200+1;
		 System.out.println(Order_pageNum);
		 order.writetoFile("showstart_"+"order"+"_"+args[0],Order_pageNum, order, Long.toString(begin_t1), begin_tp_sign);
	}

	public static void Fans(String[]args,String begin_tp_sign) throws Exception{
		Fans fans=new Fans();
		String begin_fans_url=fans.begin_url+Long.toString(begin_t1)+"&tp_sign="+begin_tp_sign;
		String begin_fans_jsonObj=API.getJsonString(begin_fans_url);
		int Fans_pageNum=API.getPageNum(begin_fans_jsonObj)/200+1;
		Set<String> artistList=fans.getArtistList(Fans_pageNum, Long.toString(begin_t1), fans, begin_tp_sign);
		//System.out.println(artistList.size());
		fans.jsonToObj_fans( "showstart_"+"fans"+"_"+args[0],artistList,begin_tp_sign,Long.toString(begin_t1));
	}
	
	
	
	
	
}
