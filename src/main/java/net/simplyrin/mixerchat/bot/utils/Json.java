package net.simplyrin.mixerchat.bot.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
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
public class Json {

	public static void saveJson(JsonHolder json, String path) {
		saveJson(json, new File(path));
	}

	public static void saveJson(JsonHolder json, File file) {
		FileWriter filewriter;
		try {
			filewriter = new FileWriter(file);
			filewriter.write(json.toString());
			filewriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static JsonHolder getJson(String path) {
		return getJson(new File(path));
	}

	public static JsonHolder getJson(File file) {
		try {
			return new JsonHolder(Files.lines(Paths.get(file.getPath()), Charset.defaultCharset()).collect(Collectors.joining(System.getProperty("line.separator"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static JsonHolder loadJson(String path) {
		return getJson(new File(path));
	}

	public static JsonHolder loadJson(File file) {
		return getJson(file);
	}

}
