package dev.yuuki.discord.ydl.modules.serverstats.handlers;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

public abstract class AbsCommandHandler {

	@NotNull
	private final SlashCommandInteractionEvent event;

	public AbsCommandHandler(@NotNull SlashCommandInteractionEvent event) {
		this.event = event;
	}

	public abstract void handleCommand();
}
