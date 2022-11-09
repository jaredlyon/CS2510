import java.util.*;
import java.util.function.Function;

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
    ArrayList<Character> initEncoder() {
      Random rand = new Random();
        
        for (char k : alphabet) {
          this.code.add(rand.nextInt(26) + 1, k);
        }
        
        return code;
    }
    
    // decodes a character
    class CharDecoder implements Function<Character, Character> {

      // converts a character to its coded reciprocal
      public Character apply(Character t) {
        int codedIndex = code.indexOf(t);
        return alphabet.get(codedIndex);
      }
    }
    
    // encodes a character
    class CharEncoder implements Function<Character, Character> {

      // converts a character to its coded reciprocal
      public Character apply(Character t) {
        int decodedIndex = alphabet.indexOf(t);
        return code.get(decodedIndex);
      }
    }

    // produce an encoded String from the given String
    String encode(String source) {
        String result = "";
        
        for (int i = 0; i < source.length(); i++) {
          result = result + source.charAt(i).apply(new CharEncoder());
        }
        
        return result;
    }

    // produce a decoded String from the given String
    String decode(String code) {
        String result = "";
        
        for (int i = 0; i < code.length(); i++) {
          result = result + code.charAt(i).apply(new CharDecoder());
        }
        
        return result;
    }
}