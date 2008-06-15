package model;

public class Model {

    private CPU cpu;
    private HDD hdd;
    private Memory memory;
    private String name;

    public CPU getCpu() {
        return cpu;
    }

    public HDD getHdd() {
        return hdd;
    }

    public Memory getMemory() {
        return memory;
    }

    public String getName() {
        return name;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public void setHdd(HDD hdd) {
        this.hdd = hdd;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        StringBuilder buff = new StringBuilder();
        buff.append(name).append('\n');
        buff.append("CPU Speed: ").append(cpu.getSpeed()).append('\n');
        buff.append("CPU Core: ").append(cpu.getCore()).append('\n');
        buff.append("Memory Size: ").append(memory.getSize()).append('\n');
        buff.append("HDD Size: ").append(memory.getSize()).append('\n');

        return buff.toString();
    }

}
