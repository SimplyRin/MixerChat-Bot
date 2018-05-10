package net.simplyrin.mixerchat.bot.discord;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.simplyrin.mixerchat.bot.Main;

/**
 * Created by SimplyRin on 2018/05/10
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
public class MessageListener extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		User sender = event.getAuthor();
		MessageChannel channel = event.getChannel();
		// User user = event.getAuthor();
		// Guild guild = event.getGuild();

		String message = event.getMessage().getContentRaw();
		// String[] args = message.toString().split(" ");

		if(!channel.getId().equals(Main.getConfig().getString("Discord.Channel-ID"))) {
			return;
		}

		/** if(Main.getChatConnectable().connect()) {
			Logger.println("[Side:Discord] " + sender.getName() + ": " + message);
			Main.getChatConnectable().send(ChatSendMethod.of("[Discord] " + sender.getName() + ": " + message));
		} */
	}

}
