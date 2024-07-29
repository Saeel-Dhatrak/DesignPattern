package design.adapter_pattern;

public class Iphone14 {

    private AppleCharger appleCharger;

    public Iphone14(AppleCharger appleCharger){
        this.appleCharger = appleCharger;
    }

    public void chargeIphone(){
        appleCharger.chargePhone();
    }
}
