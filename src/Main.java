import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> a=new ArrayList<>();//User objesi listesi olacak
        a.add("halil");
        a.add("burak");
        a.add("ali");
        a.add("veli");
        a.add("mehmet");

        HomePage h= new HomePage(a);

    }
}
