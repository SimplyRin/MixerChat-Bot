package net.simplyrin.mixerchat.bot.discord;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.simplyrin.mixerchat.bot.Main;
import net.simplyrin.mixerchat.bot.PermissionChecker;

/**
 * Created by SimplyRin on 2018/05/10
 *
 *  Copyright 2018 SimplyRin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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

		if(PermissionChecker.isAdmin(sender)) {

		}
	}

}
