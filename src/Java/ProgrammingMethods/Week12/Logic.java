package Java.ProgrammingMethods.Week12;

import java.util.Set;
    import java.util.HashSet;
    import java.util.Map;
    import java.util.HashMap;
    import java.util.List;
    import java.util.ArrayList;

/**
 * Created by Andrei on 31.05.2018.
 */
public interface Logic {
  //Convenient methods for creation of formulas.
  default Logic and(Logic that){
    return new And(this,that);
  }
  default Logic or(Logic that){
    return new Or(this,that);
  }
  default Logic implies(Logic that){
    return new Implication(this,that);
  }
  static Logic not(Logic that){
    return new Neg(that);
  }
  static Logic var(String n){
    return new Var(n);
  }

  //The method that will produce the conjunctive normal form.
  default Logic knf(){
    return
        replaceImplication().
            negationInside().
            distributiv()
        ;
  }
  //Abstract methods to be implemented in subclasses.
  Logic replaceImplication();
  Logic negationInside();
  Logic distributiv();

  static void main(String[] args){
    Logic a1 = var("a").implies(var("b"));
    System.out.println(a1.knf());

    Logic a2 = (var("B").and(var("C"))).or(var("A"));
    System.out.println(a2);
    System.out.println(a2.knf());

    Logic a3 = not(not(var("A")));
    System.out.println(a3);
    System.out.println(a3.knf());

    Logic a4 = not(var("A").and(var("B")));
    System.out.println(a4);
    System.out.println(a4.knf());

    Logic f1 =
        not(
            not(var("a").implies(var("b")))
                .and(var("c"))
                .or(var("d")));
    System.out.println(f1);
    System.out.println(f1.knf());
    System.out.println(not(not(not(not(var("a"))))));
    System.out.println(not(not(not(not(var("a"))))).knf());
  }

  //Inner classes for different forms of formulae.
  class Var implements Logic{
    String name;
    Var(String n){
      name=n;
    }
    @Override public Logic replaceImplication(){
      return this;
    }
    @Override public Logic negationInside(){
      return this;
    }
    @Override public Logic distributiv(){
      return this;
    }
    @Override public String toString(){
      return name;
    }
    @Override public void signature(Set<String> result){
      result.add(name);
    }
  }

  abstract class Operator implements Logic{
    Logic left;
    Logic right;
    Operator(Logic l,Logic r){
      left=l;
      right = r;
    }
    @Override public void signature(Set<String> result){
      left.signature(result);
      if (right!=null)right.signature(result);
    }
  }

  class Neg extends Operator{
    Neg(Logic f){
      super(f,null);
    }
    @Override public Logic replaceImplication(){
      throw new RuntimeException();
    }
    @Override public Logic negationInside(){
      if (left instanceof Neg){
        while (!(left instanceof Neg && ((Neg) left).left instanceof Var)){
          left = ((Neg) left).left;
        }
        return ((Neg) left).left;
      }
      if (left instanceof Or
          && ((Or) left).left instanceof Var
          && ((Or) left).right instanceof Var){
        return not(((Or) left).left).and(not(((Or) left).right));
      }
      if (left instanceof And
          && ((And) left).left instanceof Var
          && ((And) left).right instanceof Var){
        return not(((And) left).left).or(not(((And) left).right));
      }
      throw new RuntimeException();
    }
    @Override public Logic distributiv(){
      throw new RuntimeException();
    }
    public String toString(){
      if (!(left instanceof Var || left instanceof Neg))
        return "¬("+left+")";
      return "¬"+left;
    }
  }

  class Implication extends Operator{
    Implication(Logic l,Logic r){
      super(l,r);
    }
    @Override public Logic replaceImplication(){
      return not(left).or(right);
    }
    @Override public Logic negationInside(){
      throw new RuntimeException();
    }
    @Override public Logic distributiv(){
      throw new RuntimeException();
    }
    @Override public String toString(){
      return "("+left+"→"+right+")";
    }
  }
  class And extends Operator{
    And(Logic l,Logic r){
      super(l,r);
    }
    @Override public Logic replaceImplication(){
      throw new RuntimeException();
    }
    @Override public Logic negationInside(){
      throw new RuntimeException();
    }
    @Override public Logic distributiv(){
      throw new RuntimeException();
    }
    @Override public String toString(){
      String l = (left instanceof Or || left instanceof Implication)
          ?"("+left+")":left.toString();
      String r = (right instanceof Or || right instanceof Implication)
          ?"("+right+")":right.toString();
      return l+"∧"+r;
    }
  }
  class Or extends Operator{
    Or(Logic l,Logic r){
      super(l,r);
    }
    @Override public Logic replaceImplication(){
      throw new RuntimeException();
    }
    @Override public Logic negationInside(){
      throw new RuntimeException();
    }
    @Override public Logic distributiv(){
      if (left instanceof And){
        return ((And) left).left.or(right)
            .and(((And)left).right.or(right));
      }
      if (right instanceof And){
        return left.or(((And) right.getClauses().get(0)).left)
            .and(left.or(((And) right.getClauses().get(0)).right));
      }
      throw new RuntimeException();
    }
    @Override public String toString(){
      String l =  (left instanceof And || left instanceof Implication)
          ?"("+left+")":left.toString();
      String r =  (right instanceof And || right instanceof Implication)
          ?"("+right+")":right.toString();
      return l+"∨"+r;
    }
  }

  //Following are the methods needed, to produce the
  //data structure as defined in ADS.
  default Set<String> signature(){
    Set<String> result = new HashSet<>();
    signature(result);
    return result;
  }
  void signature(Set<String> result);

  /** Constructs an representation of the formula
   ** in conjunctive normal form as specified in the
   ** exercise of ADS. **/
  default int[][] toADS(){
    Set<String> signature = signature();
    Map<String,Integer> m = new HashMap<>();
    int i = 0;
    for (String s: signature){
      m.put(s,i++);
    }
    List<Logic> clauses = knf().getClauses();

    int[][] result = new int[clauses.size()][];
    for (int j=0;j<result.length;j++)
      result[j] = new int[signature.size()];
    i=0;
    for (Logic clause:clauses){
      clause.fillClause(result[i++],m);
    }
    return result;
  }
  /**Helper function for creation of the clause form. **/
  default void fillClause(int[] result,Map<String,Integer> m){
    if (this instanceof Var){
      result[m.get(((Var)this).name)]=1;
    }else if (this instanceof Neg){
      result[m.get(((Var)((Neg)this).left).name)]=-1;
    }else if (this instanceof Or){
      ((Or)this).left.fillClause(result,m);
      ((Or)this).right.fillClause(result,m);
    }else{
      throw new RuntimeException();
    }
  }
  /** Constructs a list of clauses.
   ** Assumes this to be in conjunctive normal form. **/
  default List<Logic> getClauses(){
    List<Logic> result = new ArrayList<>();
    getClauses(result);
    return result;
  }
  default void getClauses(List<Logic> result){
    if (this instanceof And){
      ((And)this).left.getClauses(result);
      ((And)this).right.getClauses(result);
    }else{
      result.add(this);
    }
  }
}
