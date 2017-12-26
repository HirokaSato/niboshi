package jp.co.stokichi.new_niboshi;

import java.util.concurrent.CompletableFuture;

import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.response.BotApiResponse;

public interface LineMessagingClient {
	CompletableFuture<BotApiResponse> replyMessage(ReplyMessage replyMessage);
}
