package tricolor;

import java.awt.Graphics;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jruby.Ruby;
import org.jruby.javasupport.JavaEmbedUtils;
import org.jruby.runtime.builtin.IRubyObject;

public class TricolorApp extends JPanel {

    public static void main(String[] args) {
        TricolorApp app = new TricolorApp();

        JFrame frame = new JFrame();
        frame.getContentPane().add(app);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(10, 10, 300, 200);
        frame.setTitle("Tricolor App");
        frame.setVisible(true);
    }

    private Ruby ruby;

    public TricolorApp() {
        ruby = Ruby.newInstance();
        ruby.evalScriptlet("require 'tricolor/helper'");
    }

    @Override
    protected void paintComponent(Graphics g) {
        defineGlobalVariable(g);
        evalDSL();
    }

    private void defineGlobalVariable(Graphics g) {
        IRubyObject obj = JavaEmbedUtils.javaToRuby(ruby, g);
        ruby.defineReadonlyVariable("$g", obj);
    }

    private void evalDSL() {
        InputStream in = null;
        try {
            String script = "src/main/ruby/tricolor.rb";
            File file = new File(script);

            in = new BufferedInputStream(new FileInputStream(file));
            ruby.runFromMain(in, script);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
