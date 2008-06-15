package model.ruby;

import model.CPU;
import model.HDD;
import model.Model;
import model.Memory;

import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyHash;
import org.jruby.RubyObject;
import org.jruby.RubySymbol;
import org.jruby.anno.JRubyClass;
import org.jruby.anno.JRubyMethod;
import org.jruby.javasupport.JavaEmbedUtils;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.builtin.IRubyObject;

@JRubyClass(name = "Model")
public class RubyModel extends RubyObject {

    private static final ObjectAllocator MODEL_ALLOCATOR = new ObjectAllocator() {
        public IRubyObject allocate(Ruby runtime, RubyClass klass) {
            return new RubyModel(runtime, klass);
        }
    };

    public static RubyClass createModelClass(Ruby runtime) {
        RubyClass modelClass = runtime.defineClass("Model",
                runtime.getObject(), MODEL_ALLOCATOR);
        modelClass.defineAnnotatedMethods(RubyModel.class);

        return modelClass;
    }

    private Model model;

    public RubyModel(Ruby runtime, RubyClass metaClass) {
        super(runtime, metaClass);
    }

    @JRubyMethod(name = "cpu")
    public IRubyObject cpu(IRubyObject param) {
        RubyHash opts = param.convertToHash();
        String speed = (String) opts.get(RubySymbol.newSymbol(getRuntime(),
                "speed"));
        Long core = (Long) opts.get(RubySymbol.newSymbol(getRuntime(), "core"));

        CPU cpu = new CPU();
        cpu.setSpeed(speed);
        cpu.setCore(core);

        model.setCpu(cpu);

        return this;
    }

    @JRubyMethod(name = "hdd")
    public IRubyObject hdd(IRubyObject param) {
        RubyHash opts = param.convertToHash();
        String size = (String) opts.get(RubySymbol.newSymbol(getRuntime(),
                "size"));

        HDD hdd = new HDD();
        hdd.setSize(size);

        model.setHdd(hdd);

        return this;
    }
    
    @JRubyMethod(name = "memory")
    public IRubyObject memory(IRubyObject param) {
        RubyHash opts = param.convertToHash();
        String size = (String) opts.get(RubySymbol.newSymbol(getRuntime(),
                "size"));

        Memory memory = new Memory();
        memory.setSize(size);

        model.setMemory(memory);

        return this;
    }

    @JRubyMethod(name = "initialize")
    public IRubyObject initialize(IRubyObject name) {
        model = new Model();
        String jname = (String) JavaEmbedUtils.rubyToJava(getRuntime(), name,
                String.class);
        model.setName(jname);

        return this;
    }
    
    public Model toModel() {
        return model;
    }

}
