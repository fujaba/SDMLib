package org.sdmlib.models.modelsets;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Iterator;

import org.sdmlib.CGUtil;

import de.uniks.networkparser.gui.ItemList;


public class SDMSetBase<T> extends ItemList<T>
{
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
      ST result = (ST) this.getNewInstance();
      result.addAll(this);
      result.addAll(other);
      
      return result;
   }
   
   
   public <ST extends SDMSetBase<T>> ST intersection(Collection<? extends T> other)
   {
      @SuppressWarnings("unchecked")
      ST result = (ST) this.getNewInstance();
      result.addAll(this);
      result.retainAll(other);
      return result;
   }
   
   
   @SuppressWarnings("unchecked")
   public <ST extends SDMSetBase<T>> ST minus(Object other)
   {
      ST result = (ST) this.getNewInstance();
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
	ST result = (ST) this.getNewInstance();
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
   public SDMSetBase<T> getNewInstance() 
   {
      SDMSetBase<T> result = null;
      try
      {
         result = this.getClass().newInstance();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return result;
   }

   @SuppressWarnings("unchecked")
   @Override
   public SDMSetBase<T> with(Object... values) {
      for(Object item : values){
         this.add((T) item);
      }
      return this;
   }
	@Override
	public boolean remove(Object value) {
		return removeByObject(value) >= 0;
	}
}
