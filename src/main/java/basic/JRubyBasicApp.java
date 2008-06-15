package basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.jruby.Ruby;
import org.jruby.javasupport.JavaEmbedUtils;
import org.jruby.runtime.GlobalVariable;
import org.jruby.runtime.builtin.IRubyObject;

public class JRubyBasicApp {
    public static void main(String[] args) throws FileNotFoundException {
        Ruby ruby = Ruby.newInstance();

        evalString(ruby);
        evalFile(ruby);
        printReturnValue(ruby);
        setGlobalVariable(ruby);
        getGlobalVariable(ruby);
        
        ruby.tearDown();
    }

    private static void evalString(Ruby ruby) {
        ruby.evalScriptlet("puts 'Hello, World!'");
    }

    private static void evalFile(Ruby ruby) throws FileNotFoundException {
        String filename = "src/main/ruby/hello.rb";
        InputStream inputStream = new FileInputStream(filename);
        ruby.runFromMain(inputStream, filename);
    }

    private static void printReturnValue(Ruby ruby) {
        IRubyObject obj = ruby.evalScriptlet("1 + 1");
        Integer num = (Integer) JavaEmbedUtils.rubyToJava(ruby, obj,
                Integer.class);
        System.out.println(num);
    }

    private static void setGlobalVariable(Ruby ruby) {
        IRubyObject name = JavaEmbedUtils.javaToRuby(ruby, "Duke");
        GlobalVariable variable = new GlobalVariable(ruby, "$name", name);
        ruby.defineVariable(variable);
        ruby.evalScriptlet("puts $name");
    }

    private static void getGlobalVariable(Ruby ruby) {
        ruby.evalScriptlet("$age = 13");
        IRubyObject age = ruby.getGlobalVariables().get("$age");
        System.out.println(age);
    }
}
