package algoritmo1;

public class Algoritmo1 {

   public static void main(String[] args) {
      
      // Criando uma lista de eventos (sequência principal S)
      String[] S =  {
         "buy Amazon", "buy Google", "buy Apple", "buy Google", "buy Google", "buy NVIDIA"
      };
      
      // Criando uma subsequência de eventos (lista S_Line)
      String[] S_Line = { 
         "buy Google", "buy Apple", "buy Google", "buy NVIDIA" 
      };

      // Testando o método hasTrend
      // Verifica se S_Line é uma subsequencia de S
      boolean check = hasTrend(S, S_Line);

      // Imprime o resultado
      if (check) {
         System.out.println("A lista S_Line é uma tendência da lista S.");
      } else {
         System.out.println("A lista S_Line não é uma tendência da lista S.");
      }
   }  
   
   /**
    * Método que verifica se uma subsequência de eventos (S_Line)
    * é uma tendência da sequência principal de eventos (S).
    *
    * Uma sequência é considerada uma tendência se todos os eventos da subsequência
    * aparecem na sequência principal na mesma ordem, mas não necessariamente consecutivos.
    *
    * @param S      A sequência principal de eventos.
    * @param S_Line A subsequência que queremos verificar se é uma tendência.
    * @return       true se S_Line é uma tendência de S, false caso contrário.
    */
   public static boolean hasTrend(String[] S, String[] S_Line) {
      int i = 0, j = 0;
      
      // Percorre ambas as listas simultaneamente
      while (i < S.length && j < S_Line.length) {

         // Se o evento atual de S for igual ao evento atual de S_Line
         if (S[i].equalsIgnoreCase(S_Line[j])) {
            j++; // Avança na subsequência S_Line
         }
         // Se não, apenas avança na sequência principal S
         // Isso significa que o evento atual de S não é igual ao evento atual de S_Line
         i++; 
      }

      // Se percorremos toda a subsequência S_Line, significa que ela é uma tendência de S
      // Ou seja, se j for igual ao tamanho de S_Line, significa que todos os eventos de S_Line foram encontrados em S
      return j == S_Line.length;
   }
}
