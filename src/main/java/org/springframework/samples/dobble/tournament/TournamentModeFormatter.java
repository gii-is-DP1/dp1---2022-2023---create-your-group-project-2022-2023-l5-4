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
package org.springframework.samples.dobble.tournament;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class TournamentModeFormatter implements Formatter<TournamentMode> {

	private final TournamentService tournamentService;

	@Autowired
	public TournamentModeFormatter(TournamentService tournamentService) {
		this.tournamentService = tournamentService;
	}

	@Override
	public String print(TournamentMode tournamentMode, Locale locale) {
		return tournamentMode.getName();
	}

	@Override
	public TournamentMode parse(String text, Locale locale) throws ParseException {
		Iterable<TournamentMode> tournamentModes = this.tournamentService.findTournamentModes();
		for (TournamentMode tournamentmode : tournamentModes) {
			if (tournamentmode.getName().equals(text)) {
				return tournamentmode;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}

}
