 package br.com.devpaulo.CHLegendChat.abstracts;
 
 import br.com.devpaulo.legendchat.api.events.BungeecordChatMessageEvent;
 import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
 import br.com.devpaulo.legendchat.api.events.PrivateMessageEvent;
 import com.laytonsmith.abstraction.MCPlayer;
 import com.laytonsmith.abstraction.bukkit.BukkitMCPlayer;
 import java.util.List;
 import java.util.Set;
 import org.bukkit.Bukkit;
 import org.bukkit.entity.Player;
 
 public class LegendchatEvent
 {
   public static class LegendchatAPI_BungeecordChatMessageEvent
	 implements CH_BungeecordChatMessageEvent
   {
	 BungeecordChatMessageEvent event;
 
	 public LegendchatAPI_BungeecordChatMessageEvent(BungeecordChatMessageEvent cme)
	 {
	   this.event = cme;
	 }
 
	 public Object _GetObject() {
	   return this.event;
	 }
 
	 public String getMessage() {
	   return this.event.getMessage();
	 }
 
	 public void setMessage(String message) {
	   this.event.setMessage(message);
	 }
 
	 public String getFormat() {
	   return this.event.getFormat();
	 }
 
	 public void setFormat(String format) {
	   this.event.setFormat(format);
	 }
 
	 public Set<Player> getRecipients() {
	   return this.event.getRecipients();
	 }
 
	 public String getChannelName() {
	   return this.event.getChannel().getName();
	 }
 
	 public String getBaseFormat() {
	   return this.event.getBaseFormat();
	 }
 
	 public List<String> getTags() {
	   return this.event.getTags();
	 }
 
	 public String getTagValue(String tag) {
	   return this.event.getTagValue(tag);
	 }
 
	 public void setTagValue(String tag, String value) {
	   this.event.setTagValue(tag, value);
	 }
 
	 public boolean isCancelled() {
	   return this.event.isCancelled();
	 }
 
	 public void setCancelled(boolean cancel) {
	   this.event.setCancelled(cancel);
	 }
   }
 
   public static class LegendchatAPI_ChatMessageEvent
	 implements CH_ChatMessageEvent
   {
	 ChatMessageEvent event;
 
	 public LegendchatAPI_ChatMessageEvent(ChatMessageEvent cme)
	 {
	   this.event = cme;
	 }
 
	 public Object _GetObject() {
	   return this.event;
	 }
 
	 public String getMessage() {
	   return this.event.getMessage();
	 }
 
	 public void setMessage(String message) {
	   this.event.setMessage(message);
	 }
 
	 public String getFormat() {
	   return this.event.getFormat();
	 }
 
	 public void setFormat(String format) {
	   this.event.setFormat(format);
	 }
 
	 public MCPlayer getSender() {
	   return new BukkitMCPlayer(this.event.getSender());
	 }
 
	 public void setSender(Player sender) {
	   this.event.setSender(sender);
	 }
 
	 public Set<Player> getRecipients() {
	   return this.event.getRecipients();
	 }
 
	 public String getChannelName() {
	   return this.event.getChannel().getName();
	 }
 
	 public String getBaseFormat() {
	   return this.event.getBaseFormat();
	 }
 
	 public List<String> getTags() {
	   return this.event.getTags();
	 }
 
	 public String getTagValue(String tag) {
	   return this.event.getTagValue(tag);
	 }
 
	 public void setTagValue(String tag, String value) {
	   this.event.setTagValue(tag, value);
	 }
 
	 public boolean isCancelled() {
	   return this.event.isCancelled();
	 }
 
	 public void setCancelled(boolean cancel) {
	   this.event.setCancelled(cancel);
	 }
   }
 
   public static class LegendchatAPI_PrivateMessageEvent
	 implements CH_PrivateMessageEvent
   {
	 PrivateMessageEvent event;
 
	 public LegendchatAPI_PrivateMessageEvent(PrivateMessageEvent cme)
	 {
	   this.event = cme;
	 }
 
	 public Object _GetObject() {
	   return this.event;
	 }
 
	 public String getMessage() {
	   return this.event.getMessage();
	 }
 
	 public void setMessage(String message) {
	   this.event.setMessage(message);
	 }
 
	 public MCPlayer getSender() {
	   if (this.event.getSender() == Bukkit.getConsoleSender())
		 return null;
	   return new BukkitMCPlayer((Player)this.event.getSender());
	 }
 
	 public void setSender(Player sender) {
	   this.event.setSender(sender);
	 }
 
	 public MCPlayer getReceiver() {
	   if (this.event.getSender() == Bukkit.getConsoleSender())
		 return null;
	   return new BukkitMCPlayer((Player)this.event.getSender());
	 }
 
	 public void setReceiver(Player receiver) {
	   this.event.setSender(receiver);
	 }
 
	 public boolean isCancelled() {
	   return this.event.isCancelled();
	 }
 
	 public void setCancelled(boolean cancel) {
	   this.event.setCancelled(cancel);
	 }
   }
 }