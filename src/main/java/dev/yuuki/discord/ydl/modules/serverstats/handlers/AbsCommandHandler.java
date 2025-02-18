package dev.yuuki.discord.ydl.modules.serverstats.handlers;

import dev.yuuki.discord.ydl.modules.serverstats.data.IServerStatsDatabase;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

public abstract class AbsCommandHandler {

	@NotNull
	private final SlashCommandInteractionEvent event;

	@NotNull
	private final IServerStatsDatabase database;

	public AbsCommandHandler(@NotNull SlashCommandInteractionEvent event, @NotNull IServerStatsDatabase database) {
		this.event = event;
		this.database = database;
	}

	public abstract void handleCommand();
}
