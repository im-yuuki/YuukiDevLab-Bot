package dev.yuuki.discord.ydl.modules.serverstats;

import dev.yuuki.discord.ydl.core.interfaces.SlashCommandRegistry;
import dev.yuuki.discord.ydl.modules.serverstats.handlers.*;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Event listener for server stats display management.
 */
@Service("serverStats")
public class ServerStats extends ListenerAdapter implements SlashCommandRegistry {

	Logger logger = LoggerFactory.getLogger(ServerStats.class);

	@Override
	public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
		String[] command = event.getCommandString().split(" ");
		if (command.length != 2 && command[0].equals("/stats")) {
			@Nullable AbsCommandHandler handler = null;
			switch (command[1]) {
				case "show":
					handler = new ShowStatsSettings(event);
					break;
				case "add":
					handler = new AddStatsChannel(event);
					break;
				case "delete":
					handler = new DeleteStatsChannel(event);
					break;
				case "clear":
					handler = new ClearStatsSettings(event);
					break;
				case "reactivate":
					handler = new ReactiveStatsChannel(event);
				default:
					break;
			}
			if (handler != null) {
				logger.trace("Processing {} command", event.getCommandString());
				handler.handleCommand();
			}
		}
		super.onSlashCommandInteraction(event);
	}

	@Override
	public Collection<CommandData> getCommandDataCollection() {
		return List.of(
				Commands.slash("stats", "Manage server stats display")
						.setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.MANAGE_CHANNEL))
						.addSubcommands(
								new SubcommandData("show", "Show current server stats configuration"),
								new SubcommandData("add", "Add a voice channel using for displaying server stats")
										.addOption(OptionType.CHANNEL, "channel", "Select voice channel to use", true)
										.addOption(OptionType.STRING, "format", "Text format", true, true)
										.addOption(OptionType.ROLE, "role", "Count members having this role only", true),
								new SubcommandData("delete", "Remove stats display for a configured channel")
										.addOption(OptionType.INTEGER, "id", "Channel ID", true, true),
								new SubcommandData("clear", "Remove all stats display of this server"),
								new SubcommandData("reactivate", "Reactivate stats on a channel if there was an updating error before")
										.addOption(OptionType.INTEGER, "id", "Channel ID (Leave blank to reactive all channels")
						)
		);
	}

}
