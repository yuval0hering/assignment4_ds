import java.io.File;
import java.util.Scanner;


public class Spams implements Iterable<SpamIterator>{
    private Spam[] spamArray;

    //constructor
    public Spams(String path){
        generateSpams(path);
    }

    //generate spam words, inserting it into an array
    public void generateSpams(String path){
        File spamFile= new File(path);
        int counter=spamCounter(spamFile);
        spamArray= new Spam[counter];
        try{
            Scanner sc = new Scanner(spamFile);
            for (int i=0; i<counter;i++){
                String line = sc.nextLine();
                String [] spamCouple=line.split(" ");
                spamArray[i]=new Spam(spamCouple[0],spamCouple[1]);
            }
        }
        catch (Exception e){
            throw new IllegalArgumentException();
        }

    }

    //the method above is using this method to know how many spam words there are
    public int spamCounter(File spamFile){
        int counter=0;
        try{
            Scanner scCounter = new Scanner(spamFile);
            while (scCounter.hasNext()){
                scCounter.nextLine();
                counter++;
            }
        }
        catch (Exception e){
            throw new IllegalArgumentException();
        }
        return counter;
    }

    //return the array of spam words
    public Spam[] getSpamArray() {
        return spamArray;
    }


    public Iterator<SpamIterator> iterator() {
        SpamIterator spamIterator=new SpamIterator((Spam[])this.spamArray);
        return spamIterator;
    }
}
