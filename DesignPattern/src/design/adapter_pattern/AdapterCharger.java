package design.adapter_pattern;

public class AdapterCharger implements AppleCharger{

    private AndroidCharger androidCharger;

    public AdapterCharger(AndroidCharger androidCharger){
        this.androidCharger=androidCharger;
    }
    @Override
    public void chargePhone() {
        androidCharger.chargeAndroidPhone();
        System.out.println("Your Phone is getting Charged with Adapter");
    }
}
