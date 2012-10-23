package org.sdmlib.serialization.bytes;

public class ByteConverterHex extends ByteConverter {

	/**
	 * To hex string.
	 *
	 * @param bytes the bytes
	 * @return the string
	 */
	@Override
	public String toString(byte[] values, int size) {
		String hexVal = "0123456789ABCDEF";

		StringBuilder returnValue = new StringBuilder(size*2);
		if(values!=null){
			for (int i = 0; i < size; i++) {
				int value=values[i];
				if(value<0){
					value+=256;
				}
				returnValue.append(""+hexVal.charAt(value/16)+hexVal.charAt(value%16));
			}
		}
		return returnValue.toString();
	}
	
	
	/**
	 * To byte string.
	 *
	 * @param hexString the hex string
	 * @return the byte[]
	 */
	@Override
	public byte[] decode(String value) {
		String hexVal = "0123456789ABCDEF";
		byte[] out = new byte[value.length() / 2];

		int n = value.length();

		for (int i = 0; i < n; i += 2) {
			// make a bit representation in an int of the hex value
			int hn = hexVal.indexOf(value.charAt(i));
			int ln = hexVal.indexOf(value.charAt(i + 1));

			// now just shift the high order nibble and add them together
			out[i / 2] = (byte) ((hn << 4) | ln);
		}
		return out;
	}

}
