import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while(true)
        {
            print("Введите выражение: ");
            String input = in.nextLine();
            if(input == "q")
                break;
            try
            {
                String calcResult = calc(input);
                println(input + "= " + calcResult);
            }
            catch(NumberFormatException ex)
            {
                println("Ошибка формата чисел. " + ex.getMessage());
                break;
            }
            catch(IllegalArgumentException ex)
            {
                println("Ошибка аргумента. " + ex.getMessage());
                break;
            }
            catch(Exception ex)
            {
                println(ex.getMessage());
                break;
            }
        }

        in.close();
    }

    public static String calc(String input) throws Exception
    {
        // разбиваем строку на токены
        String upperInput = input.toUpperCase(); // переводим в большой регистр для исключения ошибок с римскими цифрами
        String[] tokens = upperInput.split(" ");
        if(tokens.length < 3)
            throw new Exception("Вводная строка не является математической операцией");
        if(tokens.length > 3)
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор");

        // разбиваем выражение на операнд / оператор
        String operand1String = tokens[0];
        String operation = tokens[1];
        String operand2String = tokens[2];

        // переводим операнды в объекты класса Operand
        Operand operand1 = new Operand(operand1String);
        Operand operand2 = new Operand(operand2String);

        // получаем типы
        // если тип не будет найден, выскочит ошибка NumberFormatException
        NumberTypes operand1Type = operand1.getNumberType();
        NumberTypes operand2Type = operand2.getNumberType();
        if(operand1Type != operand2Type)
        {
            throw new Exception("Невозможно использовать одновременно разные системы счисления");
        }

        // получаем числа в формате integer
        int number1 = operand1.getIntNumber();
        int number2 = operand2.getIntNumber();

        // выполняем математическую операцию
        int intResult = MathOperations.executeOperation(number1, number2, operation);
        // приводим результат в строковой формат
        String result = NumberSystemConverter.convertIntToArabic(intResult);
        // если использовались римские числа, то и результатом будут римские числа
        if(operand1Type == NumberTypes.ROMAN)
        {
            result = NumberSystemConverter.convertIntToRoman(intResult);
        }

        return result;// возвращает результат в строковом виде
    }

    private static void print(String mess)
    {
        System.out.print(mess);
    }

    private static void println(String mess)
    {
        System.out.println(mess);
    }
}
