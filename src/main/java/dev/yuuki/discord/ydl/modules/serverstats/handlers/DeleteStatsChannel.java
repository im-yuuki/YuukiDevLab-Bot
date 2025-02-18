package dev.yuuki.discord.ydl.modules.serverstats.handlers;

import dev.yuuki.discord.ydl.modules.serverstats.data.IServerStatsDatabase;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

public class DeleteStatsChannel extends AbsCommandHandler {

	public DeleteStatsChannel(@NotNull SlashCommandInteractionEvent event, @NotNull IServerStatsDatabase database) {
		super(event, database);
	}

	@Override
	public void handleCommand() {

	}

}
