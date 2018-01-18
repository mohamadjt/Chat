package SimpleChat;

import java.io.IOException;
import java.util.Scanner;

public class Client implements Sender , Receiver
{
    private Sender sender;
    private Receiver receiver;

    public Client(int senderPort, int receiverPort, String ip)
    {
        sender = new SenderImp(senderPort , ip);
        receiver = new ReceiverImp(receiverPort);
    }

    public static void main(String[] args) throws IOException
    {
     Client client = new Client(7777,8888,"localhost");
     Scanner scanner =new Scanner(System.in);
        String s ;
        try
        {
            while (true)
            {
                s = scanner.nextLine();
                if (s.equals(".close"))
                {
                    break;
                }
                client.send(s);
                System.out.println("Server says : "+ client.receive());
            }
        }
        catch(Exception e)
        {
        }
        scanner.close();
    }

    public void close()
    {
    }

    @Override
    public String receive()
    {
        return receiver.receive();
    }

    @Override
    public void send(String string)
    {
        sender.send(string);
    }
}
