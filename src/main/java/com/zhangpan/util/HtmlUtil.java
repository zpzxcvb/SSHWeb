package com.zhangpan.util;

import java.io.IOException;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlUtil {

	public static void main(String[] args) throws IOException {
		Connection conn=Jsoup.connect("http://www.icpmp.com/fanhao/");
//		System.out.println(doc.getElementsByTag("input"));
		conn.userAgent("Mozilla").timeout(3000);
		Document doc=conn.get();
		
		Elements ele=doc.select("ul.movie_list li");
		for(Element e : ele){
			System.out.println(e);
		}
		
		String html=doc.html();
//		List<String> list=StringUtil.regexStr("href=\"(.+?)\"", html);
//		System.out.println(html);
	}

}
