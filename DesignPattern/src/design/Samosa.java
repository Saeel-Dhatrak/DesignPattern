package design;

import java.io.Serializable;

public class Samosa implements Serializable, Cloneable {
   private static Samosa samosa;

   private Samosa(){
//      if(samosa != null){
//         throw new RuntimeException("You are trying to break the singleton pattern");
//      }
   }

   public static Samosa getSamosa(){
      // Object of this class
      if(samosa == null){
         synchronized (Samosa.class){
            if(samosa == null){
               samosa = new Samosa();
            }
         }
      }
      return samosa;
   }

   public Object readResolve(){
      return samosa;
   }

   public Object clone() throws CloneNotSupportedException{
      return super.clone();
   }
}