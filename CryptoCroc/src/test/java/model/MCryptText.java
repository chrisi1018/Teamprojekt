package model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testet das monoalphabetische Verfahren
 * 
 * @author zes
 * @version 1.0
 */
public class MCryptText {

	private MCrypt test;

	private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private String textOG = "Lorem ipsum dolor sit amet, consetetur"
			+ " sadipscing elitr, sed diam nonumy eirmod tempor invidunt"
			+ " ut labore et dolore magna aliquyam erat, sed diam voluptua."
			+ " At vero eos et accusam et justo duo dolores et ea rebum. Stet"
			+ " clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor"
			+ " sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr,"
			+ " sed diam nonumy eirmod tempor invidunt ut labore et dolore magna"
			+ " aliquyam erat, sed diam voluptua. At vero eos et accusam et justo"
			+ " duo dolores et ea rebum. Stet clita kasd gubergren, no sea "
			+ "takimata sanctus est Lorem ipsum dolor sit amet.";

	// mit qwertz verschluesselter Tekst
	private String textQWERTZ = "SGKTD OHLXD RGSGK LOY QDTY, EGFLTYTYXK LQROHLEOFU TSOYK, LTR"
			+ " ROQD FGFXDN TOKDGR YTDHGK OFCORXFY XY SQWGKT TY RGSGKT DQUFQ QSOJXNQD TKQY, "
			+ "LTR ROQD CGSXHYXQ. QY CTKG TGL TY QEEXLQD TY PXLYG RXG RGSGKTL TY TQ KTWXD. L"
			+ "YTY ESOYQ AQLR UXWTKUKTF, FG LTQ YQAODQYQ LQFEYXL TLY SGKTD OHLXD RGSGK LOY Q"
			+ "DTY. SGKTD OHLXD RGSGK LOY QDTY, EGFLTYTYXK LQROHLEOFU TSOYK, LTR ROQD FGFXDN"
			+ " TOKDGR YTDHGK OFCORXFY XY SQWGKT TY RGSGKT DQUFQ QSOJXNQD TKQY, LTR ROQD CGSXH"
			+ "YXQ. QY CTKG TGL TY QEEXLQD TY PXLYG RXG RGSGKTL TY TQ KTWXD. LYTY ESOYQ AQLR U"
			+ "XWTKUKTF, FG LTQ YQAODQYQ LQFEYXL TLY SGKTD OHLXD RGSGK LOY QDTY.";

	private String random = "";

	private String randomKey = "";

	/**
	 * Initalisiert den Test und erstellt einen willkuerlichen Text und Schluessel.
	 * Dabei wird beim Schluessel darauf geachtet, dass jeder Buchstabe nur einmal
	 * vorkommt, indem doppelt vorkommende Buchstaben ersetzt werden
	 */
	@BeforeEach
	void init() {
		this.test = new MCrypt();

		// willkuerlicher Text
		int leftLimit = 65;
		int rightLimit = 122;
		int targetStringLength = 100;
		Random randomizer = new Random();
		random = randomizer.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		// willkuerlicher Schluessel
		int leftKeyLimit = 65;
		int rightKeyLimit = 90;
		int targetKeyLength = 26;
		randomKey = randomizer.ints(leftKeyLimit, rightKeyLimit + 1).limit(targetKeyLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		// erstellt ein Array mit allen Buchstaben des Alphabets und streicht die raus,
		// // die im willkuerlichen Schluessel vorkommen
		String[] alph = test.convertKeyToArray(alphabet);
		for (int i = 0; i < alph.length; i++) {
			if (randomKey.contains(alph[i])) {
				alph[i] = "";
			}
		}

		// falls Buchstaben doppelt vorkommen ersetze zweites Vorkommen durch Buchstaben
		// // der noch nicht vorkam
		for (int i = 0; i < alphabet.length(); i++) { // geht nacheinander alle Buchstaben im Schluessel durch
			char c = randomKey.charAt(i);
			for (int j = i + 1; j < alphabet.length(); j++) {
				char b = randomKey.charAt(j);

				// schaut ob der gleiche Buchstabe nochmal vorkommt, falls ja ersetze das zweite
				// // Vorkommen durch den naechsten freien Buchstaben
				if (c == b) {
					int it = 0;
					while ((c == b) && it < alph.length) {
						if (!alph[it].equals("")) {
							b = alph[it].charAt(0);
							alph[it] = "";
						}
						it++;
					}

					// modifiziert den Schluessel, so dass doppeltes Vorkommen aus dem Schluessel
					// durch neuen Buchstaben ersetzt wird
					String rebuild = "";
					for (int k = 0; k < randomKey.length(); k++) {
						if (k == j) {
							rebuild += Character.toString(b);
						} else {
							rebuild += Character.toString(randomKey.charAt(k));
						}
					}
					randomKey = rebuild;
				}
			}
		}

	}

	/**
	 * Beendet den Test
	 */
	@AfterEach
	void remove() {
		this.test = null;
	}

	/**
	 * Testet ob ein nicht gueltiger Schluessel als solchen erkannt wird
	 */
	@Test
	void invalidKey() {
		assertTrue(!test.checkKey("Q§E&(UGEB)OL=!2356tgd21qst"));
		// 26 Leerzeichen
		assertTrue(!test.checkKey("                          "));
		assertTrue(!test.checkKey("AaBbCcDdEeFfGgHhIiJjKkLlMm"));
		assertTrue(!test.checkKey("AoihbUZmOIhmHBUizbOJBSiupqNCIPAjnPAIj"));

		// gueltig bis auf ein Zeichen
		assertTrue(!test.checkKey("pLmNkoIjbHUZgVCFtrDXYse aQ"));
	}

	/**
	 * Testet ob ein gueltiger Schluessel als solches erkannt wird
	 */
	@Test
	void validKey() {
		assertTrue(test.checkKey(alphabet));
		assertTrue(test.checkKey("QwErTzUIOpasDFGHjklYXCVBnM"));
		assertTrue(test.checkKey("pLmNkoIjbHUZgVCFtrDXYseWaQ"));
	}

	/**
	 * Testet ob Text gleichbleibt bei unveraendertem Schluessel
	 */

	@Test
	void noChange() {
		assertEquals(textOG.toUpperCase(), test.cryptAll(textOG, alphabet));
		assertEquals(textOG.toUpperCase(), test.cryptAll(textOG, "AbCDEfGHiJKLmnoPQRsTuVWxYz"));
		assertEquals(textOG.toUpperCase(), test.cryptAll(textOG, alphabet.toLowerCase()));
		assertEquals(textOG.toUpperCase(), test.decryptAll(textOG, alphabet));
		assertEquals(textOG.toUpperCase(), test.decryptAll(textOG, "AbCDEfGHiJKLmnoPQRsTuVWxYz"));
		assertEquals(textOG.toUpperCase(), test.decryptAll(textOG, alphabet.toLowerCase()));
	}

	/**
	 * Testet ob Text richtig entschluesselt wird
	 */

	@Test
	void decrypt() {
		assertEquals(textOG.toUpperCase(), test.decryptAll(textQWERTZ, "QWERTZUIOPASDFGHJKLYXCVBNM"));
		assertEquals(textOG.toUpperCase(), test.decryptAll(textQWERTZ, "qwertzuiopasdfghjklyxcvbnm"));
		assertEquals(textOG.toUpperCase(), test.decryptAll(textQWERTZ, "QwErTzUIOpasDFGHjklYXCVBnM"));
	}

	/**
	 * Testet wie die Verschluesselung mit Unicodezeichen umgeht
	 */
	@Test
	void unicode() {
		assertEquals("\u00C6\u00D8\u00C5", test.cryptAll("\u00C6\u00D8\u00C5", "pLmNkoIjbHUZgVCFtrDXYseWaQ"));
	}

	/**
	 * Testet Entschluesselung bei zufaelligem Text und Schluessel
	 */
	@Test
	void random() {
		String enc = test.cryptAll(random, randomKey);
		assertEquals(random.toUpperCase(), test.decryptAll(enc, randomKey));
	}

}
