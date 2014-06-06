package org.sdmlib.models.classes;

public class Visibility
{
   public static final Visibility PUBLIC = new Visibility("public");
   public static final Visibility PACKAGE = new Visibility("");
   public static final Visibility PROTECTED = new Visibility("protected");
   public static final Visibility PRIVATE = new Visibility("private");

   public static final Visibility FINAL = new Visibility(" final");
   public static final Visibility ABSTRACT = new Visibility(" abstract");
   public static final Visibility STATIC = new Visibility(" static");

   private String value;
   Visibility(String value){
      this.setValue(value);
   }
   public String getValue()
   {
      return value;
   }
   public void setValue(String value)
   {
      this.value = value;
   }
   public Visibility withValue(String value){
      this.value = value;
      return this;
   }
   
   public static Visibility ref(String value){
      return new Visibility(value);
   }
   
   public static Visibility ref(Visibility... value){
      Visibility first=PUBLIC;
      String seconds="";
      for(Visibility item : value){
         if(item==PUBLIC || item==PACKAGE || item==PROTECTED || item==PRIVATE){
            first = item;
            continue;
         }
         seconds += item.getValue();
      }
      return new Visibility(first+seconds);
   }
   public boolean same(Visibility other)
   {
      return this.getValue().equalsIgnoreCase(other.getValue());
   }
}
