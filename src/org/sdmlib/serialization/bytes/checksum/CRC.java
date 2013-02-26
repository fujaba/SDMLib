package org.sdmlib.serialization.bytes.checksum;
/*
Copyright (c) 2013, Stefan Lindel
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
1. Redistributions of source code must retain the above copyright
   notice, this list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright
   notice, this list of conditions and the following disclaimer in the
   documentation and/or other materials provided with the distribution.
3. All advertising materials mentioning features or use of this software
   must display the following acknowledgement:
   This product includes software developed by Stefan Lindel.
4. Neither the name of contributors may be used to endorse or promote products
   derived from this software without specific prior written permission.

THIS SOFTWARE 'Json Id Serialisierung Map' IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL STEFAN LINDEL BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
public abstract class CRC extends Checksum{
	/** The fast CRC table. Computed once when the CRC32 class is loaded. */
	protected int[] crc_table = getGenTable();

	public abstract int getPolynom();
	public abstract boolean isReflect();

	/** Make the table for a fast CRC. */
	public int[] getGenTable() {
		int[] result=new int[256];
			
		int order = getOrder();
		long topBit = (long)1 << (order - 1);
		long widthMask = (((1 << (order - 1)) - 1) << 1) | 1;
		int polynom = getPolynom();
		boolean isReflect = isReflect();
		
		for (int i = 0; i < 256; ++i) 
        {
			result[i] = i;
			if (isReflect) { result[i] = Reflect(i, 8); }
			result[i] = result[i] << (order - 8);
            for (int j = 0; j < 8; ++j) 
            { 
                if ((result[i] & topBit) != 0) 
                { 
                	result[i] = (result[i] << 1) ^ polynom; 
                } 
                else 
                { 
                	result[i] <<= 1; 
                } 
            } 
            if (isReflect) { result[i] = Reflect(result[i], order); }
            result[i] &= widthMask;
        } 
		return result;
	}
	
	/// <summary>Reflects the lower bits of the value provided.</summary>
	/// <param name="data">The value to reflect.</param>
	/// <param name="numBits">The number of bits to reflect.</param>
	/// <returns>The reflected value.</returns>
	static private int Reflect(int data, int numBits) {
		int temp = data;

		for (int i = 0; i < numBits; i++) {
			long bitMask = (long)1 << ((numBits - 1) - i);

			if ((temp & (long)1) != 0) {
				data |= bitMask;
			} else {
				data &= ~bitMask;
			}

			temp >>= 1;
		}
		return data;
	}
}
