package algoritmo1;


public class Algoritmo1 {
   public static void main(String[] args) {
      
      // Creating a events sequences List
      int SLength = 8;
      String[] S =  {"buy Amazon", "buy Google", "buy Apple", "buy Google", "buy Google", "buy NVIDIA"} ;
      
      int S_LineLength = 4;   
      // Creating a possible events subsequences list
      String[] S_Line = { "buy Google", "buy Apple", "buy Google", "buy NVIDIA" };

      // Testing the hasTrend method
      // Check if S_Line is a trend of S
      boolean check = hasTrend(S, S_Line);

      // Print the result
      if (check) {
         System.out.println("The list S_Line is a trend of the list S.");
      } else {
         System.out.println("The list S_Line isn't a trend of the list S.");
      }
   }  
   
   
   public static boolean hasTrend(String[] S, String[] S_Line) {
      // Check if the subsequence is a trend of the sequence
      int i = 0, j = 0;
      while (i < S.length && j < S_Line.length) {
         if (S[i].equalsIgnoreCase(S_Line[j])) {
            j++;
         }
         i++;
      }

      return j == S_Line.length;
   }
}