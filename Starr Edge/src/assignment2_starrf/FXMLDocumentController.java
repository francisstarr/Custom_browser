/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2_starrf;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author orio5
 */
public class FXMLDocumentController implements Initializable {
    

    @FXML
    //the "go" button, press it to display the URL, part of task 2
    private Button launcher;
    
    @FXML
    //this is the window for displaying the requested web page, part of task 2
    private WebView onlineView;
    
    @FXML
    //type your URL into this textfield, part of task 2
    private TextField urlField;
    
    //this stores the the index of the current URL in the history array list
    //a value of -1 means the user hasn't visited any URLs yet
    private int position=-1;
   
    //this stores all previously visited URLs so it can be re-visited with the forwards/backwards buttons
    private ArrayList<String> history = new ArrayList<>();
   
    @FXML
    //press this button to go to your previous URL if it exists
    private Button backwards;
    
    @FXML
    //press this button to go to your next URL if it exists
    private Button forwards;
  
    //this method adds your current URL to the history array list
    private void add(String getter){
        if (position < history.size()-1){
            history.subList(position+1, history.size()).clear();
        }
        history.add(getter);
        position=history.size()-1;
    }
    
    //this method re-assigns position as the next index of the history array list 
    //if that index is within bounds
    private void backwards(){
        if (position>0) position--;
    }
    
    //this method re-assigns position as the next index of the history array list 
    //if that index is within bounds
    private void forwards(){
        if (position<history.size()-1) position++;
    }
    
    //this method displays any URL given as an argument into the WebView window called onlineView
    private void goToSite(String getter){
    
    //this method will only attempt to display the URL if the URL is a non-empty string
        if (getter.length()>0){
            
            //this loop is task 5 of the assignment
            if (!(getter.substring(0,7).equals("http://")||getter.substring(0,8).equals("https://"))){
                getter = "http://" + getter;
            }
            
            //variable x fulfills task 6
            WebEngine x = onlineView.getEngine();
            x.load(getter);  
            
            urlField.setText(getter); 
            checkButtons();
            onlineView.setOpacity(1);
        }
        
    }
    //this method disables the forwards/backwards buttons when applicable
    private void checkButtons(){
        if (position >= history.size()-1) forwards.setDisable(true);
        else forwards.setDisable(false);
        if (position <= 0) backwards.setDisable(true);
        else backwards.setDisable(false);

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //automatically disables forwards/backwards upon launch of browser
        checkButtons();
    }    
   
    @FXML
    //this method displays the URL from the textfield
    public void launching(ActionEvent event) {
        String getter = urlField.getText();
        add(getter);
        goToSite(getter);
    }
    
    @FXML
    //this method displays the previous URL
    public void goBack(ActionEvent event){
        backwards(); 
        String getter = history.get(position);
        goToSite(getter);
    }
    
    @FXML
    //this method displyas the next URL
    public void goForw(ActionEvent event){
        forwards(); 
        String getter = history.get(position);
        goToSite(getter);
    }
  
}
