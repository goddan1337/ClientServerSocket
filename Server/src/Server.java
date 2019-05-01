import com.phone.Phone;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args){


        try(ServerSocket server = new ServerSocket(8000)) {

            System.out.println("Server started!");

        while (true) {
            Phone phone = new Phone(server);
            new Thread(() -> {
                String request = phone.readLine();
                System.out.println("Request: " + request);
                String response = (int) (Math.random() * 30) - 10 + "";
                try {Thread.sleep(4000);} catch (InterruptedException e) {}
                phone.writeLine(response);
                System.out.println("Response: " + response);
                try {phone.close();} catch (IOException e) {}
            }).start();
        }

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
