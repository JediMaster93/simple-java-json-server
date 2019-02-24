import com.google.gson.Gson;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class RequestHandler implements Runnable {

    private PersonList personList;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public RequestHandler(ObjectOutputStream out, ObjectInputStream in, PersonList personList) {
        this.out = out;
        this.in = in;
        this.personList = personList;
    }

    public void run() {
        String inputString;
        try {

            while (true) {
                inputString = (String) in.readObject();
                System.out.println(inputString);


                if (Utils.isValidJson(inputString)) {

                    Gson gson = new Gson();
                    JsonObj json = gson.fromJson(inputString, JsonObj.class);
                    if (json.getMethod().equals("add")) {
                        this.personList.append(json.person);
                    }
                    if (json.getMethod().equals("get")) {
                        out.writeObject(gson.toJson(personList.getPersons()));
                    }
                    if (json.getMethod().equals("delete")) {
                        int id = json.getIdToDelete();
                        this.personList.removeById(id);
                    }
                }

            }
        } catch (IOException e) {
            // e.printStackTrace();
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
        }
    }
}