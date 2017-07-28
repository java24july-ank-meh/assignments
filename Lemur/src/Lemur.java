
class Primate{
	private String name = "DefaultName";
	public Primate(){ //need to keep default constructor
		
	}
	public Primate(String name){ //constructor with name added
		this.name = name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	static void screech(){
		System.out.println(".w.");
	}
}

interface CanSwing {
	public boolean isSwinging();
}

class Orangutan extends Primate implements CanSwing{
	public boolean isSwinging(){
		return true;
	}
	
}

public class Lemur extends Primate implements CanSwing{
	public Lemur(){
		
	}
	public Lemur (String name){
		super(name); //calls superclass constructor with field
	}
	public String name = "Bob";
	public boolean isSwinging(){
		return false;
	}

	public static void main(String[] args) {
		Lemur lemur = new Lemur();
		lemur.screech();
		Primate primate = lemur;
		primate.screech();
		CanSwing canSwing = lemur; //Interface can't access screech.
		System.out.println(canSwing.isSwinging());
		System.out.println(lemur.getName());
		lemur.setName("Bob");
		System.out.println(lemur.getName());
		Lemur lemur2 = new Lemur("Joe");
		System.out.println(lemur2.getName());
	}

}


/*Scopes: Only accessible when that section of code is "active" or being run. 
Class/Static
Object/Instance
-Example: Class "people" is scoped. People A cannot see People B and vice versa
Method
-Same as Object but with methods.
Block/Loop


Control statements:
If, While, Else, Switch
*/