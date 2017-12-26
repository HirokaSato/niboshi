package jp.co.stokichi.new_niboshi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Screaping {
	public static void main(String[] args) throws IOException {
		String event = "新宿駅";
		// ブラウザ全体の情報を取得
		Document document = Jsoup.connect("https://ramendb.supleks.jp/search?q=" + event + "++煮干&state=&order=point").get();

//		Elements elements=document.getElementsByClass("name").select("h4");
		String elementText=document.getElementsByClass("name").select("h4").text();
//		System.out.println(document);
//		System.out.println(elements);
		System.out.println(elementText);
				 
	}
}

















//		List<org.jsoup.nodes.Element> image = document.getElementsByClass("photo").select("img");
//		System.out.println(image);
//		List<String> imageList = new ArrayList<>();
//		for (int i = 0; i < 5; i++) {
//			imageList.add(image.get(i).attr("src"));
//		}
//		System.out.println(imageList);
//
//		List<String> nameList = new ArrayList<>();
//		List<org.jsoup.nodes.Element> elements = document.getElementsByClass("name").select("h4");
//		for (int i = 0; i < 5; i++) {
//			nameList.add(elements.get(i).text());
//			System.out.println(elements.get(i).text());
//		}
//		List<org.jsoup.nodes.Element> urls = document.getElementsByClass("bglink");
//		List<String> urlList = new ArrayList<>();
//		for (int i = 0; i < 5; i++) {
//			urlList.add(urls.get(i).attr("href"));
//		}
//		System.out.println(urlList);
//		System.out.println(nameList);
