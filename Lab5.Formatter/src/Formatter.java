public class Formatter {

    public static String build(String formatString, Object... arguments){
        if (formatString == null){
            return null;
        }

        StringBuilder formatedString = new StringBuilder().append(formatString);
        int currentPosition = 0;
        while (true){
            int openingBracketPosition = formatedString.indexOf("{", currentPosition);
            int closingBracketPosition = formatedString.indexOf("}", openingBracketPosition);

            if (openingBracketPosition < 0 || closingBracketPosition < 0){
                break;
            }

            try{
                int argumentNumber = Integer.parseInt(formatedString.substring(openingBracketPosition + 1, closingBracketPosition));
                formatedString.replace(openingBracketPosition, closingBracketPosition + 1, arguments[argumentNumber].toString());
            }
            catch (NumberFormatException e){
                currentPosition = openingBracketPosition + 1;
            }
            catch (ArrayIndexOutOfBoundsException e){
                return null;
            }
        }

        return formatedString.toString();
    }
}
