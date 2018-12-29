package com.acodes.api.util;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Component;

@Component
public class Common {

	public String numReference() {
		return randomGenerator(10);
	}

	public String randomGenerator(int longueur) {
		RandomStringGenerator randomStringGenerator = new RandomStringGenerator.Builder().withinRange('0', 'z')
				.filteredBy(CharacterPredicates.LETTERS,CharacterPredicates.DIGITS).build();
		return randomStringGenerator.generate(longueur);
	}
}
