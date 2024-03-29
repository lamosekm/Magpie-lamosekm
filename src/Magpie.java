
/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:
 * <ul><li>
 * Uses indexOf to find strings
 * </li><li>
 * Handles responding to simple words and phrases
 * </li></ul>
 * This version uses a nested if to handle default responses.
 *
 * @author Laurie White
 * @version April 2012
 */
public class Magpie {

    // INSTANCE VARIABLES
    
    private boolean knowsAboutPets = false;

    /**
     * Get a default greeting
     *
     * @return a greeting
     */
    public String getGreeting() {
        return "Hello, let's talk.";
    }

    /**
     * Gives a response to a user statement
     *
     * @param statement the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement) {
        // TRIM AND EARLY TERMINATION
        statement = statement.trim().toLowerCase();
        if (statement.length() == 0) {
            return "Hey, gimme something to work with";
        }
        // this is dangerous but possible, due to the fact it's all on one line
        String response = "";
        if (findKeyword(statement, "no") >= 0) {
            response = "Why so negative?";
        } else if (findKeyword(statement, "mother") >= 0
                || findKeyword(statement, "father") >= 0
                || findKeyword(statement, "sister") >= 0
                || findKeyword(statement, "brother") >= 0) {
            response = "Tell me more about your family.";
        } else if (findKeyword(statement, "dog") >= 0
                || findKeyword(statement, "cat") >= 0) {
            if (!knowsAboutPets) {
                response = "Wow, you have pets? Please tell me more!";
                knowsAboutPets = true;
            }
        } else if (findKeyword(statement, "Adiletta") >= 0
                || findKeyword(statement, "adiletta") >= 0) {
            response = "Oh, he is a very beautiful, lovely, hard working man!";
        } else if (findKeyword(statement, "weej") >= 0) {
            response = "He's such a cheeser!";
        } else if (findKeyword(statement, "java") >= 0) {
            response = "Sounds harder than Python";
        } else if (findKeyword(statement, "coffee") >= 0) {
            response = "The worst part of being a machine is the lack of emotion";
        } else if (findKeyword(statement, "weej") >= 0) {
            response = "I assume that is your fat dog!";
        } // Responses which require transformations
        else if (findKeyword(statement, "I want to", 0) >= 0) {
            response = transformIWantToStatement(statement);
        }else if (findKeyword(statement, "I want", 0) >= 0) {
            response = transformIWantStatement(statement);
        }
        else {
            // Look for a two word (you <something> me)
            // pattern
            int psn = findKeyword(statement, "you", 0);

            if (psn >= 0
                    && findKeyword(statement, "me", psn) >= 0) {
                response = transformMeYou(statement);
            } else {
                response = getRandomResponse();
            }
        }

        return response;
    }

    /**
     * Pick a default response to use if nothing else fits.
     *
     * @return a non-committal string
     */
    private String getRandomResponse() {
        final int NUMBER_OF_RESPONSES = 4;
        double r = Math.random();
        int whichResponse = (int) (r * NUMBER_OF_RESPONSES);
        String response = "";

        if (whichResponse == 0) {
            response = "Interesting, tell me more.";
        } else if (whichResponse == 1) {
            response = "Hmmm.";
        } else if (whichResponse == 2) {
            response = "Do you really think so?";
        } else if (whichResponse == 3) {
            response = "You don't say.";
        } else if (whichResponse == 4) {
            response = "Zowie! This is a hum-dinger of a conversation.";
        } else if (whichResponse == 5) {
            response = "We're having a great, very human conversation";
        }

        return response;
    }

    /**
     * Search for one word in phrase. The search is not case sensitive. This
     * method will check that the given goal is not a substring of a longer
     * string (so, for example, "I know" does not contain "no").
     *
     * @param statement the string to search
     * @param goal the string to search for
     * @param startPos the character of the string to begin the search at
     * @return the index of the first occurrence of goal in statement or -1 if
     * it's not found
     */
    private int findKeyword(String statement, String goal,
            int startPos) {
        String phrase = statement.trim().toLowerCase();
        goal = goal.toLowerCase();

        // The only change to incorporate the startPos is in
        // the line below
        int psn = phrase.indexOf(goal, startPos);

        // Refinement--make sure the goal isn't part of a
        // word
        while (psn >= 0) {
            // Find the string of length 1 before and after
            // the word
            String before = " ", after = " ";
            if (psn > 0) {
                before = phrase.substring(psn - 1, psn);
            }
            if (psn + goal.length() < phrase.length()) {
                after = phrase.substring(
                        psn + goal.length(),
                        psn + goal.length() + 1);
            }

            // If before and after aren't letters, we've
            // found the word
            if (((before.compareTo("a") < 0) || (before
                    .compareTo("z") > 0)) // before is not a
                    // letter
                    && ((after.compareTo("a") < 0) || (after
                    .compareTo("z") > 0))) {
                return psn;
            }

            // The last position didn't work, so let's find
            // the next, if there is one.
            psn = phrase.indexOf(goal, psn + 1);

        }

        return -1;
    }

    /**
     * Search for one word in phrase. The search is not case sensitive. This
     * method will check that the given goal is not a substring of a longer
     * string (so, for example, "I know" does not contain "no"). The search
     * begins at the beginning of the string.
     *
     * @param statement the string to search
     * @param goal the string to search for
     * @return the index of the first occurrence of goal in statement or -1 if
     * it's not found
     */
    private int findKeyword(String statement, String goal) {
        return findKeyword(statement, goal, 0);
    }

    /**
     * Take a statement with "I want to <something>." and transform it into
     * "What would it mean to <something>?"
     *
     * @param statement the user statement, assumed to contain "I want to"
     * @return the transformed statement
     */
    private String transformIWantToStatement(String statement) {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals(".")) {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword(statement, "I want to", 0);
        String restOfStatement = statement.substring(psn + 9).trim();
        return "What would it mean to " + restOfStatement + "?";
    }
     private String transformIWantStatement(String statement) {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals(".")) {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword(statement, "I want", 0);
        String restOfStatement = statement.substring(psn + 6).trim();
        return "Why would you want " + restOfStatement + "?";
    }

    /**
     * Take a statement with "you <something> me" and transform it into "What
     * makes you think that I <something> you?"
     *
     * @param statement the user statement, assumed to contain "you" followed by
     * "me"
     * @return the transformed statement
     */
    private String transformMeYou(String statement) {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals(".")) {
            statement = statement.substring(0, statement
                    .length() - 1);
        }

        int psnOfYou = findKeyword(statement, "you", 0);
        int psnOfMe = findKeyword(statement, "me", psnOfYou + 3);

        String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
        return "What makes you think that I " + restOfStatement + " you?";
    }

} // closes Magpie class
