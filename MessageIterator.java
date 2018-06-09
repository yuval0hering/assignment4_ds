//implementation to Iterator interface for messages.
public class MessageIterator<Message> implements Iterator<Message>{

    private int cnt;
    private Message[] messages;

    public MessageIterator(Message[] messages){
        this.messages=messages;
        this.cnt=0;
    }
    public boolean hasNext(){
        return (this.cnt<this.messages.length);//checks how many time we run the loop.
    }
    public Message next(){
        if(!hasNext()){
            throw new NullPointerException();
        }
        cnt++;
        return messages[cnt-1];// returns the message at the current place.
    }
}
