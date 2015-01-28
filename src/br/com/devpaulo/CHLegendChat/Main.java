package br.com.devpaulo.CHLegendChat;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.extensions.MSExtension;

@MSExtension("CHLegendchat")
public class Main extends AbstractExtension
{
	public static CommandHelperPlugin chp;
	public static ChatListener chat;

	public void onStartup()
	{
		System.out.println("CHLegendchat " + getVersion() + " loaded.");

		chp = CommandHelperPlugin.self;
		try {
			Static.checkPlugin("Legendchat", Target.UNKNOWN);
			chat = new ChatListener(chp);
		} catch (ConfigRuntimeException ex) {
			chp.getLogger().warning("Legendchat not found!");
		}
	}

	public void onShutdown()
	{
		System.out.println("CHLegendchat " + getVersion() + " unloaded.");
		chat.unregister();
	}

	public Version getVersion()
	{
		return new SimpleVersion(0, 8, 1);
	}
}