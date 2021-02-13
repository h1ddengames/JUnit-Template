package practice.exercism.rnatranscription;

import java.util.HashMap;
import java.util.Map;

class RnaTranscription {

    Map<Character, Character> dnaToRna = new HashMap<>() {{
        put('G', 'C');
        put('C', 'G');
        put('T', 'A');
        put('A', 'U');
    }};

    String transcribe(String dnaStrand) {
        if(dnaStrand == null || dnaStrand.contentEquals("")) { return ""; }

        char[] dnaStrandAsChars = dnaStrand.toCharArray();
        StringBuilder rnaStrand = new StringBuilder();

        for (char dnaStrandChar : dnaStrandAsChars) {
            rnaStrand.append(dnaToRna.get(dnaStrandChar));
        }

        return rnaStrand.toString();
    }

}
