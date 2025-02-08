package dev.yuuki.discord.ydl.core;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BaseEventListener extends ListenerAdapter {

	Logger logger = LoggerFactory.getLogger(BaseEventListener.class);

	@Override
	public void onReady(@NotNull ReadyEvent event) {
		logger.info("Connected to Discord Gateway");
		super.onReady(event);
	}

	@Override
	public void onGuildJoin(@NotNull GuildJoinEvent event) {
		Guild guild = event.getGuild();
		logger.info("Added to guild {} ({})", guild.getName(), guild.getId());
		super.onGuildJoin(event);
	}

	@Override
	public void onGuildLeave(@NotNull GuildLeaveEvent event) {
		Guild guild = event.getGuild();
		logger.info("Removed from guild {} ({})", guild.getName(), guild.getId());
		super.onGuildLeave(event);
	}

	@Override
	public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
		logger.info("User {} executed {} (Channel: {})", event.getUser().getAsTag(), event.getCommandString(), event.getChannelId());
		super.onSlashCommandInteraction(event);
	}

}
