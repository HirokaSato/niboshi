package jp.co.stokichi.new_niboshi;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.linecorp.bot.model.action.Action;

/**
 * @author hiroka.sato
 * 
 *         情報をつめるテンプレート
 *
 */
public class CarouselColumn {
	// URL
	String thumbnailImageUrl;
	// タイトル
	String title;
	// 紹介文
	String text;
	// クリック時のアクション
	List<Action> actions;

	public CarouselColumn(@JsonProperty("thumbnailImageUrl") String thumbnailImageUrl,
			@JsonProperty("title") String title, @JsonProperty("text") String text,
			@JsonProperty("actions") List<Action> actions) {
		this.thumbnailImageUrl = thumbnailImageUrl;
		this.title = title;
		this.text = text;
		this.actions = actions;// != null ? actions : Collections.emptyList();
	}
}
