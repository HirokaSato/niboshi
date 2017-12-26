package jp.co.stokichi.new_niboshi;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.linecorp.bot.model.message.template.Template;

/*
*カルーセルのように循環できる複数の列を持つテンプレートメッセージ。
*/
public class CarouselTemplate implements Template {
	private final List<CarouselColumn> columns;

	public CarouselTemplate(@JsonProperty("columns") List<CarouselColumn> columns) {
		this.columns = columns;
	}
}
