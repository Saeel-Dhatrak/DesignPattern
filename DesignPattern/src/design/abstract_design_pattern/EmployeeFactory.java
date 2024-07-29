package design.abstract_design_pattern;

public class EmployeeFactory {

    public static Employee getEmployee(EmployeeAbstractFactory factory){
        return factory.createEmployee();
    }
}
