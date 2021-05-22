package StudentManagementSystem;

import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Sujan
 */
public class Finance extends Main{
        String nexMnthTable=null;
       
        int x=0;
        int netLastBalance=0;
        
    public void insertLastBl(){
                        String date=dateChooserCombo2.getText();
                        String inserTable=null;
                        String month=jComboMonths1.getSelectedItem().toString();
                        String Description=month.concat("Balance Barrowed..");
                        try {
                         PreparedStatement ps=conn.prepareStatement("INSERT INTO "+nexMnthTable+"(In_Date,In_Description,In_Amount)values(?,?,?)");
                         ps.setString(1,date);
                         ps.setString(2,Description);
                         ps.setInt(3,netLastBalance);
                         ps.executeUpdate();
                         JOptionPane.showMessageDialog(null,"Income "+Description+" Added..");
                        } catch (SQLException | HeadlessException e) {
                            JOptionPane.showMessageDialog(null,e.getMessage());
                        }
    }
    //===================================================================================================
    //===================================================================================================
    public void updateLastBl(){
        String date=dateChooserCombo2.getText();
                        String inserTable=null;
                        String month=jComboMonths1.getSelectedItem().toString();
                        String Description=month.concat("Balance Barrowed..");
                        try {
                            String UpdateQuery =null;
                            PreparedStatement ps =null;
                         UpdateQuery="UPDATE "+nexMnthTable+" SET `In_Date`=?,`In_Amount`=? WHERE `In_Description`=?";
                         ps =conn.prepareStatement(UpdateQuery);
                         ps.setString(1,date);
                         ps.setInt(2,netLastBalance);
                          ps.setString(3,Description);
                         ps.executeUpdate();
                         JOptionPane.showMessageDialog(null,"Income "+Description+" Added..");
                        } catch (SQLException | HeadlessException e) {
                            JOptionPane.showMessageDialog(null,e.getMessage());
                        }
    }
    //===================================================================================================
    //===================================================================================================
    public void setNxtmnthBalance() throws SQLException{
        String year=YearIn.getText();
        String finalYearAccount="TotalAccountsOf".concat(year);
        String month=jComboMonths1.getSelectedItem().toString();
        
        String netBlns=null;
        try {
            String sql="SELECT `Net_Balance` FROM "+finalYearAccount+" WHERE Ex_Month='"+month+"'";
             ps=conn.prepareStatement(sql);
             rs=ps.executeQuery(sql);
             if(rs.next()){
             netBlns=rs.getString("Net_Balance");
        } 
        }catch (Exception e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
         netLastBalance=Integer.parseInt(netBlns);
         
        switch(month){ 
            
                    case "January":nexMnthTable=year.concat("February").concat("Accounts");
                         insertLastBl();
                         break;
                    case "February":nexMnthTable=year.concat("March").concat("Accounts");
                         insertLastBl();   
                        break;
                    case "March":nexMnthTable=year.concat("April").concat("Accounts");
                            insertLastBl();
                        break;
                    case "April":nexMnthTable=year.concat("May").concat("Accounts");
                            insertLastBl();
                        break;
                    case "May":nexMnthTable=year.concat("June").concat("Accounts");
                            insertLastBl();
                        break;
                    case "June":nexMnthTable=year.concat("July").concat("Accounts");
                            insertLastBl();
                        break;
                    case "July":nexMnthTable=year.concat("August").concat("Accounts");
                            insertLastBl();
                        break;
                    case "August":nexMnthTable=year.concat("September").concat("Accounts");
                            insertLastBl();
                        break;
                    case "September":nexMnthTable=year.concat("October").concat("Accounts");
                            insertLastBl();
                        break;
                    case "October":nexMnthTable=year.concat("November").concat("Accounts");
                            insertLastBl();
                        break;
                    case "November":nexMnthTable=year.concat("December").concat("Accounts");
                            insertLastBl();
                        break;
                    case "December":int yr=Integer.parseInt(year)+1;
                                    String jnYr=Integer.toString(yr);
                                    nexMnthTable=jnYr.concat("January").concat("Accounts");
                                    insertLastBl();
                        break;

                }
    }
    //===================================================================================================
    //===================================================================================================
    public void upNxtmnthBalance() throws SQLException{
        String year=YearIn.getText();
        String finalYearAccount="TotalAccountsOf".concat(year);
        String month=jComboMonths1.getSelectedItem().toString();
        
        String netBlns=null;
        try {
            String sql="SELECT `Net_Balance` FROM "+finalYearAccount+" WHERE Ex_Month='"+month+"'";
             ps=conn.prepareStatement(sql);
             rs=ps.executeQuery(sql);
             if(rs.next()){
             netBlns=rs.getString("Net_Balance");
        } 
        }catch (Exception e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
         netLastBalance=Integer.parseInt(netBlns);
         
        switch(month){ 
            
                    case "January":nexMnthTable=year.concat("February").concat("Accounts");
                         updateLastBl();
                         break;
                    case "February":nexMnthTable=year.concat("March").concat("Accounts");
                         updateLastBl();   
                        break;
                    case "March":nexMnthTable=year.concat("April").concat("Accounts");
                            updateLastBl();
                        break;
                    case "April":nexMnthTable=year.concat("May").concat("Accounts");
                            updateLastBl();
                        break;
                    case "May":nexMnthTable=year.concat("June").concat("Accounts");
                            updateLastBl();
                        break;
                    case "June":nexMnthTable=year.concat("July").concat("Accounts");
                            updateLastBl();
                        break;
                    case "July":nexMnthTable=year.concat("August").concat("Accounts");
                            updateLastBl();
                        break;
                    case "August":nexMnthTable=year.concat("September").concat("Accounts");
                            updateLastBl();
                        break;
                    case "September":nexMnthTable=year.concat("October").concat("Accounts");
                            updateLastBl();
                        break;
                    case "October":nexMnthTable=year.concat("November").concat("Accounts");
                            updateLastBl();
                        break;
                    case "November":nexMnthTable=year.concat("December").concat("Accounts");
                            updateLastBl();
                        break;
                    case "December":int yr=Integer.parseInt(year)+1;
                                    String jnYr=Integer.toString(yr);
                                    nexMnthTable=jnYr.concat("January").concat("Accounts");
                                    updateLastBl();
                        break;

                }
    }
}
