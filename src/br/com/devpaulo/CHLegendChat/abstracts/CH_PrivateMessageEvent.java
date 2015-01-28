package br.com.devpaulo.CHLegendChat.abstracts;

import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.core.events.BindableEvent;
import org.bukkit.entity.Player;

public abstract interface CH_PrivateMessageEvent extends BindableEvent
{
  public abstract String getMessage();

  public abstract void setMessage(String paramString);

  public abstract MCPlayer getSender();

  public abstract void setSender(Player paramPlayer);

  public abstract MCPlayer getReceiver();

  public abstract void setReceiver(Player paramPlayer);

  public abstract boolean isCancelled();

  public abstract void setCancelled(boolean paramBoolean);
}