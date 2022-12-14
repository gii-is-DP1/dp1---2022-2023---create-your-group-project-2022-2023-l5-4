/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.dobble.game;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class GameModeFormatter implements Formatter<GameMode> {

	private final GameService gameService;

	@Autowired
	public GameModeFormatter(GameService gameService) {
		this.gameService = gameService;
	}

	@Override
	public String print(GameMode gameMode, Locale locale) {
		return gameMode.getName();
	}

	@Override
	public GameMode parse(String text, Locale locale) throws ParseException {
		Collection<GameMode> gameModes = this.gameService.findGameModes();
		for (GameMode gamemode : gameModes) {
			if (gamemode.getName().equals(text)) {
				return gamemode;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}

}
