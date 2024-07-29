package design.abstract_design_pattern;

public class AndroidDeveloper implements Employee{

    public int salary(){
        return 50000;
    }

    public String name(){
        System.out.println("I am Android developer");
        return "Android Developer";
    }
}
