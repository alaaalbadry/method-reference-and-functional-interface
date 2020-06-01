import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

interface Parser{
    String parse(String s);
}
class StringParser {
    public static String convert(String s){

        if(s.length()<=3)
            s = s.toUpperCase();
        else
            s = s.toLowerCase();

        return s;
    }
    public  String nonStaticConvert(String s){

        if(s.length()<=3)
            s = s.toUpperCase();
        else
            s = s.toLowerCase();

        return s;
    }
}
class MyPrinter {
    public void print(String str,Parser p){

        str = p.parse(str);
        System.out.println(str);
    }
}
public class Demo {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("alaa","fathy","albadry");
        System.out.println("\n========================================================With Arrow Op\n");

        names.forEach(s -> System.out.println(s));
        System.out.println("\n========================================================With Consumer\n");

        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String str) {
                System.out.println(str);
            }
        };

        names.forEach(consumer);

        System.out.println("\n========================================================With Method reference\n");
        names.forEach(System.out::println);

        System.out.println("\n========================================================With anonymous inner class\n");

        MyPrinter mp = new MyPrinter();
        mp.print("ala",new Parser(){
            public String parse(String str){
                return StringParser.convert(str);
            }
        });

        System.out.println("\n========================================================With Functional Interface\n");

        mp.print("lol",s-> StringParser.convert(s));

        System.out.println("\n========================================================With static Method Reference\n");

        mp.print("soo",StringParser::convert);

        System.out.println("\n========================================================With object Method Reference\n");

        StringParser sp = new StringParser();

        mp.print("boo",sp::nonStaticConvert);
    }

}
