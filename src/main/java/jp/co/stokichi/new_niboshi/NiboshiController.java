package jp.co.stokichi.new_niboshi;

import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.*;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.StickerMessage;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.linecorp.bot.model.message.template.CarouselColumn;
import com.linecorp.bot.model.message.template.CarouselTemplate;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import retrofit2.Response;

@Controller
@LineMessageHandler
public class NiboshiController {

	// 駅名で検索するとURLを表示する。
	@EventMapping
	public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws IOException {
		//クライアントが送ったパラメータと煮干しのワードをいれて検索するURL
		String url = "https://ramendb.supleks.jp/search?q=" + event.getMessage().getText() + "++煮干&state=&order=point";
		//検索結果のブラウザ情報を取得
		Document document = Jsoup.connect(url).get();
		//ラーメンのお店の名前をelementsに代入
		String elements = document.getElementsByClass("name").select("h4").text();
		
		if (elements.isEmpty()) {
			//elementsが空だった場合
			return new TextMessage("おっと！" + event.getMessage().getText() + "には煮干しラーメンはないみたいだ！\n残念だが他の駅で探してみてくれ");
		} else {
			//elemsntsがあった場合
			//カルーセルコラム型のリスト：columnsにcreateClumns(document)を代入				
			List<CarouselColumn> columns = createColumns(document);
			CarouselTemplate template = new CarouselTemplate(columns);
			return new TemplateMessage("template", template);
		}
	}
	
		
	public List<CarouselColumn> createColumns(Document document) throws IOException {
		//カルーセルコラム型リストのcolumnsにarraylistを新しくインスタンス
		List<CarouselColumn> columns = new ArrayList<>();
		//element型のリストに写真、店名、点数、urlをそれぞれ代入
		List<Element> images = document.getElementsByClass("photo").select("img");
		List<Element> titles = document.getElementsByClass("name").select("h4");
		List<Element> point = document.getElementsByClass("point-val");
		List<Element> urls = document.getElementsByClass("bglink");
		//String型のリストに写真、店名、点数、urlをそれぞれ代入
		List<String> imageList = new ArrayList<>();
		List<String> titleList = new ArrayList<>();
		List<String> pointList = new ArrayList<>();
		//Action型のリスト：actionlistにarraylistをインスタンス
		List<List<Action>> actionList = new ArrayList<>();

		int num = 5;
		if (num < titles.size()) {
			//もし検索結果が5件以上あった場合は5件
			for (int i = 0; i < num; i++) {
				titleList.add(titles.get(i).text());
				imageList.add(images.get(i).attr("src"));
				pointList.add(point.get(i).text() + "ポイント");
				List<Action> actions = new ArrayList<>();
				actions.add(new URIAction("詳細をみる", "https://ramendb.supleks.jp" + urls.get(i).attr("href")));
				actionList.add(actions);
			}
		} else {
			//検索結果が5件未満の場合が全件表示
			for (int i = 0; i < titles.size(); i++) {
				titleList.add(titles.get(i).text());
				imageList.add(images.get(i).attr("src"));
				pointList.add(point.get(i).text() + "ポイント");
				List<Action> actions = new ArrayList<>();
				actions.add(new URIAction("詳細をみる", "https://ramendb.supleks.jp" + urls.get(i).attr("href")));
				actionList.add(actions);
			}
		}
		for (int i = 0; i < titleList.size(); i++) {
			columns.add(new CarouselColumn(imageList.get(i), titleList.get(i), pointList.get(i), actionList.get(i)));
		}

		return columns;
	}

}
