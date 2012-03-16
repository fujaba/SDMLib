package org.sdmlib.serialization.bytemsg;

public class HexConverter {
	public static String toHexString(byte[] bytes) {
		if (bytes == null) {
			return null;
		}

		String ret = "";
		for (int i = 0; i < bytes.length; i++) {

			char b = (char) (bytes[i] & 0xFF);
			if (b < 0x10) {
				ret = ret + "0";
			}
			ret = ret + (String) (Integer.toHexString(b)).toUpperCase();
		}
		return ret;

	}

	public static byte[] toByteString(String hexString) {
		String hexVal = "0123456789ABCDEF";
		byte[] out = new byte[hexString.length() / 2];

		int n = hexString.length();

		for (int i = 0; i < n; i += 2) {
			// make a bit representation in an int of the hex value
			int hn = hexVal.indexOf(hexString.charAt(i));
			int ln = hexVal.indexOf(hexString.charAt(i + 1));

			// now just shift the high order nibble and add them together
			out[i / 2] = (byte) ((hn << 4) | ln);
		}

		return out;
	}
}
