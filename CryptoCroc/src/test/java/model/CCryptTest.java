package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Random;

/**
 * Eine Testklasse fuer CCrypt
 * 
 * @author Julian Sturm
 * @version 1.0
 */
public class CCryptTest {

	private CCrypt test;

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

	private String textOne = "Mpsfn jqtvn epmps tju bnfu, dpotfufuvs" + " tbejqtdjoh fmjus, tfe ejbn opovnz fjsnpe"
			+ " ufnqps jowjevou vu mbcpsf fu epmpsf nbhob" + " bmjrvzbn fsbu, tfe ejbn wpmvquvb. Bu wfsp fpt"
			+ " fu bddvtbn fu kvtup evp epmpsft fu fb sfcvn." + " Tufu dmjub lbte hvcfshsfo, op tfb ubljnbub"
			+ " tboduvt ftu Mpsfn jqtvn epmps tju bnfu. " + "Mpsfn jqtvn epmps tju bnfu, dpotfufuvs tbejqtdjoh "
			+ "fmjus, tfe ejbn opovnz fjsnpe ufnqps jowjevou" + " vu mbcpsf fu epmpsf nbhob bmjrvzbn fsbu, tfe"
			+ " ejbn wpmvquvb. Bu wfsp fpt fu bddvtbn fu kvtup" + " evp epmpsft fu fb sfcvn. Tufu dmjub lbte"
			+ " hvcfshsfo, op tfb ubljnbub tboduvt ftu Mpsfn" + " jqtvn epmps tju bnfu.";

	private String textTwelve = "Xadqy ubegy paxad euf myqf" + ", oazeqfqfgd empubeouzs qxufd,"
			+ " eqp pumy zazgyk qudyap fqybad" + " uzhupgzf gf xmnadq qf paxadq ymszm"
			+ " mxucgkmy qdmf, eqp pumy haxgbfgm. Mf hqda" + " qae qf moogemy qf vgefa pga paxadqe qf qm"
			+ " dqngy. Efqf oxufm wmep sgnqdsdqz, za eqm fmwuymfm" + " emzofge qef Xadqy ubegy paxad euf myqf. Xadqy"
			+ " ubegy paxad euf myqf, oazeqfqfgd empubeouzs" + " qxufd, eqp pumy zazgyk qudyap fqybad uzhupgzf"
			+ " gf xmnadq qf paxadq ymszm mxucgkmy qdmf, eqp pumy" + " haxgbfgm. Mf hqda qae qf moogemy qf vgefa pga"
			+ " paxadqe qf qm dqngy. Efqf oxufm wmep sgnqdsdqz,"
			+ " za eqm fmwuymfm emzofge qef Xadqy ubegy paxad euf myqf.";

	private String textTwentyfive = "Knqdl hortl cnknq rhs zlds, bnmrdsdstq"
			+ " rzchorbhmf dkhsq, rdc chzl mnmtlx dhqlnc sdlonq hmuhctms"
			+ " ts kzanqd ds cnknqd lzfmz zkhptxzl dqzs, rdc chzl unktostz."
			+ " Zs udqn dnr ds zbbtrzl ds itrsn ctn cnknqdr ds dz qdatl."
			+ " Rsds bkhsz jzrc ftadqfqdm, mn rdz szjhlzsz rzmbstr drs Knqdl"
			+ " hortl cnknq rhs zlds. Knqdl hortl cnknq rhs zlds, bnmrdsdstq"
			+ " rzchorbhmf dkhsq, rdc chzl mnmtlx dhqlnc sdlonq hmuhctms ts"
			+ " kzanqd ds cnknqd lzfmz zkhptxzl dqzs, rdc chzl unktostz. Zs"
			+ " udqn dnr ds zbbtrzl ds itrsn ctn cnknqdr ds dz qdatl. Rsds"
			+ " bkhsz jzrc ftadqfqdm, mn rdz szjhlzsz rzmbstr drs Knqdl h" + "ortl cnknq rhs zlds.";

	/**
	 * Initalisiert den Test
	 */
	@BeforeEach
	void init() {
		this.test = new CCrypt();
	}

	/**
	 * Beendet den Test
	 */
	@AfterEach
	void remove() {
		this.test = null;
	}

	/**
	 * Testet die Caesarverschluesselung mit Buchstaben "A";
	 */
	@Test
	void fixedTestNullCrypt() {
		assertEquals(textNull, test.cryptAll(textNull, "A"));
	}

	/**
	 * Testet die Caesarentschluesselung mit Buchstaben "A";
	 */
	@Test
	void ficedTestNullDecrypt() {
		assertEquals(textNull, test.decryptAll(textNull, "A"));
	}

	/**
	 * Testet die Caesarverschluesselung mit Buchstaben "B";
	 */
	@Test
	void fixedTestOneCrypt() {
		assertEquals(textOne, test.cryptAll(textNull, "B"));
	}

	/**
	 * Testet die Caesarentschluesselung mit Buchstaben "B";
	 */
	@Test
	void fixedTestOneDecrypt() {
		assertEquals(textNull, test.decryptAll(textOne, "B"));
	}

	/**
	 * Testet die Caesarverschluesselung mit Buchstaben "M";
	 */
	@Test
	void fixedTestTwelveCrypt() {
		assertEquals(textTwelve, test.cryptAll(textNull, "M"));
	}

	/**
	 * Testet die Caesarentschluesselung mit Buchstaben "M";
	 */
	@Test
	void fixedTestTwelveDecrypt() {
		assertEquals(textNull, test.decryptAll(textTwelve, "M"));
	}

	/**
	 * Testet die Caesarverschluesselung mit Buchstaben "z";
	 */
	@Test
	void fixedTestTwentyfiveCrypt() {
		assertEquals(textTwentyfive, test.cryptAll(textNull, "z"));
	}

	/**
	 * Testet die Caesarentschluesselung mit Buchstaben "z";
	 */
	@Test
	void fixedTestTwentyfiveDecrypt() {
		assertEquals(textNull, test.decryptAll(textTwentyfive, "z"));
	}

	/**
	 * Testet wie die Verschluesselung mit Unicodezeichen umgeht.
	 */
	@Test
	void unicodeTest() {
		assertEquals("\u00C6\u00D8\u00C5", test.cryptAll("\u00C6\u00D8\u00C5", "L"));

	}

	/**
	 * Testet die Verschluesselung und Entschluesselung mit zufaelligem Text und
	 * zufaelligen Schluessel
	 */
	@Test
	void randomTest() {
		int leftLimit = 32;
		int rightLimit = 255;
		int targetStringLength = 100;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int j = 0; j < 100; j++) {
			random = new Random();
			buffer = new StringBuilder(targetStringLength);
			for (int i = 0; i < targetStringLength; i++) {
				int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
				buffer.append((char) randomLimitedInt);
			}
			String generatedString = buffer.toString();

			leftLimit = 65;
			rightLimit = 122;
			targetStringLength = 1;
			random = new Random();
			buffer = new StringBuilder(targetStringLength);
			for (int i = 0; i < targetStringLength; i++) {
				int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
				buffer.append((char) randomLimitedInt);
			}
			String key = buffer.toString();

			assertEquals(generatedString, test.decryptAll(test.cryptAll(generatedString, key), key));
		}
	}

	/**
	 * Testet die checkKey Funktion mit Funktionalem Schluessel
	 */
	@Test
	void keyTestRight() {
		assert (test.checkKey("A"));
		assert (test.checkKey("z"));
	}

	/**
	 * Testet die checkKey Funktion mit falschem Schluessel
	 */
	@Test
	void keyTestWrong() {
		assert (!test.checkKey("!"));
	}

	/**
	 * Testet die checkKey Funktion mit zu Langem Schluessel
	 */
	@Test
	void keyTestLong() {
		assert (!test.checkKey("bAHLgsdhadsgbbdjas"));
	}

	/**
	 * Testet die checkKey Funktion mit leerem String
	 */
	void keyTestNull() {
		assert (!test.checkKey(""));
	}

}
