package org.sdmlib.models.modelsets;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Iterator;

import org.sdmlib.CGUtil;

import de.uniks.networkparser.list.SimpleIterator;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.interfaces.Condition;


public abstract class SDMSet<T> extends SimpleSet<T> implements ModelSet
{
   // Added to cach iterators. Minor speedup. Serious multi threading problems. Have to think about it, again. Albert
   private SimpleIterator oldIterator = null;

   //   public static long noOfIteratorRequests = 0;
   //   public static long noOfIteratorReuses = 0;

   @SuppressWarnings("unchecked")
   public <ST extends SDMSet> ST withReadOnly(boolean value)  {
	   if(value) {
		   withFlag(SimpleSet.READONLY);
	   }
	   return (ST)this;
   }
   
   //   @Override
   //   public Iterator<T> iterator()
   //   {
   //      // noOfIteratorRequests++;
   //
   //      // try to reuse old iterator 
   //      if (oldIterator == null || ! oldIterator.isReusable())
   //      {
   //         oldIterator = (SimpleIterator) super.iterator();
   //         return oldIterator;
   //      }
   //
   //      // may reuse old
   //      // noOfIteratorReuses++;
   //      oldIterator.withList(this);
   //      return oldIterator;
   //   }
   
   @Override
   public String toString()
   {
      StringList stringList = new StringList();
      
      for (T elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }
   
   
   public String toString(String separator)
   {
      StringList stringList = new StringList();
      
      for (T elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return stringList.concat(separator);
   }
   
   
   public <ST extends SDMSet> ST instanceOf(ST target)
   {
	   String className;
	   ParameterizedType genericSuperclass = (ParameterizedType) target.getClass().getGenericSuperclass();
	   if(genericSuperclass.getActualTypeArguments().length>0){
		   className = genericSuperclass.getActualTypeArguments()[0].getTypeName();
	   }else{
	      className = target.getClass().getName();
	      className = CGUtil.baseClassName(className, "Set");
	   }
      try
      {
         Class<?> targetClass = target.getClass().getClassLoader().loadClass(className);
         for (T elem : this)
         {
            if (targetClass.isAssignableFrom(elem.getClass()))
            {
               target.add(elem);
            }
         }
      }
      catch (ClassNotFoundException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return target;
   }
   
   public <ST extends SDMSet<T>> ST union(Collection<? extends T> other)
   {
      @SuppressWarnings("unchecked")
      ST result = (ST) this.getNewList(false);
      result.addAll(this);
      result.addAll(other);
      
      return result;
   }
   
   
   public <ST extends SDMSet<T>> ST intersection(Collection<? extends T> other)
   {
      @SuppressWarnings("unchecked")
      ST result = (ST) this.getNewList(false);
      result.addAll(this);
      result.retainAll(other);
      return result;
   }
   
   @Override
   @SuppressWarnings("unchecked")
   public SDMSet<T> getNewList(boolean keyValue) {
       SDMSet<T> result = null;
       try {
           result = this.getClass().newInstance();
       } catch (Exception e) {
           e.printStackTrace();
       }
       return result;
   }
   
   @SuppressWarnings("unchecked")
   public <ST extends SDMSet<T>> ST minus(Object other)
   {
      ST result = (ST) this.getNewList(false);
      result.addAll(this);
      
      if (other instanceof Collection)
      {
         result.removeAll((Collection<?>) other);
      }
      else
      {
         result.remove(other);
      }
      
      return result;
   }

   public <ST extends SDMSet<T>> ST has(Condition<T> condition)
   {
      @SuppressWarnings("unchecked")
	ST result = (ST) this.getNewList(false);
      result.addAll(this);
      
      for (T elem : this)
      {
         if ( ! condition.check(elem))
         {
            result.remove(elem);
         }
      };
      return result;
   }
   
//   @Override
//   public AbstractList<T> clone() {
//      return this.getNewInstance().with(this);
//   }
   
   public Iterator<T> cloneIterator() {
      return super.clone().iterator();
   }
   
	@Override
	public boolean remove(Object value) {
		return removeByObject(value) >= 0;
	}
	
	// ReadOnly Add all
	@Override
	public T set(int index, T element) {
		if (isReadOnly()) {
			throw new UnsupportedOperationException("set(" + index + ")");
		}
		return super.set(index, element);
	}

	@Override
	public void add(int index, T element) {
		if (isReadOnly()) {
			throw new UnsupportedOperationException("add(" + index + ")");
		}
		super.add(index, element);
	}

	@Override
	public T remove(int index) {
		if (isReadOnly()) {
			throw new UnsupportedOperationException("remove(" + index + ")");
		}
		return super.remove(index);
	}

	@Override
	public boolean add(T newValue) {
		if (isReadOnly()) {
			throw new UnsupportedOperationException("add()");
		}
		return super.add(newValue);
	}
}
