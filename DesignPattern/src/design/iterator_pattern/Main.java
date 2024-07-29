package design.iterator_pattern;

public class Main {
    public static void main(String[] args) {
        UserManagement userManagement = new UserManagement();

        userManagement.addUser(new User("dhoni", "07"));
        userManagement.addUser(new User("kohli", "18"));
        userManagement.addUser(new User("sharma", "45"));
        userManagement.addUser(new User("bumrah", "93 "));

        MyIterator myIterator = userManagement.getIterator();
        while(myIterator.hasNext()){
            User user = (User) myIterator.next();
            System.out.println(user.getName());
        }
    }
}
