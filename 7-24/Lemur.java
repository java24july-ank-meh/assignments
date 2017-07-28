class Primate {
   private String name = "OliverLouisJulien";
   public Primate(String name){
      this.name = name;
   }
   public String getName(){
      return this.name;
   }
   public void setName(String name){
      this.name = name; //just using "name" refers to the input parameter
   }

   public void screech(){
      System.out.println("REEE");
   }
}

interface CanSwing {
   public boolean isSwinging();
}

public class Lemur extends Primate implements CanSwing{
   public String name = "Oliver";
   public Lemur(String name){
      super(name);
   }
   public boolean isSwinging(){
      return true;
   }
   public void screech(){
      System.out.println("FUCK");
   }
   public static void main(String[] args){
      
	Primate primate = new Lemur("Olly");
        primate.screech();

   }
}
class Orangutan extends Primate implements CanSwing{
   boolean field;
   public Orangutan(String name){
      super(name);
   }
   public boolean isSwinging(){
      return true;
   }
   public Orangutan(boolean a){
      super("Default Name");
      this.field = a;
   }

}