package net.simplyrin.mixerchat.bot;

import net.dv8tion.jda.core.entities.User;
import net.md_5.bungee.config.Configuration;

/**
 * Created by SimplyRin on 2018/05/11
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
public class PermissionChecker {

	public static boolean isAdmin(User user) {
		if(user == null) {
			return false;
		}

		Configuration config = Main.getConfig();
		return config.getBoolean("Discord.User." + user.getId() + ".Admin");
	}

}
