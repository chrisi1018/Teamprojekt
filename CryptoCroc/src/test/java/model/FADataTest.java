package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
/**
 * Die Klasse Testet den Algorythmus der Haufigkeitsanalyse
 * 
 * @author Julian
 * @version 1.0
 */
public class FADataTest {
	
	private int alphabetSize = 26;
	
	private String testTextOne = "Zwei flinke Boxer jagen die quirlige Eva"
			+ " und ihren Mops durch Sylt. Franz jagt im komplett verwahrlosten"
			+ " Taxi quer durch Bayern. Zwölf Boxkämpfer jagen Viktor quer über"
			+ " den großen Sylter Deich. Vogel Quax zwickt Johnys Pferd Bim."
			+ " Sylvia wagt quick den Jux bei Pforzheim. Polyfon zwitschernd"
			+ " aßen Mäxchens Vögel Rüben, Joghurt und Quark. \"Fix, Schwyz!\" "
			+ "quäkt Jürgen blöd vom Paß. Victor jagt zwölf Boxkämpfer quer "
			+ "über den großen Sylter Deich. Falsches Üben von Xylophonmusik quält"
			+ " jeden größeren Zwer";
	
	private String testTextTwo = "aäb cde fgh ijk lmn oöp qrsß tuü vwx yz AÄBC DEF"
			+ " GHI JKL MNO ÖPQ RST UÜV WXYZ !\"§ $%& /() =?* '<> #|; ²³~ @`´ ©«»"
			+ " ¼× {} aäb cde fgh ijk lmn oöp qrsß tuü vwx yz AÄBC DEF GHI JKL MNO"
			+ " ÖPQ RST UÜV WXYZ !\"§ $%& /() =?* '<> #|; ²³~ @`´ ©«» ¼× {} aäb cde"
			+ " fgh ijk lmn oöp qrsß tuü vwx yz AÄBC DEF GHI JKL MNO ÖPQ RST UÜV WXYZ"
			+ " !\"§ $%& /() =?* '<> #|; ²³~ @`´ ©«» ¼× {} aäb cde fgh ijk lmn oöp qrsß"
			+ " tuü vwx yz AÄBC DEF GHI JKL MNO ÖPQ RST UÜV WXYZ !\"§ $%& /() =?* '<> #|;"
			+ " ²³~ @`´ ©«» ¼× {} aäb cde fgh ijk lmn.";
	
	private int[] countOne = {21, 11, 11, 14, 61, 11, 13,
			14, 21, 9, 10, 17, 10, 26, 25, 9, 9, 32, 23, 17,
			21, 9, 9, 9, 9, 9, 430};
	
	private int[] countTwo = {18, 9, 9, 9, 34, 9, 9, 9,
			9, 9, 9, 9, 9, 9, 16, 8, 8, 8, 16, 8, 16, 8,
			8, 8, 8, 8, 280};
	
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
			perOne[0][i] = 100 * (countOne[i] / countOne[26]);
			perTwo[0][i] = 100 * (countTwo[i] / countTwo[26]);
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
	 * Löscht die pre-Arrays
	 */
	@AfterEach
	void removePre() {
		perOne = new float[1][alphabetSize];
		perTwo = new float[1][alphabetSize];
		perThree = new float[3][alphabetSize];
		perFour = new float[1][alphabetSize];
	}
	
	
	/**
	 * Testet ob das Array GERMAN 26 einträge hat.
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
	 * Testet den Algorythmus der Haeufigkeitsanalyse im Vergleich zu einem Online-Tool
	 * mit einer SchlüsselLänge von 1
	 */
	@Test
	void testAnalyseOne() {
		assertArrayEquals(perOne, FAData.analyse(testTextOne, 1));
		assertArrayEquals(perTwo, FAData.analyse(testTextTwo, 1));
	}
	
	/**
	 * Testet den Algorythmus der Haeufigkeitsanalyse im Vergleich zu einem Online-Tool
	 * mit einer SchlüsselLänge von 3
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
