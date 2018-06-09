public class HashList {
    private LinkedList<HashListElement> wordsList;

    //constructor of HashList
    public HashList(){
        this.wordsList=new LinkedList<HashListElement>();
    }

    //adding a new word to a link in the HashList
    public void add(HashListElement element){
        this.wordsList.addFirst(element);
    }

    //return the size of the HushList
    public int getSize(){
        return wordsList.size();
    }

    //return the HushListElement located in the index received
    public HashListElement getElement(int index){
        return wordsList.get(index);
    }
}
