package design.adapter_pattern;

public class Demo {
    public static void main(String[] args) {
        System.out.println("Program Started");

        AppleCharger charger = new AdapterCharger(new AndroidChargerImpl());

        Iphone14 iphone14 = new Iphone14(charger);

        iphone14.chargeIphone();
    }
}
