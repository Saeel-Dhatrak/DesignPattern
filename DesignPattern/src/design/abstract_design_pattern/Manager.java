package design.abstract_design_pattern;

public class Manager implements Employee{
    @Override
    public int salary() {
        return 90000;
    }

    @Override
    public String name() {
        System.out.println("I am Manager");
        return "Manager";
    }
}