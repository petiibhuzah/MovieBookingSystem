/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviebookingsystem;

/**
 *
 * @author Peti Ibhuzah
 */
public class Preferences {
     String username;
     String password;
    public Preferences() {
        username = "admin";
        password ="admin";
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername(String username) {
        return username;
    }
     public String getPassword() {
        return password;
    }

    Object getUsername() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
}
