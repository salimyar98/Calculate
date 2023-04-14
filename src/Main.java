import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите выражение в формате 'число операция число': ");

        String input = scanner.nextLine();

        String[] elements = input.split(" ");

        if (elements.length != 3) {
            System.out.println("Ошибка ввода выражения.");
            return;
        }

        String first = elements[0];
        String second = elements[2];
        String operation = elements[1];

        boolean isArabic = true;

        try {
            Integer.parseInt(first);
            Integer.parseInt(second);
        } catch (NumberFormatException e) {
            isArabic = false;
        }

        if (isArabic) {
            int a = Integer.parseInt(first);
            int b = Integer.parseInt(second);
            int result;

            if (a > 10 || b > 10) {
                System.out.println("Одно из чисел больше 10.");
                return;
            }

            switch (operation) {
                case "+":
                    result = a + b;
                    System.out.println(result);
                    break;

                case "-":
                    result = a - b;
                    System.out.println(result);
                    break;

                case "*":
                    result = a * b;
                    System.out.println(result);
                    break;

                case "/":
                    if (b == 0) {
                        System.out.println("Ошибка: деление на 0.");
                    } else {
                        result = a / b;
                        System.out.println(result);
                    }
                    break;

                default:
                    System.out.println("Ошибка: неизвестная операция.");
                    break;
            }
        } else {
            int a = RomanNumeral.convertToArabic(first);
            int b = RomanNumeral.convertToArabic(second);

            if (a == -1 || b == -1) {
                System.out.println("Ошибка: неверный формат римских чисел.");
                return;
            }

            if (a > 10 || b > 10) {
                System.out.println("Одно из чисел больше X.");
                return;
            }

            int result;

            switch (operation) {
                case "+":
                    result = a + b;
                    System.out.println(RomanNumeral.convertToRoman(result));
                    break;

                case "-":
                    result = a - b;
                    System.out.println(RomanNumeral.convertToRoman(result));
                    break;

                case "*":
                    result = a * b;
                    System.out.println(RomanNumeral.convertToRoman(result));
                    break;

                case "/":
                    if (b == 0) {
                        System.out.println("Ошибка: деление на 0.");
                    } else {
                        result = a / b;
                        System.out.println(RomanNumeral.convertToRoman(result));
                    }
                    break;

                default:
                    System.out.println("Ошибка: неизвестная операция.");
                    break;
            }
        }

        scanner.close();
    }

}

class RomanNumeral {

    public static int convertToArabic(String roman) {
        int result = 0;

        for (int i = 0; i < roman.length(); i++) {
            char letter = roman.charAt(i);
            int value = letterToValue(letter);

            if (value == -1) {
                return -1;
            }

            if (i + 1 < roman.length()) {
                int nextValue = letterToValue(roman.charAt(i + 1));

                if (nextValue == -1) {
                    return -1;
                }

                if (value < nextValue) {
                    result -= value;
                } else {
                    result += value;
                }
            } else {
                result += value;
            }
        }

        return result;
    }

    public static String convertToRoman(int number) {
        if (number < 1 || number > 3999) {
            return "Ошибка: число не может быть представлено римскими цифрами.";
        }

        String result = "";

        while (number >= 1000) {
            result += "M";
            number -= 1000;
        }

        if (number >= 900) {
            result += "CM";
            number -= 900;
        }

        if (number >= 500) {
            result += "D";
            number -= 500;
        }

        if (number >= 400) {
            result += "CD";
            number -= 400;
        }

        while (number >= 100) {
            result += "C";
            number -= 100;
        }

        if (number >= 90) {
            result += "XC";
            number -= 90;
        }

        if (number >= 50) {
            result += "L";
            number -= 50;
        }

        if (number >= 40) {
            result += "XL";
            number -= 40;
        }

        while (number >= 10) {
            result += "X";
            number -= 10;
        }

        if (number >= 9) {
            result += "IX";
            number -= 9;
        }

        if (number >= 5) {
            result += "V";
            number -= 5;
        }

        if (number >= 4) {
            result += "IV";
            number -= 4;
        }

        while (number >= 1) {
            result += "I";
            number -= 1;
        }

        return result;
    }

    private static int letterToValue(char letter) {
        switch (letter) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return -1;
        }
    }

}
