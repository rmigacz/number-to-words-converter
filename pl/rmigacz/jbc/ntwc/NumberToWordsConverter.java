package pl.rmigacz.jbc.ntwc;

class NumberToWordsConverter {

  public static void main(String[] args) {
    if (args.length == 0) {
      return;
    }

    try {
      final Long input = Long.valueOf(args[0]);
      final PolishNumberToWordsConverter c = new PolishNumberToWordsConverter();
      System.out.println(c.convert(input));
    } catch (NumberFormatException e) {
      System.err.println("Argument" + args[0] + " must be a long integer number");
    }
  }
}
