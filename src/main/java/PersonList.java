import com.google.gson.Gson;

import java.util.ArrayList;

public class PersonList {
    private ArrayList<JsonObj.Person> persons;
    private static int personsId = 0;

    public PersonList() {
        persons = new ArrayList<JsonObj.Person>();

    }

    public JsonObj.Person getByName(String name) {
        for (JsonObj.Person p : persons) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public void append(JsonObj.Person person) {
        person.setId(personsId++);
        this.persons.add(person);
    }

    public ArrayList<JsonObj.Person> getPersons() {
        return persons;
    }

    public String personsToJson() {
        Gson gson = new Gson();
        return gson.toJson(this.persons);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (JsonObj.Person p : persons) {
            output.append(p.toString());
        }
        return output.toString();
    }
    public boolean removeById(int id){
        boolean removed = false;
        for (JsonObj.Person person : persons) {
            if(person.getId() == id){
                persons.remove(person);
                removed = true;
            }

        }
        return  removed;
    }
}
