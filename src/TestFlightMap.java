import static org.junit.Assert.*;

import org.junit.Test;

public class TestFlightMap {
  @Test
  public void nodeConstructorTest() {
   Node node = new Node('c');
   assertEquals('c', node.ch);
  }
}