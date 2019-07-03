import common.IntegerOperation;
import common.StringOperation;

public class Main {

    public static void main(String[] args) {
//        System.out.println(IntegerOperation.transRadix("10", 36, 35));
//        StringOperation.replaceSpace(new StringBuffer("we are family"));
        int binaryOneCount = IntegerOperation.getBinaryOneCount(-10);
        System.out.println(binaryOneCount);
    }
}
