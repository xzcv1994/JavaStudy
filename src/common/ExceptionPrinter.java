package common;

public class ExceptionPrinter {

    public static void print(Exception e) {
        StackTraceElement element = e.getStackTrace()[0];
        System.out.println("====== ExceptionPrinter ======");
        System.out.println(e.getMessage() + "\nclass : " + element.getClassName() + "\nmethod : " + element.getMethodName() + "\nline : " + element.getLineNumber());
        System.out.println("==============================");
    }
}
