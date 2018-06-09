import java.io.File;
import java.util.Scanner;
public class Messages implements Iterable<MessageIterator>{

    private Message[] messagesArr;
    private int m;
    private HashTable[] hashTablesArr;

    //empty constructor
    public Messages() {}

    // generate messages, inserts it to message array.
    public void generateMessages(String path) {
        File messagesFile = new File(path);
        int diezCnt=diezCounter(messagesFile); // count the number of Diez signs.
        try {Scanner sc = new Scanner(messagesFile);
            if (isFileNotEmpty(sc)) {
                this.messagesArr = new Message[diezCnt + 1]; sc.nextLine();//creates array of messages
                for (int i = 0; i < diezCnt + 1; i++) {//for each message finds the from to and content.
                    String from = sc.nextLine(); String to = sc.nextLine();String content = "";
                    String partContent = sc.nextLine();
                    while (!partContent.equals("#")) {
                        content = content + " " + partContent;
                        if (sc.hasNext()) { partContent = sc.nextLine(); }
                        else { partContent = "#";
                        }
                    }
                    this.messagesArr[i] = new Message(from, to, content);//create the message object.
                }
                sc.close();
            }
            else {messagesArr= new Message[0];
            }
        } catch (Exception e) { throw new IllegalArgumentException();
        }
    }
    //count the number of messages
    public int diezCounter (File messagesFile){
        int diezCnt=0;
        try {
            Scanner sc = new Scanner(messagesFile);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.equals("#")) {
                    diezCnt++;
                }
            }
            sc.close();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
        return diezCnt;
    }

    //creates hash tables for the messages.
    public void createHashTables(String m) {
        try {
            this.m = Integer.parseInt(m);
        }
        catch (Exception e){
            throw new IllegalArgumentException("table size should be integer");
        }
        if(this.m<1)
            throw new IllegalArgumentException("m should be positive");
        this.hashTablesArr = new HashTable[this.messagesArr.length];
        int i = 0;
        MessageIterator messageIterator = new MessageIterator(this.messagesArr);
        while (messageIterator.hasNext() && this.messagesArr.length>=1) {
            Message message = (Message) messageIterator.next();
            hashTablesArr[i] = message.createHashTable(this.m);
            hashTablesArr[i].insert(message);
            i++;
        }
    }
    //finds the spam messages
    public String findSpams(String path, BTree bTree) {
        MessageIterator messageIterator = new MessageIterator(this.messagesArr);
        Spams spamWords = new Spams(path); String spamString = "";
        int messageCounter = 0; boolean isSpam = false;
        while (messageIterator.hasNext()) {
            Message currMessage = (Message) messageIterator.next();
            int messageSize = currMessage.getMessageSize();
            if (!bTree.isFriends(currMessage.getTo(), currMessage.getFrom())) { //checks if friends
                SpamIterator spamIterator = new SpamIterator(spamWords.getSpamArray());
                while (spamIterator.hasNext() & !isSpam) {
                    Spam spam = (Spam) spamIterator.next();
                    if (currMessage.getMessageHashTable().search(spam.getWord()) != null) {//checks if the spam words appear at the message
                        double wordPercentage = ((double)(currMessage.getMessageHashTable().search(spam.getWord()).getCounter() * 100) / (double)(messageSize));
                        if (wordPercentage >= spam.getPercentage()) {
                            spamString = spamString + messageCounter + ","; isSpam = true;
                        }
                    }
                }isSpam = false;
            }messageCounter++;
        }
        if (spamString.length()>1) { return spamString.substring(0, spamString.length() - 1);
        }
        return "";
    }
    //checks if the file is empty.
    public boolean isFileNotEmpty(Scanner sc){
        return sc.hasNext();
    }


    public Iterator<MessageIterator> iterator() {
        MessageIterator messagesIterator= new MessageIterator(this.messagesArr);
        return messagesIterator;
    }
}

