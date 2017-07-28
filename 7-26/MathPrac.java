public class MathPrac{
    public static void main(String[] args){
        fib(25);
        System.out.println("fib recursive");
        for (int n=0; n<25; n++){
            System.out.print(fib_recursive(n)+" ");
        }
        System.out.println();
        System.out.println(factorial(3));
        System.out.println(factorial(2));
        System.out.println(factorial(7));
    }
    public static void fib(int n){
        //prints first n fibonacci numbers
        int one_back = 0;
        int two_back = 0;
        int next = 0;
        for(int i=0; i<n; i++){
            two_back = one_back;
            one_back = next;
            if(i<2){next = i;}
            else{next = one_back + two_back;}
            System.out.print(next + " ");
        }
        System.out.println();
    }

    public static int fib_recursive(int n){
        if(n<2){return n;}
        int min_1 = fib_recursive(n-1);
        int min_2 = fib_recursive(n-2);
        //System.out.println(min_1+min_2);
        //System.out.println(min_2);
        return (min_1 + min_2);
    }

    public static int factorial(int n){
        if(n<2){return 1;}
        return n*factorial(n-1);
    }
}