package jp.co.stokichi.new_niboshi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.validation.constraints.NotNull;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
//import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.StickerMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.CarouselColumn;
//import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;

import retrofit2.Call;
import retrofit2.http.POST;

@Service
public class NiboshiService {
	public List<CarouselColumn> searchNiboshi(MessageEvent<TextMessageContent> event) throws IOException {
		// カルーセルテンプレートをつめるリスト生成
		List<CarouselColumn> noodleList = new ArrayList<>();
		// CarouselColumnを生成するためにスクレイピングde情報取得
		CarouselColumn column = screaping(event);
		noodleList.add(column);
		// 閉店のお店は非表示にしたい
		// テンプレートに5店舗表示したい//5店舗だけ抽出//テンプレートにつめる//
		// document.getElementsByClass("name").select("h4").first().text();

		return noodleList;
	}

	public CarouselColumn screaping(MessageEvent<TextMessageContent> event) throws IOException {
		// 取得したい情報があるURL(送られてくるeventと煮干で検索)
		String url = "https://ramendb.supleks.jp/search?q=" + event.getMessage().getText() + "++煮干&state=&order=point";
		// ブラウザ全体の情報を取得
		Document document = Jsoup.connect(url).get();// Jsoupはdocument型でとれる。
		// class名がnameの中のh4タグの要素をString型で取得（ブラウザで表示してあるすべてのお店の名前がとれてくる）
		String elements = document.getElementsByClass("name").select("h4").first().text();
		String link = "https://ramendb.supleks.jp"
				+ document.getElementById("searched").select("a").first().attr("href");
		List<Action> actions = new ArrayList<>();
		return new CarouselColumn(link, elements, "ラーメン", actions);
	}
	// public String searchNiboshi(MessageEvent<TextMessageContent> event)
	// throws IOException {
	// List<String> nameList = new ArrayList<>();
	// // 取得したい情報があるURL(送られてくるeventと煮干で検索)
	// String url = "https://ramendb.supleks.jp/search?q=" +
	// event.getMessage().getText() + "++煮干&state=&order=point";
	// // ブラウザ全体の情報を取得
	// Document document = Jsoup.connect(url).get();// Jsoupはdocument型でとれる。
	// // class名がnameの中のh4タグの要素をString型で取得（ブラウザで表示してあるすべてのお店の名前がとれてくる）
	// String elements =
	// document.getElementsByClass("name").select("h4").text();
	// //閉店のお店は非表示にしたい
	// //テンプレートに5店舗表示したい//5店舗だけ抽出//テンプレートにつめる//
	// // document.getElementsByClass("name").select("h4").first().text();
	//
	// return elements;
	// }

	// ※idでとる場合
	// Element element=document.getElementById()・・・
	// ※classの場合（複数のためelements型）
	// Elements elements=document.getElementsByClass()・・・

	// String pointvals=document.getElementsByClass().text();
	// System.out.println(pointvals);⇒ポイントの配列
	// 配列作る
	// 拡張for文でpointvalsをまわす
	// 作った配列にadd
	// mpdelにつめこむ
	// とばす

}
