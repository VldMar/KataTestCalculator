class MathOperations {
    public static int executeOperation(int number1, int number2, String operation) throws Exception
    {
        switch(operation){
            case "+":
                return sum(number1, number2);
            case "*":
                return mult(number1, number2);
            case "/":
                return divide(number1, number2);
            case "-":
                return substract(number1, number2);
            default:
                throw new Exception("Математическая операция " + operation + " недоступна!");
        }
    }
    // Складывает числа
    public static int sum(int number1, int number2)
    {
        return number1 + number2;
    }
    // Вычитает числа
    public static int substract(int number1, int number2) {
        return number1 - number2;
    }
    // Делит числа
    public static int divide(int number1, int number2) throws Exception
    {
        if(number2 == 0)
            throw new Exception("Деление на ноль запрещено");

        return  number1 / number2;
    }
    // Умножает числа
    public static int mult(int number1, int number2)
    {
        return  number1 * number2;
    }
}
