package dev.yuuki.discord.ydl.core;

import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.util.Collection;

/**
 * Interface for beans that provide slash command data.
 */
public interface ISlashCommandRegistry {

	Collection<CommandData> getCommandDataCollection();

}
