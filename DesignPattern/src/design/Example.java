package design;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

public class Example  {
    public static void main(String[] args) throws Exception{

        //Samosa samosa1 = Samosa.getSamosa();
//        Samosa samosa1 = Samosa.INSTANCE;
//        System.out.println(samosa1.hashCode());  // 13648335
//
//        Constructor<Samosa> constructor = Samosa.class.getDeclaredConstructor();
//        constructor.setAccessible(true);
//        Samosa samosa3 = constructor.newInstance();
//        System.out.println(samosa3.hashCode()); // 312116338

//        Samosa samosa2 = Samosa.getSamosa();
//        System.out.println(samosa2.hashCode());
//
//        System.out.println(Jalebi.getJalebi().hashCode());
//        System.out.println(Jalebi.getJalebi().hashCode());


        Samosa samosa1 = Samosa.getSamosa();
        System.out.println(samosa1.hashCode());
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("abc.ob"));
//        oos.writeObject(samosa1);
//
//        System.out.println("Serialization Done !!");
//
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("abc.ob"));
//        Samosa samosa2 = (Samosa) ois.readObject();
        Samosa samosa2 = (Samosa) samosa1.clone();
        System.out.println(samosa2.hashCode());

    }
}
