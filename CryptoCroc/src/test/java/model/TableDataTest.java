package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Die Klasse testet die Ausgabe der Prozentwerte und das Verschieben von Buchstaben in den
 * Textfeldern.
 * 
 * @author chrisi
 * @version 1.0
 *
 */
public class TableDataTest {
	
	private TableData test;
	
	private float[] testFrequencyPercentage = {6.51f /*A*/, 1.89f /*B*/, 3.06f /*C*/, 5.08f /*D*/,
			17.4f /*E*/, 1.66f /*F*/, 3.01f /*G*/, 4.76f /*H*/, 7.55f /*I*/, 0.27f /*J*/,
			1.21f /*K*/, 3.44f /*L*/, 2.53f /*M*/, 9.78f /*N*/, 2.51f /*O*/, 0.79f /*P*/,
			0.02f /*Q*/, 7.0f /*R*/, 7.87f /*S*/, 6.15f /*T*/, 4.35f /*U*/, 0.67f /*V*/,
			1.89f /*W*/, 0.03f /*X*/, 0.04f /*Y*/, 1.13f /*Z*/};
	
	private float[] expectedFrequencyPercentage = {7.55f /*I*/, 1.89f /*B*/, 3.06f /*C*/, 5.08f /*D*/,
			17.4f /*E*/, 1.66f /*F*/, 3.01f /*G*/, 4.76f /*H*/, 6.51f /*A*/, 0.27f /*J*/,
			1.21f /*K*/, 3.44f /*L*/, 2.53f /*M*/, 9.78f /*N*/, 2.51f /*O*/, 0.79f /*P*/,
			0.02f /*Q*/, 7.0f /*R*/, 7.87f /*S*/, 6.15f /*T*/, 4.35f /*U*/, 0.67f /*V*/,
			1.89f /*W*/, 0.03f /*X*/, 0.04f /*Y*/, 1.13f /*Z*/};
	
	/**
	 * Initialisiere die Test-Klasse mit Prozentwerten und gebe den Textfeldern den Initialwert.
	 */
	@BeforeEach
	void init() {
		this.test = new TableData(this.testFrequencyPercentage);
		this.test.initTextFieldChar();
	}
	
	/**
	 * Loesche die Test-Klasse.
	 */
	@AfterEach
	void remove() {
		this.test = null;
	}
	
	/**
	 * Testet, ob die Textfelder richtig veraendert werden und ob sich die Prozentwerte mit aendern,
	 * wenn diese fuer den Graph ausgegeben werden sollen.
	 */
	@Test
	void testTextFieldCharAndGetForGraph() {
		this.test.setTextFieldChar('A', 8);
		this.test.setTextFieldChar('I', 0);
		
		assertEquals('A', this.test.getTextFieldChar(8));
		assertEquals('I', this.test.getTextFieldChar(0));
		assertArrayEquals(this.expectedFrequencyPercentage, this.test.getForGraph());
	}

}
