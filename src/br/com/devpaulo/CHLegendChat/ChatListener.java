 package br.com.devpaulo.CHLegendChat;
 
 import br.com.devpaulo.CHLegendChat.abstracts.LegendchatEvent;
 import br.com.devpaulo.legendchat.api.events.BungeecordChatMessageEvent;
 import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
 import br.com.devpaulo.legendchat.api.events.PrivateMessageEvent;
 import com.laytonsmith.commandhelper.CommandHelperPlugin;
 import com.laytonsmith.core.events.Driver;
 import com.laytonsmith.core.events.EventUtils;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 
 public class ChatListener
   implements Listener
 {
   public ChatListener(CommandHelperPlugin chp)
   {
	 chp.registerEvent(this);
   }
 
   public void unregister() {
	 ChatMessageEvent.getHandlerList().unregister(this);
   }
   @EventHandler
   public void ChatMsg(ChatMessageEvent event) {
	 LegendchatEvent.LegendchatAPI_ChatMessageEvent rte = new LegendchatEvent.LegendchatAPI_ChatMessageEvent(event);
	 EventUtils.TriggerListener(Driver.EXTENSION, "chat_message", rte);
   }
   @EventHandler
   public void ChatMsg2(BungeecordChatMessageEvent event) {
	 LegendchatEvent.LegendchatAPI_BungeecordChatMessageEvent rte = new LegendchatEvent.LegendchatAPI_BungeecordChatMessageEvent(event);
	 EventUtils.TriggerListener(Driver.EXTENSION, "bungeecord_chat_message", rte);
   }
   @EventHandler
   public void PrivateMsg(PrivateMessageEvent event) {
	 LegendchatEvent.LegendchatAPI_PrivateMessageEvent rte = new LegendchatEvent.LegendchatAPI_PrivateMessageEvent(event);
	 EventUtils.TriggerListener(Driver.EXTENSION, "private_message", rte);
   }
 }