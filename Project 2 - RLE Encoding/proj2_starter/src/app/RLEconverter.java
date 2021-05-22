package app;

import java.util.Scanner;
import java.io.*;

public class RLEconverter {
   private char[] fileChars; // stores unique chars as they appear in the file.
   private final static int DEFAULT_LEN = 100; // used to create arrays.
   private int numLines; // number of lines in the file that is read.
   
   /*
    *  Initialize the char array to length 2.
    *  as the number of characters is 2 for any file.
    */
   public RLEconverter(){
      fileChars = new char[2];
      numLines=0;
   }

   /*
    *  This method reads in an uncompressed ascii image file that contains 
    *  2 characters. It stores each line of the file in an array.
    *  It then calls compressLines to get an array that stores the compressed
    *  version of each uncompressed line from the file. The compressed array
    *  is then passed to the getCompressedFileStr method which returns a String
    *  of all compressed lines (the two charcaters are written in the first line)
    *  in CSV format. This String is written to a text file with the prefix "RLE_"
    *  added to the original, uncompressed file name.
    */   
  public void compressFile(String fileName) throws IOException{
    Scanner scan = new Scanner(new FileReader(fileName));
    String line = null;
    String[] decompressed = new String [DEFAULT_LEN];
    numLines=0;
    while(scan.hasNext()){
      line = scan.next();
      if(line != null && line.length()>0)
        decompressed[numLines]=line;
        numLines++;
    }
    scan.close();
    String[] compressed = compressLines(decompressed);
    writeFile(getCompressedFileStr(compressed, fileChars), "RLE_"+fileName);
  }
  
   
/*
 * This method implements the RLE compression algorithm. It takes a line of uncompressed data
 * from an ascii file and returns the RLE encoding of that line in CSV format.
 * The two characters that make up the image file are passed in as a char array, where
 * the first cell contains the first character that occurred in the file.
*/
public String compressLine(String line, char[] fileChars){
   //TODO1: Implement this method
   String res = "";
   int stringLength = 0;
   for (int i = 0; i < line.length(); i++) {
      switch (i) {
         case 0: if (line.charAt(i) != fileChars[0]) {
            res = res + stringLength;
         } 
         stringLength ++;
         break;
      }
      if (i != 0) {
         if (line.charAt(i) == line.charAt(i - 1)) {
            stringLength++;
         }
         else {
            if (res.length() > 0) {
               res = res + ",";
            }
            res = res + stringLength;
            stringLength = 1;
         }
         if (i == line.length() - 1) {
            if (res.length() > 0) {
               res = res + ",";
            }
            res = res + stringLength;
         }
      }
   }
   return res;
}

  /*
   *  This method discovers the two ascii characters that make up the image. 
   *  It iterates through all of the lines and writes each compressed line
   *  to a String array which is returned. The method compressLine is called on 
   *  each line.
   */
  public String[] compressLines(String[] lines){
   //TODO2: Implement this method
   String[] compressed = null;
   fileChars = new char[2];
   fileChars[0] = lines[0].charAt(0);
   for (int i = 0; i < lines[0].length(); i++) {
      if (fileChars[0] != lines[i].charAt(i)) {
         fileChars[1] = lines[i].charAt(i);
      }
      break;
   }
   compressed = new String[lines.length];
   for (int i = 0; i< lines.length; i++) {
      compressed[i] = compressLine(lines[i], fileChars);
   }
   return compressed;
}


/*
 *  This method assembles the lines of compressed data for
 *  writing to a file. The first line must be the 2 ascii characters
 *  in comma-separated format. 
 */
public String getCompressedFileStr(String[] compressed, char[] fileChars) {
   //TODO3: Implement this method
   String data = "";
   data = data + fileChars[0] + "," + fileChars[1];
   for (int i = 0; i < compressed.length; i++) {
      data = data + compressed[i];
   }
   return data;
}
   /*
    *  This method reads in an RLE compressed ascii image file that contains 
    *  2 characters. It stores each line of the file in an array.
    *  It then calls decompressLines to get an array that stores the decompressed
    *  version of each compressed line from the file. The first row contains the two 
    *  ascii charcaters used in the original image file. The decompressed array
    *  is then passed to the getDecompressedFileStr method which returns a String
    *  of all decompressed lines, thus restoring the original, uncompressed image.
    *  This String is written to a text file with the prefix "DECOMP_"
    *  added to the original, compressed file name.
    */   
  public void decompressFile(String fileName) throws IOException{
    Scanner scan = new Scanner(new FileReader(fileName));
    String line = null;
    String[] compressed = new String [DEFAULT_LEN];
    numLines=0;
    while(scan.hasNext()){
      line = scan.next();
      if(line != null && line.length()>0)
        compressed[numLines]=line;
        numLines++;
    }
    scan.close();
    String[] decompressed = decompressLines(compressed);
    writeFile(getDecompressedFileStr(decompressed), "DECOMP_"+fileName);
  }
 
   /*
   * This method decodes lines that were encoded by the RLE compression algorithm. 
   * It takes a line of compressed data and returns the decompressed, or original version
   * of that line. The two characters that make up the image file are passed in as a char array, 
   * where the first cell contains the first character that occurred in the file.
   */
   public String decompressLine(String line, char[] fileChars){
      //TODO4: Implement this method
      String res = "";
      String[] decompressString = line.split(",");
      int decompressInt = 0;
      for (int i = 0; i < decompressString.length; i++) {
         for (int j = 0; j < Integer.parseInt(decompressString[i]); j++) {
            res = res + fileChars[decompressInt];
         }
         decompressInt = 1 - decompressInt;
      }
      return res;
   }
    /*
   *  This method iterates through all of the compressed lines and writes 
   *  each decompressed line to a String array which is returned. 
   *  The method decompressLine is called on each line. The first line in
   *  the compressed array passed in are the 2 ascii characters used to make
   *  up the image. 
   */
  public String[] decompressLines(String[] lines){
   //TODO5: Implement this method
   String[] decompressed = null;
   decompressed = new String[lines.length - 1];
   char[] decompressChars = new char[2];
   decompressChars[0] = lines[0].charAt(0);
   decompressChars[1] = lines[0].charAt(2);
   for (int i = 0; i < lines.length; i++) {
      decompressed[i] = decompressLine(lines[i + 1], fileChars);
   }
   return decompressed;
  }
  
  /*
   *  This method assembles the lines of decompressed data for
   *  writing to a file. 
   */
  public String getDecompressedFileStr(String[] decompressed){
   //TODO6: Implement this method
   String data = "";
   for (int i = 0; i < decompressed.length; i++) {
      data = data + "\n" + decompressed[i];
   }
   return data;
  }


   
   public void writeFile(String data, String fileName) throws IOException{
		PrintWriter pw = new PrintWriter(fileName);
      pw.print(data);
      pw.close();
   }
}