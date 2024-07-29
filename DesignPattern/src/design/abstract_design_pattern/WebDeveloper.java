package design.abstract_design_pattern;

public class WebDeveloper implements Employee{

    public int salary(){
        return 40000;
    }

    public String name(){
        System.out.println("I am Web developer");
        return "Web Developer";
    }
}
