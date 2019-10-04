import java.io.*;
import java.net.*;
import java.util.LinkedList;

/**
 * проект реализует консольный многопользовательский чат.
 * вход в программу запуска сервера - в классе Server.
 * @author izotopraspadov, the tech
 * @version 2.0
 */

class ServerSomthing extends Thread {
	
	public static byte newMT = 3;
	public static byte wordMT = 1;
	public static byte koordsMT = 2;
	public static byte absNew = 4;
	public static byte massivMT = 5;
	public static byte sendOldKoords = 6;
	
	static public boolean createPls = false;
	public static boolean found = false;
	
	public int number;
	
	public Player P;
	
    private Socket socket; // сокет, через который сервер общается с клиентом,
    // кроме него - клиент и сервер никак не связаны
    private DataInputStream reader;
    private DataOutputStream writer;
    //private BufferedReader in; // поток чтения из сокета
    //private BufferedWriter out; // поток завписи в сокет
    
    /**
     * для общения с клиентом необходим сокет (адресные данные)
     * @param socket
     * @throws IOException
     */
    
    public ServerSomthing(Socket socket) throws IOException {
    	//this.P.x = 83;
    	//this.P.y = 0;
        this.socket = socket;
        // если потоку ввода/вывода приведут к генерированию искдючения, оно проброситься дальше
        //in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        reader = new DataInputStream(socket.getInputStream());
        writer = new DataOutputStream(socket.getOutputStream());
        //ServerCore.story.printStory(out); // поток вывода передаётся для передачи истории последних 10
        // сооюбщений новому поключению
        start(); // вызываем run()
    }
    @Override
    public void run() {
    	char c = 1;
        String word = "";
        String passWord = "";
		String nickname = "";
        try {
            // первое сообщение отправленное сюда - это никнейм     ??????????!!!!!!! <------------------------!    
            
            while (c == 1) {
            	byte messageType = reader.readByte();
            	if (messageType == wordMT) {
            		nickname = reader.readUTF();
            		System.out.println("NICKNAME: " + nickname);
					passWord = reader.readUTF();
					System.out.println("PASSWORD: " + passWord);
            		//this.send(word);  
            		//Берем ник игрока
//            		char[] name = new char[word.length()];
//            		name = word.toCharArray();
//            		String absNick = "";
//            		for (int i = 7;i<word.length();i++) {
//            			absNick = absNick + name[i];
//            		}
            		Controller.setSysText(nickname + " connected!");
            		//this.P = new Player(absNick, PassWord);
            		
            		/*for (int i=0;i<Player.playersCount;i++) {
            			if(Player.allPlayers[i].passW == PassWord) {
            				writer.writeByte(sendOldKoords);
            				writer.writeInt(Player.allPlayers[i].x);
            				writer.writeInt(Player.allPlayers[i].y);
            				found = true;
            				break;
            			}
            		}
            		System.out.println("i am still here");*/
            		//Player.myNumber = this.number;
//            		if (!found) {
//	            		Player.allPlayers[this.number] = this.P;
//	            		writer.writeByte(newMT);
//	            		writer.writeInt(this.number);
//	            		writer.flush();
//            		}
            		
            		//createPls = true;
            		
            		c = 0;
            		
            		//Controller.setSysText(this.P.nickName);
            	}
            }
            /*switch(messageType)
            {
            	case 1:
            		 word = reader.readUTF();
            }*/
            
           
            
//            try {
//				byte messageType = reader.readByte();
//				if (messageType == wordMT) {
//					writer.writeByte(wordMT);
//					writer.writeUTF(word + "\n");
//					writer.flush(); // flush() нужен для выталкивания оставшихся данных
//					// если такие есть, и очистки потока для дьнейших нужд
//				}
//            } catch (IOException ignored) {}
            try {
                while (true) {
                	//Отправка сведений об игроках каждому пользователю
                	
                	
                	
                	if (createPls) {
                		//if (this.number != 0) {
                		for (int j=0;j<ServerCore.serverList.size()-1;j++) {
            				if (Player.allPlayers[j].nickName != null && j != this.number) {
            					writer.writeByte(massivMT);
            					writer.writeUTF(Player.allPlayers[j].nickName);
            					writer.writeInt(j);
            					writer.flush();
            					
            				}
            			}
                		//if (!found) {
	                		for (int i=0;i<=ServerCore.serverList.size()-1;i++) {
	                			
	                        	if (this != ServerCore.serverList.get(i)) {
	                        		ServerCore.serverList.get(i).writer.writeByte(newMT);
	                        		ServerCore.serverList.get(i).writer.writeUTF(this.P.nickName);
	                        		ServerCore.serverList.get(i).writer.writeInt(this.number);
	                        		ServerCore.serverList.get(i).writer.flush();
	                        		System.out.println("HERE" + i);
	                        	}
	                        }
                		//}
                		createPls = false;
                	}
                	
                	byte messageType2 = reader.readByte();
                	
                	/*for (int i=0;i<=ServerCore.serverList.size()-1;i++) {
            			System.out.println("I TRY");
                    	if (this != ServerCore.serverList.get(i)) {
                    		ServerCore.serverList.get(i).writer.writeByte(koordsMT);
                    		ServerCore.serverList.get(i).writer.writeInt(this.number);
                    		ServerCore.serverList.get(i).writer.writeInt(this.P.x);
                    		ServerCore.serverList.get(i).writer.writeInt(this.P.y);
                    		ServerCore.serverList.get(i).writer.flush();
                    		System.out.println("SEND KOORDS" + i);
                    	}
                    }*/
                	if (messageType2 == wordMT) {
                    	word = reader.readUTF();
                   		Controller.setSysText("Echoing: " + word);
                   		for (int i=0;i<ServerCore.serverList.size();i++) {
                        	if (this != ServerCore.serverList.get(i)) {
                        		ServerCore.serverList.get(i).send(word);
                        		//Controller.setSysText("SEND");
                        	}
                        }
                    } else
                	
                    if (messageType2 == koordsMT) {
                    	//Controller.setSysText("pervyi");
                    	int X = reader.readInt();
                    	int Y = reader.readInt();
                    	Player.allPlayers[this.number].x = X;
                    	Player.allPlayers[this.number].y = Y;
                    	
                    	for (int i=0;i<=ServerCore.serverList.size()-1;i++) {
                			//System.out.println("I TRY");
                        	if (this != ServerCore.serverList.get(i)) {
                        		ServerCore.serverList.get(i).writer.writeByte(koordsMT);
                        		ServerCore.serverList.get(i).writer.writeInt(this.number);
                        		ServerCore.serverList.get(i).writer.writeInt(this.P.x);
                        		ServerCore.serverList.get(i).writer.writeInt(this.P.y);
                        		ServerCore.serverList.get(i).writer.flush();
                        		//System.out.println("SEND KOORDS, client number: " + this.number);
                        	}
                        }
                    	
                    	//Controller.setSysText("vtoroi");
                    	//Player.playerList[number].x = d;
                    	//this.P.x = d;
                    	//Player.list[number-1].x = d;
                    	//Controller.setSysText("X: " + this.P.x + this.number);
                    }
                    	
                    	/*	
                    	case 3:
                    		Controller.setSysText(Integer.toString(reader.readInt()));//Player.playerList.get(number).y = reader.readInt();*/
                    
                    
                    if(word.equals("stop")) {
                        this.downService(); // харакири
                        break; // если пришла пустая строка - выходим из цикла прослушки
                    }
                    
                    //ServerCore.story.addStoryEl(word);
                    
                    
                    
                }
            } catch (NullPointerException ignored) {}

            
        } catch (IOException e) {
            this.downService();
        }
    }
    
    
    private void sendKoords(int koords, int identifikator) {
    	try {
			writer.writeByte(identifikator + 4);
	        writer.writeUTF(koords + "\n");
	        writer.flush();
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    /**
     * отсылка одного сообщения клиенту по указанному потоку
     * @param msg
     */
    private void send(String msg) {
    	try {
			writer.writeByte(wordMT);
	        writer.writeUTF(msg + "\n");
	        writer.flush();
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    /**
     * закрытие сервера
     * прерывание себя как нити и удаление из списка нитей
     */
    private void downService() {
            try {
            if(!socket.isClosed()) {
                socket.close();
                reader.close();
                writer.close();
                /*for (ServerSomthing vr : ServerCore.serverList) {
                    if(vr.equals(this)) vr.interrupt();
                    ServerCore.serverList.remove(this);
                }*/
                ServerCore.serverList.remove(this);
            }
        } catch (IOException ignored) {}
    }
}

/**
 * класс хранящий в ссылочном приватном
 * списке информацию о последних 10 (или меньше) сообщениях
 */

/*class Story {
    
    private LinkedList<String> story = new LinkedList<>();
  
    
    public void addStoryEl(String el) {
        // если сообщений больше 10, удаляем первое и добавляем новое
        // иначе просто добавить
        if (story.size() >= 10) {
            story.removeFirst();
            story.add(el);
        } else {
            story.add(el);
        }
    }
    
    /**
     * отсылаем последовательно каждое сообщение из списка
     * в поток вывода данному клиенту (новому подключению)
     * @param writer
     */
    
    /*public void printStory(BufferedWriter writer) {
        if(story.size() > 0) {
            try {
                writer.write("History messages" + "\n");
                for (String vr : story) {
                    writer.write(vr + "\n");
                }
                writer.write("/...." + "\n");
                writer.flush();
            } catch (IOException ignored) {}
            
        }
        
    }
}*/

//class ServerWithPlayer {
	
//}

public class ServerCore {

	static public int schetchik = 0;
	static public ServerSocket server;
	
    public static final int PORT = 8080;
    public static LinkedList<ServerSomthing> serverList = new LinkedList<>(); // список всех нитей - экземпляров
    // сервера, слушающих каждый своего клиента
    //public static Story story; // история переписки
    
    /**
     *
     * @throws IOException
     */
    
    public static void StartServer() throws IOException {
        server = new ServerSocket(PORT);
        //story = new Story();
        System.out.println("Server Started");
        try {
            while (true) {
                // Блокируется до возникновения нового соединения:
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerSomthing(socket)); // добавить новое соединенние в список
                    serverList.get(serverList.size()-1).number = schetchik;
                    //Player.playerList.add(new Player());
                    schetchik++;
                } catch (IOException e) {
                    // Если завершится неудачей, закрывается сокет,
                    // в противном случае, нить закроет его:
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}