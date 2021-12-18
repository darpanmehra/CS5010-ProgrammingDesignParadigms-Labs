package lookandsay;

import java.math.BigInteger;
import java.util.NoSuchElementException;

/**
 * An iterator that returns the next number in the sequence of numbers.
 */
public class LookAndSayIterator implements RIterator<BigInteger> {

  static private final int LARGEST_DIGIT_NUMBER = 100;
  private BigInteger start;
  private final BigInteger end;
  private BigInteger current;

  /**
   * Constructor for LookAndSayIterator with no start or end.
   */
  public LookAndSayIterator() {
    this(new BigInteger("1"));
  }

  /**
   * Constructor for LookAndSayIterator with start.
   *
   * @param start start number
   */
  public LookAndSayIterator(BigInteger start) {

    this(start, new BigInteger("9".repeat(LARGEST_DIGIT_NUMBER)));

  }

  /**
   * Constructor for LookAndSayIterator with start and end.
   *
   * @param start start number
   * @param end   end number
   */
  public LookAndSayIterator(BigInteger start, BigInteger end) {

    if (start.compareTo(new BigInteger("0")) < 0) {
      throw new IllegalArgumentException("Start must be greater than 0");
    }
    if (start.compareTo(end) >= 0) {
      throw new IllegalArgumentException("Start must be less than end");
    }
    if (start.toString().contains("0")) {
      throw new IllegalArgumentException("Start must not contain 0");
    }

    this.start = start;
    this.end = end;
    this.current = start;
  }

  @Override
  public BigInteger prev() throws NoSuchElementException {
    if (!hasPrevious()) {
      throw new NoSuchElementException("No previous");
    }

    start = current;
    current = getPrev();
    return current;
  }

  private BigInteger getPrev() {
    StringBuilder result = new StringBuilder();
    char[] list = current.toString().toCharArray();
    for (int i = 0; i < list.length; i = i + 2) {
      String value = String.valueOf(list[i + 1]).repeat(Integer.parseInt(String.valueOf(list[i])));
      result.append(value);
    }
    return new BigInteger(result.toString());
  }

  @Override
  public boolean hasPrevious() {
    return current.toString().length() % 2 == 0 && getPrev().compareTo(end) <= 0;
  }

  @Override
  public boolean hasNext() {
    return start.compareTo(end) < 0;
  }

  @Override
  public BigInteger next() {
    if (!hasNext()) {
      throw new NoSuchElementException("No next");
    }
    current = start;
    start = getNextBigInt();
    return current;
  }

  private BigInteger getNextBigInt() {
    StringBuilder result = new StringBuilder();
    char rep_char = start.toString().charAt(0);
    String curr_number = start.toString().substring(1) + " ";
    int count = 1;
    for (char actual : curr_number.toCharArray()) {
      if (actual != rep_char) {
        result.append(count).append(rep_char);
        count = 1;
        rep_char = actual;
      } else {
        count += 1;
      }
    }
    return new BigInteger(result.toString());
  }

}
