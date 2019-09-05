/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 * 		    Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie
{
    // INSTANCE VARIABLES
    
    private boolean knowsAboutPets = false;
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */
	public String getGreeting()
	{
		return "Hello, let's talk.";
	}
	
	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
            // TRIM AND EARLY TERMINATION
            statement = statement.trim().toLowerCase();
            if (statement.length() == 0) return "Hey, gimme something to work with";
            // this is dangerous but possible, due to the fact it's all on one line
		String response = "";
		if (statement.indexOf("no") >= 0)
		{
			response = "Why so negative?";
		}
		else if (statement.indexOf("mother") >= 0
				|| statement.indexOf("father") >= 0
				|| statement.indexOf("sister") >= 0
				|| statement.indexOf("brother") >= 0)
		{
			response = "Tell me more about your family.";
		}
                else if (statement.indexOf("dog") >= 0
                        || statement.indexOf("cat") >= 0){
                    if(!knowsAboutPets){
                        response = "Wow, you have pets? Please tell me more!";
                        knowsAboutPets = true;
                    }
                }
                else if (statement.indexOf("Adiletta") >= 0
                        || statement.indexOf("adiletta") >=0){
                    response = "Oh, he is a very beautiful, lovely, hard working man!";
                }
                else if(statement.indexOf("weej") >=0){
                    response = "He's such a cheeser!";
                }
                else if(statement.indexOf("java") >=0){
                    response = "Sounds harder than Python";
                }
                else if(statement.indexOf("coffee") >=0){
                    response = "The worst part of being a machine is the lack of emotion";
                }
                else if(statement.indexOf("archie") >=0){
                    response = "I assume that is your fat dog!";
                }
		else
		{
			response = getRandomResponse();
		}
		return response;
	}
	
	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse()
	{
		final int NUMBER_OF_RESPONSES = 4;
		double r = Math.random();
		int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
		String response = "";
		
		if (whichResponse == 0)
		{
			response = "Interesting, tell me more.";
		}
		else if (whichResponse == 1)
		{
			response = "Hmmm.";
		}
		else if (whichResponse == 2)
		{
			response = "Do you really think so?";
		}
		else if (whichResponse == 3)
		{
			response = "You don't say.";
		}
                else if (whichResponse == 4)
		{
			response = "Zowie! This is a hum-dinger of a conversation.";
		}
                else if (whichResponse == 5)
		{
			response = "We're having a great, very human conversation";
		}
		
		return response;
	}
}
