package model;

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
		System.out.println(test.cryptAll(textNull, "ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
		assertEquals(textNull.toUpperCase(), test.cryptAll(textNull, "ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
	}
}
