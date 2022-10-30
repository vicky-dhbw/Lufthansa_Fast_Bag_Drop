package livingComponents;

public class FingerPrint {
    private String[][] fingerprint;

    public void setFingerPrint(String[][] fingerprint){
        this.fingerprint=fingerprint;
    }
    public FingerPrint(){fingerprint=null;}


    public String[][] getFingerPrint(){
        return fingerprint;
    }
}
