package dev.yuuki.discord.ydl.modules.greet;

import dev.yuuki.discord.ydl.core.interfaces.SlashCommandRegistry;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service("activityLogging")
public class EventListener extends ListenerAdapter implements SlashCommandRegistry {

	@Override
	public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
		if (event.getCommandString().equals("/ping")) {
			event.reply("pong!").setEphemeral(true).queue();
		}
		super.onSlashCommandInteraction(event);
	}

	@Override
	public Collection<CommandData> getCommandDataCollection() {
		return List.of(Commands.slash("ping", "For testing response"));
	}
}
