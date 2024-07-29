package design.observer_pattern;

public class Subscriber implements Observer{

    private String name;

    Subscriber(String name){
        this.name = name;
    }
    @Override
    public void notified(String title){
        System.out.println("Hello " + this.name + " new video uploaded with title : " + title);
    }
}
