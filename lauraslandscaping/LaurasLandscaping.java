/**
 * --------------------------------------------------------
 * Class: Laura's Landscaping
 *
 * @author 
 * 
 * Developed: August
 *
 * Purpose: Employee Details screen from the larger development project.
 *
 * ----------------------------------------------------------
 */


package lauraslandscaping;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Set;


public class LaurasLandscaping extends JFrame implements ActionListener
{
    private int totale = 14;
    private String[] fieldLabels = {"Surname","Given Name","Phone","Fax","Mobile","Email","User Name","Password","User Level","Address 1","Address 2","Suburb","State","Postcode",};
    
    private JLabel[] labels = new JLabel[totale];
    private JTextField[] fields = new JTextField[totale];
    private JButton btn1, btn2, btn3, btnFind, btnExit, btn4, btn5, btnNext, btnLast;
    private String dataFileName = "LaurasLandscaping.csv";

    int max = 1000;
    int numE = 0;
    int entry = 0;

    Employee[] members = new Employee[max];
    
    
                public static void main(String[] args)
    {
        LaurasLandscaping lauraslandscapingApplication = new LaurasLandscaping();
        lauraslandscapingApplication.run();
    }
    
        private void run()
    {
setBounds(100, 50, 750, 360);
setTitle("Laura's Landscaping Employees Data Entry Screen");

addWindowListener(new WindowAdapter()
{
@Override
public void windowClosing(WindowEvent e)
{
System.exit(0);
}
});
        
        displayGUI();
        readDataFile(dataFileName);
                setResizable(false);
                setVisible(true);
            displayEntry(0);
    }


    private void displayGUI()
    {
        SpringLayout springLayout = new SpringLayout();   setLayout(springLayout);          displayLabels(springLayout); displayTextFields(springLayout); displayButtons(springLayout);
    }

    private void displayLabels(SpringLayout layout)
    {
                    int yPos;
        for (int y = 0; y < 9; y++) { yPos = y * 25 + 25; labels[y] = LibraryComponents.LocateAJLabel(this, layout, fieldLabels[y] + ":", 20, yPos);
        }
        for (int y = 9; y < 14; y++) {
   yPos = (y - 9) * 25 + 25; labels[y] = LibraryComponents.LocateAJLabel(this, layout, fieldLabels[y] + ":", 320, yPos);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Display GUI">    
    private void setupTable(SpringLayout layout)
    {
        setFieldProperties(6, 10, false, 254, 253, 205); setFieldProperties(6, 11, false, 254, 253, 205);
    } 

    public void setFieldProperties(int x, int y, boolean editable, int r, int g, int b)
    {
//  fields[x][y].setEditable(editable); fields[x][y].setBackground(new Color(r, g, b));
    }

    
    
    
    
    
    private void displayTextFields(SpringLayout layout)
    {
        int yPos;
        for (int y = 0; y < 9; y++) 
        {
            yPos = y * 25 + 25;
            fields[y] = LibraryComponents.LocateAJTextField(this, layout, 14, 120, yPos);
        }
        for (int y = 9; y < 14; y++) 
        {
            yPos = (y - 9) * 25 + 25;
            fields[y] = LibraryComponents.LocateAJTextField(this, layout, 14, 400, yPos);
        }
    }

    //</editor-fold>   
        private void displayButtons(SpringLayout layout) {
        btn4 = LibraryComponents.LocateAJButton(this, this, layout, "|<", 90, 260, 50, 25);
        btn5 = LibraryComponents.LocateAJButton(this, this, layout, "<", 140, 260, 50, 25);
        btnNext = LibraryComponents.LocateAJButton(this, this, layout, ">", 190, 260, 50, 25);
        btnLast = LibraryComponents.LocateAJButton(this, this, layout, ">|", 240, 260, 50, 25);
        btn1 = LibraryComponents.LocateAJButton(this, this, layout, "New", 600, 20, 120, 25);
        btn2 = LibraryComponents.LocateAJButton(this, this, layout, "Save", 600, 50, 120, 25);
        btn3 = LibraryComponents.LocateAJButton(this, this, layout, "Delete", 600, 80, 120, 25);
        btnFind = LibraryComponents.LocateAJButton(this, this, layout, "Find", 600, 110, 120, 25);
        btnExit = LibraryComponents.LocateAJButton(this, this, layout, "Exit", 600, 260, 120, 25);
    }
    


                
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btn4)
        {
            entry = 0;
            displayEntry(entry);
        }
        if(e.getSource() == btn5)
        {
            if(entry > 0)
            {
                entry--;
                displayEntry(entry);
            }
        }
        if (e.getSource()== btnNext)
        {
            if(entry < numE - 1)
            {
                entry++;
                displayEntry(entry);
            }
        }
    //<editor-fold defaultstate="collapsed" desc="Action and Key Listeners">    

        
        
        
        if(e.getSource() == btnLast)
        {
            entry = numE - 1;
            displayEntry(entry);
        }
        if (e.getSource() == btn1)
        {
            if (numE < max - 1)
            {
                numE++;
                entry = numE - 1;
                members[entry] = new Employee();
                displayEntry(entry);
            }
        }
        if (e.getSource() == btn2)
        {
            saveEntry(entry);
            writeDataFile(dataFileName);
        }
        if (e.getSource() == btn3)
        {
        }
        if (e.getSource() == btnFind)
        {
        }
        if (e.getSource() == btnExit)
        {
            System.exit(0);
        }
    }

    //</editor-fold>
    

    //<editor-fold defaultstate="collapsed" desc="Manage Screen Data">    

    public void displayEntry(int i)
    {
        String temp[] = members[i].getPersonDetails();                    
        for (int y = 0; y < 14; y++) 
        {
            fields[y].setText(temp[y]);
        }
    }

    public void saveEntry(int i)
    {
        String temp[] = new String[14];                    
        for (int y = 0; y < 14; y++) 
        {
            temp[y] = fields[y].getText();
        }
        members[i].setPersonDetails(temp);
    }
    
    private void ClearData()
    {
        for (int y = 0; y < 14; y++) 
        {
            fields[y].setText("");
        }
    }
 

            
    //</editor-fold>    

    
    //<editor-fold defaultstate="collapsed" desc="File Management">    

    private void readDataFile(String fileName)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            
            int i = 0;
            String line;
            
            line = br.readLine();
            
            while ((line = br.readLine()) != null)
            {
                String temp[] = line.split(",");  
                members[i] = new Employee(); 
                members[i].setPersonDetails(temp);
                i++;
            }
            numE = i;
            br.close();
        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());            
        }
    }

    public void writeDataFile(String fileName)
    {
        try
        {
            //BufferedWriter outFile = new BufferedWriter(new FileWriter(fileName));
            BufferedWriter outFile = new BufferedWriter(new FileWriter("LaurasLandscaping_NEW.csv"));
            
            String temp[];
            
            for (int i = 0; i < numE; i++)
            {
                temp = members[i].getPersonDetails();
                for (int j = 0; j < totale - 1; j++)
                {
                    outFile.write(temp[j] + ",");
                }
                outFile.write(temp[13]);
                outFile.newLine();
            }
            outFile.close();
            System.out.println("Laura's Landscaping data has been saved.");
        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }

    //</editor-fold>    

}


class Employee extends Person
{
    
    public Employee()
    {    
 	super();
        this.setUserLevel("Employee");     
    }

}


class Administrator extends Person
{
    
    public Administrator()
    {    
 	super();
        this.setUserLevel("Administrator");     
    }

}


class Person
{
    private String Surname = new String();   
    private String GivenName = new String();
    private String Phone = new String();
    private String Fax = new String();   
    private String Mobile = new String();
    private String Email = new String();
    private String UserName = new String();   
    private String Password = new String();
    private String UserLevel = new String();
    private String Address1 = new String();
    private String Address2 = new String();   
    private String Suburb = new String();
    private String State = new String();
    private String Postcode = new String();
    
    public Person()
    {    
        Surname = "Enter Surname...";
        GivenName = "Enter Given Name...";
        Phone = "";
        Fax = "";
        Mobile = "";
        Email = "";
        UserName = "";
        Password = "";
        UserLevel = "Undefined";
        Address1 = "";
        Address2 = "";
        Suburb = "";
        State = "";
        Postcode = "";
    }

    public void setPersonDetails(String[] personDetails)
    {
        Surname = personDetails[0];
        GivenName = personDetails[1];
        Phone = personDetails[2];
        Fax = personDetails[3];
        Mobile = personDetails[4];
        Email = personDetails[5];
        UserName = personDetails[6];
        Password = personDetails[7];
        UserLevel = personDetails[8];
        Address1 = personDetails[9];
        Address2 = personDetails[10];
        Suburb = personDetails[11];
        State = personDetails[12];
        Postcode = personDetails[13];
    }
    

    public String[] getPersonDetails()
    {
        String[] personDetails = new String[14];
        personDetails[0] = Surname;
        personDetails[1] = GivenName;
        personDetails[2] = Phone;
        personDetails[3] = Fax;
        personDetails[4] = Mobile;
        personDetails[5] = Email;
        personDetails[6] = UserName;
        personDetails[7] = Password;
        personDetails[8] = UserLevel;
        personDetails[9] = Address1;
        personDetails[10] = Address2;
        personDetails[11] = Suburb;
        personDetails[12] = State;
        personDetails[13] = Postcode;
        return personDetails;
    }

    
    //<editor-fold defaultstate="collapsed" desc="Setters">   
    
    public void setSurname(String Surname)
    {
        this.Surname = Surname;
    }

    public void setGivenName(String GivenName)
    {
        this.GivenName = GivenName;
    }

    public void setPhone(String Phone)
    {
        this.Phone = Phone;
    }

    public void setFax(String Fax)
    {
        this.Fax = Fax;
    }

    public void setMobile(String Mobile)
    {
        this.Mobile = Mobile;
    }

    public void setEmail(String Email)
    {
        this.Email = Email;
    }

    public void setUserName(String UserName)
    {
        this.UserName = UserName;
    }

    public void setPassword(String Password)
    {
        this.Password = Password;
    }

    public void setUserLevel(String UserLevel)
    {
        this.UserLevel = UserLevel;
    }

    public void setAddress1(String Address1)
    {
        this.Address1 = Address1;
    }

    public void setAddress2(String Address2)
    {
        this.Address2 = Address2;
    }

    public void setSuburb(String Suburb)
    {
        this.Suburb = Suburb;
    }

    public void setState(String State)
    {
        this.State = State;
    }

    public void setPostcode(String Postcode)
    {
        this.Postcode = Postcode;
    }

    //</editor-fold> 
    
    
    //<editor-fold defaultstate="collapsed" desc="Getters">   

    public String getSurname()
    {
        return Surname;
    }

    public String getGivenName()
    {
        return GivenName;
    }

    public String getPhone()
    {
        return Phone;
    }

    public String getFax()
    {
        return Fax;
    }

    public String getMobile()
    {
        return Mobile;
    }

    public String getEmail()
    {
        return Email;
    }

    public String getUserName()
    {
        return UserName;
    }

    public String getPassword()
    {
        return Password;
    }

    public String getUserLevel()
    {
        return UserLevel;
    }

    public String getAddress1()
    {
        return Address1;
    }

    public String getAddress2()
    {
        return Address2;
    }

    public String getSuburb()
    {
        return Suburb;
    }

    public String getState()
    {
        return State;
    }

    public String getPostcode()
    {
        return Postcode;
    }
 
    //</editor-fold> 


}

