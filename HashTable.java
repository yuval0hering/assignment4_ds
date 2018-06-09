public class HashTable {
    private HashList[] table;
    private String[] contentArray;
    private int m;

    //constructor of a HashTable
    public HashTable(int m){
        this.m=m;
        this.table=new HashList[this.m];
        for (int i=0; i<this.m; i++){
            table[i]=new HashList();
        }
    }

    //inserting words from message to the message's HashTable
    public void insert(Message message){
        contentArray=message.getContent().split(" ");
        message.setMessageSize(contentArray.length);
        for (int i=0; i<contentArray.length; i++){
            if (search(contentArray[i])==null){
                HashListElement element=new HashListElement(contentArray[i]);
                table[hashFunction(contentArray[i])].add(element);
            }
            else{
                search(contentArray[i]).addAppearance();
            }
        }
    }

    //searching if the word already exists in the message, if it does, return the HashListElement
    public HashListElement search(String spamWord){
        HashList list =table[hashFunction(spamWord)];
        for (int i=0; i<list.getSize();i++){
            if (list.getElement(i).getWord().equals(spamWord))
                return list.getElement(i);
        }
        return null;
    }

    //The function is converting the word to the sum of the Ascii characters
    //and according to that and the size of the table determine the location of the word
    public int hashFunction(String word){
        int index=0;
        int wordToAscii=0;
        for(int i=0;i<word.length();i++){
            wordToAscii=wordToAscii+(int)word.charAt(i);
        }
        index=wordToAscii%this.m;
        return index;
    }
}
