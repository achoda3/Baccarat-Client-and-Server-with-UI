import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;



public class Client extends Thread{

	
	Socket socketClient;
	String ip;
	int port;
	ObjectOutputStream out;
	ObjectInputStream in;
	
	private Consumer<Serializable> callback;
	
	Client(Consumer<Serializable> call,String ip, int port){
	
		callback = call;
		this.ip=ip;
		this.port=port;
	}
	
	
	public void run() {
		
		try {
		socketClient= new Socket(ip,port);
	    out = new ObjectOutputStream(socketClient.getOutputStream());
	    in = new ObjectInputStream(socketClient.getInputStream());
	    socketClient.setTcpNoDelay(true);
		}
		catch(Exception e) {}
		
		while(true) {
			 
			try {
			BaccaratInfo info;
			info = (BaccaratInfo)in.readObject();
			callback.accept(info);
			}
			catch(Exception e) {}
		}
	
    }
	
	public void send(BaccaratInfo info) {
		
		try {
			out.writeObject(info);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
