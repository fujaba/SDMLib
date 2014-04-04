package org.sdmlib.serialization.gui.table;

/*
 NetworkParser
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
import java.util.Collection;
import java.util.Iterator;

public class ListIteratorImpl<T> implements java.util.ListIterator<T>
{
   private Collection<T> owner;
   private int index = 0;
   private Iterator<T> iterator = null;

   public ListIteratorImpl(Collection<T> owner)
   {
      this.owner = owner;
   }

   public Iterator<T> getInterator()
   {
      if (iterator == null)
      {
         iterator = owner.iterator();
      }
      return iterator;
   }

   @Override
   public boolean hasNext()
   {
      return iterator.hasNext();
   }

   @Override
   public T next()
   {
      index++;
      return iterator.next();
   }

   @Override
   public boolean hasPrevious()
   {
      return index > 0;
   }

   @Override
   public T previous()
   {
      T item = null;
      if (index > 0)
      {
         index--;
         iterator = owner.iterator();
         for (int z = 0; z < index; z++)
         {
            item = iterator.next();
         }
      }
      return item;
   }

   @Override
   public int nextIndex()
   {
      return index + 1;
   }

   @Override
   public int previousIndex()
   {
      return index - 1;
   }

   @Override
   public void remove()
   {
      iterator.remove();
   }

   @Override
   public void set(T e)
   {
      iterator.remove();
      owner.add(e);
   }

   @Override
   public void add(T e)
   {
      owner.add(e);
   }
}
