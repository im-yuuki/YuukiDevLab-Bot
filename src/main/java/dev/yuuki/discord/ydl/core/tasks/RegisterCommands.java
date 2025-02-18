package dev.yuuki.discord.ydl.core.tasks;

import dev.yuuki.discord.ydl.core.ISlashCommandRegistry;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class RegisterCommands implements IStartupTask {

	private final JDA jda;
	private final Collection<ISlashCommandRegistry> registries;

	public RegisterCommands(JDA jda, Collection<ISlashCommandRegistry> registries) {
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
