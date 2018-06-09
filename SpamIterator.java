//implementation to Iterator interface for spam words.
public class SpamIterator<Spam> implements Iterator<Spam>{

    int cnt=0;
    Spam[] spamWords;

    public SpamIterator(Spam[] spamWords){
        this.spamWords=spamWords;
    }
    public boolean hasNext(){
        return (this.cnt<this.spamWords.length);//checks how many time we run the loop.
    }
    public Spam next(){
        if(!hasNext()){
            throw new NullPointerException();
        }
        cnt++;
        return spamWords[cnt-1];// returns the message at the current place.
    }
}