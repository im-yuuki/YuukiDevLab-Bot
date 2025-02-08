package dev.yuuki.discord.ydl.modules.serverstats;

import dev.yuuki.discord.ydl.core.interfaces.InteractionModule;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandReference;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.internal.interactions.command.SlashCommandInteractionImpl;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.objenesis.instantiator.sun.SunReflectionFactoryInstantiator;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service("serverStatsListener")
public class EventListener extends ListenerAdapter implements InteractionModule {

	Logger logger = LoggerFactory.getLogger(EventListener.class);

	@Override
	public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

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
