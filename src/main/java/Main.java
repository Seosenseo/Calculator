import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try{
            System.out.println("Введите выражение вида: <Число1 Операция Число2>. C числами от 1 до 10 или от I до X включительно.");
            BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
            String calcString = bReader.readLine();

            Calc calc = new Calc();
            String result = calc.result(calcString);
            System.out.println("Ответ: " + result);
        }
        catch(CalcException | IOException e){

        }
    }
}
