package dev.yuuki.discord.ydl.modules.serverstats;

import java.util.ArrayList;

public class GuildConfiguration {

	private final long guildId;
	private final ArrayList<ChannelConfiguration> channelConfigurations = new ArrayList<>();

	public GuildConfiguration(long guildId) {
		this.guildId = guildId;
	}

	public long getGuildId() {
		return guildId;
	}

}
