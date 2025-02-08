package dev.yuuki.discord.ydl.core.interfaces;

import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.util.Collection;

public interface InteractionModule {

	Collection<CommandData> getCommandDataCollection();

}
