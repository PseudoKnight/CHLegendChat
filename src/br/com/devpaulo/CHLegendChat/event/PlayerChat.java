 package br.com.devpaulo.CHLegendChat.event;
 
 import br.com.devpaulo.CHLegendChat.abstracts.CH_ChatMessageEvent;
 import br.com.devpaulo.CHLegendChat.abstracts.CH_BungeecordChatMessageEvent;
 import br.com.devpaulo.CHLegendChat.abstracts.CH_PrivateMessageEvent;

import com.laytonsmith.PureUtilities.Version;

 import com.laytonsmith.abstraction.bukkit.BukkitMCPlayer;
 import com.laytonsmith.annotations.api;
 import com.laytonsmith.core.CHVersion;
 import com.laytonsmith.core.Static;
 import com.laytonsmith.core.constructs.CArray;
 import com.laytonsmith.core.constructs.CBoolean;
 import com.laytonsmith.core.constructs.CString;
 import com.laytonsmith.core.constructs.Construct;
 import com.laytonsmith.core.constructs.Target;
 import com.laytonsmith.core.events.AbstractEvent;
 import com.laytonsmith.core.events.BindableEvent;
 import com.laytonsmith.core.events.Driver;
 import com.laytonsmith.core.events.Prefilters;

 import com.laytonsmith.core.exceptions.EventException;
 import com.laytonsmith.core.exceptions.PrefilterNonMatchException;
 import java.util.ArrayList;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Map;
 import java.util.Set;
 import org.bukkit.entity.Player;
 
 public class PlayerChat
 {
   @api
   public static class bungeecord_chat_message extends AbstractEvent
   {
	 public String getName()
	 {
	   return "bungeecord_chat_message";
	 }
 
	 public String docs() {
	   return "http://dev.bukkit.org/bukkit-plugins/legendchat/pages/command-helper-extension/#w-bungeecord_chat_message";
	 }
 
	 public Driver driver() {
	   return Driver.EXTENSION;
	 }
 
	 public BindableEvent convert(CArray manualObject) {
	   return null;
	 }
 
	 public Version since() {
	   return CHVersion.V3_3_1;
	 }
 
	 public Map<String, Construct> evaluate(BindableEvent event) throws EventException {
	   CH_BungeecordChatMessageEvent e = (CH_BungeecordChatMessageEvent)event;
	   Map<String, Construct> cme = evaluate_helper(e);
	   cme.put("message", new CString(e.getMessage(), Target.UNKNOWN));
	   cme.put("format", new CString(e.getFormat(), Target.UNKNOWN));
	   cme.put("baseformat", new CString(e.getBaseFormat(), Target.UNKNOWN));
	   cme.put("channel", new CString(e.getChannelName(), Target.UNKNOWN));
	   cme.put("cancelled", CBoolean.get(e.isCancelled()));
	   for (String tag : e.getTags())
		 cme.put("tag_" + tag, new CString(e.getTagValue(tag), Target.UNKNOWN));
	   List<Construct> list = new ArrayList<>();
	   for (String tag : e.getTags())
		 list.add(new CString(tag, Target.UNKNOWN));
	   cme.put("tags", new CArray(Target.UNKNOWN, list));
	   list.clear();
	   for (Player p : e.getRecipients())
		 list.add(new CString(p.getName(), Target.UNKNOWN));
	   cme.put("recipients", new CArray(Target.UNKNOWN, list));
	   return cme;
	 }
 
	 public boolean modifyEvent(String key, Construct value, BindableEvent event) {
	   if ((event instanceof CH_BungeecordChatMessageEvent)) {
		 CH_BungeecordChatMessageEvent e = (CH_BungeecordChatMessageEvent)event;
		 if (key.equals("message")) {
		   e.setMessage(value.val());
		   return true;
		 }
		 if (key.equals("format")) {
		   e.setFormat(value.val());
		   return true;
		 }
		 if (key.equals("cancelled")) {
		   if (!(value instanceof CBoolean))
			 return false;
		   e.setCancelled(((CBoolean)value).getBoolean());
		   return true;
		 }
		 if (key.startsWith("recipients")) {
		   if (!(value instanceof CArray))
			 return false;
		   CArray array = (CArray)value;
		   List<Player> list = new ArrayList<>();
		   Set<Player> recipients = new HashSet<>();
		   recipients.addAll(e.getRecipients());
		   for (String vl : array.stringKeySet())
			 list.add(((BukkitMCPlayer)Static.GetPlayer(vl, Target.UNKNOWN))._Player());
		   List<Player> to_add = new ArrayList<>();
		   List<Player> to_rem = new ArrayList<>();
		   for (Player p : recipients)
			 if (!list.contains(p))
			   to_rem.add(p);
		   for (Player p : list)
			 if (!recipients.contains(p))
			   to_add.add(p);
		   for (Player p : to_rem)
			 e.getRecipients().remove(p);
		   for (Player p : to_add)
			 e.getRecipients().add(p);
		   to_add.clear();
		   to_rem.clear();
		   recipients.clear();
		   list.clear();
		   return true;
		 }
		 if ((key.startsWith("tag_")) && (key.length() > 4)) {
		   String tag = key.substring(4).toLowerCase();
		   if (!e.getTags().contains(tag))
			 return false;
		   e.setTagValue(tag, value.val());
		   return true;
		 }
	   }
	   return false;
	 }
 
	 public boolean matches(Map<String, Construct> prefilter, BindableEvent event) throws PrefilterNonMatchException {
	   if ((event instanceof CH_BungeecordChatMessageEvent)) {
		 CH_BungeecordChatMessageEvent e = (CH_BungeecordChatMessageEvent)event;
 
		 Prefilters.match(prefilter, "message", e.getMessage(), Prefilters.PrefilterType.STRING_MATCH);
		 Prefilters.match(prefilter, "format", e.getFormat(), Prefilters.PrefilterType.STRING_MATCH);
		 Prefilters.match(prefilter, "baseformat", e.getBaseFormat(), Prefilters.PrefilterType.STRING_MATCH);
		 Prefilters.match(prefilter, "channel", e.getChannelName(), Prefilters.PrefilterType.STRING_MATCH);
		 Prefilters.match(prefilter, "cancelled", e.isCancelled(), Prefilters.PrefilterType.BOOLEAN_MATCH);
 
		 return true;
	   }
	   return false;
	 }

       @Override
       public BindableEvent convert(CArray cArray, Target target) {
           return null;
       }
   }
 
   @api
   public static class chat_message extends AbstractEvent
   {
	 public String getName()
	 {
	   return "chat_message";
	 }
 
	 public String docs() {
	   return "http://dev.bukkit.org/bukkit-plugins/legendchat/pages/command-helper-extension/#w-chat_message";
	 }
 
	 public Driver driver() {
	   return Driver.EXTENSION;
	 }
 
	 public BindableEvent convert(CArray manualObject) {
	   return null;
	 }
 
	 public Version since() {
	   return CHVersion.V3_3_1;
	 }
 
	 public Map<String, Construct> evaluate(BindableEvent event) throws EventException {
	   CH_ChatMessageEvent e = (CH_ChatMessageEvent)event;
	   Map<String, Construct> cme = evaluate_helper(e);
	   cme.put("message", new CString(e.getMessage(), Target.UNKNOWN));
	   cme.put("format", new CString(e.getFormat(), Target.UNKNOWN));
	   cme.put("baseformat", new CString(e.getBaseFormat(), Target.UNKNOWN));
	   cme.put("sender", new CString(e.getSender().getName(), Target.UNKNOWN));
	   cme.put("channel", new CString(e.getChannelName(), Target.UNKNOWN));
	   cme.put("cancelled", CBoolean.get(e.isCancelled()));
	   for (String tag : e.getTags())
		 cme.put("tag_" + tag, new CString(e.getTagValue(tag), Target.UNKNOWN));
	   List<Construct> list = new ArrayList<>();
	   for (String tag : e.getTags())
		 list.add(new CString(tag, Target.UNKNOWN));
	   cme.put("tags", new CArray(Target.UNKNOWN, list));
	   list.clear();
	   for (Player p : e.getRecipients())
		 list.add(new CString(p.getName(), Target.UNKNOWN));
	   cme.put("recipients", new CArray(Target.UNKNOWN, list));
	   return cme;
	 }
 
	 public boolean modifyEvent(String key, Construct value, BindableEvent event) {
	   if ((event instanceof CH_ChatMessageEvent)) {
		 CH_ChatMessageEvent e = (CH_ChatMessageEvent)event;
		 if (key.equals("message")) {
		   e.setMessage(value.val());
		   return true;
		 }
		 if (key.equals("format")) {
		   e.setFormat(value.val());
		   return true;
		 }
		 if (key.equals("sender")) {
		   e.setSender(((BukkitMCPlayer)Static.GetPlayer(value.val(), Target.UNKNOWN))._Player());
		   return true;
		 }
		 if (key.equals("cancelled")) {
		   if (!(value instanceof CBoolean))
			 return false;
		   e.setCancelled(((CBoolean)value).getBoolean());
		   return true;
		 }
		 if (key.startsWith("recipients")) {
		   if (!(value instanceof CArray))
			 return false;
		   CArray array = (CArray)value;
		   List<Player> list = new ArrayList<>();
		   Set<Player> recipients = new HashSet<>();
		   recipients.addAll(e.getRecipients());
		   for (int i = 0; i < array.size(); i++)
			 list.add(((BukkitMCPlayer)Static.GetPlayer(array.get(i, Target.UNKNOWN), Target.UNKNOWN))._Player());
		   List<Player> to_add = new ArrayList<>();
		   List<Player> to_rem = new ArrayList<>();
		   for (Player p : recipients)
			 if (!list.contains(p))
			   to_rem.add(p);
		   for (Player p : list)
			 if (!recipients.contains(p))
			   to_add.add(p);
		   for (Player p : to_rem)
			 e.getRecipients().remove(p);
		   for (Player p : to_add)
			 e.getRecipients().add(p);
		   to_add.clear();
		   to_rem.clear();
		   recipients.clear();
		   list.clear();
		   return true;
		 }
		 if ((key.startsWith("tag_")) && (key.length() > 4)) {
		   String tag = key.substring(4).toLowerCase();
		   if (!e.getTags().contains(tag))
			 return false;
		   e.setTagValue(tag, value.val());
		   return true;
		 }
	   }
	   return false;
	 }
 
	 public boolean matches(Map<String, Construct> prefilter, BindableEvent event) throws PrefilterNonMatchException {
	   if ((event instanceof CH_ChatMessageEvent)) {
		 CH_ChatMessageEvent e = (CH_ChatMessageEvent)event;
 
		 Prefilters.match(prefilter, "sender", e.getSender().getName(), Prefilters.PrefilterType.STRING_MATCH);
		 Prefilters.match(prefilter, "message", e.getMessage(), Prefilters.PrefilterType.STRING_MATCH);
		 Prefilters.match(prefilter, "format", e.getFormat(), Prefilters.PrefilterType.STRING_MATCH);
		 Prefilters.match(prefilter, "baseformat", e.getBaseFormat(), Prefilters.PrefilterType.STRING_MATCH);
		 Prefilters.match(prefilter, "channel", e.getChannelName(), Prefilters.PrefilterType.STRING_MATCH);
		 Prefilters.match(prefilter, "cancelled", e.isCancelled(), Prefilters.PrefilterType.BOOLEAN_MATCH);
 
		 return true;
	   }
	   return false;
	 }

       @Override
       public BindableEvent convert(CArray cArray, Target target) {
           return null;
       }
   }
 
   @api
   public static class private_message extends AbstractEvent {
	 public String getName() {
	   return "private_message";
	 }
 
	 public String docs() {
	   return "http://dev.bukkit.org/bukkit-plugins/legendchat/pages/command-helper-extension/#w-private_message";
	 }
 
	 public Driver driver() {
	   return Driver.EXTENSION;
	 }
 
	 public BindableEvent convert(CArray manualObject) {
	   return null;
	 }
 
	 public Version since() {
	   return CHVersion.V3_3_1;
	 }
 
	 public Map<String, Construct> evaluate(BindableEvent event) throws EventException {
	   CH_PrivateMessageEvent e = (CH_PrivateMessageEvent)event;
	   Map<String, Construct> cme = evaluate_helper(e);
	   cme.put("message", new CString(e.getMessage(), Target.UNKNOWN));
	   cme.put("sender", new CString(e.getSender().getName(), Target.UNKNOWN));
	   cme.put("receiver", new CString(e.getReceiver().getName(), Target.UNKNOWN));
	   cme.put("cancelled", CBoolean.get(e.isCancelled()));
	   return cme;
	 }
 
	 public boolean modifyEvent(String key, Construct value, BindableEvent event) {
	   if ((event instanceof CH_PrivateMessageEvent)) {
		 CH_PrivateMessageEvent e = (CH_PrivateMessageEvent)event;
		 if (key.equals("message")) {
		   e.setMessage(value.val());
		   return true;
		 }
		 if (key.equals("sender")) {
		   e.setSender(((BukkitMCPlayer)Static.GetPlayer(value.val(), Target.UNKNOWN))._Player());
		   return true;
		 }
		 if (key.equals("receiver")) {
		   e.setReceiver(((BukkitMCPlayer)Static.GetPlayer(value.val(), Target.UNKNOWN))._Player());
		   return true;
		 }
		 if (key.equals("cancelled")) {
		   if (!(value instanceof CBoolean))
			 return false;
		   e.setCancelled(((CBoolean)value).getBoolean());
		   return true;
		 }
	   }
	   return false;
	 }
 
	 public boolean matches(Map<String, Construct> prefilter, BindableEvent event) throws PrefilterNonMatchException {
	   if ((event instanceof CH_PrivateMessageEvent)) {
		 CH_PrivateMessageEvent e = (CH_PrivateMessageEvent)event;
 
		 Prefilters.match(prefilter, "sender", e.getSender().getName(), Prefilters.PrefilterType.STRING_MATCH);
		 Prefilters.match(prefilter, "receiver", e.getReceiver().getName(), Prefilters.PrefilterType.STRING_MATCH);
		 Prefilters.match(prefilter, "message", e.getMessage(), Prefilters.PrefilterType.STRING_MATCH);
		 Prefilters.match(prefilter, "cancelled", e.isCancelled(), Prefilters.PrefilterType.BOOLEAN_MATCH);
		 return true;
	   }
	   return false;
	 }

       @Override
       public BindableEvent convert(CArray cArray, Target target) {
           return null;
       }
   }
 }