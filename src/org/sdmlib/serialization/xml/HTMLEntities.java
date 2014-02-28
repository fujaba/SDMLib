package org.sdmlib.serialization.xml;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.
 
 Licensed under the EUPL, Version 1.1 or (as soon they
 will be approved by the European Commission) subsequent
 versions of the EUPL (the "Licence");
 You may not use this work except in compliance with the Licence.
 You may obtain a copy of the Licence at:

 http://ec.europa.eu/idabc/eupl5

 Unless required by applicable law or agreed to in
 writing, software distributed under the Licence is
 distributed on an "AS IS" basis,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 express or implied.
 See the Licence for the specific language governing
 permissions and limitations under the Licence.
*/
import java.util.HashMap;

public class HTMLEntities {
	/**
	 * Map to convert extended characters in html entities.
	 */
	private HashMap<Integer, String> htmlentities_map = new HashMap<Integer, String>();

	/**
	 * Map to convert html entities in exteden characters.
	 */
	private HashMap<String, Integer> unhtmlentities_map = new HashMap<String, Integer>();

	/**
	 * Initialize HTML translation maps.
	 */
	public HTMLEntities() {
		init();
	}

	private void addEntity(String value, int key) {
		this.htmlentities_map.put(Integer.valueOf(key), value);
		this.unhtmlentities_map.put(value, Integer.valueOf(key));
	}

	public void init() {
		addEntity("&Aacute;", 193);
		addEntity("&aacute;", 225);
		addEntity("&Acirc;", 194);
		addEntity("&acirc;", 226);
		addEntity("&acute;", 180);
		addEntity("&AElig;", 198);
		addEntity("&aelig;", 230);
		addEntity("&Agrave;", 192);
		addEntity("&agrave;", 224);
		addEntity("&alefsym;", 8501);
		addEntity("&Alpha;", 913);
		addEntity("&alpha;", 945);
		addEntity("&amp;", 38);
		addEntity("&and;", 8743);
		addEntity("&ang;", 8736);
		addEntity("&Aring;", 197);
		addEntity("&aring;", 229);
		addEntity("&asymp;", 8776);
		addEntity("&Atilde;", 195);
		addEntity("&atilde;", 227);
		addEntity("&Auml;", 196);
		addEntity("&auml;", 228);
		addEntity("&bdquo;", 8222);
		addEntity("&Beta;", 914);
		addEntity("&beta;", 946);
		addEntity("&brvbar;", 166);
		addEntity("&bull;", 8226);
		addEntity("&cap;", 8745);
		addEntity("&Ccedil;", 199);
		addEntity("&ccedil;", 231);
		addEntity("&cedil;", 184);
		addEntity("&cent;", 162);
		addEntity("&Chi;", 935);
		addEntity("&chi;", 967);
		addEntity("&circ;", 710);
		addEntity("&clubs;", 9827);
		addEntity("&cong;", 8773);
		addEntity("&copy;", 169);
		addEntity("&crarr;", 8629);
		addEntity("&cup;", 8746);
		addEntity("&curren;", 164);
		addEntity("&dagger;", 8224);
		addEntity("&Dagger;", 8225);
		addEntity("&darr;", 8595);
		addEntity("&dArr;", 8659);
		addEntity("&deg;", 176);
		addEntity("&Delta;", 916);
		addEntity("&delta;", 948);
		addEntity("&diams;", 9830);
		addEntity("&divide;", 247);
		addEntity("&Eacute;", 201);
		addEntity("&eacute;", 233);
		addEntity("&Ecirc;", 202);
		addEntity("&ecirc;", 234);
		addEntity("&Egrave;", 200);
		addEntity("&egrave;", 232);
		addEntity("&empty;", 8709);
		addEntity("&emsp;", 8195);
		addEntity("&ensp;", 8194);
		addEntity("&Epsilon;", 917);
		addEntity("&epsilon;", 949);
		addEntity("&equiv;", 8801);
		addEntity("&Eta;", 919);
		addEntity("&eta;", 951);
		addEntity("&ETH;", 208);
		addEntity("&eth;", 240);
		addEntity("&Euml;", 203);
		addEntity("&euml;", 235);
		addEntity("&euro;", 8364);
		addEntity("&exist;", 8707);
		addEntity("&fnof;", 402);
		addEntity("&forall;", 8704);
		addEntity("&frac12;", 189);
		addEntity("&frac14;", 188);
		addEntity("&frac34;", 190);
		addEntity("&frasl;", 8260);
		addEntity("&Gamma;", 915);
		addEntity("&gamma;", 947);
		addEntity("&ge;", 8805);
		addEntity("&harr;", 8596);
		addEntity("&hArr;", 8660);
		addEntity("&hearts;", 9829);
		addEntity("&hellip;", 8230);
		addEntity("&Iacute;", 205);
		addEntity("&iacute;", 237);
		addEntity("&Icirc;", 206);
		addEntity("&icirc;", 238);
		addEntity("&iexcl;", 161);
		addEntity("&Igrave;", 204);
		addEntity("&igrave;", 236);
		addEntity("&image;", 8465);
		addEntity("&infin;", 8734);
		addEntity("&int;", 8747);
		addEntity("&Iota;", 921);
		addEntity("&iota;", 953);
		addEntity("&iquest;", 191);
		addEntity("&isin;", 8712);
		addEntity("&Iuml;", 207);
		addEntity("&iuml;", 239);
		addEntity("&Kappa;", 922);
		addEntity("&kappa;", 954);
		addEntity("&Lambda;", 923);
		addEntity("&lambda;", 955);
		addEntity("&lang;", 9001);
		addEntity("&laquo;", 171);
		addEntity("&larr;", 8592);
		addEntity("&lArr;", 8656);
		addEntity("&lceil;", 8968);
		addEntity("&ldquo;", 8220);
		addEntity("&le;", 8804);
		addEntity("&lfloor;", 8970);
		addEntity("&lowast;", 8727);
		addEntity("&loz;", 9674);
		addEntity("&lrm;", 8206);
		addEntity("&lsaquo;", 8249);
		addEntity("&lsquo;", 8216);
		addEntity("&macr;", 175);
		addEntity("&mdash;", 8212);
		addEntity("&micro;", 181);
		addEntity("&middot;", 183);
		addEntity("&minus;", 8722);
		addEntity("&Mu;", 924);
		addEntity("&mu;", 956);
		addEntity("&nabla;", 8711);
		addEntity("&nbsp;", 160);
		addEntity("&ndash;", 8211);
		addEntity("&ne;", 8800);
		addEntity("&ni;", 8715);
		addEntity("&not;", 172);
		addEntity("&notin;", 8713);
		addEntity("&nsub;", 8836);
		addEntity("&Ntilde;", 209);
		addEntity("&ntilde;", 241);
		addEntity("&Nu;", 925);
		addEntity("&nu;", 957);
		addEntity("&Oacute;", 211);
		addEntity("&oacute;", 243);
		addEntity("&Ocirc;", 212);
		addEntity("&ocirc;", 244);
		addEntity("&OElig;", 338);
		addEntity("&oelig;", 339);
		addEntity("&Ograve;", 210);
		addEntity("&ograve;", 242);
		addEntity("&oline;", 8254);
		addEntity("&Omega;", 937);
		addEntity("&omega;", 969);
		addEntity("&Omicron;", 927);
		addEntity("&omicron;", 959);
		addEntity("&oplus;", 8853);
		addEntity("&or;", 8744);
		addEntity("&ordf;", 170);
		addEntity("&ordm;", 186);
		addEntity("&Oslash;", 216);
		addEntity("&oslash;", 248);
		addEntity("&Otilde;", 213);
		addEntity("&otilde;", 245);
		addEntity("&otimes;", 8855);
		addEntity("&Ouml;", 214);
		addEntity("&ouml;", 246);
		addEntity("&para;", 182);
		addEntity("&part;", 8706);
		addEntity("&permil;", 8240);
		addEntity("&perp;", 8869);
		addEntity("&Phi;", 934);
		addEntity("&phi;", 966);
		addEntity("&Pi;", 928);
		addEntity("&pi;", 960);
		addEntity("&piv;", 982);
		addEntity("&plusmn;", 177);
		addEntity("&pound;", 163);
		addEntity("&prime;", 8242);
		addEntity("&Prime;", 8243);
		addEntity("&prod;", 8719);
		addEntity("&prop;", 8733);
		addEntity("&Psi;", 936);
		addEntity("&psi;", 968);
		addEntity("&radic;", 8730);
		addEntity("&rang;", 9002);
		addEntity("&raquo;", 187);
		addEntity("&rarr;", 8594);
		addEntity("&rArr;", 8658);
		addEntity("&rceil;", 8969);
		addEntity("&rdquo;", 8221);
		addEntity("&real;", 8476);
		addEntity("&reg;", 174);
		addEntity("&rfloor;", 8971);
		addEntity("&Rho;", 929);
		addEntity("&rho;", 961);
		addEntity("&rlm;", 8207);
		addEntity("&rsaquo;", 8250);
		addEntity("&rsquo;", 8217);
		addEntity("&sbquo;", 8218);
		addEntity("&Scaron;", 352);
		addEntity("&scaron;", 353);
		addEntity("&sdot;", 8901);
		addEntity("&sect;", 167);
		addEntity("&shy;", 173);
		addEntity("&Sigma;", 931);
		addEntity("&sigma;", 963);
		addEntity("&sigmaf;", 962);
		addEntity("&sim;", 8764);
		addEntity("&spades;", 9824);
		addEntity("&sub;", 8834);
		addEntity("&sube;", 8838);
		addEntity("&sum;", 8721);
		addEntity("&sup1;", 185);
		addEntity("&sup2;", 178);
		addEntity("&sup3;", 179);
		addEntity("&sup;", 8835);
		addEntity("&supe;", 8839);
		addEntity("&szlig;", 223);
		addEntity("&Tau;", 932);
		addEntity("&tau;", 964);
		addEntity("&there4;", 8756);
		addEntity("&Theta;", 920);
		addEntity("&theta;", 952);
		addEntity("&thetasym;", 977);
		addEntity("&thinsp;", 8201);
		addEntity("&THORN;", 222);
		addEntity("&thorn;", 254);
		addEntity("&tilde;", 732);
		addEntity("&times;", 215);
		addEntity("&trade;", 8482);
		addEntity("&Uacute;", 218);
		addEntity("&uacute;", 250);
		addEntity("&uarr;", 8593);
		addEntity("&uArr;", 8657);
		addEntity("&Ucirc;", 219);
		addEntity("&ucirc;", 251);
		addEntity("&Ugrave;", 217);
		addEntity("&ugrave;", 249);
		addEntity("&uml;", 168);
		addEntity("&upsih;", 978);
		addEntity("&Upsilon;", 933);
		addEntity("&upsilon;", 965);
		addEntity("&Uuml;", 220);
		addEntity("&uuml;", 252);
		addEntity("&weierp;", 8472);
		addEntity("&Xi;", 926);
		addEntity("&xi;", 958);
		addEntity("&Yacute;", 221);
		addEntity("&yacute;", 253);
		addEntity("&yen;", 165);
		addEntity("&yuml;", 255);
		addEntity("&Yuml;", 376);
		addEntity("&Zeta;", 918);
		addEntity("&zeta;", 950);
		addEntity("&zwj;", 8205);
		addEntity("&zwnj;", 8204);

		// extra data
		addEntity("&lt;", 60);
		addEntity("&gt;", 62);

	}

	/**
	 * Convert special and extended characters into HTML entitities.
	 * @param str
	 *            input string
	 * @return formatted string
	 * @see #unhtmlentities(String)
	 */
	public String encode(String str) {

		if (str == null) {
			return "";
		}

		StringBuilder buf = new StringBuilder(); // the otput string buffer

		for (int i = 0; i < str.length(); ++i) {
			char ch = str.charAt(i);
			String entity = this.htmlentities_map.get(Integer.valueOf(ch)); // get equivalent html entity
			if (entity == null) { // if entity has not been found
				if (ch > 128) { // check if is an extended character
					buf.append("&#" + ((int) ch) + ";"); // convert extended
															// character
				} else {
					buf.append(ch); // append the character as is
				}
			} else {
				buf.append(entity); // append the html entity
			}
		}
		return buf.toString();
	}

	/**
	 * Convert HTML entities to special and extended unicode characters
	 * equivalents.
	 * 
	 * @param str
	 *            input string
	 * @return formatted string
	 * @see #htmlentities(String)
	 */
	public String decode(String str) {
		StringBuilder buf = new StringBuilder();

		for (int i = 0; i < str.length(); ++i) {
			char ch = str.charAt(i);
			if (ch == '&') {
				int semi = str.indexOf(';', i + 1);
				if ((semi == -1) || ((semi - i) > 7)) {
					buf.append(ch);
					continue;
				}
				String entity = str.substring(i, semi + 1);
				Integer iso;
				if (entity.charAt(1) == ' ') {
					buf.append(ch);
					continue;
				}
				if (entity.charAt(1) == '#') {
					if (entity.charAt(2) == 'x') {
						iso = Integer.valueOf(
								entity.substring(3, entity.length() - 1), 16);
					} else {
						iso = Integer.valueOf(entity.substring(2,
								entity.length() - 1));
					}
				} else {
					iso = this.unhtmlentities_map.get(entity);
				}
				if (iso == null) {
					buf.append(entity);
				} else {
					buf.append((char) (iso.intValue()));
				}
				i = semi;
			} else {
				buf.append(ch);
			}
		}
		return buf.toString();
	}

	// methods to convert special characters

	/**
	 * Replace single quotes characters with HTML entities.
	 * 
	 * @param str
	 *            the input string
	 * @return string with replaced single quotes
	 */
	public static String htmlSingleQuotes(String str) {
		String s = str.replaceAll("[\']", "&rsquo;");
		s = s.replaceAll("&#039;", "&rsquo;");
		s = s.replaceAll("&#145;", "&rsquo;");
		s = s.replaceAll("&#146;", "&rsquo;");
		return s;
	}

	/**
	 * Replace single quotes HTML entities with equivalent character.
	 * 
	 * @param str
	 *            the input string
	 * @return string with replaced single quotes
	 */
	public static String unhtmlSingleQuotes(String str) {
		return str.replaceAll("&rsquo;", "\'");
	}

	/**
	 * Replace double quotes characters with HTML entities.
	 * 
	 * @param str
	 *            the input string
	 * @return string with replaced double quotes
	 */
	public static String htmlDoubleQuotes(String str) {
		return str.replaceAll("[\"]", "&quot;").replaceAll("&#147;", "&quot;")
				.replaceAll("&#148;", "&quot;");
	}

	/**
	 * Replace single quotes HTML entities with equivalent character.
	 * 
	 * @param str
	 *            the input string
	 * @return string with replaced single quotes
	 */
	public static String unhtmlDoubleQuotes(String str) {
		return str.replaceAll("&quot;", "\"");
	}

	/**
	 * Replace &amp; characters with &amp;amp; HTML entities.
	 * 
	 * @param str
	 *            the input string
	 * @return string with replaced characters
	 */
	public static String htmlAmpersand(String str) {
		return str.replaceAll("&", "&amp;");
	}

	/**
	 * Replace &amp;amp; HTML entities with &amp; characters.
	 * 
	 * @param str
	 *            the input string
	 * @return string with replaced entities
	 */
	public static String unhtmlAmpersand(String str) {
		return str.replaceAll("&amp;", "&");
	}
}
