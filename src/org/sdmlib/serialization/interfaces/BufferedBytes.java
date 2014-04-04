package org.sdmlib.serialization.interfaces;

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

public interface BufferedBytes extends Buffer
{
   public byte getByte();

   public short getShort();

   public long getLong();

   public int getInt();

   public float getFloat();

   public double getDouble();

   public byte[] getValue(int len);

   public byte[] getValue(int start, int len);

   public byte[] array();

   public void put(byte value);

   public void put(short value);

   public void put(int value);

   public void put(long value);

   public void put(byte[] value);

   public void put(char value);

   public void put(float value);

   public void put(double value);

   public void put(byte[] value, int offset, int length);

   public void flip();

   public BufferedBytes getNewBuffer(int capacity);

   public BufferedBytes getNewBuffer(byte[] array);

}
