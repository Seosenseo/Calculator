import java.util.Arrays;
import java.util.List;

public class Calc {
    private int num1, num2;
    private String operator;

    private int calcExp(int n1, String operator, int n2) {
        int res;
        switch (operator) {
            case "+":
                res = n1 + n2;
                break;
            case "-":
                res = n1 - n2;
                break;
            case "*":
                res = n1 * n2;
                break;
            case "/":
                res = n1 / n2;
                break;
            default:
                throw new AssertionError();
        }
        return res;
    }


    public String result(String expression) throws CalcException {
        boolean isRomanExp;
        Parse parse = new Parse();

        List<String> listItems = Arrays.asList(expression.split(" "));

        if (listItems.size() != 3) {
            throw new CalcException("ОШИБКА. Выражение должно иметь вид: <Число1 Операция Число2>, разделенные пробелом...");
        }

        if (parse.checkOperator(listItems.get(1))) {
            operator = listItems.get(1);
        } else {
            throw new CalcException("ОШИБКА. Оператор '" + listItems.get(1) + "' не корректен");
        }

        if (parse.isNumeric(listItems.get(0)) && parse.isNumeric(listItems.get(2))) {
            num1 = Integer.parseInt(listItems.get(0));
            num2 = Integer.parseInt(listItems.get(2));
            isRomanExp = false;
        } else if (parse.isRoman(listItems.get(0)) && parse.isRoman(listItems.get(2))) {
            num1 = parse.romeToArabConvert(listItems.get(0));
            num2 = parse.romeToArabConvert(listItems.get(2));
            isRomanExp = true;
        } else {
            throw new CalcException("ОШИБКА. Числа должны быть оба арабские или оба римские");
        }


        if (!(num1 >= 1 && num1 <= 10)) {
            throw new CalcException("ОШИБКА. Число #1 должно быть от 1 до 10 или от I до X включительно");
        }

        if (!(num2 >= 1 && num2 <= 10)) {
            throw new CalcException("ОШИБКА. Число #2 должно быть от 1 до 10 или от I до X включительно");
        }

        int res = calcExp(num1, operator, num2);

        if (isRomanExp) {
            String sign = res < 0 ? "-" : "";
            return sign + parse.arabToRomeConvert(Math.abs(res));
        }

        //--- возвращаем ответ - арабское число
        return String.valueOf(res);
    }

}
