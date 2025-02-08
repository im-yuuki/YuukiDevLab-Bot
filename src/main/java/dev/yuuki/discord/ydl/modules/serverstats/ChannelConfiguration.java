package dev.yuuki.discord.ydl.modules.serverstats;

public class ChannelConfiguration {

	static final String TOTAL_PLACEHOLDER = "${TOTAL}";
	static final String HUMAN_PLACEHOLDER = "${HUMAN}";
	static final String BOT_PLACEHOLDER = "${BOT}";
	static final String ONLINE_PLACEHOLDER = "${ONLINE}";
	static final String IDLE_PLACEHOLDER = "${IDLE}";
	static final String DND_PLACEHOLDER = "${DND}";
	static final String IN_VOICE_PLACEHOLDER = "${VOICE}";

	private final long channelId;
	private final String fmt;
	private final long roleId;
	private boolean enabled = true;

	public ChannelConfiguration(long channelId, String fmt) {
		this.channelId = channelId;
		this.fmt = fmt;
		this.roleId = 0;
	}

	public ChannelConfiguration(long channelId, String fmt, long filterByRoleId) {
		this.channelId = channelId;
		this.fmt = fmt;
		this.roleId = filterByRoleId;
	}

	public long getChannelId() {
		return channelId;
	}

	public String getFmt() {
		return fmt;
	}

	public long getFilterRoleId() {
		return roleId;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String format(
			int totalCount,
			int humanCount,
			int botCount,
			int onlineCount,
			int idleCount,
			int dndCount,
			int inVoiceCount
	) {
		return getFmt()
				.replace(TOTAL_PLACEHOLDER, Integer.toString(totalCount))
				.replace(HUMAN_PLACEHOLDER, Integer.toString(humanCount))
				.replace(BOT_PLACEHOLDER, Integer.toString(botCount))
				.replace(ONLINE_PLACEHOLDER, Integer.toString(onlineCount))
				.replace(IDLE_PLACEHOLDER, Integer.toString(idleCount))
				.replace(DND_PLACEHOLDER, Integer.toString(dndCount))
				.replace(IN_VOICE_PLACEHOLDER, Integer.toString(inVoiceCount));
	}

}
