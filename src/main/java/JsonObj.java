public class JsonObj {
    private String method;
    private int idToDelete; //for deleting
    public Person person = new Person();

    public int getIdToDelete() {
        return idToDelete;
    }

    public static class Person {
        private int age;
        private String name;
        private int id;

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
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
