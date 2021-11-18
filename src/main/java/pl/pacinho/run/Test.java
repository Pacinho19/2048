package pl.pacinho.run;

public class Test {

    public static void main(String[] args) {
        boolean test = true;
        for (int i = (test ? 0 : 10);
             test ? i < 10 : i >= 0;
             i++
        ) {
            System.out.println(i);
            if(!test){
                i-=2;
            }
        }
    }
}