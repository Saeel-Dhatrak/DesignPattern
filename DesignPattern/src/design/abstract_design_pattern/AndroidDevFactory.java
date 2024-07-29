package design.abstract_design_pattern;

public class AndroidDevFactory extends EmployeeAbstractFactory{

    @Override
    public Employee createEmployee(){
        return new AndroidDeveloper();
    }
}
