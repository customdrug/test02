package test.task04.models;

final class IdGenerator {
    private static int next = 0;
    private IdGenerator() {}
    static int nextId() { return ++next; }


}