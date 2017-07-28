public class StringExercises{
    public static void main(String[] args){
        System.out.println(reverse("hello"));
        System.out.println(sub("hello", 3));
    }

    public static String reverse(String str){
        char[] result = new char[str.length()];
        for(int idx= str.length()-1; idx>-1; idx--){
            result[str.length()-idx-1] = str.charAt(idx); 
        }
        return new String(result);
    }

    public static String reverse_recursive(String str){
        if(str.length()<2){return str.charAt(0);}
        return reverse_recursive(sub(str, str.length()-2)) + str.charAt(0);
    }

    public static String sub(String str, int idx){
        char[] result = new char[idx];
        for(int i=0; i<idx; i++){
            result[i] = str.charAt(i);
        }
        return new String(result);
    }
}