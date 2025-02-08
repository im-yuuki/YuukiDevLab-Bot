package dev.yuuki.discord.ydl.core.tasks;

import dev.yuuki.discord.ydl.core.interfaces.InteractionModule;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class RegisterCommands implements Runnable {

	private final JDA jda;
	private final Collection<InteractionModule> interactionModules;

	public RegisterCommands(JDA jda, Collection<InteractionModule> interactionModules) {
		this.jda = jda;
		this.interactionModules = interactionModules;
	}

	@Override
	public void run() {
		CommandListUpdateAction action = jda.updateCommands();
		interactionModules.forEach(interactionModule -> {
			interactionModule.getCommandDataCollection().forEach(action::addCommands);
		});
		try {
			jda.awaitReady();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		action.queue();
	}

}
