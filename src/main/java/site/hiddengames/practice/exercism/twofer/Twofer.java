package site.hiddengames.practice.exercism.twofer;

import site.hiddengames.annotations.*;


/**
 * Two-fer or 2-fer is short for two for one. One for you and one for me.
 */
@ClassMetadata(
        author = "Shahid Karim", dateCreated = "2/13/2021",
        currentRevision = 1, lastModified = "2/13/2021", lastModifiedBy = "Shahid Karim",
        reviewers = {}
)
@API(status = API.Status.STABLE, since = "Project-Template-v3.0.0", consumers = { "Twofer" })
public class Twofer {
    public String twofer(String name) {
        if(name == null || name.contentEquals("")) {
            return "One for you, one for me.";
        } else {
            return "One for " + name + ", one for me.";
        }
    }
}

