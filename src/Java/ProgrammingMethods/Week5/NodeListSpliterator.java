package Java.ProgrammingMethods.Week5;

import java.util.Spliterator;

import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by Andrei on 19.05.2018.
 */
public class NodeListSpliterator implements Spliterator<Node> {
  long start;
  long end;
  NodeList ns;

  public NodeListSpliterator(NodeList ns) {
    this.ns = ns;
    start = 0;
    end = ns.getLength() - 1;
  }
  public NodeListSpliterator(NodeList ns, long start, long end) {
    this.ns = ns;
    this.start = start;
    this.end = end;
  }


  Stream<Node> stream(){
    return StreamSupport.stream(this, false);
  }

  Stream<Node> parallelStream(){
    return StreamSupport.stream(this, true);
  }

  @Override
  public boolean tryAdvance(Consumer<? super Node> action) {
    if (start <= end) {
      action.accept(ns.item((int) start));
      start++;
      return true;
    } else { // cannot advance
      return false;
    }
  }

  @Override
  public Spliterator<Node> trySplit() {
    long low = start; // divide range in half
    long mid = ((low + end) >>> 1) & ~1; // force midpoint to be even
    if (low < mid) { // split out left half
      start = mid; // reset this Spliterator's origin
      return new NodeListSpliterator(ns, low, mid);
    }else { // too small to split
      return null;
    }
  }

  @Override
  public long estimateSize() {
    return (end - start) / 2;
  }

  @Override
  public int characteristics() {
    return ORDERED;
  }
}
