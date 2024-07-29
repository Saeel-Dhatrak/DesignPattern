package design.builder;

public class Main {
    public static void main(String[] args) {
        User user = new User.UserBuilder().setUserName("Dhoni").setEmailId("Dhoni@ms.in").setUserId("07").build();
        System.out.println(user); // Dhoni : Dhoni@ms.in : 07
    }
}
