package org.sdmlib.serialization.bytes;

public class ByteConverterString extends ByteConverter {

	/**
	 * To simple string.
	 *
	 * @param bytes the bytes
	 * @return the string
	 */
	@Override
	public String toString(byte[] values, int size) {
		StringBuilder returnValue = new StringBuilder(size);
		if(values!=null){
			for (int i = 0; i < size; i++) {
				returnValue.append((char)values[i]);
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
		byte[] out = new byte[value.length()];
		int n = value.length();

		for (int i = 0; i < n; i ++) {
			out[i] = (byte) value.charAt(i);
		}
		return out;
	}

}
