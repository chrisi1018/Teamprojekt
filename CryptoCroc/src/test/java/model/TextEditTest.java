package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Eine Testklasse für die Klasse TextEdit
 * 
 * @author Julian
 * @version 1.0
 */
public class TextEditTest {
	
	/**
	 * Ein Test String
	 */
	private static String testText = "Angesichts Namens F\u00fcrsorge erf\u00fcllt er,"
			+ " ihn etwas will an beinahe hatte einzigen auch daß haben, daß"
			+ " nicht ihm ervielleicht man Handschuhe wom\u00f6glich auf meisten"
			+ " ihm, die seine von bei so abgegangen zwar, seiner zueinander"
			+ " immer erkannte den in. Er sich fand seine Fr\u00fchst\u00fcck zuvor,"
			+ " wobei fand H\u00e4nden streckte nat\u00fcrlich gekr\u00e4nkt die Deutschland"
			+ " diesem, vielleicht standen hatten Besseres Mann, wozu von ging"
			+ " ervielleicht f\u00fcr Gewohnheit, wie sie der vom Gel\u00e4chter der. Es"
			+ " setzte den Unterschied sagte. Doch Gesellschaft neuen begreifend"
			+ " Vielfalt, den Zimmer daß man zu dem, sagte Frau verleumdet der"
			+ " Erl\u00f6s erreichte Internet. Die zu das Medien wie, was T\u00fcr verwaschenen.";
	
	/**
	 * Der Test String in Großbuchstaben
	 */
	private static String expectedTextOne = "ANGESICHTS NAMENS F\u00dcRSORGE ERF\u00dcLLT ER, IHN"
			+ " ETWAS WILL AN BEINAHE HATTE EINZIGEN AUCH DAß HABEN, DAß NICHT IHM"
			+ " ERVIELLEICHT MAN HANDSCHUHE WOM\u00d6GLICH AUF MEISTEN IHM, DIE SEINE VON"
			+ " BEI SO ABGEGANGEN ZWAR, SEINER ZUEINANDER IMMER ERKANNTE DEN IN. ER"
			+ " SICH FAND SEINE FR\u00dcHST\u00dcCK ZUVOR, WOBEI FAND H\u00c4NDEN STRECKTE NAT\u00dcRLICH"
			+ " GEKR\u00c4NKT DIE DEUTSCHLAND DIESEM, VIELLEICHT STANDEN HATTEN BESSERES MANN"
			+ ", WOZU VON GING ERVIELLEICHT F\u00dcR GEWOHNHEIT, WIE SIE DER VOM GEL\u00c4CHTER DER."
			+ " ES SETZTE DEN UNTERSCHIED SAGTE. DOCH GESELLSCHAFT NEUEN BEGREIFEND"
			+ " VIELFALT, DEN ZIMMER DAß MAN ZU DEM, SAGTE FRAU VERLEUMDET DER ERL\u00d6S"
			+ " ERREICHTE INTERNET. DIE ZU DAS MEDIEN WIE, WAS T\u00dcR VERWASCHENEN.";
	
	/**
	 * Der Test String in Großbuchstaben und ohne Umlaute
	 */
	private static String expectedTextTwo = "ANGESICHTS NAMENS FUERSORGE ERFUELLT ER, IHN"
			+ " ETWAS WILL AN BEINAHE HATTE EINZIGEN AUCH DASS HABEN, DASS NICHT IHM"
			+ " ERVIELLEICHT MAN HANDSCHUHE WOMOEGLICH AUF MEISTEN IHM, DIE SEINE VON"
			+ " BEI SO ABGEGANGEN ZWAR, SEINER ZUEINANDER IMMER ERKANNTE DEN IN. ER"
			+ " SICH FAND SEINE FRUEHSTUECK ZUVOR, WOBEI FAND HAENDEN STRECKTE NATUERLICH"
			+ " GEKRAENKT DIE DEUTSCHLAND DIESEM, VIELLEICHT STANDEN HATTEN BESSERES MANN"
			+ ", WOZU VON GING ERVIELLEICHT FUER GEWOHNHEIT, WIE SIE DER VOM GELAECHTER DER."
			+ " ES SETZTE DEN UNTERSCHIED SAGTE. DOCH GESELLSCHAFT NEUEN BEGREIFEND"
			+ " VIELFALT, DEN ZIMMER DASS MAN ZU DEM, SAGTE FRAU VERLEUMDET DER ERLOES"
			+ " ERREICHTE INTERNET. DIE ZU DAS MEDIEN WIE, WAS TUER VERWASCHENEN.";
	
	/**
	 * Testet die Methode changeUmlauts, also die Umwandlung von Umlauten
	 */
	@Test
	void testChangeUmlauts() {
		assertEquals(TextEdit.changeUmlauts('\u00c4'), "Ae");
		assertEquals(TextEdit.changeUmlauts('\u00e4'), "ae");
		assertEquals(TextEdit.changeUmlauts('\u00D6'), "Oe");
		assertEquals(TextEdit.changeUmlauts('\u00f6'), "oe");
		assertEquals(TextEdit.changeUmlauts('\u00dc'), "Ue");
		assertEquals(TextEdit.changeUmlauts('\u00fc'), "ue");
		assertEquals(TextEdit.changeUmlauts('\u00df'), "ss");
		assertEquals(TextEdit.changeUmlauts('o'), "o");
		assertEquals(TextEdit.changeUmlauts('.'), ".");
	}
	
	/**
	 * Testet die Methode "char changeToCpaitalLetter(Char c)"
	 */
	@Test
	void testCapitalOne() {
		assertEquals(TextEdit.changeToCapitalLetter('a'), 'A');
		assertEquals(TextEdit.changeToCapitalLetter('n'), 'N');
		assertEquals(TextEdit.changeToCapitalLetter('z'), 'Z');
		assertEquals(TextEdit.changeToCapitalLetter('A'), 'A');
		assertEquals(TextEdit.changeToCapitalLetter('N'), 'N');
		assertEquals(TextEdit.changeToCapitalLetter('Z'), 'Z');
		assertEquals(TextEdit.changeToCapitalLetter('.'), '.');
		assertEquals(TextEdit.changeToCapitalLetter('\u00e4'), '\u00c4'); //ä -->Ä
		assertEquals(TextEdit.changeToCapitalLetter('\u00f6'), '\u00d6'); //ö -->Ö
		assertEquals(TextEdit.changeToCapitalLetter('\u00fc'), '\u00dc'); //ü -->Ü
	}
	
	/**
	 * Testet die Methode "String change ToCapitalLetter(String text)
	 */
	@Test
	void testCapitalTwo() {
		assertEquals(expectedTextOne, TextEdit.changeToCapitalLetter(testText));
		assertEquals("", TextEdit.changeToCapitalLetter(""));
	}
	
	/**
	 * Testet die Methide editText
	 */
	@Test
	void testEditText() {
		assertEquals(expectedTextTwo, TextEdit.editText(testText));
		assertEquals("", TextEdit.editText(""));
	}
}
