class NumberSystemConverter {
    private static final int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    private static final String[] romanLiterals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    public static String convertIntToRoman(int num) {
        if(num <= 0)
            throw new IllegalArgumentException("Римские числа не могут быть меньше нуля");

        StringBuilder roman = new StringBuilder();
        for(int i=0;i<values.length;i++) {
            while(num >= values[i]) {
                num -= values[i];
                roman.append(romanLiterals[i]);
            }
        }
        return roman.toString();
    }

    public static int convertRomanToInt(String roman)
    {
        int len = roman.length();
        if(len == 1)
        {
            return getRomanValue(roman.charAt(0));
        }
        int sum = 0;
        char lastChar = ' ';
        for (int i = 0; i< len; i++)
        {
            char el = roman.charAt(i);
            // если тот же символ или он последний, добавляем значение
            if(el == lastChar || i == len - 1)
            {
                sum += getRomanValue(el);
                lastChar = el;
                continue;
            }
            // иначе смотри и последующие числа тоже
            char nextEl = roman.charAt(i+1);
            int roman1 = getRomanValue(el);
            int roman2 = getRomanValue(nextEl);
            int curSum = 0;
            if(roman2 > roman1)  curSum = roman2 - roman1;
            else  curSum = roman2 + roman1;
            sum+= curSum;
            lastChar = nextEl;
            i++;
        }

        return sum;
    }

    public static String convertIntToArabic(int num)
    {
        return String.valueOf(num);
    }

    public static String convertArabicToRoman(String arabic)
    {
        int num = Integer.parseInt(arabic);
        return convertIntToRoman(num);
    }

    public static String convertRomanToArabic(String roman)
    {
        int num = convertRomanToInt(roman);
        return String.valueOf(num);
    }

    private static int getRomanValue(char roman) throws NumberFormatException
    {
        if(roman == 'I') return 1;
        else if(roman == 'V') return 5;
        else if(roman == 'X') return 10;
        else if(roman == 'L') return 50;
        else if(roman == 'C') return 100;
        else if(roman == 'D') return 500;
        else if(roman == 'M') return 1000;

        throw new NumberFormatException("Операнд " + roman + " неизвестен");
    }
}
