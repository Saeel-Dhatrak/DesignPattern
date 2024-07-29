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
        Thread.sleep(2000);
        // It will take 5 minutes
    }

    @Override
    public String toString(){
        return this.ip + " : " + this.importantData + " : " + this.domains;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException{
        // logic for cloning
        NetworkConnection networkConnection = new NetworkConnection();
        networkConnection.setIp(this.getIp());
        networkConnection.setImportantData(this.getImportantData());

        for(String d : this.getDomains()){
            networkConnection.getDomains().add(d);
        }
        return networkConnection;
    }
}
