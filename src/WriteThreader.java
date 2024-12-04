import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLOutput;
import java.util.Scanner;

public class WriteThreader implements Runnable{
    String name;
    ObjectOutputStream oos;
    Thread t;
    WriteThreader(ObjectOutputStream oos,String name){
        this.oos=oos;
        this.name=name;
        t=new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        Scanner sc=new Scanner(System.in);
        while (true)
        {
            System.out.println("Enter message:");
            String msg=sc.nextLine();
            AES aes=new AES();
            String encryptmsg=aes.encrypt(msg);
            System.out.println("Encrypted message: "+encryptmsg);
            try {
                oos.writeObject(encryptmsg);
                System.out.println("Message sent...");
            } catch (IOException e) {
                throw new RuntimeException(e);

            }
        }


    }
}
