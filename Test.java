import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        int i = 0;
        
        Scanner in;
        String command = "";
        while (i <200 ) {
            System.out.println(i);
            in = new Scanner(System.in);
            command = in.nextLine();     
            i++;
        }
    }
    
}
