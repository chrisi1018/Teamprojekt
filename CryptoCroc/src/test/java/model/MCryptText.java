package model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testet das Monoalphabetische Verfahren
 * 
 * @author zes
 * @version 1.0
 */
public class MCryptText {

	private MCrypt test;

	private String textNull = "Lorem ipsum dolor sit amet, consetetur"
			+ " sadipscing elitr, sed diam nonumy eirmod tempor invidunt"
			+ " ut labore et dolore magna aliquyam erat, sed diam voluptua."
			+ " At vero eos et accusam et justo duo dolores et ea rebum. Stet"
			+ " clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor"
			+ " sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr,"
			+ " sed diam nonumy eirmod tempor invidunt ut labore et dolore magna"
			+ " aliquyam erat, sed diam voluptua. At vero eos et accusam et justo"
			+ " duo dolores et ea rebum. Stet clita kasd gubergren, no sea "
			+ "takimata sanctus est Lorem ipsum dolor sit amet.";

	private String textOne = "SGKTD OHLXD RGSGK LOY QDTY, EGFLTYTYXK LQROHLEOFU TSOYK, LTR"
			+ " ROQD FGFXDN TOKDGR YTDHGK OFCORXFY XY SQWGKT TY RGSGKT DQUFQ QSOJXNQD TKQY, "
			+ "LTR ROQD CGSXHYXQ. QY CTKG TGL TY QEEXLQD TY PXLYG RXG RGSGKTL TY TQ KTWXD. L"
			+ "YTY ESOYQ AQLR UXWTKUKTF, FG LTQ YQAODQYQ LQFEYXL TLY SGKTD OHLXD RGSGK LOY Q"
			+ "DTY. SGKTD OHLXD RGSGK LOY QDTY, EGFLTYTYXK LQROHLEOFU TSOYK, LTR ROQD FGFXDN"
			+ " TOKDGR YTDHGK OFCORXFY XY SQWGKT TY RGSGKT DQUFQ QSOJXNQD TKQY, LTR ROQD CGSXH"
			+ "YXQ. QY CTKG TGL TY QEEXLQD TY PXLYG RXG RGSGKTL TY TQ KTWXD. LYTY ESOYQ AQLR U"
			+ "XWTKUKTF, FG LTQ YQAODQYQ LQFEYXL TLY SGKTD OHLXD RGSGK LOY QDTY.";

	private String nonsense = "";

	/**
	 * Initalisiert den Test
	 */
	@BeforeEach
	void init() {
		this.test = new MCrypt();
	}

	/**
	 * Beendet den Test
	 */
	@AfterEach
	void remove() {
		this.test = null;
	}

	/**
	 * Testet ob Text gleichbleibt bei unveraendertem Schluessel
	 */
	@Test
	void noChange() {
		assertEquals(textNull.toUpperCase(), test.cryptAll(textNull, "ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
		assertEquals(textNull.toLowerCase(), test.cryptAll(textNull, "abcdefghijklmnopqrstuvwxyz"));
		assertEquals(textNull.toUpperCase(), test.decryptAll(textNull, "ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
		assertEquals(textNull.toUpperCase(), test.decryptAll(textNull, "abcdefghijklmnopqrstuvwxyz"));
	}

	/**
	 * Testet ob Text richtig entschluesselt wird
	 */
	@Test
	void decrypt() {
		assertEquals(textNull.toUpperCase(), test.decryptAll(textOne, "QWERTZUIOPASDFGHJKLYXCVBNM"));
	}

	/**
	 * Testet ob ein nicht gueltiger Schluessel als solchen erkannt wird
	 */
	@Test
	void invalidKey() {
		assertTrue(!test.checkKey("Q§E&(UGEB)OL=!2356tgd21qst"));
		assertTrue(test.checkKey("Q§E&(UGFB)OL=!#356tgd21qsp"));
	}
}
