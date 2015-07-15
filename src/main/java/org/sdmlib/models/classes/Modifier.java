package org.sdmlib.models.classes;

public class Modifier
{
   public static final Modifier PUBLIC = new Modifier("public");
   public static final Modifier PACKAGE = new Modifier("");
   public static final Modifier PROTECTED = new Modifier("protected");
   public static final Modifier PRIVATE = new Modifier("private");

   public static final Modifier FINAL = new Modifier(" final");
   public static final Modifier ABSTRACT = new Modifier(" abstract");
   public static final Modifier STATIC = new Modifier(" static");

   private String value;
   Modifier(String value){
      this.withValue(value);
   }
   public String getValue()
   {
      return value;
   }
   public Modifier withValue(String value){
      this.value = value;
      return this;
   }
   
   public static Modifier ref(String value){
      return new Modifier(value);
   }
   
   public static Modifier ref(Modifier... value){
      Modifier first=PUBLIC;
      String seconds="";
      for(Modifier item : value){
         if(item==PUBLIC || item==PACKAGE || item==PROTECTED || item==PRIVATE){
            first = item;
            continue;
         }
         seconds += item.getValue();
      }
      return new Modifier(first+seconds);
   }
   public boolean equals(Modifier other)
   {
      return this.getValue().equalsIgnoreCase(other.getValue());
   }
   
   public boolean has(Modifier other)
   {
      return this.getValue().contains(other.getValue());
   }
   
   @Override
   public String toString(){
      return this.value;
   }
}
