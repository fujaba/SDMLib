package org.sdmlib.serialization.bytes.checksum;

/*
 Json Id Serialisierung Map
 Copyright (c) 2011 - 2013, Stefan Lindel
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

 THE SOFTWARE 'AS IS' IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
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

public class Crc32 extends CRC{
/** Make the table for a fast CRC. */
	public int[] getGenTable() {
		int[] crc_table = new int[256];
		for (int n = 0; n < 256; n++) {
			int c = n;
			for (int k = 8; --k >= 0;) {
				if ((c & 1) != 0)
					c = 0xedb88320 ^ (c >>> 1);
				else
					c = c >>> 1;
			}
			crc_table[n] = c;
		}
		return crc_table;
	}

	public void update(int bval) {
		super.update(bval);
		int c = (int) ~value;
		c = crc_table[(c ^ bval) & 0xff] ^ (c >>> 8);
		value = ~c;
	}

	@Override
	public int getPolynom() {
		return 0;
	}

	@Override
	public boolean isReflect() {
		return false;
	}

	@Override
	public int getOrder() {
		return 32;
	}
}
