package pl.rmigacz.jbc.ntwc;

class PolishNumberToWordsConverter {

  private static final String MINUS = "minus";

  private static final String ZERO = "zero";

  //@formatter:off
  private static final String[] ONES = { 
      "", 
      "jeden",
      "dwa",
      "trzy",
      "cztery",
      "pięć", 
      "sześć", 
      "siedem", 
      "osiem", 
      "dziewięć"
  };

  private static final String[] TEENS = {
      "", 
      "jedenaście", 
      "dwanaście", 
      "trzynaście",
      "czternaście", 
      "piętnaście", 
      "szesnaście", 
      "siedemnaście", 
      "osiemnaście", 
      "dziewiętnaście"
  };

  private static final String[] TENS = {
      "", 
      "dziesięć", 
      "dwadzieścia", 
      "trzydzieści", 
      "czterdzieści", 
      "pięćdziesiąt",
      "sześćdziesiąt", 
      "siedemdziesiąt", 
      "osiemdziesiąt", 
      "dziewięćdziesiąt"
  };

  private static final String[] HUNDREADS = {
      "", 
      "sto", 
      "dwieście", 
      "trzysta", 
      "czterysta", 
      "pięćset",
      "sześćset", 
      "siedemset", 
      "osiemset", 
      "dziewięćset"
  };

  private static final String[][] GRAMMATICAL_FORMS = {
      {"", "", ""},
      {"tysiąc", "tysiące", "tysięcy"}, 
      {"milion", "miliony", "milionów"},
      {"miliard", "miliardy", "miliardów"}, 
      {"bilion", "biliony", "bilionów"},
      {"biliard", "biliardy", "biliardów"}, 
      {"trylion", "tryliony", "trylionów"}
  };
  //@formatter:on 

  public String convert(long number) {
    if (number == 0) {
      return ZERO;
    }

    final StringBuilder stringBuilder = new StringBuilder(number < 0 ? MINUS : "");
    final int stringBuilderStartIndex = number < 0 ? MINUS.length() : 0;

    int hundreadsDigit = 0;
    int tensDigit = 0;
    int teens = 0;
    int onesDigit = 0;

    int groupNumber = 0;
    int grammaticalForm = 0;

    while (number != 0) {
      hundreadsDigit = (int) Math.abs((number % 1000) / 100);
      tensDigit = (int) Math.abs((number % 100) / 10);
      onesDigit = (int) Math.abs(number % 10);

      if (tensDigit == 1 && onesDigit > 0) {
        teens = onesDigit;
        tensDigit = 0;
        onesDigit = 0;
      } else {
        teens = 0;
      }

      if (onesDigit == 1 && hundreadsDigit + tensDigit + teens == 0) {
        grammaticalForm = 0;
      } else if (onesDigit == 2 || onesDigit == 3 || onesDigit == 4) {
        grammaticalForm = 1;
      } else {
        grammaticalForm = 2;
      }

      if (hundreadsDigit + tensDigit + teens + onesDigit > 0) {
        final String words =
            String.format(" %s %s %s %s %s", HUNDREADS[hundreadsDigit], TENS[tensDigit],
                TEENS[teens], ONES[onesDigit], GRAMMATICAL_FORMS[groupNumber][grammaticalForm]);
        stringBuilder.insert(stringBuilderStartIndex, words);
      }

      groupNumber++;
      number /= 1000;
    }

    return stringBuilder.toString().trim().replaceAll(" +", " ");
  }

}
