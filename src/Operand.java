import java.util.Arrays;
import java.util.HashSet;

/* Operand - класс обертка для чисел.
    Через него можно получить представления числа в тип int,
    а также определить тип вводимого числа: Numbertypes.ROMAN, NumberTypes.ARABIC
* */
class Operand {

    // используя HashSet мы заранее ограничили ввод данных по заданным операндам
    // так мы отсеем и числа с плавающей запятой и т.д
    private static final HashSet<String> _arabicNumbers = new HashSet<>(
            Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
    );
    private static final  HashSet<String> _romanNumbers = new HashSet<>(
            Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X")
    );

    private String _number;
    private NumberTypes _type;

    Operand(String number)
    {
        _number = number;
    }

    public  NumberTypes getNumberType() throws NumberFormatException
    {
        if(_type != null)
            return _type;

        if(this.isArabic())
            _type =  NumberTypes.ARABIC;
        else if(this.isRoman())
            _type = NumberTypes.ROMAN;
        else
            throw new NumberFormatException("Заданный операнд " + _number + " недоступен");

        return _type;
    }

    public int getIntNumber() throws Exception
    {
        String arabicNumber = _number;
        if(this.getNumberType() == NumberTypes.ROMAN)
        {
            arabicNumber = NumberSystemConverter.convertRomanToArabic(_number);
        }

        return  Integer.parseInt(arabicNumber);
    }

    private boolean isRoman()
    {
        return _romanNumbers.contains(_number);
    }

    private boolean isArabic()
    {
        return _arabicNumbers.contains(_number);
    }
}
