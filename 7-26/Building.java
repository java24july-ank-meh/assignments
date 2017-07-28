public abstract class Building{
    protected int height;
    protected String material;

    public abstract void maintain();
}

class Schoolhouse extends Building{
    public Schoolhouse(){}
    public Schoolhouse(int height, String material){

    }

    @Override
    public void maintain(){
        System.out.println("maintaining...");
    }
}

class Main{
    public static void main(String[] args){
        
    }
}
