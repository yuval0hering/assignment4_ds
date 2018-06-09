public class Spam {
    private String word;
    private double percentage;
    //constructor
    public Spam(String word, String percentage){
        this.word=word;
        this.percentage=Double.parseDouble(percentage);
    }
    //getters:
    public String getWord() {
        return word;
    }
    public double getPercentage() {
        return percentage;
    }
}
