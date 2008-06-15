package model.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import model.ruby.RubyModel;

import org.jruby.Ruby;

public class ModelApp {
    public static void main(String[] args) throws Exception {
        String script = readScriptFromFile();

        Ruby ruby = Ruby.newInstance();
        RubyModel.createModelClass(ruby);
        ruby.evalScriptlet("require 'model/helper'");
        RubyModel model = (RubyModel) ruby.evalScriptlet(script);

        System.out.println(model.toModel());
    }

    private static String readScriptFromFile() throws IOException {
        File file = new File("src/main/ruby/model.rb");

        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader reader = new BufferedReader(isr);

        String tmp = null;
        StringBuilder sb = new StringBuilder();
        while ((tmp = reader.readLine()) != null) {
            sb.append(tmp).append('\n');
        }
        return sb.toString();
    }
}
