public class Encryptor
{
    /** A two-dimensional array of single-character strings, instantiated in the constructor */
    private String[][] letterBlock;

    /** The number of rows of letterBlock, set by the constructor */
    private int numRows;

    /** The number of columns of letterBlock, set by the constructor */
    private int numCols;

    /** Constructor*/
    public Encryptor(int r, int c)
    {
        letterBlock = new String[r][c];
        numRows = r;
        numCols = c;
    }

    public String[][] getLetterBlock()
    {
        return letterBlock;
    }

    /** Places a string into letterBlock in row-major order.
     *
     *   @param str  the string to be processed
     *
     *   Postcondition:
     *     if str.length() < numRows * numCols, "A" in each unfilled cell
     *     if str.length() > numRows * numCols, trailing characters are ignored
     */
    public void fillBlock(String str)
    {
       int i=0;
       for (int e=0; e< letterBlock.length; e++){
           for (int d=0; d< letterBlock[0].length; d++) {
               if (i < str.length()) {
                   letterBlock[e][d] = str.substring(i, i + 1);
                   i++;
               }
               else{
                   letterBlock[e][d]= "A";
               }

           }
       }
    }

    /** Extracts encrypted string from letterBlock in column-major order.
     *
     *   Precondition: letterBlock has been filled
     *
     *   @return the encrypted string from letterBlock
     */
    public String encryptBlock()
    {
        String g= "";
        for (int e=0; e< letterBlock[0].length; e++){
            for (int d=0; d< letterBlock.length; d++) {
                g+= letterBlock[d][e];
            }
        }
         return g;
    }

    /** Encrypts a message.
     *
     *  @param message the string to be encrypted
     *
     *  @return the encrypted message; if message is the empty string, returns the empty string
     */
    public String encryptMessage(String message)
    {
        String [] mess= new String [message.length()/(numRows* numCols)+1];
        String d="";
        int h=0;
        for (int e=0; e< mess.length; e++){
         mess[e]=  message.substring(h, numRows *numCols+ h);
         h= numRows* numCols+ h;
        }
        while( message.length()>numRows*numCols){
            fillBlock(message);
            d+= encryptBlock();
            message.substring(numRows, numCols);
        }
        if (d.length()> message.length()){

        }
        return d;


    }

    /**  Decrypts an encrypted message. All filler 'A's that may have been
     *   added during encryption will be removed, so this assumes that the
     *   original message (BEFORE it was encrypted) did NOT end in a capital A!
     *
     *   NOTE! When you are decrypting an encrypted message,
     *         be sure that you have initialized your Encryptor object
     *         with the same row/column used to encrypted the message! (i.e.
     *         the “encryption key” that is necessary for successful decryption)
     *         This is outlined in the precondition below.
     *
     *   Precondition: the Encryptor object being used for decryption has been
     *                 initialized with the same number of rows and columns
     *                 as was used for the Encryptor object used for encryption.
     *
     *   @param encryptedMessage  the encrypted message to decrypt
     *
     *   @return  the decrypted, original message (which had been encrypted)
     *
     *   TIP: You are encouraged to create other helper methods as you see fit
     *        (e.g. a method to decrypt each section of the decrypted message,
     *         similar to how encryptBlock was used)
     */
    public String decryptMessage(String encryptedMessage)
    {
        fillBlock(encryptedMessage);
        return encryptBlock();

    }
}