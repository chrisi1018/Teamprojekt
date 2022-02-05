package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Die Klasse Testet den Algorithmus der Haeufigkeitsanalyse
 * 
 * @author Julian
 * @version 1.0
 */
public class FADataTest {

	private int alphabetSize = 26;

	private String testTextOne = "Zwei flinke Boxer jagen die quirlige Eva"
			+ " und ihren Mops durch Sylt. Franz jagt im komplett verwahrlosten"
			+ " Taxi quer durch Bayern. Zw\u00f6lf Boxk\u00e4mpfer jagen Viktor quer \u00fcber"
			+ " den gro\u00dfen Sylter Deich. Vogel Quax zwickt Johnys Pferd Bim."
			+ " Sylvia wagt quick den Jux bei Pforzheim. Polyfon zwitschernd"
			+ " a\u00dfen M\u00e4xchens V\u00f6gel R\u00fcben, Joghurt und Quark. \"Fix, Schwyz!\" "
			+ "qu\u00e4kt J\u00fcrgen bl\u00f6d vom Pa\u00df. Victor jagt zw\u00f6lf Boxk\u00e4mpfer quer "
			+ "\u00fcber den gro\u00dfen Sylter Deich. Falsches \u00fcben von Xylophonmusik qu\u00e4lt"
			+ " jeden gr\u00f6\u00dferen Zwer";

	private String testTextTwo = "a\u00e4b cde fgh ijk lmn o\u00f6p qrs\u00df tu\u00fc vwx yz A\u00c4BC DEF"
			+ " GHI JKL MNO \u00d6PQ RST U\u00dcV WXYZ !\"\u00a7 $%& /() =?* '<> #|; \u00b2\u00b3~ @`\u00b4 "
			+ "\u00a9\u00ab\u00bb"
			+ " \u00bc\u00d7 {} a\u00e4b cde fgh ijk lmn o\u00f6p qrs\u00df tu\u00fc vwx yz A\u00c4BC DEF GHI JKL MNO"
			+ " \u00d6PQ RST U\u00dcV WXYZ !\"\u00a7 $%& /() =?* '<> #|; \u00b2\u00b3~ @`\u00b4 \u00a9\u00ab\u00bb "
			+ "\u00bc\u00d7 {} a\u00e4b cde"
			+ " fgh ijk lmn o\u00f6p qrs\u00df tu\u00fc vwx yz A\u00c4BC DEF GHI JKL MNO \u00d6PQ RST U\u00dcV WXYZ"
			+ " !\"\u00a7 $%& /() =?* '<> #|; \u00b2\u00b3~ @`\u00b4 \u00a9\u00ab\u00bb \u00bc\u00d7 {} a\u00e4b cde "
			+ "fgh ijk lmn o\u00f6p qrs\u00df"
			+ " tu\u00fc vwx yz A\u00c4BC DEF GHI JKL MNO \u00d6PQ RST U\u00dcV WXYZ !\"\u00a7 $%& /() =?* '<> #|;"
			+ " \u00b2\u00b3~ @`\u00b4 \u00a9\u00ab\u00bb \u00bc\u00d7 {} a\u00e4b cde fgh ijk lmn.";

	private int[] countOne = { 21, 11, 11, 14, 61, 11, 13, 14, 21, 9, 10, 17, 10, 26, 25, 9, 9, 32, 23, 17, 21, 9, 9, 9,
			9, 9, 430 };

	private int[] countTwo = { 18, 9, 9, 9, 34, 9, 9, 9, 9, 9, 9, 9, 9, 9, 16, 8, 8, 8, 16, 8, 16, 8, 8, 8, 8, 8, 280 };

	private String testTextThree = "abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabc";

	private float[][] perOne = new float[1][alphabetSize];
	private float[][] perTwo = new float[1][alphabetSize];
	private float[][] perThree = new float[3][alphabetSize];
	private float[][] perFour = new float[1][alphabetSize];

	/**
	 * Initialisiert die per-Arrays
	 */
	@BeforeEach
	void initPer() {
		for (int i = 0; i < alphabetSize; i++) {
			perOne[0][i] = 100 * ((float) countOne[i] / countOne[26]);
			perTwo[0][i] = 100 * ((float) countTwo[i] / countTwo[26]);
			perThree[0][i] = 0.0f;
			perThree[1][i] = 0.0f;
			perThree[2][i] = 0.0f;
			perFour[0][i] = 0.0f;
		}
		perThree[0][0] = 100.0f;
		perThree[1][1] = 100.0f;
		perThree[2][2] = 100.0f;
	}

	/**
	 * Loescht die pre-Arrays
	 */
	@AfterEach
	void removePre() {
		perOne = new float[1][alphabetSize];
		perTwo = new float[1][alphabetSize];
		perThree = new float[3][alphabetSize];
		perFour = new float[1][alphabetSize];
	}

	/**
	 * Testet ob das Array GERMAN 26 eintraege hat.
	 */
	@Test
	void testGermanLength() {
		assertEquals(alphabetSize, FAData.GERMAN.length);
	}

	/**
	 * Testet ob sich die Prozentzahlen in GERMAN zu ca. 100% addieren
	 */
	@Test
	void testGERMANPercent() {
		float count = 0.0f;
		for (int j = 0; j < alphabetSize; j++) {
			count = count + FAData.GERMAN[j];
		}
		assert (99.0f < count && count < 101.0f);
	}

	/**
	 * Testet den Algorithmus der Haeufigkeitsanalyse im Vergleich zu einem
	 * Online-Tool mit einer Schluessel Laenge von 1
	 */
	@Test
	void testAnalyseOne() {
		assertArrayEquals(perOne, FAData.analyse(testTextOne, 1));
		assertArrayEquals(perTwo, FAData.analyse(testTextTwo, 1));
	}

	/**
	 * Testet den Algorythmus der Haeufigkeitsanalyse im Vergleich zu einem
	 * Online-Tool mit einer Schluessel Laenge von 3
	 */
	@Test
	void testAnalyseThree() {
		assertArrayEquals(perThree, FAData.analyse(testTextThree, 3));
	}

	/**
	 * Testet den Umgang mit einem Leeren String
	 */
	@Test
	void testAnalyseArrayEmpty() {
		assertArrayEquals(perFour, FAData.analyse("", 1));
	}

}
