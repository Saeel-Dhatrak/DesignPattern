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
            networkConnection.getDomains().remove(0);
            System.out.println(networkConnection);
            System.out.println(networkConnection2);
            System.out.println(networkConnection3);
        }
        catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
    }
}
