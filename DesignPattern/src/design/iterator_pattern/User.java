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
