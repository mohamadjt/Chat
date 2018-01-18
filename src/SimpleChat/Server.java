package SimpleChat;

import java.util.Scanner;

/**
 * Created by Hamed-Abbaszadeh on 12/28/2017.
 */
public class Server implements Sender, Receiver
{
    private Sender sender = null;
    private Receiver receiver = null;

    public Server(String ip, int senderPort, int receiverPort)
    {
        receiver = new ReceiverImp(receiverPort);
        sender = new SenderImp(senderPort, ip);
    }

    public static void main(String[] args)
    {
        Server server = new Server("localhost", 8888, 7777);
        Scanner scanner = new Scanner(System.in);
        String s;
        try
        {
            while (true)
            {
                System.out.println("client says : "+ server.receive());
                s = scanner.nextLine();
                if (s.equals(".close"))
                {
                    System.err.println("chat closed");
                    break;
                }
                server.send(s);
            }
        }
        catch (Exception e)
        {

        }

        scanner.close();
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
