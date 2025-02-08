package dev.yuuki.discord.ydl.core;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.EnumSet;

@Configuration
@ComponentScan("dev.yuuki.discord.ydl")
public class AppConfiguration {

	private final Logger logger = LoggerFactory.getLogger(AppConfiguration.class);

	private static final EnumSet<GatewayIntent> intents = EnumSet.of(
			GatewayIntent.GUILD_MESSAGES,
			GatewayIntent.GUILD_MEMBERS,
			GatewayIntent.GUILD_PRESENCES,
			GatewayIntent.GUILD_VOICE_STATES
	);

	@Bean("jda")
	public JDA createJDAClient(Collection<ListenerAdapter> listeners) {
		String token = System.getenv("DISCORD_TOKEN");
		if (token == null) {
			logger.error("DISCORD_TOKEN is not set");
			throw new IllegalStateException("DISCORD_TOKEN is not set");
		}
		JDABuilder builder = JDABuilder.create(token, intents);
		logger.debug("Adding {} event listeners", listeners.size());
		listeners.forEach(builder::addEventListeners);
		return builder.build();
	}

	public void onShutdown() {
		logger.info("Shutting down");
	}

}
