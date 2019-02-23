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
/*
                        System.out.println(personList.getPersons());;
                        System.out.println("writing");
                        out.writeObject(personList.getPersons().toString());
                        System.out.println("written");
*/
                        out.writeObject(gson.toJson(personList.getPersons()));
                        //System.out.println(gson.toJson(personList.getPersons()));
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