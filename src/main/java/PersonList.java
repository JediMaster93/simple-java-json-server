import java.util.ArrayList;

public class PersonList {
    private ArrayList<JsonObj.Person> persons;
    public PersonList(){
        persons = new ArrayList<JsonObj.Person>();

    };
    public JsonObj.Person getByName(String name){
        for(JsonObj.Person p : persons){
            if (p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }
    public void append(JsonObj.Person person){
        this.persons.add(person);
    }

    public ArrayList<JsonObj.Person> getPersons() {
        return persons;
    }

    @Override
    public String toString() {
        String output="";
        for(JsonObj.Person p : persons){
            output += p.toString();
        }
        return output;

    }
}
