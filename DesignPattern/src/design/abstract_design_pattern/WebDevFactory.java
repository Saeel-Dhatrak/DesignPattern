package design.abstract_design_pattern;

public class WebDevFactory extends EmployeeAbstractFactory{

    @Override
    public Employee createEmployee(){
        return new WebDeveloper();
    }
}
