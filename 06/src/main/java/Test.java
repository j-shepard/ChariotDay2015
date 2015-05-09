import com.chariotsolutions.jshepard.NoNPE;
import com.chariotsolutions.jshepard.Person;

public class Test {
  public static void main(String... args) {
    new Test().test();
  }

  private void test() {
    Person person = new Person("name");
    @NoNPE String name = "City name:" + person.getAddress().getCity().getName();
    System.out.println("Name:" + name);
  }
}
