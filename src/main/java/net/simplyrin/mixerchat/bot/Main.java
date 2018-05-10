package net.simplyrin.mixerchat.bot;

import java.io.File;
import java.io.IOException;

import com.mixer.api.MixerAPI;
import com.mixer.api.resource.MixerUser;
import com.mixer.api.resource.chat.MixerChat;
import com.mixer.api.resource.chat.events.IncomingMessageEvent;
import com.mixer.api.resource.chat.methods.AuthenticateMessage;
import com.mixer.api.resource.chat.replies.AuthenticationReply;
import com.mixer.api.resource.chat.replies.ReplyHandler;
import com.mixer.api.resource.chat.ws.MixerChatConnectable;
import com.mixer.api.services.impl.ChatService;
import com.mixer.api.services.impl.UsersService;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Game.GameType;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.md_5.bungee.config.Configuration;
import net.simplyrin.mixerchat.bot.discord.MessageListener;
import net.simplyrin.mixerchat.bot.utils.Config;

/**
 * Created by SimplyRin on 2018/05/10
 *
 * Mixer のチャットを Discord へ送信するもの。
 *
 * Copyright (C) 2018 SimplyRin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
public class Main {

	// @Getter
	private static JDA jda;
	// @Getter
	private static MixerAPI mixer;
	// @Getter
	private static MixerChatConnectable chatConnectable;

	public static void main(String[] args) {
		File file = new File("config.yml");
		Configuration config = null;
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			config = Config.getConfig(file);

			config.set("Discord.Token", "TOKEN");
			config.set("Discord.Guild", "GUILD");
			config.set("Discord.Channel-ID", "CHANNEL-ID");
			config.set("Discord.Game", "GAME");

			config.set("Mixer.Token", "TOKEN");

			Config.saveConfig(config, file);
		}

		config = Config.getConfig(file);

		/**
		 * JDA start.
		 */
		JDABuilder jdaBuilder = new JDABuilder(AccountType.BOT);
		jdaBuilder.setToken(config.getString("Discord.Token"));
		jdaBuilder.addEventListener(new MessageListener());

		try {
			jda = jdaBuilder.buildBlocking();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
			return;
		}

		jda.getPresence().setGame(Game.of(GameType.WATCHING, config.getString("Discord.Game")));

		/**
		 * MixerAPI start.
		 */
		mixer = new MixerAPI(config.getString("Mixer.Token"));

		MixerUser user;
		MixerChat chat;

		try {
			user = mixer.use(UsersService.class).getCurrent().get();
			chat = mixer.use(ChatService.class).findOne(user.channel.id).get();

			chatConnectable = chat.connectable(mixer);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
			return;
		}

		if(chatConnectable.connect()) {
			chatConnectable.send(AuthenticateMessage.from(user.channel, user, chat.authkey), new ReplyHandler<AuthenticationReply>() {
				@Override
				public void onSuccess(AuthenticationReply authReply) {
					Logger.println("Mixer-API - Finished Loading!");
				}
				@Override
				public void onFailure(Throwable throwable) {
					Logger.println("Mixer-API - Failed Loading");
					throwable.printStackTrace();
					System.exit(0);
				}
			});

			chatConnectable.on(IncomingMessageEvent.class, event -> {
				String sender = event.data.userName;
				String message = event.data.message.message.get(0).text;

				Logger.println("[Side:Mixer] " + sender + ": " + message);

				Guild guild = jda.getGuildById(getConfig().getString("Discord.Guild"));
				MessageChannel channel = guild.getTextChannelById(getConfig().getString("Discord.Channel-ID"));

				channel.sendMessage("[" + Logger.getTime() + " | Mixer] " + sender + ": " + message).complete();
			});
		}
	}

	public static Configuration getConfig() {
		File file = new File("config.yml");
		Configuration config = Config.getConfig(file);
		return config;
	}

}
