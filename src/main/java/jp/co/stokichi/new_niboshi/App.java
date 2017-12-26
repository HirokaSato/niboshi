package jp.co.stokichi.new_niboshi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

/**
 * Hello world!
 *
 */
@SpringBootApplication
//@LineMessageHandler
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		System.out.println("hello");
	}

//	@EventMapping
//	public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
//		System.out.println("event: " + event);
//		return new TextMessage("にぼしくんだよー");
//		//return new TextMessage(event.getMessage().getText());
//	}
//
//	@EventMapping
//	public void handleDefaultMessageEvent(Event event) {
//		System.out.println("event: " + event);
//	}
}
