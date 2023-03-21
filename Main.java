import java.util.Scanner;
import java.util.HashMap;
class Main {
  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in); 
    String line = scanner.nextLine(); 
    scanner.close();
    String[] parts = line.trim().split(" ");
    if (parts.length != 3) {
      throw new Exception("Error: calculator expected ONLY 3 arguments, example: `1 + 1`");
    }
    String a = parts[0];
    String op = parts[1];
    String b = parts[2];
    boolean isRomanResult = false;
    int x = 0;
    int y = 0;
    int result = 0;
    if (isArab(a) && isArab(b)) {
      x = Integer.parseInt(a);
      y = Integer.parseInt(b);
    }
    else if (isRoman(a) && isRoman(b)) {
      x = fromRoman(a);
      y = fromRoman(b);
      isRomanResult = true;
    }
    else {
      throw new Exception("Error: Cannot parse your input, Use ONLY Arabic or Roman form for 2 numbers, example: `1 + 1` or `VI - II`");
    }
    if ((x < 1 || x > 10) || (y < 1 || y > 10)) {
      throw new Exception("Error: Calculator accepts only 1,2,3..10 range of numbers");
    }
    switch (op) {
      case "+":
        result = x + y; 
        break;
      case "-":
        result = x - y; 
        break;
      case "/":
        result = x / y; 
        break;
      case "*":
        result = x * y; 
        break;
    
      default:
        throw new Exception("Can't use your operator");
    }
    if (isRomanResult) {
      if (result > 0) {
        System.out.println(toRoman(result));
      } else {
        throw new Exception("Error: result < 0");
      }
    } else {
      System.out.println(result);
    }
  }

  public static boolean isArab(String strNum) {
    if (strNum == null) {
      return false;
    }
    try {
      int d = Integer.parseInt(strNum);
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }
  public static boolean isRoman(String strNum) {
    String romanChars = "IVXLCDM";
    for (int i = 0; i < strNum.length(); i++) {
      if (romanChars.indexOf(strNum.charAt(i)) == -1) {
        return false;
      }
    }
    return true;
  }

  public static String toRoman(int num) {
    HashMap<Integer, String> numInv = new HashMap<Integer, String>();
    numInv.put(1000, "M");
	  numInv.put(900, "CM");
		numInv.put(500, "D");
		numInv.put(400, "CD");
		numInv.put(100, "C");
		numInv.put(90, "XC");
		numInv.put(50, "L");
		numInv.put(40, "XL");
		numInv.put(10, "X");
		numInv.put(9, "IX");
		numInv.put(5, "V");
		numInv.put(4, "IV");
		numInv.put(1, "I");
    String out = "";
    while (num > 0) {
      int v = highestDecimal(num);
      out += numInv.get(v);
      num -= v;
    }
    return out;
  }
  private static int highestDecimal(int num) {
    int[] maxTable = {
      1000,
      900,
      500,
      400,
      100,
      90,
      50,
      40,
      10,
      9,
      5,
      4,
      1,
    };
    for (int v : maxTable) {
      if (v <= num) {
        return v;
      }
    }
    return 1; 
  }
  public static int fromRoman(String strNum) {
    HashMap<Character, Integer> num = new HashMap<Character, Integer>();
    num.put('I', 1);
    num.put('V', 5);
    num.put('X', 10);
    num.put('L', 50);
    num.put('C', 100);
    num.put('D', 500);
    num.put('M', 1000);
    int out = 0;
    for (int i = 0; i < strNum.length(); i++) {
      int vc = num.get(strNum.charAt(i));
      if (i < strNum.length()-1) {
        int vcnext = num.get(strNum.charAt(i+1));
        if (vc < vcnext) {
          out += vcnext - vc;
          i++;
        } else {
          out += vc;
        }
      } else {
        out += vc;
      }
    }
    return out;
  }
}