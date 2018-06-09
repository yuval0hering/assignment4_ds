public class Message {
    private String from;
    private String to;
    private String content;
    private HashTable messageHashTable;
    private int messageSize =0;

    //constructor
    public Message(String from, String to, String content){

        this.from=from.substring(from.indexOf(':')+1);
        this.to=to.substring(to.indexOf(':')+1);
        this.content=content;
    }

    //getters:
    public String getContent(){
        return this.content;
    }
    public String getFrom() { return from; }
    public String getTo() {
        return to;
    }
    public int getMessageSize(){
        return messageSize;
    }
    public HashTable getMessageHashTable() {
        return messageHashTable;
    }

    //set message size
    public void setMessageSize(int size){
        this.messageSize =size;
    }

    //create hash table for the message.
    public HashTable createHashTable(int m){
        this.messageHashTable= new HashTable(m);
        return this.messageHashTable;
    }


}
