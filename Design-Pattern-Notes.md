### 1. What is Design Pattern | How many Design Patterns are there?

- Design pattern are the well proven solution of commonly occuring problems in Software design.
- Software creation just does mean writing the code but there are many phases in the creation of a software like requirement analysis, designing, etc
- Consider we are facing an issue creating a sofware and we have many developers in our team and every developer can solve different problems with different approach and it is not necessary that whichever developer is solving the issue it might be the 100% correct approach
- Even handing over the issue to another developer then he/she again might not be solving the issue with 100% correct approach.
- So what design pattern tells is that when the software gets built there are some general problems that come into picture and design pattern provides way/approach to solve these issues.
- So Design pattern are basically the pre-defined approach provided to solve the particular problem. One of the well proven design pattern is `Singleton Design Pattern`.
- If we want to create an object and we want this object to be only one throughout the whole codebase then we can make use of singleton design pattern
- Design Patterns represents an idea and it doesn't represent implementation
- Categories of design pattern
    - Creational Design Pattern
        - Factory pattern, Builder Pattern, Singleton pattern
    - Structural Design Pattern
        - Proxy Pattern, Adaptor Pattern
    - Behavioral Design Pattern
        - Observer Pattern, State Pattern, Iterator pattern
- `Christopher Alexander` was the first person who invented all the above design pattern in 1977 but later the `Gang of Four - Design Patterns, element of reusable object-oriented software` book was wriitten by a four persons named as Erich Gamma, Richrd Helm, Ralph Johnson and John Vlissides in 1995.

### 2.Singleton Design Pattern | Multi-Threaded Environments

- If we want to create an object only one time in whole project and want to re-use it again & again then the singleton design pattern will be helpful.
- Example: Suppose that we are doing work related to database like JDBC code and in this we are performing crud operations, but your database and app's connection need to be created again & again and in this case we need an object to be created only once and get re-used again & again
- what we will do is create a class named `Samosa` and as we know that constructor is used to create an object so if any other class uses new keyword like `new Samosa()` then a new object will get created and we don't want this to happen.
- And for this purpose we will make our constructor private in the `Samosa` class so that no other class will be able to call this constructor
- In the `Samosa.java` class we made constructor private as `private Samosa() {  }`
- Now we need a method which will give us the object of the Samosa class. As we want to return object of type `Samosa` we need to keep the return type of this method as `Samosa`
- `public Samosa getSamosa()`
- But again to call the above method we would require an object as it is a non-static method so we need to make the above method as static
- `public static Samosa getSamosa()` . Now this method is static and it is associated with the class and we will be able to call it by `Samosa.getSamosa();`
- ```java
    public static Samosa getSamosa(){
        // Give Object of this class
        Samosa samosa = new Samosa();
        return samosa;     
    }
  ```
- We have returned the object in the above method, but there might be a chance that someone is calling this method n number of times and then n number object will get created. We want this line of code `new Samosa()` to run only once. And if it gets called any number of times then also same object should get returned.
- We will create a variable of type Samosa and will make it private so that it not accessible outside. `private Samosa samosa` and will use this variable inside the method
- But our method is static and we cannot use non static varibale inside the static method so `private static Samosa samosa`
- Now in our method we want to check if this varible contains any object/value, if it does not contain any value i.e if it is null then we will create new object and place it in this variable as `samosa = new Samosa()`.
- ```java
    // Samosa.java
    package design;

    public class Samosa {

        private static Samosa samosa; // static variable to store object value

        private Samosa(){ } // private constructor

        public static Samosa getSamosa(){ // static method to get object of this class
            if(samosa == null){
                samosa = new Samosa();
            }   
            return samosa;
        }
    }
  ```
- Now we will create a `Example.java` class to try and play with our created `Samosa` class object and check whether we are getting only one object or not.
- ```java
    // Example.java
    package design;

    public class Example {
        public static void main(String[] args) {

            Samosa samosa1 = Samosa.getSamosa();
            System.out.println(samosa1.hashCode()); // 13648335

            Samosa samosa2 = Samosa.getSamosa();
            System.out.println(samosa2.hashCode()); // 13648335

        }
    }
  ```
- As we can see that both the hashCodes are same and we can say that we have achieved the Singleton design pattern.
- Here we can note that we lazily initialized our object that means when the client called at that time only the object got created
- Now we will see how to get the object by Eager initialization
- For this we will create a new class `Jalebi.java`, here we will store the object in the variable and in the method we will return the already created object everytime the client calls it.
- ```java
    // Jalebi.java
    package design;

    public class Jalebi {
        private static Jalebi jalebi = new Jalebi();

        public static Jalebi getJalebi(){
            return jalebi;
        }
    }
  ```
- Same again we will call this getJalebi method two times from the Example.java class to check if two hashcode ares same or not
- ```java
    // Example.java
    package design;

    public class Example {
        public static void main(String[] args) {

            Samosa samosa1 = Samosa.getSamosa();
            System.out.println(samosa1.hashCode()); // 13648335

            Samosa samosa2 = Samosa.getSamosa();
            System.out.println(samosa2.hashCode()); // 13648335

            System.out.println(Jalebi.getJalebi().hashCode()); // 453211571
            System.out.println(Jalebi.getJalebi().hashCode()); // 453211571
        }
    }
  ```
- Difference between Eager and Lazy way of object initialization.
- Eager way means the static variable will get initialized in the beginning i.e the static variable jalebi will get initialized as soon as the Jalebi class loads. Now what if the client did not require the object but the eager way has already initialized the variable with the object.
- In case of lazy the object will only get created when the client calls, but we have not understand this from the perspective of threads
- In multithreading we know that thread operates concurrently and what if multiple threads hits the `getSamosa()` method at the same time and both of them gets the response back as null in the variable then for both the methods the object will get created meaning object will get created two times as the line `samosa = new Samosa()` will get excuted twice.
- That means if thread safety is an important aspect of our project then we have to use some synchronized way of creating the threads BUT if thread safety is not the concern in our project then the above lazy approach is good.
- One way to implement thread safety is by making the getSamosa method as synchronized and this will make the method accessibke to only one thread ata time.
- So we will make use of synchronized block
- ```java
    public static Samosa getSamosa(){
        // Object of this class
        if(samosa == null){
            synchronized (Samosa.class){
                if(samosa == null){
                    samosa = new Samosa();
                }
            }
        }
        return samosa;

    }
  ```
- Now at a time one thread will be able to access this block of code where the object creation happens.

### 3. Three ways to break singleton pattern | Solution of problem in detail

- We can use Reflection API to break Singleton pattern because we know that with Reflection Api we can change/modify any class or interface properties.
- In our `Example` class we will first get the constructor of the `Samosa` class, after getting the constructor we need to change the accessibility of it from private to public. Then we need to create the instance of that constructor and call the hashCode method to check whether both the hashCodes are same or not.
- ```java
    // Example.java
    package design;
    import java.lang.reflect.Constructor;

    public class Example  {
        public static void main(String[] args) throws Exception{

            Samosa samosa1 = Samosa.getSamosa();
            System.out.println(samosa1.hashCode());  // 13648335

            Constructor<Samosa> constructor = Samosa.class.getDeclaredConstructor(); // here we first loaded the class Samosa which will then give us the Samosa type reference and on which we called getDeclaredConstructor method and to store this we have a class named `Contructor` 
            constructor.setAccessible(true); // If we do not make the constructor public then we will get exception saying cannot access a member of class Samosa with modifier "private"
            // Now once we got the construtor we can create a new instance using the it
            Samosa samosa3 = constructor.newInstance(); 
            System.out.println(samosa3.hashCode()); // 312116338
        }
    }
  ```
- As we can see both the hashCodes are different hence the singleton pattern is broken. Now there are two ways to stop this from breaking
- 1. If object already exist then throw exception from inside the constructor
- ```java
    // Samosa.java
    // constructor
    private Samosa(){
        if(samosa != null){
            throw new RuntimeException("You are trying to break the singleton pattern");
        }
    }
  ```
- 13648335, is the first hashcode and when tried to get the ibject for second time then we got the exception `java.lang.RuntimeException: You are trying to break the singleton pattern`

- 2. use ENUM
- For this we need to make our Samosa class as ENUM and then we would not require the constructor or any method that we were using earlier inside the Samosa class
- ```java
    // Current Version of Samosa.java
    package design;

    public class Samosa {
        private static Samosa samosa;

        private Samosa(){
            if(samosa != null){ // solution for reflection breaking singketon pattern
                throw new RuntimeException("You are trying to break the singleton pattern");
            }
        }

        public static Samosa getSamosa(){
            // Object of this class
            if(samosa == null){
                synchronized (Samosa.class){
                    if(samosa == null){
                        samosa = new Samosa();
                    }
                }
            }
            return samosa;
        }
    }
  ```
- After enum new version of Samosa class
- ```java
    package design;

    public enum Samosa {
        INSTANCE;
    }
  ```
- And in Example class instead of calling the getSamosa method we can now call the `Samosa samosaInstance = Samosa.INSTANCE;` and it will return back the instance of enum Samosa.
- And as there is no constructor present in the Samosa the singleton pattern won't get broken using the constructor as we did earlier. So after running the code only one object that is only one hashcode will get printed and an exception that is `NoSuchMethodException` because constructor.newInstance will not work as we do not have any constructor in the Samosa class
- Enum actually internally get managed by class only, so we can call any other method present inside the enum.
- For example if the method is `public void test(){ System.out.println("this is test method"); }` present inside enum then we can call it using `samosaInstance.test()` and we will get output.
- Now there are other ways to break singleton pattern and for that again we need to convert enum samosa to Samosa class as it was earlier.
- ```java
    package design;

    public class Samosa {
        private static Samosa samosa;

        private Samosa(){ }

        public static Samosa getSamosa(){
            // Object of this class
            if(samosa == null){
                synchronized (Samosa.class){
                    if(samosa == null){
                    samosa = new Samosa();
                    }
                }
            }
            return samosa;
        }
    }
  ```
- 3. Using Deserialization.
- First we have to implement Serializable interface in our Samosa class importing it from `java.io.Serializable`
- Now in our Example class, we can deserialize the object using `ObjectOutputStream` and once deserialization is done we will again serialize it using the `ObjectInputStream`
- ```java
    // Example.java'
    package design;

    import java.io.FileInputStream;
    import java.io.FileOutputStream;
    import java.io.ObjectInputStream;
    import java.io.ObjectOutputStream;
    import java.lang.reflect.Constructor;

    public class Example  {
        public static void main(String[] args) throws Exception{

            Samosa samosa1 = Samosa.getSamosa();
            System.out.println(samosa1.hashCode());
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("abc.ob"));
            oos.writeObject(samosa1);

            System.out.println("Serialization Done !!");

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("abc.ob"));
            Samosa samosa2 = (Samosa) ois.readObject();
            System.out.println(samosa2.hashCode());

        }
    }
  ```
- Now we will get different hashcodes upon running the Example class. So again the singleton pattern is broken
- To solve it breaking from this method we can implement `readResolve` method in the samosa class and return the same samosa object inside it.
- ```java
    package design;

    import java.io.Serializable;

    public class Samosa implements Serializable {
        private static Samosa samosa;

        private Samosa(){}

        public static Samosa getSamosa(){
            // Object of this class
            if(samosa == null){
                synchronized (Samosa.class){
                    if(samosa == null){
                    samosa = new Samosa();
                    }
                }
            }
            return samosa;
        }

        public Object readResolve(){
            return samosa;
        }
    }
  ```
- Now upon running the Example class we will not get the same hashcodes.
- 4. Using Cloning
- We can break the singleton pattern if we clone the object
- ```java
    // Samosa.java
    package design;

    import java.io.Serializable;

    public class Samosa implements Serializable, Cloneable { // need to implement Cloneable
        private static Samosa samosa;
        private Samosa(){ }

        public static Samosa getSamosa(){
            // Object of this class
            if(samosa == null){
                synchronized (Samosa.class){
                    if(samosa == null){
                    samosa = new Samosa();
                    }
                }
            }
            return samosa;
        }

        @Override
        public Object clone() throws CloneNotSupportedException{ // overrided method
            return super.clone();
        }
    }
    //Example.java
    package design;
    import java.lang.reflect.Constructor;

    public class Example  {
        public static void main(String[] args) throws Exception{

                Samosa samosa1 = Samosa.getSamosa();
                System.out.println(samosa1.hashCode());

                Samosa samosa2 = (Samosa) samosa1.clone();
                System.out.println(samosa2.hashCode());
            }
        }
  ```
- And again this will give different hascode and the pattern will be broken.
- The solution is in the overrided clone method we can return the same object that is `samosa` and upon doing this we will get the same objects that is same hashcodes

### 4. Understand factory Design Pattern

- Also called as factory method design pattern
- When there is super class and multiple subclasses and we want to get object of subclass based on input and requirements then we create factory class which takes the responsibility of creating object of class based on input.
- Create an Employee interface, AndroidDeveloper class, WebDeveloper class and DeveloperClient class as shown below
- ```java
    // Employee.java
    package design;

    public interface Employee {
        int salary();
    }

    // AndroidDeveloper.java
    package design;

    public class AndroidDeveloper implements Employee{
        public int salary(){
            System.out.println("Getting android developer salary");
            return 50000;
        }
    }

    // WebDeveloper.java
    package design;

    public class WebDeveloper implements Employee{
        public int salary(){
            System.out.println("Getting web developer salary");
            return 40000;
        }
    }

    // DeveloperClient.java
    package design;

    public class DeveloperClient {
        public static void main(String[] args) {

            Employee employee = new AndroidDeveloper();
        }
    }
  ```
- Here in `DeveloperClient`, we are by ourself creating AndroidDeveloper that means the client himself have to see and decide which developer it wants i.e either andorid or web
- Also it is tight coupling and based on parameter we are not able change objects
- To solve these problems our class should not be tightly coupled and based on input we should be able to create objects i.e we want to remove the creation of object from this `DeveloperClient` class
- We need to create a `EmployeeFactory` class which will have methods which will return employee objects based on the input
- ```java
    package design;

    public class EmployeeFactory {

        public static Employee getEmployee(String empType){
            if(empType.trim().equalsIgnoreCase("ANDROID DEVELOPER")){
                return new AndroidDeveloper();
            }else if(empType.trim().equalsIgnoreCase("WEB DEVELOPER")){
                return new WebDeveloper();
            }else{
                return null;
            }
        }
    }
  ```
- So we will call `EmployeeFactory.getEmployee` and pass in the empType then it will create the object of that type. BUT this class is responsible for creation of the inputted object and is still unknown to the client class that it `DeveloperClient` do not know about this class. So we have to use it in the client.
- ```java
    // DeveloperCLient.java
    package design;

    public class DeveloperClient {
        public static void main(String[] args) {

            Employee employee = EmployeeFactory.getEmployee("Android Developer");// Getting android developer
            int salary = employee.salary();
            System.out.println(salary); // output : 50000


            Employee employee2 = EmployeeFactory.getEmployee("Web Developer"); // Getting web developer
            int salary2 = employee2.salary();
            System.out.println(salary2); // output : 40000
        }
    }
  ```
- So this is how factory design pattern works in which we have to create a class and based on parameters we need to create object and this class is called as factory class
- Factory class is the class which has methods to return other class objects based on the requirement
- Advantages of Factory Design Pattern
    - 1. Focus on creating object for interface rather than implementation
        - It will focus on interface, that is we do not think of android or web developer but we are focused only on our employee
        - That's why we have used the `EmployeeFactory.getEmployee()` and how to create andorid or web developer is not this class tension, rather we just need to pass in what we need
    - 2. Loose coupling, more robust code
        - Whatever object client is using it does not know where it is getting created, also we can change the type on runtime we can pass in another type because we are only passing the String
        - If we change the string on runtime then the object will also get change


### 5. Abstract Factory Design Pattern

- Similar to Factory design pattern ,it will provide the concept of Factory of Fcatories.
- It will basically add one more layer that Client will call EmployeeFactory and EmployeeFactory will call EmployeeAbstractFactory. To understand it better we will create new classes and interfaces in a new package under the design package named `abstract_design_pattern`
- Untill now what we have created is listed below
- ```java
    // Employee.java
    package design.abstract_design_pattern;

    public interface Employee {
        int salary();
        String name();
    }

    // AndroidDeveloper.java
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

    // WebDeveloper.java
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

    // EmployeeAbstractFactory.java
    package design.abstract_design_pattern;

    abstract public class EmployeeAbstractFactory {

        public abstract Employee createEmployee();
    }

    // EmployeeFactory.java
    package design.abstract_design_pattern;

    public class EmployeeFactory {

        public static Employee getEmployee(EmployeeAbstractFactory factory){
            return factory.createEmployee();
        }
    }
  ```
- Here in EmployeeFactory inside the getEmployee method the class thinks that createEmployee method will give us the Employee, but it does not know that EmployeeAbstractFactory is itself an abstract class so the createEmployee does not have any implementation.
- Now we need to create an implementation class for EmployeeAbstractFactory class and we need to create two classes one for web developer and one for android developer
- The meaning of Abstract Factory Design Pattern is that we will create a factory which will call another factory
- ```java
    // AndroidDevFactory.java
    package design.abstract_design_pattern;

    public class AndroidDevFactory extends EmployeeAbstractFactory{
        @Override
        public Employee createEmployee(){
            return new AndroidDeveloper();
        }
    }

    // WebDevFactory.java
    package design.abstract_design_pattern;

    public class WebDevFactory extends EmployeeAbstractFactory{

        @Override
        public Employee createEmployee(){
            return new WebDeveloper();
        }
    }
  ```
- Now we need to create a Client.java class who needs object on demand
- ```java
    // Client.java
    package design.abstract_design_pattern;

    public class Client {
        public static void main(String[] args) {
            Employee e1 = EmployeeFactory.getEmployee(new AndroidDevFactory());
            e1.name();

            Employee e2 = EmployeeFactory.getEmployee(new WebDevFactory());
            e2.name();
        }
    }
  ```
- So what is the difference because here also we are creating the object?
    - Here when we are calling the `EmployeeFactory.getEmployee` then we need an object, the EmployeeFactory is also using another factory which is `EmployeeAbstractFactory` so the EmployeeAbstractFactory itself does not know which object to create. Depending upon the implementation it will get clear that which object we want to create
    - So basically this type of design pattern give more flexibility, for suppose in future we got another class `Manager.java` which implements Employee interface then we can provide its own salary and name.
- ```java
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
  ```
- Now depending upon which type of employee we need we will create its factory `ManagerFactory.java`
- ```java
    package design.abstract_design_pattern;

    public class ManagerFactory extends EmployeeAbstractFactory {
        @Override
        public Employee createEmployee() {
            return new Manager();
        }
    }
  ```
- Now we can get this manager employeee in the client
- ```java
    // Client.java
    Employee e3 = EmployeeFactory.getEmployee(new ManagerFactory());
    e3.name();
  ```

### 6. Builder Design Pattern

- In factory design pattern while creating an object when object contain many attributes there are many problems that exist
    - 1. we have to pass many arguments to create objects
    - 2. some parameters might be optional
    - 3. factory class takes all the responsibility for creating object, if the object is heavy then all complexity is the part of factory class
- So in builder pattern we create object step by step and finally return final object with desired values of attributes.
- Builder patterns helps in creating immuatable objects
- We will create User class in which we will have 3 fields and a private constructor, getters and we will not create setters so that we won't be able to set te data in the User class object
- ```java
    package design.builder;

    public class User {

        private final String userId;
        private final String userName;
        private final String emailId;

        private User(UserBuilder builder){
            // initialize
            this.userId = builder.userId;
            this.userName = builder.userName;
            this.emailId = builder.emailId;
        }

        public String getUserId() {
            return userId;
        }

        public String getUserName() {
            return userName;
        }

        public String getEmailId() {
            return emailId;
        }

        @Override
        public String toString(){
            return this.userName + " : " + this.emailId + " : " + this.userId;
        }

        // inner class to create object
        static class UserBuilder{
            private String userId;
            private String userName;
            private String emailId;

            public UserBuilder(){ }

            public UserBuilder setUserId(String userId) {
                this.userId = userId;
                return this;
            }

            public UserBuilder setUserName(String userName) {
                this.userName = userName;
                return this;
            }

            public UserBuilder setEmailId(String emailId) {
                this.emailId = emailId;
                return this;
            }

            public User build(){
                User user = new User(this);
                return user;
            }
        }
    }
  ```
- ```java
    // Main.java
    package design.builder;

    public class Main {
        public static void main(String[] args) {
            User user = new User.UserBuilder().setUserName("Dhoni").setEmailId("Dhoni@ms.in").setUserId("07").build();
            System.out.println(user); // Dhoni : Dhoni@ms.in : 07
        }
    }
  ```

### 7. Prototype Design Pattern

- The concept is to copy an existing object rather than creating a new instance from scratch because creating new objects may be costly.
- This approach save costly resources and time, especially when object creation is a heavy process.
- It's a creational design pattern which means that we will create objects using this pattern.
- What this pattern says is that if we are creating any object of anyclass and while creating the object if we are performing any complex logic then we should not create that object again & again directly.
- Because the number of times we create that object that many times the complex logic would get executed. Complex logic such as database connectivity which is costly, reading a large size file, calling a network to make connection, etc
- Suppose that we have an object that means our complex logic is executed, now do not need to create this object from scratch again and we need to make use of the already created object to create another object by using clone method
- This way we did not execute that complex logic again and our new object got created using the old one. Similarly we can have multiple objects using copy technique.
- ```java
    // NetworkConnection.java
    package design.prototype_pattern;

    public class NetworkConnection {
        private String ip;
        private String importantData;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getImportantData() {
            return importantData;
        }

        public void setImportantData(String importantData) {
            this.importantData = importantData;
        }

        public void loadImportantData() throws InterruptedException{
            this.importantData = "Very very important data";
            Thread.sleep(5000)
            // It will take 5 seconds
        }

        @Override
        public String toString(){
            return this.ip + " : " + this.importantData;
        }
    }
  ```
- Consider that `loadImportantData()` method takes 5 seconds time to complete and this is what we do not want because everytime we create an object it will tkae our precious 5 seconds
- Now we will create a `Main` class inside which we will create object of NetworkConnection class
- ```java
    package design.prototype_pattern;

    public class Main {
        public static void main(String[] args) {
            System.out.println("Creating object using prototype design pattern");
            NetworkConnection networkConnection = new NetworkConnection();
            networkConnection.setIp("10.208.11.11");
            networkConnection.loadImportantData();
            System.out.println(networkConnection); // output: 10.208.11.11 : Very very important data
        }
    }
  ```
- Here we can say that our complex operation which took 5 seconds (assumption) complete was done and new oject was created. To create a new object from already created object we can use clone method but to that we first have to make the class NetworkConnection implement Cloneable interface `public class NetworkConnection implements Cloneable` and is a marker interface which tells that now this class can be cloned and we need to override the clone method in the NetworkConnection class.
- ```java
    // NetworkConnection.java
    @Override
    protected Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
  ```
- Now we can call the clone method from the Main class
- ```java
    package design.prototype_pattern;

    public class Main {
        public static void main(String[] args) throws InterruptedException {
            System.out.println("Creating object using prototype design pattern");
            NetworkConnection networkConnection = new NetworkConnection();
            networkConnection.setIp("10.208.11.11");
            networkConnection.loadImportantData();
            System.out.println(networkConnection);

            System.out.println("Creating object using clone method");
            try{
            NetworkConnection networkConnection2 = (NetworkConnection) networkConnection.clone();
                System.out.println(networkConnection2); // output: 10.208.11.11 : Very very important data
            }
            catch(CloneNotSupportedException e){
                e.printStackTrace();
            }
        }
    }
  ```
- We have not set any data in the ip using the networkConnection2 object still we are getting the data this is because we have clone the previous object and we are getting back its values as it is
- Also it can be seen while we run the application that for the creation of first object it literally takes 5 seconds and for second object it does not take any time. Same if we create 3rd object then also it won't take any time to create the object.

### 8. Shallow Copy vs Deep Copy Explained!

- *Shallow Copy*
- Example: Suppose that we have an object as obj1 which has some properties int a =8, b=10; and it may have anither object for suppose Student object stored in the obj1 itself.
- Now when we do shallow copy then the primitives such int a & b will get copied as it is inside the obj2 but the Student object which was inside the obj1 wil not get directctly copied in the obj2. Instead the reference of Stident object will get stored in the obj2
- And upon accessing any Student property using the obj2 and modifying the property value then the actual vakue kept in the student object will get changed that means the value will also get changed for obj1.Student
- *Deep Copy*
- Consider the same scenario of obj1 & obj2, now when we create obj2 then a new duplicate copy of Student object will also get created and the operations performed on Student object using the obj2 will be separated and won't affect obj1 Student

### 9. Implementing Deep Copy

- Earlier when we were creating the object of NetworkConnection class then for the first attempt of object creation we were made to wait for 5 seconds but for 2nd & 3rd attempt the object were getting created immediately as they were just clone of object1.
- In the methods where we were maud to wait 5 seconds they was simple String, now we will create a list of domains which is a reference variable and `new Arraylist` is an object that means now the `NetworkConnection` class contains another object or the reference of other object and when we will copy it then at that time the refrence of the other object will get copied
- ```java
    package design.prototype_pattern;

    import java.util.ArrayList;
    import java.util.List;

    public class NetworkConnection implements Cloneable{
        private String ip;
        private String importantData;
        private List<String> domains = new ArrayList<>();

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getImportantData() {
            return importantData;
        }

        public void setImportantData(String importantData) {
            this.importantData = importantData;
        }

        public List<String> getDomains(){
            return this.domains;
        }

        public void setDomains(List<String> domains){
            this.domains = domains;
        }

        public void loadImportantData() throws InterruptedException {
            this.importantData = "Very very important data";
            domains.add("www.google.com");
            domains.add("www.udemy.com");
            domains.add("www.coursera.com");
            domains.add("www.youtube.com");
            Thread.sleep(5000);
            // It will take 5 minutes
        }

        @Override
        public String toString(){
            return this.ip + " : " + this.importantData + " : " + this.domains;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException{
            return super.clone();
        }
    }  
  ```
- ```java
    // Main.java
    package design.prototype_pattern;

    public class Main {
        public static void main(String[] args) throws InterruptedException {
            System.out.println("Creating object using prototype design pattern");
            NetworkConnection networkConnection = new NetworkConnection();
            networkConnection.setIp("10.208.11.11");
            networkConnection.loadImportantData();
            System.out.println(networkConnection);

            System.out.println("Creating object using clone method");
            try{
                NetworkConnection networkConnection2 = (NetworkConnection) networkConnection.clone();
                NetworkConnection networkConnection3 = (NetworkConnection) networkConnection.clone();
                System.out.println(networkConnection2);
                System.out.println(networkConnection3);
            }
            catch(CloneNotSupportedException e){
                e.printStackTrace();
            }
        }
    }

    // Output : 
    Creating object using prototype design pattern
    10.208.11.11 : Very very important data : [www.google.com, www.udemy.com, www.coursera.com, www.youtube.com]
    Creating object using clone method
    10.208.11.11 : Very very important data : [www.google.com, www.udemy.com, www.coursera.com, www.youtube.com]
    10.208.11.11 : Very very important data : [www.google.com, www.udemy.com, www.coursera.com, www.youtube.com]
    
  ```
- Here the data from first object has came directly into other two objects, now what if we make changes to the domains list from the first object itself.
- ```java
    package design.prototype_pattern;

    public class Main {
        public static void main(String[] args) throws InterruptedException {
            System.out.println("Creating object using prototype design pattern");
            NetworkConnection networkConnection = new NetworkConnection();
            networkConnection.setIp("10.208.11.11");
            networkConnection.loadImportantData();
            System.out.println(networkConnection);

            System.out.println("Creating object using clone method");
            try{
                NetworkConnection networkConnection2 = (NetworkConnection) networkConnection.clone();
                NetworkConnection networkConnection3 = (NetworkConnection) networkConnection.clone();
                networkConnection.getDomains().remove(0); // here we removed the indexed zero element
                System.out.println(networkConnection);
                System.out.println(networkConnection2);
                System.out.println(networkConnection3);
            }
            catch(CloneNotSupportedException e){
                e.printStackTrace();
            }
        }
    }

    // Output:
    Creating object using prototype design pattern
    10.208.11.11 : Very very important data : [www.google.com, www.udemy.com, www.coursera.com, www.youtube.com]
    Creating object using clone method
    10.208.11.11 : Very very important data : [www.udemy.com, www.coursera.com, www.youtube.com]
    10.208.11.11 : Very very important data : [www.udemy.com, www.coursera.com, www.youtube.com]
    10.208.11.11 : Very very important data : [www.udemy.com, www.coursera.com, www.youtube.com]
  ```
- So as we can see that we first created the copied object 2 & 3 and then removed the element at zero index from object1 still it git removed from object 2 & 3
- To avoid this from happening we will make changes in our logic of clone method of the NetworkConnection class.
- ```java
    // NetworkConnection.java
    @Override
    protected Object clone() throws CloneNotSupportedException{
        // logic for cloning
        NetworkConnection networkConnection = new NetworkConnection();
        networkConnection.setIp(this.getIp());
        networkConnection.setImportantData(this.getImportantData());
        
        for(String d : this.getDomains()){
            networkConnection.getDomains().add(d)
        }
        return networkConnection;
    }
  ```
- What we have done in the clone method is that now the actually resource will not get loaded which initaially happening that it was taking 5 seconds to load the importantData and we have taken ip also from the object and we will loop around the domains list. So that the object that will get created will have a new list which will get the copy of data and finally we will return the new created object
- Now upon running the Main class the ony changes that will take place will be on the ibject that removes the zero indexed element.
- ```java
    Creating object using prototype design pattern
    10.208.11.11 : Very very important data : [www.google.com, www.udemy.com, www.coursera.com, www.youtube.com]
    Creating object using clone method
    10.208.11.11 : Very very important data : [www.udemy.com, www.coursera.com, www.youtube.com]
    10.208.11.11 : Very very important data : [www.google.com, www.udemy.com, www.coursera.com, www.youtube.com]
    10.208.11.11 : Very very important data : [www.google.com, www.udemy.com, www.coursera.com, www.youtube.com]
  ```

### 10. Observer Design Pattern with Console Based app

- Untill now we covered the creational design pattern which were focused on how the object should get created and now we will look at Observer Design Pattern whose main focus will on how the two or more objects will intercat with eact other.
- In this design pattern when subject chnages the state all its dependent objects notify the changes.
- Understand this with example of youtube, suppose there is one youtube channel consider this channel as `subject`. Now this channel mustbe having subscribers consider them as `observer`, whenever the subject uploads any video then automatically the observer gets notified about the newly uploaded video.
- We will create a `Subject` inteface, `Observer` interface
- ```java
    // Subject.java
    package design.observer_pattern;

    public interface Subject {
        void subscribe(Observer ob);
        void unsubscribe(Observer ob);
        void newVideoUploaded(String title);
    }

    // Observer.java
    package design.observer_pattern;

    public interface Observer {
        void notified(String title);
    }
    ```
- We have created two interfaces in the Subject we have 3 methods
    - `subscribe(Observer ob)` will take observer name as input parameter to subscribe to the youtube channel
    - `void unsubscribe(Observer ob);` will take observer name as input to unsubscribe to the youtube channel
    - `void newVideoUploaded();` will notify the observer about new released video by youtube channel
- In the Observer interface only single method
    - `notified()` will notify the observer about the new video by the subject i.e Youtube channel
- Now we will create implementation classes for this two interfaces. The `YoutubeChannel` will implement `Subject` interface and will override its abstract methods.
- ```java
    package design.observer_pattern;

    import java.util.ArrayList;
    import java.util.List;

    public class YoutubeChannel implements Subject {
        List<Observer> subscribers = new ArrayList<>();

        @Override
        public void subscribe(Observer ob) {
            this.subscribers.add(ob);
        }

        @Override
        public void unsubscribe(Observer ob) {
            this.subscribers.remove(ob);
        }

        @Override
        public void newVideoUploaded(String title) {
            for(Observer ob : this.subscribers){
                ob.notified(title);
            }
        }
    }
  ```
- And the `Subscriber` class which will implement the `Observer` interface abstract methods.
- ```java
    package design.observer_pattern;

    public class Subscriber implements Observer{
        private String name;

        Subscriber(String name){
            this.name = name;
        }

        @Override
        public void notified(String title){
            System.out.println("Hello " + this.name + " new video uploaded with title :" + title);
        }
    }
  ```
- And finally we will have a main class as demo to create object and call all the methods.
- ```java
    package design.observer_pattern;

    public class Demo {
        public static void main(String[] args) {
            Subscriber sc = new Subscriber("MS");
            Subscriber sc1 = new Subscriber("Virat");
            YoutubeChannel ch = new YoutubeChannel();
            ch.subscribe(sc);
            ch.subscribe(sc1);
            ch.newVideoUploaded("How to play tennis");
        }
    }

    // Output :
    Hello MS new video uploaded with title : How to play tennis
    Hello Virat new video uploaded with title : How to play tennis
  ```
- We again call `newVideoUploaded` with different title and it will get to the observer
- Now we will create this as a menu driven program in which we have to upload video and exit
- ```java
    // Demo.java
    package design.observer_pattern;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;

    public class Demo {
        public static void main(String[] args) throws IOException {
            Observer sc = new Subscriber("MS");
            Observer sc1 = new Subscriber("Virat");
            Subject ch = new YoutubeChannel();
            ch.subscribe(sc);
            ch.subscribe(sc1);
            ch.newVideoUploaded("How to play tennis");
            ch.newVideoUploaded("How to play football");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while(true){
                System.out.println("Press 1 to upload video");
                System.out.println("Press 2 to create new subscriber");
                System.out.println("Press 3 to exit");

                int c = Integer.parseInt(br.readLine());
                if(c==1){
                    // new video upload code
                    System.out.println("Enter video title : ");
                    String videoTitle = br.readLine();
                    ch.newVideoUploaded(videoTitle);
                }else if(c==2){
                    // create new subscriber
                    System.out.println("Enter name of subscriber : ");
                    String subName = br.readLine();
                    Observer subscriber3 = new Subscriber(subName);
                    ch.subscribe(subscriber3);
                }else if(c==3){
                // exit
                    System.out.println("Thank you for using app");
                    break;
                }else{
                    // exit wrong input
                    System.out.println("Wrong input");
                }
            }
        }
    }

    // Output : 
    Hello MS new video uploaded with title : How to play tennis
    Hello Virat new video uploaded with title : How to play tennis
    Hello MS new video uploaded with title : How to play football
    Hello Virat new video uploaded with title : How to play football
    Press 1 to upload video
    Press 2 to create new subscriber
    Press 3 to exit
    1
    Enter video title : 
    How to learn AWS
    Hello MS new video uploaded with title : How to learn AWS
    Hello Virat new video uploaded with title : How to learn AWS
    Press 1 to upload video
    Press 2 to create new subscriber
    Press 3 to exit
    2
    Enter name of subscriber : 
    Saeel
    Press 1 to upload video
    Press 2 to create new subscriber
    Press 3 to exit
    1
    Enter video title : 
    How to stop procastination
    Hello MS new video uploaded with title : How to stop procastination
    Hello Virat new video uploaded with title : How to stop procastination
    Hello Saeel new video uploaded with title : How to stop procastination
    Press 1 to upload video
    Press 2 to create new subscriber
    Press 3 to exit
    3
    Thank you for using app

    Process finished with exit code 0
  ``` 

### 11. Iterator Design Pattern

- The iterator pattern provides a way to access the elements of an object without exposing its underlying implementation
- ```java
    // 
    package design.iterator_pattern;

    public class User {
        private String name;
        private String userId;

        User(String name, String userId){
            this.name = name;
            this.userId = userId;
        }
        public String getName(){
            return this.name;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getUserId(){
            return this.userId;
        }
        public void setUserId(String userId){
            this.userId = userId;
        }
    }
  ```
- ```java
    // UserManagement.java
    package design.iterator_pattern;

    import java.util.ArrayList;

    public class UserManagement {

        private ArrayList<User> userList = new ArrayList<User>();
        
        public void addUser(User user){
            userList.add(user);
        }
        
        public User getUser(int index){
            return userList.get(index);
        }

        public MyIterator getIterator(){
            return new MyIteratorImpl(userList);
        }
    }
  ```
- What we want to do is to traverse all the users present in the userList of UserManagement and traverse in such a way that the one who is traversing should not know the implementation of traversing
- For this we will create a method which will retun the `Iterator` object which will help in iterating this class object. And now we need to create an interface `MyIterator` and its implementation class `MyIteratorImpl`.
- ```java
    // MyIterator.java
    package design.iterator_pattern;

    public interface MyIterator {

        boolean hasNext();

        Object next();
    }

    // MyIteratorImpl.java
    package design.iterator_pattern;

    import java.util.List;

    public class MyIteratorImpl implements MyIterator{

        private List<User> list;
        private int length;
        private int position = 0;
        public MyIteratorImpl(List<User> list) {
            this.list = list;
            this.length = length;
        }

        @Override
        public boolean hasNext(){
            if(position >= length){
                return false;
            }else{
                return true;
            }
        }

        @Override
        public Object next(){
            User user = list.get(position);
            position += 1;
            return user;
        }
    }
  ``` 

### 12. Adpater Design Pattern

- Consider the example of apple smartphone which only gets chrged by lightning port cable, android smartphone get charge by c-type port cable. Now suppose we have apple smartphone and we forgot to bring the lightning port cable then we will take a adapter which will take input from c-type cable and get attched in apple smartphone lightning port so that it eill get charge
- So adapter is basically providing us an interface and we are making feature of charging from the c-type charger as well.
- So basically we will create a main class, Iphone class, AppleCharger Interface
- ```java
    // AppleCharger.java
    package design.adapter_pattern;

    public interface AppleCharger {
        void chargePhone();
    }

    // Iphone14.java
    package design.adapter_pattern;

    public class Iphone14 {
        private AppleCharger appleCharger;

        public Iphone14(AppleCharger appleCharger){
            this.appleCharger = appleCharger;
        }
        public void chargeIphone(){
            appleCharger.chargePhone();
        }
    }

    // ChargerXYZ.java
    package design.adapter_pattern;

    public class AppleChargerImpl implements AppleCharger{
        @Override
        public void chargePhone(){
            System.out.println("Your Iphone is Charging");
        }
    }

    // Demo.java
    package design.adapter_pattern;

    public class Demo {
        public static void main(String[] args) {
            System.out.println("Program Started");

            AppleCharger charger = new AppleChargerImpl();

            Iphone14 iphone14 = new Iphone14(charger);
            
            iphone14.chargeIphone(); // Your Iphone is Charging
        }
    }
  ```
- So in Demo class when we created an iphone then we provided it charger which is a AppleCharger and whenever iphone14 will get discharge the we need to call `iphone14.chargeIphone()` and then AppleCharger will get invoke and it will start charging the phone.
- But what if the case is iphone charger is not available. So we will create a AndroidCharger interface first and then we will look into this scenario.
- ```java
    // AndroidCharger.java
    package design.adapter_pattern;

    public interface AndroidCharger {
 
        void chargeAndroidPhone();
    }

    // AndroidChargerImpl.java
    package design.adapter_pattern;

    public class AndroidChargerImpl implements AndroidCharger{
        @Override
        public void chargeAndroidPhone() {
            System.out.println("Charging your Android Phone");
        }
    }
  ```
- So to consider that we do not have apple charger, the available phone is Iphone14 i.e `AppleCharger charger = ---------------`. So that now we will create a adpater so that our apple iphone can get charge with help of this adapter and for this we need a `AdapterCharger` class.
- ```java
    // AdapterCharger.java
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
  ```
- This class is implementing `AppleCharger` interface that means it is going provide implementation for charging the Iphone method also we are passing the `AndroidCharger` object in the constructor and calling the `chargeAndroidPhone` in the `chargePhone` method so that inside the iphone charging method the response will be of charging the android phone.
- Means the AdapterCharger class is doing both the things becoz it is implemnting the `AppleCharger` interface we can give this class directly impl of AppleChargr class also internally it is using Android Charger to charge Iphone.
- Now we can use this in the Constructor of the `Demo.java`
- ```java
    package design.adapter_pattern;

    public class Demo {
        public static void main(String[] args) {
            System.out.println("Program Started");

            AppleCharger charger = new AdapterCharger(new AndroidChargerImpl());

            Iphone14 iphone14 = new Iphone14(charger);

            iphone14.chargeIphone();
        }
    }
  ```