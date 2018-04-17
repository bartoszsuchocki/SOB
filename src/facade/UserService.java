package facade;

import database.DataBase;
import usersAndBooks.User;

public class UserService {

    private DataBase db;
    private User u;

    public UserService() {
        db = new DataBase();
    }

    public void autheniticate(String login, String password, StringBuilder errorBuffer,
                              StringBuilder whichGui) {


        u = db.getUser(login);

        if (u != null && !(login.equals(u.getLogin())) && !(password.equals(u.getPassword()))) {
            u = null;
        } else {
            if (u.getRole() == 1)
                whichGui.append("wypozyczanie");
            else if (u.getRole() == 2)
                whichGui.append("admin");
        }


    }

    public void register(String login, String pesel, String name, String surname, String password,
                         StringBuilder errorBuffer) {

        User resultUser = db.getUser(login);

        if (resultUser != null) {
            errorBuffer.append("Login zajety!");
        } else {
            if (db.addUser(Long.valueOf(pesel), name, surname, login, password) == 1) {
                errorBuffer.append("");
            } else {
                errorBuffer.append("Nie udalo sie zarejestrowac");
            }
        }

    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }
}
