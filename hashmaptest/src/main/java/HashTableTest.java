import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/**
 * Created by Aaron on 05/07/2017.
 */
public class HashTableTest {
    @Getter
    @Setter
    @AllArgsConstructor
    static class Person{
        String name;
        int age;

        @Override
        public boolean equals(Object obj) {
            return age!=((Person)obj).age;
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Person{");
            sb.append("name='").append(name).append('\'');
            sb.append(", age=").append(age);
            sb.append('}');
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        HashMap<Person,Person> pp=new HashMap<>();
        pp.put(new Person("test",20),new Person("test",10));
        pp.put(new Person("test",10),new Person("test",10));
        pp.forEach((k,v)->{
            System.out.println(k);
        });
    }
}
