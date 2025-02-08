package dev.yuuki.discord.ydl.core;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
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

	private static final EnumSet<CacheFlag> cacheFlags = EnumSet.of(
			CacheFlag.ACTIVITY,
			CacheFlag.CLIENT_STATUS,
			CacheFlag.MEMBER_OVERRIDES,
			CacheFlag.ONLINE_STATUS,
			CacheFlag.VOICE_STATE
	);

	@Bean("jda")
	public JDA createJDAClient(Collection<ListenerAdapter> listeners) throws InterruptedException {
		// Get bot token from environment variable
		String token = System.getenv("DISCORD_TOKEN");
		if (token == null) throw new IllegalStateException("DISCORD_TOKEN is not set");
		// Builder
		JDABuilder builder = JDABuilder.create(token, intents);
		builder.setAutoReconnect(true);
		builder.useSharding(0, 1);
		builder.setMemberCachePolicy(MemberCachePolicy.ALL);
		builder.disableCache(EnumSet.allOf(CacheFlag.class));
		builder.enableCache(cacheFlags);
		listeners.forEach(builder::addEventListeners);
		return builder.build().awaitReady();
	}

}
