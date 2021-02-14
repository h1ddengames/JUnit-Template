package site.hiddengames.practice.exercism.rnatranscription;

import site.hiddengames.annotations.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a DNA strand, return its RNA complement (per RNA transcription).
 */
@ClassMetadata(
        author = "Shahid Karim", dateCreated = "2/13/2021",
        currentRevision = 1, lastModified = "2/13/2021", lastModifiedBy = "Shahid Karim",
        reviewers = {}
)
@API(status = API.Status.STABLE, since = "Project-Template-v3.0.0", consumers = { "RnaTranscriptionTest" })
public class RnaTranscription {

    Map<Character, Character> dnaToRna = new HashMap<>() {{
        put('G', 'C');
        put('C', 'G');
        put('T', 'A');
        put('A', 'U');
    }};

    public String transcribe(String dnaStrand) {
        if(dnaStrand == null || dnaStrand.contentEquals("")) { return ""; }

        char[] dnaStrandAsChars = dnaStrand.toCharArray();
        StringBuilder rnaStrand = new StringBuilder();

        for (char dnaStrandChar : dnaStrandAsChars) {
            rnaStrand.append(dnaToRna.get(dnaStrandChar));
        }

        return rnaStrand.toString();
    }

}
