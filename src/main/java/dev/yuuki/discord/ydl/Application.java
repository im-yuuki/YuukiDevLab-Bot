package dev.yuuki.discord.ydl;

import dev.yuuki.discord.ydl.core.AppConfiguration;
import dev.yuuki.discord.ydl.core.tasks.RegisterCommands;
import net.dv8tion.jda.api.JDA;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		JDA jda = context.getBean(JDA.class);
		jda.awaitReady();
		context.getBean(RegisterCommands.class).run();
	}

}
