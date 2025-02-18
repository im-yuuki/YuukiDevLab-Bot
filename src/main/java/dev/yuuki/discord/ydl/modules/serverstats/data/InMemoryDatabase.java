package dev.yuuki.discord.ydl.modules.serverstats.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class InMemoryDatabase implements IServerStatsDatabase {

	private final int POLICY_MAX_CHANNEL_PER_GUILD = 15;
	private final HashMap<Long, GuildConfiguration> storage = new HashMap<>();

	@NotNull
	private GuildConfiguration getOrCreateGuildConfiguration(long guildId) {
		GuildConfiguration configuration = storage.get(guildId);
		if (configuration == null) {
			configuration = new GuildConfiguration(guildId);
			storage.put(guildId, configuration);
		}
		return configuration;
	}

	@Override
	@Nullable
	public GuildConfiguration getGuildConfiguration(long guildId) {
		return storage.get(guildId);
	}

	@Override
	public void clearGuildConfiguration(long guildId) {
		storage.remove(guildId);
	}

	@Override
	public void addChannel(long guildId, @NotNull ChannelConfiguration configuration) {
		GuildConfiguration guildConfiguration = getOrCreateGuildConfiguration(guildId);
		if (guildConfiguration.getChannelConfigurations().size() >= POLICY_MAX_CHANNEL_PER_GUILD) {
			throw new IllegalStateException("Max channel per guild reached");
		}
		guildConfiguration.getChannelConfigurations().forEach(channel -> {
			if (channel.getChannelId() == configuration.getChannelId()) {
				throw new IllegalStateException("Channel already exists");
			}
		});
		guildConfiguration.getChannelConfigurations().add(configuration);
	}

	@Override
	public void updateChannel(long guildId, @NotNull ChannelConfiguration configuration) {
		GuildConfiguration guildConfiguration = getOrCreateGuildConfiguration(guildId);
		for (int i = 0; i < guildConfiguration.getChannelConfigurations().size(); i++) {
			ChannelConfiguration channel = guildConfiguration.getChannelConfigurations().get(i);
			if (channel.getChannelId() == configuration.getChannelId()) {
				guildConfiguration.getChannelConfigurations().set(i, configuration);
				return;
			}
		}
	}

	@Override
	public void removeChannel(long guildId, long channelId) {

	}

	@Override
	public void reactivateChannel(long guildId, long channelId) {

	}
}
