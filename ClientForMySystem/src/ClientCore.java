import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * создание клиента со всеми необходимыми утилитами, точка входа в программу в классе Client
 */

class ClientSomthing {
    
	public static LinkedList<Player> allPlayers = new LinkedList<>();
	
	public static byte newMT = 3;
	public static byte wordMT = 1;
	public static byte koordsMT = 2;
	public static byte absNew = 4;
	public static byte massivMT = 5;
	
	//public static int playersCount = 0;
	
    public Socket socket;
    //private BufferedReader in; // поток чтения из сокета
    //private BufferedWriter out; // поток чтения в сокет
    private DataOutputStream send;
    private DataInputStream read;
    private BufferedReader inputUser; // поток чтения с консоли
    private String addr; // ip адрес клиента
    private int port; // порт соединения
    private Date time;
    private String dtime;
    private SimpleDateFormat dt1;
    
    /**
     * для создания необходимо принять адрес и номер порта
     *
     * @param addr
     * @param port
     */
    
    public ClientSomthing(String addr, int port) {
        this.addr = addr;
        this.port = port;
        try {
            this.socket = new Socket(addr, port);
        } catch (IOException e) {
            System.err.println("Socket failed");
        }
        try {
            // потоки чтения из сокета / записи в сокет, и чтения с консоли
            //inputUser = new BufferedReader(new InputStreamReader(System.in));
            send = new DataOutputStream(socket.getOutputStream());
            read = new DataInputStream(socket.getInputStream());
            //in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            new GetUserInfo().start(); // перед началом необходимо спросить имя
            new ReadMsg().start(); // нить читающая сообщения из сокета в бесконечном цикле
            new WriteMsg().start(); // нить пишущая сообщения в сокет приходящие с консоли в бесконечном цикле
        } catch (IOException e) {
            // Сокет должен быть закрыт при любой
            // ошибке, кроме ошибки конструктора сокета:
            ClientSomthing.this.downService();
        }
        // В противном случае сокет будет закрыт
        // в методе run() нити.
    }
    
    /**
     * просьба ввести имя,
     * и отсылка эхо с приветсвием на сервер
     */

    private class GetUserInfo extends Thread {
        @Override
        public void run() {
            Controller.setSysText("Press your nick: ");
            try {
                while (Controller.stage == Controller.getNickname) {
                    System.out.println(Controller.stage);
                    if (Controller.stage != Controller.getNickname)
                        break;
                }
                System.out.println(ClientCore.nickname);
                Controller.setSysText("Hello, " + ClientCore.nickname);
                Controller.setSysText("Give me your password: ");
                while (Controller.stage == Controller.getPassword){
                    System.out.println(Controller.stage);
                    if (Controller.stage != Controller.getPassword)
                        break;
                }
                System.out.println(ClientCore.password);

                send.writeByte(wordMT);
                send.writeUTF(ClientCore.nickname);
                send.writeUTF(ClientCore.password);
                send.flush();
                //                while (!exit2) {
                //                    if (read.readByte() == newMT) {
                //                        int num = read.readInt();
                //                        Player.allPlayers[num].nickName = nickname;
                //                        Player.myNumber = num;
                //                        KeySys.go = true;
                //                        Controller.play = true;
                //                        exit2 = true;
                //                    }
                //                }
            } catch (IOException ignored) {

            }
        }
    }
    
    /**
     * закрытие сокета
     */
    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                read.close();
                send.close();
            }
        } catch (IOException ignored) {}
    }
    
    // нить чтения сообщений с сервера
    private class ReadMsg extends Thread {
        @Override
        public void run() {
            
            String str = "";
            try {
                while (true) {
                	byte messageType = read.readByte();
					if (messageType == wordMT) {
						str = read.readUTF();
						Controller.setSysText(str);
						//Controller.setSysText("READING");
					} 
					//Добавляем нового игрока в массив
					if (messageType == newMT) {
		            	String Imya = read.readUTF();
		            	int num = read.readInt();
		            	//Player.allPlayers[num].nickName = Imya;
		            	Controller.setSysText("CREATED");
		            }
					//Получаем координаты для каждого игрока
					if (messageType == koordsMT) {
						//Controller.setSysText("ITRY");
						int curNumber = read.readInt();
						int curPx = read.readInt();
						int curPy = read.readInt();
						Player.allPlayers[curNumber].x = curPx;
						Player.allPlayers[curNumber].y = curPy;
						//for (int i=0;i<5;i++) {
							//if (i == Player.myNumber) {
							//System.out.print(Player.allPlayers[i].nickName + "|");
							//}
						//}
						/*send.writeByte(koordsMT);
	                    send.writeInt(Player.allPlayers[Player.myNumber].x);
	                    send.writeInt(Player.allPlayers[Player.myNumber].y);
	                    send.flush();*/
					}
					//Получаем предыдущих игроков
					if (messageType == massivMT) {
						String lastName = read.readUTF();
						int j = read.readInt();
						Player.allPlayers[j].nickName = lastName;
					}
                   
                    
                    if (str.equals("stop")) {
                        ClientSomthing.this.downService(); // харакири
                        break; // выходим из цикла если пришло "stop"
                    }
                    //System.out.println(str); // пишем сообщение с сервера на консоль
                }
            } catch (IOException e) {
                ClientSomthing.this.downService();
            }
        }
    }
    
    // нить отправляющая сообщения приходящие с консоли на сервер
    public class WriteMsg extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    if (socket.isClosed() && Controller.stage == Controller.chat) {
                        Controller.stage = Controller.talk;
                    }

                    //out.write("(" + dtime + ") " + nickname + ": " + "A" + "\n");
                    //out.flush();
                    time = new Date(); // текущая дата
                    dt1 = new SimpleDateFormat("HH:mm:ss"); // берем только время до секунд
                    dtime = dt1.format(time); // время
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
//                        send.writeByte(koordsMT);
//                        send.writeInt(Player.allPlayers[Player.myNumber].x);
//                        send.writeInt(Player.allPlayers[Player.myNumber].y);
//                        send.flush();

                    //userWord = Controller.word; // сообщения с консоли

                    if (ClientCore.userWord.equals("stop")) {
                        //out.write("stop" + "\n");
                        ClientSomthing.this.downService(); // харакири
                        break; // выходим из цикла если пришло "stop"
                    } else {
                        while(!Controller.enterPressed){
                            System.out.println(Controller.enterPressed);
                        }
                        Controller.enterPressed = false;
                        //System.out.println("HERE");
                        //Player pl = new Player("(" + dtime + ") " + nickname + ": " + ClientCore.userWord + "\n");
                        //Controller.setYourMassage("You say: " + ClientCore.userWord);
                        //System.out.println("send byte");
                        send.writeByte(wordMT);
                        //System.out.println("send word, client number: " + Player.myNumber);
                        send.writeUTF("(" + dtime + ") " + ClientCore.nickname + ": " + ClientCore.userWord + "\n"); // отправляем на сервер
                        Controller.setYourMassage("You say: " + ClientCore.userWord);
                        ClientCore.userWord = "";
                        send.flush();
                    }


                    //Player.me.x = 100;

                    //if (Player.me.x<200) {
                    //Player.me.x++;
                    // }
                    //else {Player.me.x--;}
                    //send.writeByte(3);
                    //send.writeInt(Player.me.y);

                    //send.flush(); // чистим
                } catch (IOException e) {
                    ClientSomthing.this.downService(); // в случае исключения тоже харакири

                }
            }
        }
    }
}

public class ClientCore {
    public static String userWord;
    public static String nickname;
    public static String password;
    public static String ipAddr = "localhost";
    public static int port = 8080;
    
    /**
     * создание клиент-соединения с узананными адресом и номером порта
     *
     */
    
    public static void StartClient() {
    	//Controller.setSysText(ClientForMySys.IP);
        new ClientSomthing(ipAddr, port);//ClientForMySys.IP, ClientForMySys.port);
    }
}