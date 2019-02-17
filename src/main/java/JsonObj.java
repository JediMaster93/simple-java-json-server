public class JsonObj {
    private String method;
    public  Person person = new Person();
    public static class Person{
        private int age;
        private String name;


        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "name: " + name + " age: " + age;
        }
    }

    public String getMethod() {
        return method;
    }
}
