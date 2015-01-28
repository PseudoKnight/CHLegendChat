package br.com.devpaulo.CHLegendChat.abstracts;

import com.laytonsmith.core.events.BindableEvent;
import java.util.List;
import java.util.Set;
import org.bukkit.entity.Player;

public abstract interface CH_BungeecordChatMessageEvent extends BindableEvent
{
  public abstract String getMessage();

  public abstract void setMessage(String paramString);

  public abstract String getFormat();

  public abstract void setFormat(String paramString);

  public abstract Set<Player> getRecipients();

  public abstract String getChannelName();

  public abstract String getBaseFormat();

  public abstract List<String> getTags();

  public abstract String getTagValue(String paramString);

  public abstract void setTagValue(String paramString1, String paramString2);

  public abstract boolean isCancelled();

  public abstract void setCancelled(boolean paramBoolean);
}