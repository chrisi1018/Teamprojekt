package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Random;

/**
 * Eine TestKlasse fuer VCryptTest
 * 
 * @author Julian Sturm
 * @version 1.0
 */
public class VCryptTest {

	private VCrypt test;

	private String testText = "Lorem ipsum dolor sit amet," 
			+ " consectetuer adipiscing elit. Aenean commodo"
			+ " ligula eget dolor. Aenean massa. Cum sociis"
			+ " natoque penatibus et magnis dis parturient"
			+ " montes, nascetur ridiculus mus. Donec quam"
			+ " felis, ultricies nec, pellentesque eu, pretium"
			+ " quis, sem. Nulla consequat massa quis enim. Donec"
			+ " pede justo, fringilla vel, aliquet nec, vulputate"
			+ " eget, arcu. In enim justo, rhoncus ut, imperdiet a,"
			+ " venenatis vitae, justo. Nullam dictum felis eu pede"
			+ " mollis pretium. Integer tincidunt. Cras dapibus. Vivamus"
			+ " elementum semper nisi. Aenean vulputate eleifend"
			+ " tellus. Aenean leo ligula, porttitor eu, consequat"
			+ " vitae, eleifend ac, enim. Aliquam lorem ante, dapibus"
			+ " in, viverra quis, feugiat a, tellus. Phasellus viverra"
			+ " nulla ut metus varius laoreet. Quisque rutrum. Aenean"
			+ " imperdiet. Etiam ultricies nisi vel augue. Curabitur"
			+ " ullamcorper ultricies nisi. Nam eget dui. Etiam"
			+ " rhoncus. Maecenas tempus, tellus eget condimentum"
			+ " rhoncus, sem quam semper libero, sit amet adipiscing"
			+ " sem neque sed ipsum. Nam quam nunc, blandit vel,"
			+ " luctus pulvinar, hendrerit id, lorem. Maecenas nec"
			+ " odio et ante tincidunt tempus. Donec vitae sapien"
			+ " ut libero venenatis faucibus. Nullam quis ante."
			+ " Etiam sit amet orci eget eros faucibus tincidunt."
			+ " Duis leo. Sed fringilla mauris sit amet nibh. Donec"
			+ " sodales sagittis magna. Sed consequat, leo eget"
			+ " bibendum sodales, augue velit cursus nunc,";

	private String codeText = "Bkvvf hjaib dgotx zrd lkbv, xpaeuyxvmtyz"
			+ " osihlxipwq pjfv. Vfaqqj gfflilc aiyxqg lpoe blnjs."
			+ " Nqdaee fzmao. Rue vtiprc yyqqlvr bujekbaoa si"
			+ " msjsoz msd nxtoveuujx dhmnmg, cakfjzba btbfepmhe"
			+ " cqw. Uhmyk ejae ijrpb, ewrokxjre dag, gxkfmbiektzk ld"
			+ ", zccqkpn dgyo, wvf. Motzp cgqxkxdke kxunb dgyo iebl."
			+ " Xwbtc hhik qdcem, ctdotubhe mxk, utwfuww skj, eewnrvvur"
			+ " qwax, rkbo. Qb tnap oazcy, cflpxvf gj, eqgxqxqsi a,"
			+ " nhskujdtq skobr, vkoxf. Gtftob dafyat oowgp gp qrpu"
			+ " iscehm xfttaxr. Oucorco vdoputqrk. Vqua rppaezy. "
			+ "Crflkru zmryujxlf ryudtr flxo. Hnxpyk xpmcgjwxv xkyqttnv "
			+ "wjrsdc. Lckgvo yqe hmxnku, xcgtllyuy ne, nmkuzrhmj rmktd, "
			+ "ytsxfwqi gj, nxtk. Xndrhmc hsixl uvht, dssnhbb sy, tfxzsem "
			+ "gqmj, ydoowpt s, wjrsdc. Afxuzmygi rmmxqli bjldd zz tndfq "
			+ "scmjhe bwsixdn. Yixsixj xbcbfk. Xgifnz yitvkccmh. Ttadr"
			+ " ascbtafgn ovey ric ttacs. Rujdgoadb fjichdbdfav leslqqxek"
			+ " qnyp. Wkx cdgo ehu. Upmrf qbwbruk. Pfkjnxlq qghqhe, japcnr"
			+ " yosi cgqiotnxesj tcpaoko, wvf poia heesjx srlppl, udu nyup"
			+ " eubocaqxny vjs unafc pgy jceki. Rrf poia cuff, grhwntr sgg,"
			+ " mhojqw gnkpqbpr, zhsjynbtr ff, gpeqc. Ievvdhig ceu riov nd"
			+ " llqg ojaoyzyem syudjs. Vrskj eseyb uvqvqd qx cbayzc kefhsgarc"
			+ " qyredche. Dqpctl kcwh afwj. Karkx qfv vnrf engz xfyb sgok"
			+ " ifajrlfq qkidvpkjx. Unhm tsd. Swg kxpwqtjic hbhdyo wzm zgmh"
			+ " citk. Iuunm dmacgff eqcmkmhm uovns. Vjj jxxdcnwvu, yqe akvm"
			+ " acjscdmp xukjvpq, xwbvr huhmk vtlaih nmqh,";

	private String testkey = "qwertzuiopasdfghjklyxcvbnm";

	/**
	 * Initilisiert die Klasse in der die Verschluesselungmethoden gespeichert sind.
	 */
	@BeforeEach
	void init() {
		this.test = new VCrypt();
	}

	/**
	 * Loescht die Klasse in der die Verschluesselungmethoden gespeichert sind.
	 */
	@AfterEach
	void remove() {
		this.test = null;
	}

	/**
	 * Testet die Methode encryptAll und decrypt All mit einem BeispielText und
	 * BeispeilSchluessel
	 */
	void testCryptAll() {
		assertEquals(codeText, test.cryptAll(testText, testkey));
		assertEquals(testText, test.decryptAll(codeText, testkey));
	}

	/**
	 * Testet die checkKey-Methode, ob sie einen Korekten Schluessel richtig erkennt
	 */
	@Test
	void testCheckKeyTrue() {
		assert (test.checkKey("Test"));
	}

	/**
	 * Testet ob die checkKey-Methode richtig mit einem leeren Schluessel umgeht.
	 */
	@Test
	void testCheckKeyEmpty() {
		assert (!test.checkKey(""));
	}

	/**
	 * Testet wie die checkKey-Methode mit einigen falschen Schluesseln umgeht.
	 */
	@Test
	void testCheckKeyFalse() {
		assert (!test.checkKey("Das ist ein Test"));
		assert (!test.checkKey("Das ist ein Test!"));
		assert (!test.checkKey("awldk1j2\u00fci/][0e12uedwqd"));
		assert (!test.checkKey("2"));
		assert (!test.checkKey("\u00fc\u00f6\u00e4"));
		assert (!test.checkKey("[]"));
	}

	/**
	 * Testet die Verschluesselung und Entschluesselung mit zufaelligem Text und
	 * zufaelligen Schluessel
	 */
	@Test
	void randomTest() {
		int leftLimit = 32;
		int rightLimit = 255;
		int targetStringLength = 1000;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int j = 0; j < 100; j++) { //Schleife um den Test mehrfach durch zu fuehren
			random = new Random();
			buffer = new StringBuilder(targetStringLength);
			for (int i = 0; i < targetStringLength; i++) { //Schleife fuer Stringbuilder fuer den Random Text
				int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
				buffer.append((char) randomLimitedInt);
			}
			String generatedString = buffer.toString();

			leftLimit = 65;
			rightLimit = 122;
			targetStringLength = j + 1;
			random = new Random();
			buffer = new StringBuilder(targetStringLength);
			for (int i = 0; i < targetStringLength; i++) { //Schleife fuer Stringbuilder fuer den Schluessel
				int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
				buffer.append((char) randomLimitedInt);
			}
			String key = buffer.toString();

			assertEquals(generatedString, test.decryptAll(test.cryptAll(generatedString, key), key));
		}
	}
}
