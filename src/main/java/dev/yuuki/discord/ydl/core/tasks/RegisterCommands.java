package dev.yuuki.discord.ydl.core.tasks;

import dev.yuuki.discord.ydl.core.interfaces.SlashCommandRegistry;
import dev.yuuki.discord.ydl.core.interfaces.StartupTask;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class RegisterCommands implements StartupTask {

	private final JDA jda;
	private final Collection<SlashCommandRegistry> registries;

	public RegisterCommands(JDA jda, Collection<SlashCommandRegistry> registries) {
		this.jda = jda;
		this.registries = registries;
	}

	@Override
	public void run() {
		CommandListUpdateAction action = jda.updateCommands();
		registries.forEach(interactionModule -> {
			interactionModule.getCommandDataCollection().forEach(action::addCommands);
		});
		action.queue();
	}

}
