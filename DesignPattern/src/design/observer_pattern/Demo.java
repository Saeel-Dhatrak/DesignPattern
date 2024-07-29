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
