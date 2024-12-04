import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadThreader implements Runnable {
    String name;
    ObjectInputStream ois;
    Thread t;
    ReadThreader(ObjectInputStream ois,String name)
    {
        this.ois=ois;
        this.name=name;
        t=new Thread(this);
        t.start();
    }

    @Override
    public void run() {

        while (true)
        {

            try {
                Object msg=ois.readObject();
                String enmsg=(String) msg;
                System.out.println(name+"Received Encrypted message from Server: "+enmsg);
                AES aes=new AES();
                String decrmsg = aes.decrypt(enmsg);
                System.out.println("Decrypted message: "+decrmsg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
