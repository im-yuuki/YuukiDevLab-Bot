package dev.yuuki.discord.ydl;

import dev.yuuki.discord.ydl.core.AppConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

	public static void main(String[] args) {
		new AnnotationConfigApplicationContext(AppConfiguration.class);
	}

}
