package StudentManagementSystem;

import static StudentManagementSystem.Main.jTableStudentView;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;


/**
 *
 * @author Sujan
 */
public class Student extends Main{
    
    
    //===========================================================================
        //RETRIVE ALL STUDENT DATA FROM DATABASE
    //===========================================================================
    public void allStudentDataView(){
        try {
                String qry="select St_Index,St_Name,St_Address,St_Tel,St_Parent,St_Emgy_tel from studentdetails";
                PreparedStatement ps=conn.prepareStatement(qry);
                rs=ps.executeQuery();
                jTableStudentView.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
    }
    
    
    
    //===========================================================================
}
