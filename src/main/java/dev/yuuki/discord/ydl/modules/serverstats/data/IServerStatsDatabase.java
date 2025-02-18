package dev.yuuki.discord.ydl.modules.serverstats.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IServerStatsDatabase {

	@Nullable
	GuildConfiguration getGuildConfiguration(long guildId);
	void clearGuildConfiguration(long guildId);

	void addChannel(long guildId, @NotNull ChannelConfiguration configuration);
	void updateChannel(long guildId, @NotNull ChannelConfiguration configuration);
	void removeChannel(long guildId, long channelId);
	void reactivateChannel(long guildId, long channelId);

}
