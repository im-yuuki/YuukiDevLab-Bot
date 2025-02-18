package dev.yuuki.discord.ydl.core;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ExceptionEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.events.session.ShutdownEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Base event listener for handling common events and logging debug information.
 */
@Service
public class BaseEventListener extends ListenerAdapter {

	private final Logger logger = LoggerFactory.getLogger(BaseEventListener.class);
	private final ApplicationContext context;

	public BaseEventListener(ApplicationContext context) {
		this.context = context;
	}

	@Override
	public void onReady(@NotNull ReadyEvent event) {
		logger.info("Connected to Discord Gateway as {}", event.getJDA().getSelfUser().getAsTag());
		super.onReady(event);
	}

	@Override
	public void onShutdown(@NotNull ShutdownEvent event) {
		logger.info("Shutting down");
		super.onShutdown(event);
	}

	@Override
	public void onException(@NotNull ExceptionEvent event) {
		super.onException(event);
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
