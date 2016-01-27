package org.sdmlib.models.modelsets;

import java.lang.reflect.ParameterizedType;

import org.sdmlib.CGUtil;

import de.uniks.networkparser.list.SimpleSet;


public abstract class SDMSet<T> extends SimpleSet<T> {

   
   public <ST extends SDMSet<?>> ST instanceOf(ST target)
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
               target.withAll(elem);
            }
         }
      }
      catch (ClassNotFoundException e) {
      }
      return target;
   }
   
//   public <ST extends SDMSet<T>> ST union(Collection<? extends T> other)
//   {
//      @SuppressWarnings("unchecked")
//      ST result = (ST) this.getNewList(false);
//      result.addAll(this);
//      result.addAll(other);
//      
//      return result;
//   }
//   
//   
//   public <ST extends SDMSet<T>> ST intersection(Collection<? extends T> other)
//   {
//      @SuppressWarnings("unchecked")
//      ST result = (ST) this.getNewList(false);
//      result.addAll(this);
//      result.retainAll(other);
//      return result;
//   }
//   
//   @SuppressWarnings("unchecked")
//   public <ST extends SDMSet<T>> ST minus(Object other)
//   {
//      ST result = (ST) this.getNewList(false);
//      result.addAll(this);
//      
//      if (other instanceof Collection)
//      {
//         result.removeAll((Collection<?>) other);
//      }
//      else
//      {
//         result.remove(other);
//      }
//      
//      return result;
//   }
}
