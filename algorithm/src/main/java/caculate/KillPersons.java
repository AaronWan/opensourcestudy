package caculate;

/**
 * @author 万松(Aaron)
 * @creat_date: 2020-04-16
 * @creat_time: 15:45
 * @since 6.6
 */
public class KillPersons {
  public static void main(String[] args) {
    int[] persons = new int[20];
    kill(persons, 2);
  }

  private static void kill(int[] persons, int m) {
    int remains = persons.length;
    int current = 0;
    while (remains>1){
    for (int i = 0; i < persons.length; i++) {
      if(persons[i]==0){
        if(++current==m){
          current=0;
          System.out.print(i+"\t");
          persons[i]=1;
          remains--;
          if(remains==1){
            break;
          }
        }
      }
    }
    }
    System.out.println();
    for (int i = 0; i < persons.length; i++) {
      System.out.print(i+"..."+persons[i]+"\t");
    }
  }
}
