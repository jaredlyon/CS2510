import java.awt.Color;
import java.util.*;
import tester.Tester;

/**
 * A class that defines a new permutation code, as well as methods for encoding
 * and decoding of the messages that use this code.
 */
class PermutationCode {
  // The original list of characters to be encoded
  ArrayList<Character> alphabet = 
      new ArrayList<Character>(Arrays.asList(
          'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
          'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
          't', 'u', 'v', 'w', 'x', 'y', 'z'));

  ArrayList<Character> code = new ArrayList<Character>(26);

  // A random number generator
  Random rand = new Random();

  // Create a new instance of the encoder/decoder with a new permutation code 
  PermutationCode() {
    this.code = this.initEncoder();
  }

  // Create a new instance of the encoder/decoder with the given code 
  PermutationCode(ArrayList<Character> code) {
    this.code = code;
  }

  // Initialize the encoding permutation of the characters
  public ArrayList<Character> initEncoder() {
    Random rand = new Random();
    ArrayList<Character> tempAlphabet = new ArrayList<Character>(Arrays.asList(
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
        't', 'u', 'v', 'w', 'x', 'y', 'z'));
    
    for (int i = 0; i < 26; i++) {
      this.code.add(tempAlphabet.remove(rand.nextInt(tempAlphabet.size())));
    }
    
    return code;
  }


  // produce an encoded String from the given String
  public String encode(String source) {
    String result = "";

    for (int i = 0; i < source.length(); i++) {
      char decodedChar = source.charAt(i); // get character at i
      if (decodedChar == ' ') {
        result = result + decodedChar; // checks for spaces
      } else {
        int decodedIndex = alphabet.indexOf(decodedChar); // what index in alphabet is char?
        char encodedChar = this.code.get(decodedIndex); // what char at the coded index corresponds?
        result = result + encodedChar; // append char to result
      }
    }

    return result;
  }

  // produce a decoded String from the given String
  public String decode(String code) {
    String result = "";

    for (int i = 0; i < code.length(); i++) {
      char encodedChar = code.charAt(i); // get character at i
      if (encodedChar == ' ') {
        result = result + encodedChar; // checks for spaces
      } else {
        int encodedIndex = alphabet.indexOf(encodedChar); // what index in the alphabet is i?
        char decodedChar = this.code.get(encodedIndex); // what code index corresponds?
        result = result + decodedChar; // append char to result
      }
    }

    return result;
  }
}

class ExamplesPermutationCode {
  ExamplesPermutationCode() {
    
  }
  
  ArrayList<Character> key = 
      new ArrayList<Character>(Arrays.asList(
          'z', 'y', 'x', 'w', 'v', 'u', 't', 's', 'r', 'q', 
          'p', 'o', 'n', 'm', 'l', 'k', 'j', 'i', 'h', 
          'g', 'f', 'e', 'd', 'c', 'b', 'a'));
  
  PermutationCode keyedCode = new PermutationCode(key);
  String decoded1 = "hello";
  String decoded2 = "world";
  String decoded3 = "fundies two";
  String decoded4 = "qzivw";
  String encoded1 = "svool";
  String encoded2 = "dliow";
  String encoded3 = "ufmwrvh gdl";
  String encoded4 = "jared";
 
  // test initEncoder
  
  // test encode
  void testEncode(Tester t) {
    t.checkExpect(keyedCode.encode(decoded1), encoded1);
    t.checkExpect(keyedCode.encode(decoded2), encoded2);
    t.checkExpect(keyedCode.encode(decoded3), encoded3);
    t.checkExpect(keyedCode.encode(decoded4), encoded4);
  }
  
  // test decode
  void testDecode(Tester t) {
    t.checkExpect(keyedCode.decode(encoded1), decoded1);
    t.checkExpect(keyedCode.decode(encoded2), decoded2);
    t.checkExpect(keyedCode.decode(encoded3), decoded3);
    t.checkExpect(keyedCode.decode(encoded4), decoded4);
  }
}