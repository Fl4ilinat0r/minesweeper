import java.util.Scanner;

/**
 * Created by admin on 14/01/2015.
 */
public class FieldTest {
    public static void main(String[] args) {

        Field field = new Field(10,10,30);
        Scanner keyboard = new Scanner(System.in);

        field.Show();
       // field.addMine();

        System.out.println(" ");
        field.CalcNumbers();

        System.out.println(" ");
        field.Show();

        Boolean keepGoing = true;
        int x = 0;
        int y = 0;
        String answ = "";
        while(keepGoing == true){

            System.out.println("give x cord");
            x = keyboard.nextInt();

            System.out.println("give y cord");
            y = keyboard.nextInt();

            field.click(x -1 ,y - 1);

            System.out.println("stop ?");
            answ = keyboard.next();

            if(answ == "yes"){
                keepGoing = false;
            }
            field.Show();

        }





    }

}
