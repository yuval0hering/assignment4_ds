public class HashListElement {
    private String word;
    private int counter;

    //constructor of a new HashListElement
    public HashListElement(String word){
        this.word=word;
        this.counter=1;
    }

    //increasing the number of appearances of a word
    public void addAppearance(){
        this.counter++;
    }

    //return the word of the HashListElement
    public String getWord(){
        return this.word;
    }

    //return the number of appearances of the word
    public int getCounter(){
        return this.counter;
    }
}
