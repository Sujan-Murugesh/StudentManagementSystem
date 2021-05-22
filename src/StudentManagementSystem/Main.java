
package StudentManagementSystem;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Sujan
 */
public class Main extends javax.swing.JFrame {
    Connection conn;
    Statement stmt=null;
    PreparedStatement ps;
    ResultSet rs;
    static Student stn=new Student();
    static Courses chooice=new Courses();
    static Courses insertcourse=new Courses();
    static Courses Upcourse=new Courses();
    static Courses Deletecourse=new Courses();
    static Courses courseView=new Courses();
    static Courses courseREsView=new Courses();
    static Student viewallStu=new Student();
    static Finance nxtMn=new Finance();
    static Teachers dpChse=new Teachers();
    static Password chpwd=new Password();
    /**
     * Creates new form Main
     */
    public Main() {
        URL iconUrl = getClass().getResource("ssslogo200.png");
        ImageIcon imageicon=new ImageIcon(iconUrl);
        this.setIconImage(imageicon.getImage());
        this.setTitle("Student Management System  Developer:Sujan Murugesh sjnkayal36@gmail.com");
        initComponents();
        //conn=Db_connection.connect();
        conn=ServerConnectionPanel.connect();
        setResizable(false);
        tableHead();
        //lblBatch.setVisible(false);
        //Set_Date_And_Time();
        
        
    }
    
    //===========================================   =============================================
        //THIS SECTION USED TO GET ST ID AND NAME SET JTABLE ATTENDANCE REGISTRATION FORM
    //==========================================================================================
     public ArrayList<Attendance> getStudentRow()
        {
            ArrayList<Attendance> productList = new ArrayList<Attendance>();
            if("".equals(AttenBatch.getText())){
                JOptionPane.showMessageDialog(null,"Fill the batch feild..");
                AttenBatch.requestFocus();
            }
            else if(jComboBoxcrs.getSelectedItem().equals("Select Course")){
                JOptionPane.showMessageDialog(null,"Please select the course...");
            }
            else{
                
                String batch=AttenBatch.getText();
                String course=jComboBoxcrs.getSelectedItem().toString(); 
                //Connection conn = getconnection();
                String query ="SELECT * FROM `studentdetails` where St_Batch ='"+batch+"' and `"+course+"`='Yes'";
            
           // Statement st;
           // ResultSet rs;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            //rs = (resultSet) st.executeQuery(query);
            Attendance attendance; 
            if(rs.next()){
                while(rs.next())
                {
                        attendance = new Attendance(rs.getString("St_Index"),rs.getString("St_Name"));
                        productList.add(attendance);
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Sorry Something went wrrong!!!");
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
           JOptionPane.showMessageDialog(null,ex.getMessage());
        }
           
            }
         return productList;
        }
     //===========================================================
            //THIS SECTION USED TO SET JTABLE  
     public  void show_Students_In_JTable()
        {
            ArrayList<Attendance> list= getStudentRow();
            DefaultTableModel model = (DefaultTableModel)jTableStAttendance.getModel();
            
            //clear JTable content
            model.setRowCount(0);
            //show Jtable
            Object[] row =new Object[2];
            for (int  i= 0; i<list.size(); i++) {
             
                row[0] =list.get(i).getId();
                row[1] =list.get(i).getName();
                model.addRow(row);
            }
        }
     
     //============================================================================================
    
    //Resize image
        String ImgPath = null;
        public ImageIcon ResizeImage(String ImgPath,byte[] pic)
        {
            ImageIcon myImage= null;
            if(ImgPath != null)
            {
                myImage = new ImageIcon(ImgPath);
            }else
            {
                myImage =new ImageIcon(pic);
            }
            
            Image img= myImage.getImage();
            Image img2= img.getScaledInstance(lbl_image.getWidth(),lbl_image.getHeight(),Image.SCALE_SMOOTH);
            ImageIcon image =new ImageIcon(img2);
            return image;
        }
    
        //=================================
        //This method setting system time ....
        //=================================
         // private void Set_Date_And_Time(){
          //    Time.setTime();
         // }
       //==================================
            public void view_All_Student_Data(){
                viewallStu.allStudentDataView();
            }
        //=================================
            public void chFiles(){
                chooice.chooiceResourse();
            }
        //=================================
            public void insertCourses(){
                insertcourse.inserting_Course();
            }
        //=================================
            public void UpdateCourses(){
                Upcourse.updating_Course();
            }
        //================================= 
            public void Delete_Course_Rows(){
                Deletecourse.deleteCourseRows();
            }
        //================================= 
            public void courcesViewing(){
                courseView.viewingCourse();
            }
          //=================================    
            public void courceResourceFileOpen(){
                courseREsView.showCourseResorce();
            }
         //================================= 
            public void insertNxtMntNtBlnc() throws SQLException{
                nxtMn.setNxtmnthBalance();
            }
         //==================================
            public void upNxtMnthBlnc() throws SQLException{
                nxtMn.upNxtmnthBalance();
            }
            
         //===================================
            public void teacherDPchoicebtn(){
                dpChse.chooiceTeachImg();
            }
            
            public void addTeacherInfo(){
                dpChse.addTechInfor();
            }
            
            public void clrTechFm(){
                dpChse.clearinTechForm();
            }
            //
            public void UpdateTeacherInfo(){
                dpChse.UpdatTechInfor();
            }
            
            public void viewTeacherList(){
                 dpChse.seeTeacList();
            }
            
            public void techClickOpenInfo(){
                dpChse.techViewTblinfoView();
            }
        //==================================================
            public void passwordChanging() throws NoSuchAlgorithmException{
                chpwd.changeUserPassword();
            }
            
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooserDialog1 = new datechooser.beans.DateChooserDialog();
        main_panel = new javax.swing.JPanel();
        Title_panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        StaticPane = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        dynamic = new javax.swing.JPanel();
        Title = new javax.swing.JPanel();
        homeTitle = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        Student = new javax.swing.JPanel();
        St_select = new javax.swing.JPanel();
        btn_registration = new javax.swing.JButton();
        btn_view_stu_info = new javax.swing.JButton();
        jButton62 = new javax.swing.JButton();
        jButton64 = new javax.swing.JButton();
        jButton65 = new javax.swing.JButton();
        St_base = new javax.swing.JPanel();
        Stu_registration_form = new javax.swing.JPanel();
        St_reg = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        St_index = new javax.swing.JTextField();
        St_name = new javax.swing.JTextField();
        St_address = new javax.swing.JTextField();
        St_mail = new javax.swing.JTextField();
        St_tel = new javax.swing.JTextField();
        St_batch = new javax.swing.JTextField();
        St_emgy_tel = new javax.swing.JTextField();
        St_parent_name = new javax.swing.JTextField();
        St_pic_select = new javax.swing.JButton();
        St_course_btech = new javax.swing.JRadioButton();
        St_course_stech = new javax.swing.JRadioButton();
        St_course_agri = new javax.swing.JRadioButton();
        St_addmissionfee_chbox = new javax.swing.JCheckBox();
        lbl_image = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        St_age = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jButton35 = new javax.swing.JButton();
        Stu_view_info = new javax.swing.JPanel();
        St_view = new javax.swing.JPanel();
        jButton17 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableStudentView = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton18 = new javax.swing.JButton();
        findBatchStu = new javax.swing.JTextField();
        jButton19 = new javax.swing.JButton();
        jButton55 = new javax.swing.JButton();
        Batch = new javax.swing.JPanel();
        batchTitle = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        batchbase = new javax.swing.JPanel();
        addnewbatch = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtbatchyear = new javax.swing.JTextField();
        btn_create_batch = new javax.swing.JButton();
        btn_delete_batch = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        Searchbatch = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        batchTxtFind = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFindBatch = new javax.swing.JTable();
        jButton56 = new javax.swing.JButton();
        Finance = new javax.swing.JPanel();
        FinanceTitle = new javax.swing.JPanel();
        FinanceBase = new javax.swing.JPanel();
        FinanceFees = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableFeesRetriver = new javax.swing.JTable();
        jButton20 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton21 = new javax.swing.JButton();
        financeStuName = new javax.swing.JLabel();
        jRadioButton_bTech = new javax.swing.JRadioButton();
        jRadioButton_sTech = new javax.swing.JRadioButton();
        jRadioButton_agri = new javax.swing.JRadioButton();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jTextFeesAmount = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jComboMonths = new javax.swing.JComboBox<>();
        jLabelBatch = new javax.swing.JLabel();
        jTextFieldBATCHinput = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jButton24 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jComboBoxLevel = new javax.swing.JComboBox<>();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jButton53 = new javax.swing.JButton();
        jButton54 = new javax.swing.JButton();
        FinanceStatements = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableFeesRetriver1 = new javax.swing.JTable();
        jButton27 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        DescripsionIn = new javax.swing.JTextField();
        jButton28 = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        jTextFeesAmount1 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jButton29 = new javax.swing.JButton();
        jComboMonths1 = new javax.swing.JComboBox<>();
        jTextFieldBATCHinput1 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jButton31 = new javax.swing.JButton();
        jComboBoxLevel1 = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();
        jLabel48 = new javax.swing.JLabel();
        YearIn = new javax.swing.JTextField();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        TotalExpensetxt = new javax.swing.JTextField();
        TotalIncometxt = new javax.swing.JTextField();
        Totalbalancetxt = new javax.swing.JTextField();
        jButton34 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        Course = new javax.swing.JPanel();
        Course_title = new javax.swing.JPanel();
        jButton15 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        Course_base = new javax.swing.JPanel();
        add_course = new javax.swing.JPanel();
        course_form = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jTextC_Session_Id = new javax.swing.JTextField();
        jTextC_Session_name = new javax.swing.JTextField();
        jTextC_theory_hourse = new javax.swing.JTextField();
        jTextC_Practical_hours = new javax.swing.JTextField();
        jTextC_remarks = new javax.swing.JTextField();
        jButtonChooseResourse = new javax.swing.JButton();
        jButtonAddCourse = new javax.swing.JButton();
        jButtonUpdateCourse = new javax.swing.JButton();
        jButtonDeleteCourse = new javax.swing.JButton();
        jComboBoxchCourse = new javax.swing.JComboBox<>();
        jLabel89 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        Course_view = new javax.swing.JPanel();
        Course_view1 = new javax.swing.JPanel();
        jCombocourse = new javax.swing.JComboBox<>();
        jButton16 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablecourceView = new javax.swing.JTable();
        courceRePath = new javax.swing.JLabel();
        settings = new javax.swing.JPanel();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        SettingBase = new javax.swing.JPanel();
        jTeachersView = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableTeacherView = new javax.swing.JTable();
        jLabel100 = new javax.swing.JLabel();
        jButton46 = new javax.swing.JButton();
        TECH_IMG_LBL1 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabelTname = new javax.swing.JLabel();
        jLabelTnic = new javax.swing.JLabel();
        jLabeldob = new javax.swing.JLabel();
        jLabelTsubject = new javax.swing.JLabel();
        jLabelTemail = new javax.swing.JLabel();
        jLabelTtel = new javax.swing.JLabel();
        jLabelTqualific = new javax.swing.JLabel();
        jLabelTdoJoin = new javax.swing.JLabel();
        jLabelTaddress = new javax.swing.JLabel();
        jPanelRegisterTeacher = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        TREG_name = new javax.swing.JTextField();
        TREG_nic = new javax.swing.JTextField();
        TREG_dob = new javax.swing.JTextField();
        TREG_subject = new javax.swing.JTextField();
        TREG_email = new javax.swing.JTextField();
        TREG_tel = new javax.swing.JTextField();
        TREG_qualific = new javax.swing.JTextField();
        TREG_joindate = new javax.swing.JTextField();
        TECH_IMG_LBL = new javax.swing.JLabel();
        jButton41 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jLabel58 = new javax.swing.JLabel();
        TREG_address = new javax.swing.JTextField();
        jPanelChangePwd = new javax.swing.JPanel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jButtonSavePwd = new javax.swing.JButton();
        jButtoncancelpwd = new javax.swing.JButton();
        pwdUserId = new javax.swing.JTextField();
        pwdOld = new javax.swing.JPasswordField();
        pwdNew = new javax.swing.JPasswordField();
        pwdConfirm = new javax.swing.JPasswordField();
        jLabel94 = new javax.swing.JLabel();
        Developer = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel112 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        Attendance = new javax.swing.JPanel();
        dateChooserCombo3 = new datechooser.beans.DateChooserCombo();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableStAttendance = new javax.swing.JTable();
        jButton47 = new javax.swing.JButton();
        jLabel75 = new javax.swing.JLabel();
        AttenBatch = new javax.swing.JTextField();
        jComboBoxcrs = new javax.swing.JComboBox<>();
        jButton48 = new javax.swing.JButton();
        jButton49 = new javax.swing.JButton();
        jButton50 = new javax.swing.JButton();
        jLabel76 = new javax.swing.JLabel();
        AttenCyear = new javax.swing.JTextField();
        jComboBoxMonth = new javax.swing.JComboBox<>();
        jCheckBoxPresense = new javax.swing.JCheckBox();
        jButton51 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        AttendanceViewPanel = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        AttenBatch1 = new javax.swing.JTextField();
        jComboBoxcrs1 = new javax.swing.JComboBox<>();
        jLabel78 = new javax.swing.JLabel();
        AttenCyear1 = new javax.swing.JTextField();
        jComboBoxMonth1 = new javax.swing.JComboBox<>();
        jButton58 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableAttendanceViewer = new javax.swing.JTable();
        attendanceText = new javax.swing.JLabel();
        addFinalResults = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        resultBATCH = new javax.swing.JTextField();
        jButton57 = new javax.swing.JButton();
        resulStu_id = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        jButton59 = new javax.swing.JButton();
        StNameRes = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        resultIndexNo = new javax.swing.JTextField();
        btlbl = new javax.swing.JLabel();
        stlbl = new javax.swing.JLabel();
        agrilbl = new javax.swing.JLabel();
        resultbtecIn = new javax.swing.JTextField();
        stIn = new javax.swing.JTextField();
        agriIn = new javax.swing.JTextField();
        jButton60 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTableStFinalResults = new javax.swing.JTable();
        jButton61 = new javax.swing.JButton();
        jButton63 = new javax.swing.JButton();
        exams_and_marks = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        marksStuId = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        jButton66 = new javax.swing.JButton();
        jLabel96 = new javax.swing.JLabel();
        marks_batch = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        examTitle = new javax.swing.JTextField();
        jButton67 = new javax.swing.JButton();
        marksSTUname = new javax.swing.JLabel();
        marksIn = new javax.swing.JTextField();
        btlbl1 = new javax.swing.JLabel();
        jButton68 = new javax.swing.JButton();
        jButton69 = new javax.swing.JButton();
        jComboBoxchCourse1 = new javax.swing.JComboBox<>();
        jButton70 = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTableMarksView = new javax.swing.JTable();
        jButton71 = new javax.swing.JButton();
        graphs = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jButton72 = new javax.swing.JButton();
        graphBatchIN = new javax.swing.JTextField();
        jProgressBar1 = new javax.swing.JProgressBar();
        jProgressBar2 = new javax.swing.JProgressBar();
        jProgressBar3 = new javax.swing.JProgressBar();
        jLabel98 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jProgressBar4 = new javax.swing.JProgressBar();
        jProgressBar5 = new javax.swing.JProgressBar();
        jProgressBar6 = new javax.swing.JProgressBar();
        jProgressBar7 = new javax.swing.JProgressBar();
        jProgressBar8 = new javax.swing.JProgressBar();
        jProgressBar9 = new javax.swing.JProgressBar();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jProgressBar10 = new javax.swing.JProgressBar();
        jProgressBar11 = new javax.swing.JProgressBar();
        jProgressBar12 = new javax.swing.JProgressBar();
        jLabel104 = new javax.swing.JLabel();
        jProgressBar13 = new javax.swing.JProgressBar();
        jProgressBar14 = new javax.swing.JProgressBar();
        jProgressBar15 = new javax.swing.JProgressBar();
        jLabel79 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabelba = new javax.swing.JLabel();
        jLabelbb = new javax.swing.JLabel();
        jLabelbc = new javax.swing.JLabel();
        jLabelbs = new javax.swing.JLabel();
        jLabelbf = new javax.swing.JLabel();
        jLabelsa = new javax.swing.JLabel();
        jLabelsb = new javax.swing.JLabel();
        jLabelsc = new javax.swing.JLabel();
        jLabelss = new javax.swing.JLabel();
        jLabelsf = new javax.swing.JLabel();
        jLabelaa = new javax.swing.JLabel();
        jLabelab = new javax.swing.JLabel();
        jLabelac = new javax.swing.JLabel();
        jLabelas = new javax.swing.JLabel();
        jLabelaf = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabelTBA = new javax.swing.JLabel();
        jLabelTBB = new javax.swing.JLabel();
        jLabelTBC = new javax.swing.JLabel();
        jLabelTBS = new javax.swing.JLabel();
        jLabelTBF = new javax.swing.JLabel();
        jLabelTSA = new javax.swing.JLabel();
        jLabelTSB = new javax.swing.JLabel();
        jLabelTSC = new javax.swing.JLabel();
        jLabelTSS = new javax.swing.JLabel();
        jLabelTSF = new javax.swing.JLabel();
        jLabelTAA = new javax.swing.JLabel();
        jLabelTAB = new javax.swing.JLabel();
        jLabelTAC = new javax.swing.JLabel();
        jLabelTAS = new javax.swing.JLabel();
        jLabelTAF = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel110 = new javax.swing.JLabel();
        jLabeltOTALsTUDENTS = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        main_panel.setBackground(new java.awt.Color(102, 102, 102));

        Title_panel.setBackground(new java.awt.Color(0, 102, 204));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Trajan Pro 3", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("INSTITUTE  MANAGEMENT SYSTEM");

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Sujan Software Solutions");

        javax.swing.GroupLayout Title_panelLayout = new javax.swing.GroupLayout(Title_panel);
        Title_panel.setLayout(Title_panelLayout);
        Title_panelLayout.setHorizontalGroup(
            Title_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Title_panelLayout.createSequentialGroup()
                .addGroup(Title_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Title_panelLayout.createSequentialGroup()
                        .addGap(285, 285, 285)
                        .addComponent(jLabel1))
                    .addGroup(Title_panelLayout.createSequentialGroup()
                        .addGap(478, 478, 478)
                        .addComponent(jLabel4)))
                .addContainerGap(407, Short.MAX_VALUE))
        );
        Title_panelLayout.setVerticalGroup(
            Title_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Title_panelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        StaticPane.setBackground(new java.awt.Color(0, 102, 102));
        StaticPane.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(204, 255, 255), null, null));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/home.png"))); // NOI18N
        jButton1.setText("HOME ");
        jButton1.setBorder(new javax.swing.border.MatteBorder(null));
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 51, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/settins.png"))); // NOI18N
        jButton2.setText("SETTINGS");
        jButton2.setBorder(new javax.swing.border.MatteBorder(null));
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 51, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_key_50px.png"))); // NOI18N
        jButton3.setText("PCY&POLICY");
        jButton3.setBorder(new javax.swing.border.MatteBorder(null));
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 51, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_short_hair_girl_question_mark_50px.png"))); // NOI18N
        jButton4.setText("HELP GUIDE");
        jButton4.setBorder(new javax.swing.border.MatteBorder(null));
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 51, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_under_computer_50px.png"))); // NOI18N
        jButton5.setText("DEVELOPER");
        jButton5.setBorder(new javax.swing.border.MatteBorder(null));
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton30.setForeground(new java.awt.Color(0, 51, 255));
        jButton30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_shutdown_50px.png"))); // NOI18N
        jButton30.setText("EXIS");
        jButton30.setBorder(new javax.swing.border.MatteBorder(null));
        jButton30.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jButton37.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton37.setForeground(new java.awt.Color(0, 51, 255));
        jButton37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_under_computer_50px.png"))); // NOI18N
        jButton37.setText("ATTENDANCE");
        jButton37.setBorder(new javax.swing.border.MatteBorder(null));
        jButton37.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout StaticPaneLayout = new javax.swing.GroupLayout(StaticPane);
        StaticPane.setLayout(StaticPaneLayout);
        StaticPaneLayout.setHorizontalGroup(
            StaticPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StaticPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(StaticPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        StaticPaneLayout.setVerticalGroup(
            StaticPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StaticPaneLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        dynamic.setBackground(new java.awt.Color(153, 255, 153));
        dynamic.setLayout(new java.awt.CardLayout());

        Title.setBackground(new java.awt.Color(153, 255, 153));

        homeTitle.setBackground(new java.awt.Color(0, 153, 255));

        jLabel2.setBackground(new java.awt.Color(153, 255, 204));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_id_male_student_100px.png"))); // NOI18N
        jLabel2.setText("STUDENT    DETAILS");
        jLabel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setIconTextGap(10);
        jLabel2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Bamini", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 0));
        jLabel9.setText("khjh gpjh FU nja;tk;.......");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_batch_assign_filled_100px.png"))); // NOI18N
        jLabel3.setText("BATCH    DETAILS");
        jLabel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setIconTextGap(10);
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_training_100px.png"))); // NOI18N
        jLabel5.setText("COURSE   DETAILS");
        jLabel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel5.setIconTextGap(10);
        jLabel5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_pos_terminal_100px.png"))); // NOI18N
        jLabel6.setText("FINANCE AND ACCOUNTS");
        jLabel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel6.setIconTextGap(10);
        jLabel6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/ex.jpg"))); // NOI18N
        jLabel7.setText("class Logo");
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/ssslogo200.png"))); // NOI18N
        jLabel8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel8MouseEntered(evt);
            }
        });

        jLabel111.setFont(new java.awt.Font("Brush Script MT", 1, 60)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(255, 255, 255));
        jLabel111.setText("The Express Institute. Norwood");

        javax.swing.GroupLayout homeTitleLayout = new javax.swing.GroupLayout(homeTitle);
        homeTitle.setLayout(homeTitleLayout);
        homeTitleLayout.setHorizontalGroup(
            homeTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeTitleLayout.createSequentialGroup()
                .addGroup(homeTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(homeTitleLayout.createSequentialGroup()
                        .addGroup(homeTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(homeTitleLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homeTitleLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(50, 50, 50)
                        .addGroup(homeTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(homeTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(homeTitleLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(homeTitleLayout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(248, Short.MAX_VALUE))
        );
        homeTitleLayout.setVerticalGroup(
            homeTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeTitleLayout.createSequentialGroup()
                .addGroup(homeTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(homeTitleLayout.createSequentialGroup()
                        .addGroup(homeTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(homeTitleLayout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(jLabel7))
                            .addGroup(homeTitleLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel9)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(38, 38, 38)
                        .addGroup(homeTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homeTitleLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout TitleLayout = new javax.swing.GroupLayout(Title);
        Title.setLayout(TitleLayout);
        TitleLayout.setHorizontalGroup(
            TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(homeTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        TitleLayout.setVerticalGroup(
            TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(homeTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        dynamic.add(Title, "card5");

        Student.setBackground(new java.awt.Color(153, 255, 153));

        St_select.setBackground(new java.awt.Color(0, 153, 153));
        St_select.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btn_registration.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_registration.setText("Registration");
        btn_registration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrationActionPerformed(evt);
            }
        });

        btn_view_stu_info.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_view_stu_info.setText("View Students Info");
        btn_view_stu_info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_view_stu_infoActionPerformed(evt);
            }
        });

        jButton62.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton62.setText("Set Final Results");
        jButton62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton62ActionPerformed(evt);
            }
        });

        jButton64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_mission_of_a_company_30px.png"))); // NOI18N
        jButton64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton64ActionPerformed(evt);
            }
        });

        jButton65.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton65.setText("Exams And Marks");
        jButton65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton65ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout St_selectLayout = new javax.swing.GroupLayout(St_select);
        St_select.setLayout(St_selectLayout);
        St_selectLayout.setHorizontalGroup(
            St_selectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(St_selectLayout.createSequentialGroup()
                .addComponent(btn_registration, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btn_view_stu_info, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton62, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton65)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton64, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 838, Short.MAX_VALUE))
        );
        St_selectLayout.setVerticalGroup(
            St_selectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(St_selectLayout.createSequentialGroup()
                .addGroup(St_selectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton64, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(St_selectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btn_registration, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(St_selectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton62, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_view_stu_info, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton65, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 1, Short.MAX_VALUE))
        );

        St_base.setBackground(new java.awt.Color(0, 0, 0));
        St_base.setLayout(new java.awt.CardLayout());

        Stu_registration_form.setBackground(new java.awt.Color(0, 0, 0));

        St_reg.setBackground(new java.awt.Color(204, 204, 255));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Student Index");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Name");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("Address ");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("E-Mail");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("Telephone");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("Batch");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setText("Courses");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("Emergency Tel");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setText("Parent/Gardian");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setText("Addmission fee");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setText("Student Photo  ");

        St_index.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        St_index.setForeground(new java.awt.Color(51, 51, 255));

        St_name.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        St_name.setForeground(new java.awt.Color(51, 51, 255));

        St_address.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        St_address.setForeground(new java.awt.Color(51, 51, 255));

        St_mail.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        St_mail.setForeground(new java.awt.Color(51, 51, 255));

        St_tel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        St_tel.setForeground(new java.awt.Color(51, 51, 255));

        St_batch.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        St_batch.setForeground(new java.awt.Color(51, 51, 255));

        St_emgy_tel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        St_emgy_tel.setForeground(new java.awt.Color(51, 51, 255));

        St_parent_name.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        St_parent_name.setForeground(new java.awt.Color(51, 51, 255));

        St_pic_select.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        St_pic_select.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_google_photos_40px.png"))); // NOI18N
        St_pic_select.setText("Chooice Picture");
        St_pic_select.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        St_pic_select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                St_pic_selectActionPerformed(evt);
            }
        });

        St_course_btech.setText("B_TECH");

        St_course_stech.setText("S_TECH");

        St_course_agri.setText("AGRICULTURE");

        St_addmissionfee_chbox.setText("yes");

        lbl_image.setBackground(new java.awt.Color(0, 153, 153));
        lbl_image.setOpaque(true);

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/adduser.png"))); // NOI18N
        jButton7.setText("Add Student");
        jButton7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_cancel_40px.png"))); // NOI18N
        jButton8.setText("Cancel");
        jButton8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setIconTextGap(8);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_broom_50px.png"))); // NOI18N
        jButton9.setText("Clear");
        jButton9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 255));
        jLabel21.setText("Student Registration Form");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setText("Age");

        St_age.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        St_age.setForeground(new java.awt.Color(51, 51, 255));

        jButton6.setBackground(new java.awt.Color(51, 255, 153));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(51, 51, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_find_user_male_50px.png"))); // NOI18N
        jButton6.setText("Search");
        jButton6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setIconTextGap(3);
        jButton6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText("============================");

        jButton35.setBackground(new java.awt.Color(0, 255, 0));
        jButton35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton35.setForeground(new java.awt.Color(51, 255, 51));
        jButton35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_forward_40px.png"))); // NOI18N
        jButton35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton35.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout St_regLayout = new javax.swing.GroupLayout(St_reg);
        St_reg.setLayout(St_regLayout);
        St_regLayout.setHorizontalGroup(
            St_regLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(St_regLayout.createSequentialGroup()
                .addGroup(St_regLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(St_regLayout.createSequentialGroup()
                        .addGap(316, 316, 316)
                        .addComponent(jLabel21))
                    .addGroup(St_regLayout.createSequentialGroup()
                        .addGap(295, 295, 295)
                        .addComponent(jLabel25))
                    .addGroup(St_regLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(St_regLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel22)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGap(42, 42, 42)
                        .addGroup(St_regLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(St_index, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(St_name, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(St_age, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(St_address, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(St_mail, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(St_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(St_regLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton6)
                            .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(St_regLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel15)
                        .addGap(120, 120, 120)
                        .addComponent(St_batch, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(St_regLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel16)
                        .addGap(100, 100, 100)
                        .addComponent(St_course_stech)
                        .addGap(10, 10, 10)
                        .addComponent(St_course_btech)
                        .addGap(10, 10, 10)
                        .addComponent(St_course_agri))
                    .addGroup(St_regLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel17)
                        .addGap(39, 39, 39)
                        .addComponent(St_emgy_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(St_regLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel18)
                        .addGap(31, 31, 31)
                        .addComponent(St_parent_name, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(St_regLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(St_regLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20))
                        .addGap(34, 34, 34)
                        .addGroup(St_regLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(St_addmissionfee_chbox)
                            .addComponent(St_pic_select, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(870, Short.MAX_VALUE))
        );
        St_regLayout.setVerticalGroup(
            St_regLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(St_regLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel21)
                .addGap(6, 6, 6)
                .addComponent(jLabel25)
                .addGap(22, 22, 22)
                .addGroup(St_regLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(St_regLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel10)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel11)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel22)
                        .addGap(17, 17, 17)
                        .addComponent(jLabel12)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel13)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel14))
                    .addGroup(St_regLayout.createSequentialGroup()
                        .addComponent(St_index, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(St_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(St_age, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(St_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(St_mail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(St_tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(St_regLayout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addGap(17, 17, 17)
                        .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(St_regLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(St_regLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel15))
                    .addComponent(St_batch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(St_regLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addGroup(St_regLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(St_regLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(St_course_stech)
                            .addComponent(St_course_btech)
                            .addComponent(St_course_agri))))
                .addGap(7, 7, 7)
                .addGroup(St_regLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(St_regLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel17))
                    .addComponent(St_emgy_tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(St_regLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(St_regLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel18))
                    .addComponent(St_parent_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(St_regLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(St_regLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20))
                    .addGroup(St_regLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(St_addmissionfee_chbox)
                        .addGap(7, 7, 7)
                        .addComponent(St_pic_select, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Stu_registration_formLayout = new javax.swing.GroupLayout(Stu_registration_form);
        Stu_registration_form.setLayout(Stu_registration_formLayout);
        Stu_registration_formLayout.setHorizontalGroup(
            Stu_registration_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(St_reg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Stu_registration_formLayout.setVerticalGroup(
            Stu_registration_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(St_reg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        St_base.add(Stu_registration_form, "card2");

        Stu_view_info.setBackground(new java.awt.Color(0, 0, 0));

        St_view.setBackground(new java.awt.Color(204, 255, 204));

        jButton17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton17.setForeground(new java.awt.Color(204, 0, 153));
        jButton17.setText("View All Students");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jTableStudentView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(jTableStudentView);

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(204, 0, 153));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Cource Followers", "B-Tech", "S-Tech", "Agriculture" }));

        jButton18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton18.setForeground(new java.awt.Color(204, 0, 153));
        jButton18.setText("View");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        findBatchStu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        findBatchStu.setForeground(new java.awt.Color(204, 0, 153));

        jButton19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton19.setForeground(new java.awt.Color(204, 0, 153));
        jButton19.setText("FindBatch");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton55.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton55.setForeground(new java.awt.Color(255, 0, 0));
        jButton55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_send_to_printer_70px_1.png"))); // NOI18N
        jButton55.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton55.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton55.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton55.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton55ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout St_viewLayout = new javax.swing.GroupLayout(St_view);
        St_view.setLayout(St_viewLayout);
        St_viewLayout.setHorizontalGroup(
            St_viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(St_viewLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(St_viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, St_viewLayout.createSequentialGroup()
                        .addComponent(jButton17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(findBatchStu, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton19))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton55, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(824, Short.MAX_VALUE))
        );
        St_viewLayout.setVerticalGroup(
            St_viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(St_viewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(St_viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton55)
                    .addGroup(St_viewLayout.createSequentialGroup()
                        .addGroup(St_viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton17)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(findBatchStu, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(122, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Stu_view_infoLayout = new javax.swing.GroupLayout(Stu_view_info);
        Stu_view_info.setLayout(Stu_view_infoLayout);
        Stu_view_infoLayout.setHorizontalGroup(
            Stu_view_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1719, Short.MAX_VALUE)
            .addGroup(Stu_view_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(St_view, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Stu_view_infoLayout.setVerticalGroup(
            Stu_view_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 584, Short.MAX_VALUE)
            .addGroup(Stu_view_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(St_view, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        St_base.add(Stu_view_info, "card2");

        javax.swing.GroupLayout StudentLayout = new javax.swing.GroupLayout(Student);
        Student.setLayout(StudentLayout);
        StudentLayout.setHorizontalGroup(
            StudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(St_base, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
            .addComponent(St_select, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        StudentLayout.setVerticalGroup(
            StudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StudentLayout.createSequentialGroup()
                .addComponent(St_select, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(St_base, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        dynamic.add(Student, "card2");

        Batch.setBackground(new java.awt.Color(153, 255, 153));

        batchTitle.setBackground(new java.awt.Color(0, 102, 102));

        jButton11.setBackground(new java.awt.Color(0, 204, 204));
        jButton11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_microscope_40px.png"))); // NOI18N
        jButton11.setText("Searching Batch");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(0, 204, 204));
        jButton10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_family_40px.png"))); // NOI18N
        jButton10.setText("Add New Batch");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        batchbase.setBackground(new java.awt.Color(102, 204, 255));
        batchbase.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        batchbase.setLayout(new java.awt.CardLayout());

        addnewbatch.setBackground(new java.awt.Color(102, 204, 255));
        addnewbatch.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        addnewbatch.setMaximumSize(new java.awt.Dimension(697, 1759));

        jLabel24.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 51, 153));
        jLabel24.setText("Create A New Batch");

        jLabel26.setFont(new java.awt.Font("sansserif", 1, 30)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Year Of the Batch");

        txtbatchyear.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        txtbatchyear.setForeground(new java.awt.Color(0, 0, 255));

        btn_create_batch.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        btn_create_batch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_worker_male_50px_1.png"))); // NOI18N
        btn_create_batch.setText("Create Batch");
        btn_create_batch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_create_batchActionPerformed(evt);
            }
        });

        btn_delete_batch.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        btn_delete_batch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_minus_50px.png"))); // NOI18N
        btn_delete_batch.setText("Delete Batch");
        btn_delete_batch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_batchActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setText("======================");

        javax.swing.GroupLayout addnewbatchLayout = new javax.swing.GroupLayout(addnewbatch);
        addnewbatch.setLayout(addnewbatchLayout);
        addnewbatchLayout.setHorizontalGroup(
            addnewbatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addnewbatchLayout.createSequentialGroup()
                .addGroup(addnewbatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addnewbatchLayout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addnewbatchLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel27)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(addnewbatchLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(addnewbatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(btn_create_batch, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addnewbatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtbatchyear)
                    .addComponent(btn_delete_batch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 1086, Short.MAX_VALUE))
        );
        addnewbatchLayout.setVerticalGroup(
            addnewbatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addnewbatchLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel24)
                .addGap(1, 1, 1)
                .addComponent(jLabel27)
                .addGap(31, 31, 31)
                .addGroup(addnewbatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtbatchyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(addnewbatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_create_batch, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_delete_batch, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(317, Short.MAX_VALUE))
        );

        batchbase.add(addnewbatch, "card2");

        Searchbatch.setBackground(new java.awt.Color(102, 204, 255));
        Searchbatch.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Searchbatch.setMaximumSize(new java.awt.Dimension(1250, 1750));

        jButton12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_find_user_male_50px.png"))); // NOI18N
        jButton12.setText("Find the batch");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        batchTxtFind.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        batchTxtFind.setForeground(new java.awt.Color(255, 0, 51));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 0));
        jLabel28.setText("Year Of the Batch");

        jTableFindBatch.setBackground(new java.awt.Color(153, 153, 255));
        jTableFindBatch.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTableFindBatch.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableFindBatch);

        jButton56.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton56.setForeground(new java.awt.Color(255, 0, 0));
        jButton56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_send_to_printer_70px_1.png"))); // NOI18N
        jButton56.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton56.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton56.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton56.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton56ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SearchbatchLayout = new javax.swing.GroupLayout(Searchbatch);
        Searchbatch.setLayout(SearchbatchLayout);
        SearchbatchLayout.setHorizontalGroup(
            SearchbatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchbatchLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(SearchbatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(SearchbatchLayout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(18, 18, 18)
                        .addComponent(batchTxtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton56, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(801, Short.MAX_VALUE))
        );
        SearchbatchLayout.setVerticalGroup(
            SearchbatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchbatchLayout.createSequentialGroup()
                .addGroup(SearchbatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SearchbatchLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(SearchbatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SearchbatchLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel28))
                            .addComponent(batchTxtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(SearchbatchLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton56, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        batchbase.add(Searchbatch, "card3");

        javax.swing.GroupLayout batchTitleLayout = new javax.swing.GroupLayout(batchTitle);
        batchTitle.setLayout(batchTitleLayout);
        batchTitleLayout.setHorizontalGroup(
            batchTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(batchTitleLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(batchbase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        batchTitleLayout.setVerticalGroup(
            batchTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(batchTitleLayout.createSequentialGroup()
                .addGroup(batchTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(batchbase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout BatchLayout = new javax.swing.GroupLayout(Batch);
        Batch.setLayout(BatchLayout);
        BatchLayout.setHorizontalGroup(
            BatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(batchTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        BatchLayout.setVerticalGroup(
            BatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(batchTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        dynamic.add(Batch, "card3");

        Finance.setBackground(new java.awt.Color(153, 255, 153));

        FinanceTitle.setBackground(new java.awt.Color(255, 153, 153));

        FinanceBase.setBackground(new java.awt.Color(153, 255, 153));
        FinanceBase.setMaximumSize(new java.awt.Dimension(1079, 596));
        FinanceBase.setLayout(new java.awt.CardLayout());

        FinanceFees.setBackground(new java.awt.Color(102, 204, 255));
        FinanceFees.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTableFeesRetriver.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "B-Tech Fees", "S-tech Fees", "Agri Fees"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTableFeesRetriver);

        jButton20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton20.setForeground(new java.awt.Color(0, 0, 255));
        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_checked_radio_button_40px.png"))); // NOI18N
        jButton20.setText("Check");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 255));
        jLabel33.setText("Stu Index");

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(0, 0, 255));

        jButton21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_filter_filled_26px.png"))); // NOI18N
        jButton21.setText("Search");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        financeStuName.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        financeStuName.setForeground(new java.awt.Color(255, 0, 0));

        jRadioButton_bTech.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton_bTech.setForeground(new java.awt.Color(0, 0, 255));
        jRadioButton_bTech.setText("B-Tech");

        jRadioButton_sTech.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton_sTech.setForeground(new java.awt.Color(0, 0, 255));
        jRadioButton_sTech.setText("S-Tech");

        jRadioButton_agri.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton_agri.setForeground(new java.awt.Color(0, 0, 255));
        jRadioButton_agri.setText("Agriculture");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 0, 255));
        jLabel36.setText("Cource");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 255));
        jLabel37.setText("Amount");

        jTextFeesAmount.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel38.setText("Rs");

        jButton22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton22.setForeground(new java.awt.Color(0, 0, 255));
        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_add_40px_2.png"))); // NOI18N
        jButton22.setText("Add");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton23.setForeground(new java.awt.Color(0, 0, 255));
        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_update_left_rotation_40px.png"))); // NOI18N
        jButton23.setText("Update");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jComboMonths.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboMonths.setForeground(new java.awt.Color(0, 0, 204));
        jComboMonths.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        jComboMonths.setPreferredSize(new java.awt.Dimension(118, 30));

        jLabelBatch.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelBatch.setForeground(new java.awt.Color(255, 0, 0));

        jTextFieldBATCHinput.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextFieldBATCHinput.setForeground(new java.awt.Color(255, 0, 51));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 0, 204));
        jLabel39.setText("Batch");

        jButton24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton24.setForeground(new java.awt.Color(0, 0, 255));
        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_add_database_26px.png"))); // NOI18N
        jButton24.setText("Create Fess Table");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 0, 204));
        jLabel40.setText("Name");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 0, 204));
        jLabel41.setText("Batch");

        jComboBoxLevel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBoxLevel.setForeground(new java.awt.Color(0, 0, 255));
        jComboBoxLevel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Level", "Level1", "Level2", "Level3" }));

        dateChooserCombo1.setCalendarBackground(new java.awt.Color(0, 0, 255));
        dateChooserCombo1.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));

        jButton53.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton53.setForeground(new java.awt.Color(255, 0, 0));
        jButton53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_send_to_printer_70px_1.png"))); // NOI18N
        jButton53.setText("PRINT");
        jButton53.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton53.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton53.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton53.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButton54.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton54.setForeground(new java.awt.Color(255, 0, 0));
        jButton54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_send_to_printer_70px_1.png"))); // NOI18N
        jButton54.setText("PRINT");
        jButton54.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton54.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton54.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton54.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton54ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FinanceFeesLayout = new javax.swing.GroupLayout(FinanceFees);
        FinanceFees.setLayout(FinanceFeesLayout);
        FinanceFeesLayout.setHorizontalGroup(
            FinanceFeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FinanceFeesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FinanceFeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FinanceFeesLayout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addGap(39, 39, 39)
                        .addComponent(jLabelBatch, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(FinanceFeesLayout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton_bTech)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton_sTech)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton_agri))
                    .addGroup(FinanceFeesLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton54, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(FinanceFeesLayout.createSequentialGroup()
                        .addGroup(FinanceFeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel40))
                        .addGap(10, 10, 10)
                        .addGroup(FinanceFeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FinanceFeesLayout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jButton21))
                            .addComponent(financeStuName, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(FinanceFeesLayout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addGap(40, 40, 40)
                        .addComponent(jTextFieldBATCHinput, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboMonths, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jComboBoxLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton24)
                        .addGap(31, 31, 31)
                        .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(FinanceFeesLayout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addGap(10, 10, 10)
                        .addComponent(jTextFeesAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel38))
                    .addGroup(FinanceFeesLayout.createSequentialGroup()
                        .addComponent(jButton22)
                        .addGap(18, 18, 18)
                        .addComponent(jButton23)
                        .addGap(10, 10, 10)
                        .addComponent(jButton20)))
                .addContainerGap(245, Short.MAX_VALUE))
            .addGroup(FinanceFeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(FinanceFeesLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButton53)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        FinanceFeesLayout.setVerticalGroup(
            FinanceFeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FinanceFeesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FinanceFeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(FinanceFeesLayout.createSequentialGroup()
                        .addGroup(FinanceFeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FinanceFeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(FinanceFeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldBATCHinput)
                                    .addComponent(jLabel39))
                                .addComponent(jComboBoxLevel)
                                .addGroup(FinanceFeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboMonths, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton24)))
                            .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(FinanceFeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FinanceFeesLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel33))
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(financeStuName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel40))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(FinanceFeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(FinanceFeesLayout.createSequentialGroup()
                        .addGroup(FinanceFeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FinanceFeesLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel41))
                            .addComponent(jLabelBatch, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(FinanceFeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FinanceFeesLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel36))
                            .addComponent(jRadioButton_bTech)
                            .addComponent(jRadioButton_sTech)
                            .addComponent(jRadioButton_agri))
                        .addGap(18, 18, 18)
                        .addGroup(FinanceFeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FinanceFeesLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel37))
                            .addComponent(jTextFeesAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(FinanceFeesLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel38)))
                        .addGap(18, 18, 18)
                        .addGroup(FinanceFeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton22)
                            .addComponent(jButton23)
                            .addComponent(jButton20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton54))
                .addContainerGap(334, Short.MAX_VALUE))
            .addGroup(FinanceFeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(FinanceFeesLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButton53)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        FinanceBase.add(FinanceFees, "card3");

        FinanceStatements.setBackground(new java.awt.Color(204, 255, 255));
        FinanceStatements.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        FinanceStatements.setMaximumSize(new java.awt.Dimension(955, 1198));

        jTableFeesRetriver1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "B-Tech Fees", "S-tech Fees", "Agri Fees"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTableFeesRetriver1);

        jButton27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton27.setForeground(new java.awt.Color(0, 0, 255));
        jButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_checked_radio_button_40px.png"))); // NOI18N
        jButton27.setText("MONTHLY");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 255));
        jLabel34.setText("Description");

        DescripsionIn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        DescripsionIn.setForeground(new java.awt.Color(0, 0, 255));

        jButton28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton28.setForeground(new java.awt.Color(0, 0, 255));
        jButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_task_completed_26px.png"))); // NOI18N
        jButton28.setText("Finish Monthly transactions");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 0, 255));
        jLabel43.setText("Amount");

        jTextFeesAmount1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jTextFeesAmount1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFeesAmount1ActionPerformed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel44.setText("Rs");

        jButton29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton29.setForeground(new java.awt.Color(0, 0, 255));
        jButton29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_add_40px_2.png"))); // NOI18N
        jButton29.setText("INCOME");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jComboMonths1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboMonths1.setForeground(new java.awt.Color(0, 0, 204));
        jComboMonths1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        jComboMonths1.setPreferredSize(new java.awt.Dimension(118, 30));

        jTextFieldBATCHinput1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextFieldBATCHinput1.setForeground(new java.awt.Color(255, 0, 51));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 0, 204));
        jLabel45.setText("Batch");

        jButton31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton31.setForeground(new java.awt.Color(0, 0, 255));
        jButton31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_add_database_26px.png"))); // NOI18N
        jButton31.setText("Create A/C Table");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jComboBoxLevel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBoxLevel1.setForeground(new java.awt.Color(0, 0, 255));
        jComboBoxLevel1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Level", "Level1", "Level2", "Level3" }));

        jLabel35.setText("Finance Statements");

        dateChooserCombo2.setCalendarBackground(new java.awt.Color(255, 102, 255));
        dateChooserCombo2.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(0, 0, 204));
        jLabel48.setText("Year");

        YearIn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        YearIn.setForeground(new java.awt.Color(255, 0, 51));

        jButton32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton32.setForeground(new java.awt.Color(0, 0, 255));
        jButton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_tollbooth_filled_26px.png"))); // NOI18N
        jButton32.setText("Add Current Month Fees");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jButton33.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton33.setForeground(new java.awt.Color(0, 0, 255));
        jButton33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_add_40px_2.png"))); // NOI18N
        jButton33.setText("EXPENSE");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 0, 255));
        jLabel42.setText("Total Expense");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 0, 255));
        jLabel46.setText("Total Income ");

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 0, 0));
        jLabel47.setText("Total Balance");

        TotalExpensetxt.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        TotalExpensetxt.setForeground(new java.awt.Color(0, 0, 255));

        TotalIncometxt.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        TotalIncometxt.setForeground(new java.awt.Color(0, 255, 51));

        Totalbalancetxt.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        Totalbalancetxt.setForeground(new java.awt.Color(255, 0, 0));

        jButton34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton34.setForeground(new java.awt.Color(51, 51, 255));
        jButton34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_full_version_26px.png"))); // NOI18N
        jButton34.setText("Add Addmision Fees");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        jButton36.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton36.setForeground(new java.awt.Color(0, 0, 255));
        jButton36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_checked_radio_button_40px.png"))); // NOI18N
        jButton36.setText("YEARLY");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        jButton45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton45.setForeground(new java.awt.Color(255, 0, 0));
        jButton45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_send_to_printer_70px_1.png"))); // NOI18N
        jButton45.setText("PRINT");
        jButton45.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton45.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton45.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton45.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FinanceStatementsLayout = new javax.swing.GroupLayout(FinanceStatements);
        FinanceStatements.setLayout(FinanceStatementsLayout);
        FinanceStatementsLayout.setHorizontalGroup(
            FinanceStatementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FinanceStatementsLayout.createSequentialGroup()
                .addGroup(FinanceStatementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FinanceStatementsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(FinanceStatementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FinanceStatementsLayout.createSequentialGroup()
                                .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel35))
                            .addGroup(FinanceStatementsLayout.createSequentialGroup()
                                .addGroup(FinanceStatementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34)
                                    .addComponent(jLabel43))
                                .addGap(18, 18, 18)
                                .addGroup(FinanceStatementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(FinanceStatementsLayout.createSequentialGroup()
                                        .addComponent(jTextFeesAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel44))
                                    .addComponent(DescripsionIn, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(FinanceStatementsLayout.createSequentialGroup()
                        .addGroup(FinanceStatementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FinanceStatementsLayout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(jButton33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton29)
                                .addGap(36, 36, 36)
                                .addComponent(jButton27)
                                .addGap(18, 18, 18)
                                .addComponent(jButton36))
                            .addGroup(FinanceStatementsLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(FinanceStatementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(FinanceStatementsLayout.createSequentialGroup()
                                        .addGroup(FinanceStatementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel42)
                                            .addComponent(jLabel47))
                                        .addGap(24, 24, 24)
                                        .addGroup(FinanceStatementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(TotalExpensetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Totalbalancetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(FinanceStatementsLayout.createSequentialGroup()
                                        .addComponent(jLabel46)
                                        .addGap(25, 25, 25)
                                        .addComponent(TotalIncometxt, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 103, Short.MAX_VALUE)))
                .addGap(84, 84, 84))
            .addGroup(FinanceStatementsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldBATCHinput1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxLevel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jComboMonths1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel48)
                .addGap(18, 18, 18)
                .addComponent(YearIn, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton31)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        FinanceStatementsLayout.setVerticalGroup(
            FinanceStatementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FinanceStatementsLayout.createSequentialGroup()
                .addGroup(FinanceStatementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FinanceStatementsLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel35))
                    .addGroup(FinanceStatementsLayout.createSequentialGroup()
                        .addGroup(FinanceStatementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(YearIn)
                            .addComponent(jButton31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboMonths1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(jTextFieldBATCHinput1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45)
                            .addComponent(jComboBoxLevel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(FinanceStatementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateChooserCombo2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(FinanceStatementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 11, 11)))
                .addGroup(FinanceStatementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DescripsionIn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addGroup(FinanceStatementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FinanceStatementsLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel44)
                        .addGap(18, 18, 18))
                    .addGroup(FinanceStatementsLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(FinanceStatementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFeesAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43))
                        .addGap(22, 22, 22)))
                .addGroup(FinanceStatementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton33)
                    .addComponent(jButton29)
                    .addComponent(jButton27)
                    .addComponent(jButton36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(FinanceStatementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(FinanceStatementsLayout.createSequentialGroup()
                        .addGroup(FinanceStatementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TotalIncometxt, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46))
                        .addGap(18, 18, 18)
                        .addGroup(FinanceStatementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TotalExpensetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42))
                        .addGap(22, 22, 22)
                        .addGroup(FinanceStatementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(Totalbalancetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        FinanceBase.add(FinanceStatements, "card3");

        jButton25.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton25.setForeground(new java.awt.Color(0, 0, 255));
        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_add_40px_2.png"))); // NOI18N
        jButton25.setText("Add Class Fees");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton26.setForeground(new java.awt.Color(0, 0, 255));
        jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_visa_40px.png"))); // NOI18N
        jButton26.setText("Finance Statements");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FinanceTitleLayout = new javax.swing.GroupLayout(FinanceTitle);
        FinanceTitle.setLayout(FinanceTitleLayout);
        FinanceTitleLayout.setHorizontalGroup(
            FinanceTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FinanceTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton26)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(FinanceTitleLayout.createSequentialGroup()
                .addComponent(FinanceBase, javax.swing.GroupLayout.PREFERRED_SIZE, 1077, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        FinanceTitleLayout.setVerticalGroup(
            FinanceTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FinanceTitleLayout.createSequentialGroup()
                .addGroup(FinanceTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton25)
                    .addComponent(jButton26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FinanceBase, javax.swing.GroupLayout.PREFERRED_SIZE, 893, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout FinanceLayout = new javax.swing.GroupLayout(Finance);
        Finance.setLayout(FinanceLayout);
        FinanceLayout.setHorizontalGroup(
            FinanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FinanceTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        FinanceLayout.setVerticalGroup(
            FinanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FinanceLayout.createSequentialGroup()
                .addComponent(FinanceTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        dynamic.add(Finance, "card4");

        Course.setBackground(new java.awt.Color(153, 255, 153));

        Course_title.setBackground(new java.awt.Color(0, 153, 153));

        jButton15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 0, 102));
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_visible_40px.png"))); // NOI18N
        jButton15.setText("View Cources");
        jButton15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 0, 102));
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_insert_button_40px.png"))); // NOI18N
        jButton14.setText("Inserting Cources");
        jButton14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        Course_base.setBackground(new java.awt.Color(153, 255, 153));
        Course_base.setLayout(new java.awt.CardLayout());

        add_course.setBackground(new java.awt.Color(153, 255, 153));

        course_form.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 51, 255));
        jLabel30.setText("Session_id");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 51, 255));
        jLabel31.setText("Session_Name");

        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(0, 51, 255));
        jLabel85.setText("Theory_Hours");

        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(0, 51, 255));
        jLabel86.setText("Practical_Hours");

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(0, 51, 255));
        jLabel87.setText("Resources");

        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(0, 51, 255));
        jLabel88.setText("Remarks");

        jTextC_Session_Id.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextC_Session_Id.setForeground(new java.awt.Color(0, 0, 255));
        jTextC_Session_Id.setCaretColor(new java.awt.Color(204, 0, 204));
        jTextC_Session_Id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextC_Session_IdActionPerformed(evt);
            }
        });

        jTextC_Session_name.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextC_Session_name.setForeground(new java.awt.Color(0, 0, 255));
        jTextC_Session_name.setCaretColor(new java.awt.Color(204, 0, 204));

        jTextC_theory_hourse.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextC_theory_hourse.setForeground(new java.awt.Color(0, 0, 255));
        jTextC_theory_hourse.setCaretColor(new java.awt.Color(204, 0, 204));

        jTextC_Practical_hours.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextC_Practical_hours.setForeground(new java.awt.Color(0, 0, 255));
        jTextC_Practical_hours.setCaretColor(new java.awt.Color(204, 0, 204));

        jTextC_remarks.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextC_remarks.setForeground(new java.awt.Color(0, 0, 255));
        jTextC_remarks.setCaretColor(new java.awt.Color(204, 0, 204));
        jTextC_remarks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextC_remarksActionPerformed(evt);
            }
        });

        jButtonChooseResourse.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonChooseResourse.setForeground(new java.awt.Color(0, 0, 255));
        jButtonChooseResourse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/Resourses_upload.png"))); // NOI18N
        jButtonChooseResourse.setText("Chooice");
        jButtonChooseResourse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonChooseResourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChooseResourseActionPerformed(evt);
            }
        });

        jButtonAddCourse.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonAddCourse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_add_40px_2.png"))); // NOI18N
        jButtonAddCourse.setText("Add");
        jButtonAddCourse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAddCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddCourseActionPerformed(evt);
            }
        });

        jButtonUpdateCourse.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonUpdateCourse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_update_left_rotation_40px.png"))); // NOI18N
        jButtonUpdateCourse.setText("Update");
        jButtonUpdateCourse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonUpdateCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateCourseActionPerformed(evt);
            }
        });

        jButtonDeleteCourse.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonDeleteCourse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_delete_sign_40px.png"))); // NOI18N
        jButtonDeleteCourse.setText("Delete");
        jButtonDeleteCourse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonDeleteCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteCourseActionPerformed(evt);
            }
        });

        jComboBoxchCourse.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jComboBoxchCourse.setForeground(new java.awt.Color(0, 51, 255));
        jComboBoxchCourse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Course Code", "B_Tech", "S_Tech", "Agriculture" }));
        jComboBoxchCourse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(0, 51, 255));
        jLabel89.setText("Course");

        jButton13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_broom_40px.png"))); // NOI18N
        jButton13.setText("Clear");
        jButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 51, 204));
        jLabel32.setText("COURSE RESOURCE FORM");

        javax.swing.GroupLayout course_formLayout = new javax.swing.GroupLayout(course_form);
        course_form.setLayout(course_formLayout);
        course_formLayout.setHorizontalGroup(
            course_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(course_formLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(501, 501, 501))
            .addGroup(course_formLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(course_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(course_formLayout.createSequentialGroup()
                        .addComponent(jLabel86)
                        .addGap(18, 18, 18)
                        .addComponent(jTextC_Practical_hours, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel88)
                    .addGroup(course_formLayout.createSequentialGroup()
                        .addComponent(jButtonAddCourse)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonUpdateCourse)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDeleteCourse)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton13))
                    .addGroup(course_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(course_formLayout.createSequentialGroup()
                            .addComponent(jLabel31)
                            .addGap(30, 30, 30)
                            .addComponent(jTextC_Session_name))
                        .addGroup(course_formLayout.createSequentialGroup()
                            .addComponent(jLabel30)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextC_Session_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(course_formLayout.createSequentialGroup()
                            .addGroup(course_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel85)
                                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(34, 34, 34)
                            .addGroup(course_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBoxchCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextC_theory_hourse, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(course_formLayout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(jLabel32))
                    .addGroup(course_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, course_formLayout.createSequentialGroup()
                            .addComponent(jLabel87)
                            .addGap(70, 70, 70)
                            .addComponent(jButtonChooseResourse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, course_formLayout.createSequentialGroup()
                            .addGap(164, 164, 164)
                            .addComponent(jTextC_remarks, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(787, Short.MAX_VALUE))
        );
        course_formLayout.setVerticalGroup(
            course_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(course_formLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel32)
                .addGap(18, 18, 18)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(course_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxchCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel89))
                .addGap(28, 28, 28)
                .addGroup(course_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextC_Session_name, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(29, 29, 29)
                .addGroup(course_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextC_Session_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addGap(27, 27, 27)
                .addGroup(course_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextC_theory_hourse, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel85))
                .addGap(28, 28, 28)
                .addGroup(course_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextC_Practical_hours, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel86))
                .addGap(26, 26, 26)
                .addGroup(course_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel88)
                    .addComponent(jTextC_remarks, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(course_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonChooseResourse, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel87))
                .addGap(33, 33, 33)
                .addGroup(course_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddCourse)
                    .addComponent(jButtonUpdateCourse)
                    .addComponent(jButtonDeleteCourse)
                    .addComponent(jButton13))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout add_courseLayout = new javax.swing.GroupLayout(add_course);
        add_course.setLayout(add_courseLayout);
        add_courseLayout.setHorizontalGroup(
            add_courseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1392, Short.MAX_VALUE)
            .addGroup(add_courseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(course_form, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        add_courseLayout.setVerticalGroup(
            add_courseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 586, Short.MAX_VALUE)
            .addGroup(add_courseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(course_form, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Course_base.add(add_course, "card4");

        Course_view.setBackground(new java.awt.Color(153, 255, 153));

        Course_view1.setBackground(new java.awt.Color(153, 255, 153));

        jCombocourse.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jCombocourse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select cource", "B-Tech", "S-Tech", "Agriculture" }));
        jCombocourse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton16.setBackground(new java.awt.Color(51, 153, 255));
        jButton16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_visible_40px.png"))); // NOI18N
        jButton16.setText("View Session");
        jButton16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jTablecourceView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTablecourceView.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTablecourceView.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jTablecourceViewMouseDragged(evt);
            }
        });
        jTablecourceView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablecourceViewMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTablecourceViewMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTablecourceViewMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTablecourceView);

        courceRePath.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        courceRePath.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout Course_view1Layout = new javax.swing.GroupLayout(Course_view1);
        Course_view1.setLayout(Course_view1Layout);
        Course_view1Layout.setHorizontalGroup(
            Course_view1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Course_view1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(Course_view1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(courceRePath, javax.swing.GroupLayout.PREFERRED_SIZE, 871, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Course_view1Layout.createSequentialGroup()
                        .addComponent(jCombocourse, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(491, Short.MAX_VALUE))
        );
        Course_view1Layout.setVerticalGroup(
            Course_view1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Course_view1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Course_view1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCombocourse)
                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(courceRePath, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );

        javax.swing.GroupLayout Course_viewLayout = new javax.swing.GroupLayout(Course_view);
        Course_view.setLayout(Course_viewLayout);
        Course_viewLayout.setHorizontalGroup(
            Course_viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1392, Short.MAX_VALUE)
            .addGroup(Course_viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Course_viewLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Course_view1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        Course_viewLayout.setVerticalGroup(
            Course_viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 586, Short.MAX_VALUE)
            .addGroup(Course_viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Course_viewLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Course_view1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        Course_base.add(Course_view, "card2");

        javax.swing.GroupLayout Course_titleLayout = new javax.swing.GroupLayout(Course_title);
        Course_title.setLayout(Course_titleLayout);
        Course_titleLayout.setHorizontalGroup(
            Course_titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Course_titleLayout.createSequentialGroup()
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton15)
                .addContainerGap(914, Short.MAX_VALUE))
            .addGroup(Course_titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Course_base, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Course_titleLayout.setVerticalGroup(
            Course_titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Course_titleLayout.createSequentialGroup()
                .addGroup(Course_titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton15)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(556, 556, 556))
            .addGroup(Course_titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Course_titleLayout.createSequentialGroup()
                    .addGap(52, 52, 52)
                    .addComponent(Course_base, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout CourseLayout = new javax.swing.GroupLayout(Course);
        Course.setLayout(CourseLayout);
        CourseLayout.setHorizontalGroup(
            CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Course_title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        CourseLayout.setVerticalGroup(
            CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CourseLayout.createSequentialGroup()
                .addComponent(Course_title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        dynamic.add(Course, "card5");

        settings.setBackground(new java.awt.Color(255, 204, 153));

        jButton38.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton38.setForeground(new java.awt.Color(255, 51, 51));
        jButton38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_unlock_26px.png"))); // NOI18N
        jButton38.setText("Change Password");
        jButton38.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        jButton39.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton39.setForeground(new java.awt.Color(255, 51, 51));
        jButton39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_registered_trademark_26px.png"))); // NOI18N
        jButton39.setText("Register Teacher");
        jButton39.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        jButton40.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton40.setForeground(new java.awt.Color(255, 51, 51));
        jButton40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_binoculars_26px.png"))); // NOI18N
        jButton40.setText("View Teachers Details");
        jButton40.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        SettingBase.setBackground(new java.awt.Color(255, 153, 153));
        SettingBase.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        SettingBase.setForeground(new java.awt.Color(255, 102, 255));
        SettingBase.setLayout(new java.awt.CardLayout());

        jTeachersView.setBackground(new java.awt.Color(102, 255, 0));
        jTeachersView.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTeachersView.setForeground(new java.awt.Color(255, 102, 255));

        jLabel99.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(0, 0, 255));
        jLabel99.setText("TEACHERS DETAILS");

        jTableTeacherView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableTeacherView.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTableTeacherView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTeacherViewMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTableTeacherView);

        jLabel100.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(0, 0, 255));

        jButton46.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton46.setForeground(new java.awt.Color(0, 0, 255));
        jButton46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_visible_40px.png"))); // NOI18N
        jButton46.setText("TEACHERS LIST");
        jButton46.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });

        TECH_IMG_LBL1.setBackground(new java.awt.Color(51, 255, 153));
        TECH_IMG_LBL1.setForeground(new java.awt.Color(51, 255, 204));
        TECH_IMG_LBL1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        TECH_IMG_LBL1.setOpaque(true);

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setText("ADDRESS");

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setText("NAME");

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setText("NIC NUMBER");

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setText("DATE OF BIRTH");

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("SUBJECT");

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 255, 255));
        jLabel64.setText("EMAIL");

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 255, 255));
        jLabel65.setText("TELEPHONE");

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setText("QUALIFICATION");

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setText("DATE OF JOINED");

        jLabelTname.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelTname.setForeground(new java.awt.Color(0, 0, 255));
        jLabelTname.setText("|");

        jLabelTnic.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelTnic.setForeground(new java.awt.Color(0, 0, 255));
        jLabelTnic.setText("|");

        jLabeldob.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabeldob.setForeground(new java.awt.Color(0, 0, 255));
        jLabeldob.setText("|");

        jLabelTsubject.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelTsubject.setForeground(new java.awt.Color(0, 0, 255));
        jLabelTsubject.setText("|");

        jLabelTemail.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelTemail.setForeground(new java.awt.Color(0, 0, 255));
        jLabelTemail.setText("|");

        jLabelTtel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelTtel.setForeground(new java.awt.Color(0, 0, 255));
        jLabelTtel.setText("|");

        jLabelTqualific.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelTqualific.setForeground(new java.awt.Color(0, 0, 255));
        jLabelTqualific.setText("|");

        jLabelTdoJoin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelTdoJoin.setForeground(new java.awt.Color(0, 0, 255));
        jLabelTdoJoin.setText("|");

        jLabelTaddress.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelTaddress.setForeground(new java.awt.Color(0, 0, 255));
        jLabelTaddress.setText("|");

        javax.swing.GroupLayout jTeachersViewLayout = new javax.swing.GroupLayout(jTeachersView);
        jTeachersView.setLayout(jTeachersViewLayout);
        jTeachersViewLayout.setHorizontalGroup(
            jTeachersViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTeachersViewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jTeachersViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jTeachersViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel99))
                .addGroup(jTeachersViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jTeachersViewLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jTeachersViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel67)
                            .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jTeachersViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTtel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jTeachersViewLayout.createSequentialGroup()
                                .addGroup(jTeachersViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabelTname, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                                    .addComponent(jLabelTsubject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelTemail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabeldob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelTnic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabelTqualific, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTdoJoin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTaddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTeachersViewLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 245, Short.MAX_VALUE)
                        .addGroup(jTeachersViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTeachersViewLayout.createSequentialGroup()
                                .addComponent(jLabel100)
                                .addGap(265, 265, 265))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTeachersViewLayout.createSequentialGroup()
                                .addComponent(TECH_IMG_LBL1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(296, 296, 296))))))
        );
        jTeachersViewLayout.setVerticalGroup(
            jTeachersViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTeachersViewLayout.createSequentialGroup()
                .addGap(39, 314, Short.MAX_VALUE)
                .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTqualific, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTdoJoin)
                .addGap(181, 181, 181))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTeachersViewLayout.createSequentialGroup()
                .addGap(0, 18, Short.MAX_VALUE)
                .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton46)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(163, 163, 163))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTeachersViewLayout.createSequentialGroup()
                .addComponent(TECH_IMG_LBL1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jTeachersViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelTname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60))
                .addGap(3, 3, 3)
                .addGroup(jTeachersViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(jLabelTnic, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jTeachersViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(jLabeldob))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jTeachersViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(jLabelTsubject, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jTeachersViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(jLabelTemail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jTeachersViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(jLabelTtel))
                .addGap(18, 18, 18)
                .addComponent(jLabel66)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel67)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jTeachersViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(jLabelTaddress, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(139, 139, 139))
        );

        SettingBase.add(jTeachersView, "card3");

        jPanelRegisterTeacher.setBackground(new java.awt.Color(255, 102, 255));
        jPanelRegisterTeacher.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelRegisterTeacher.setForeground(new java.awt.Color(255, 102, 255));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("TEACHERS REGISTRATION FORM");

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("NAME");

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("NIC NUMBER");

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("DATE OF BIRTH");

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("SUBJECT");

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("EMAIL");

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("TELEPHONE");

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("QUALIFICATION");

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("DATE OF JOINED");

        TREG_name.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TREG_name.setForeground(new java.awt.Color(0, 0, 255));

        TREG_nic.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TREG_nic.setForeground(new java.awt.Color(0, 0, 255));

        TREG_dob.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TREG_dob.setForeground(new java.awt.Color(0, 0, 255));

        TREG_subject.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TREG_subject.setForeground(new java.awt.Color(0, 0, 255));

        TREG_email.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TREG_email.setForeground(new java.awt.Color(0, 0, 255));

        TREG_tel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TREG_tel.setForeground(new java.awt.Color(0, 0, 255));

        TREG_qualific.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TREG_qualific.setForeground(new java.awt.Color(0, 0, 255));

        TREG_joindate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TREG_joindate.setForeground(new java.awt.Color(0, 0, 255));

        TECH_IMG_LBL.setBackground(new java.awt.Color(51, 255, 153));
        TECH_IMG_LBL.setForeground(new java.awt.Color(51, 255, 204));
        TECH_IMG_LBL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        TECH_IMG_LBL.setOpaque(true);

        jButton41.setBackground(new java.awt.Color(255, 51, 51));
        jButton41.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_google_photos_40px.png"))); // NOI18N
        jButton41.setText("CHOOICE IMAGE");
        jButton41.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });

        jButton42.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/adduser.png"))); // NOI18N
        jButton42.setText("ADD INFO");
        jButton42.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });

        jButton43.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_update_left_rotation_40px.png"))); // NOI18N
        jButton43.setText("UPDATE");
        jButton43.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });

        jButton44.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_broom_40px.png"))); // NOI18N
        jButton44.setText("CLEAR");
        jButton44.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("ADDRESS");

        TREG_address.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TREG_address.setForeground(new java.awt.Color(0, 0, 255));

        javax.swing.GroupLayout jPanelRegisterTeacherLayout = new javax.swing.GroupLayout(jPanelRegisterTeacher);
        jPanelRegisterTeacher.setLayout(jPanelRegisterTeacherLayout);
        jPanelRegisterTeacherLayout.setHorizontalGroup(
            jPanelRegisterTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRegisterTeacherLayout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addComponent(jLabel49)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanelRegisterTeacherLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanelRegisterTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanelRegisterTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRegisterTeacherLayout.createSequentialGroup()
                        .addComponent(jButton42)
                        .addGap(18, 18, 18)
                        .addComponent(jButton43)
                        .addGap(18, 18, 18)
                        .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelRegisterTeacherLayout.createSequentialGroup()
                        .addGroup(jPanelRegisterTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TREG_email, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TREG_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TREG_name, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TREG_dob, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TREG_subject, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TREG_nic, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TREG_qualific, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TREG_joindate, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelRegisterTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TECH_IMG_LBL, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(TREG_address, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(266, Short.MAX_VALUE))
        );
        jPanelRegisterTeacherLayout.setVerticalGroup(
            jPanelRegisterTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRegisterTeacherLayout.createSequentialGroup()
                .addComponent(jLabel49)
                .addGap(27, 27, 27)
                .addGroup(jPanelRegisterTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRegisterTeacherLayout.createSequentialGroup()
                        .addGroup(jPanelRegisterTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TREG_name, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelRegisterTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51)
                            .addComponent(TREG_nic, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelRegisterTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel52)
                            .addComponent(TREG_dob, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelRegisterTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53)
                            .addComponent(TREG_subject, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelRegisterTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54)
                            .addComponent(TREG_email, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelRegisterTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel55)
                            .addComponent(TREG_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelRegisterTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel56)
                            .addComponent(TREG_qualific, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelRegisterTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel57)
                            .addComponent(TREG_joindate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelRegisterTeacherLayout.createSequentialGroup()
                        .addComponent(TECH_IMG_LBL, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton41)))
                .addGap(8, 8, 8)
                .addGroup(jPanelRegisterTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(TREG_address, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanelRegisterTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton44)
                    .addComponent(jButton43)
                    .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(93, Short.MAX_VALUE))
        );

        SettingBase.add(jPanelRegisterTeacher, "card2");

        jPanelChangePwd.setBackground(new java.awt.Color(255, 204, 0));
        jPanelChangePwd.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelChangePwd.setForeground(new java.awt.Color(255, 102, 255));

        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(255, 255, 255));
        jLabel90.setText("User Name");

        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(255, 255, 255));
        jLabel91.setText("Old Password");

        jLabel92.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(255, 255, 255));
        jLabel92.setText("New Password");

        jLabel93.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(255, 255, 255));
        jLabel93.setText("Conform New Password");

        jButtonSavePwd.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonSavePwd.setForeground(new java.awt.Color(0, 0, 255));
        jButtonSavePwd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_save_48px_1.png"))); // NOI18N
        jButtonSavePwd.setText("Save Password");
        jButtonSavePwd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonSavePwd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSavePwdActionPerformed(evt);
            }
        });

        jButtoncancelpwd.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtoncancelpwd.setForeground(new java.awt.Color(0, 0, 255));
        jButtoncancelpwd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_cancel_40px.png"))); // NOI18N
        jButtoncancelpwd.setText("Cancel");
        jButtoncancelpwd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtoncancelpwd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtoncancelpwdActionPerformed(evt);
            }
        });

        pwdUserId.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        pwdUserId.setForeground(new java.awt.Color(51, 51, 255));
        pwdUserId.setEnabled(false);

        pwdOld.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pwdOld.setForeground(new java.awt.Color(0, 0, 255));
        pwdOld.setEnabled(false);

        pwdNew.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pwdNew.setForeground(new java.awt.Color(0, 0, 255));

        pwdConfirm.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pwdConfirm.setForeground(new java.awt.Color(0, 0, 255));
        pwdConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pwdConfirmActionPerformed(evt);
            }
        });

        jLabel94.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(0, 0, 255));
        jLabel94.setText("You can Change Your Password here....");

        javax.swing.GroupLayout jPanelChangePwdLayout = new javax.swing.GroupLayout(jPanelChangePwd);
        jPanelChangePwd.setLayout(jPanelChangePwdLayout);
        jPanelChangePwdLayout.setHorizontalGroup(
            jPanelChangePwdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelChangePwdLayout.createSequentialGroup()
                .addGroup(jPanelChangePwdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelChangePwdLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanelChangePwdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelChangePwdLayout.createSequentialGroup()
                                .addComponent(jButtonSavePwd)
                                .addGap(31, 31, 31)
                                .addComponent(jButtoncancelpwd))
                            .addGroup(jPanelChangePwdLayout.createSequentialGroup()
                                .addGroup(jPanelChangePwdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelChangePwdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel93, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(jPanelChangePwdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel90, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel91, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(28, 28, 28)
                                .addGroup(jPanelChangePwdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pwdOld, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pwdUserId, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pwdNew, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pwdConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanelChangePwdLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel94)))
                .addGap(204, 204, 204))
        );
        jPanelChangePwdLayout.setVerticalGroup(
            jPanelChangePwdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelChangePwdLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanelChangePwdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel90)
                    .addComponent(pwdUserId, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanelChangePwdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel91)
                    .addComponent(pwdOld, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanelChangePwdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pwdNew, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel92))
                .addGap(26, 26, 26)
                .addGroup(jPanelChangePwdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel93)
                    .addComponent(pwdConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanelChangePwdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSavePwd)
                    .addComponent(jButtoncancelpwd, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(255, Short.MAX_VALUE))
        );

        SettingBase.add(jPanelChangePwd, "card4");

        javax.swing.GroupLayout settingsLayout = new javax.swing.GroupLayout(settings);
        settings.setLayout(settingsLayout);
        settingsLayout.setHorizontalGroup(
            settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton38)
                .addGap(27, 27, 27)
                .addComponent(jButton39)
                .addGap(18, 18, 18)
                .addComponent(jButton40)
                .addContainerGap(865, Short.MAX_VALUE))
            .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(settingsLayout.createSequentialGroup()
                    .addComponent(SettingBase, javax.swing.GroupLayout.PREFERRED_SIZE, 1012, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 609, Short.MAX_VALUE)))
        );
        settingsLayout.setVerticalGroup(
            settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsLayout.createSequentialGroup()
                .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton38, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addComponent(jButton39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(618, Short.MAX_VALUE))
            .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(settingsLayout.createSequentialGroup()
                    .addGap(46, 46, 46)
                    .addComponent(SettingBase, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        dynamic.add(settings, "card7");

        Developer.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(51, 0, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/developersjn.png"))); // NOI18N
        jLabel68.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel68MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel68)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel68)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel69.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel69.setText("Sujan Murugesh");

        jLabel72.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel72.setText("Department of Information and communication technology,");

        jLabel73.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel73.setText("Faculty of Technology,");

        jLabel74.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel74.setText("University of Ruhuna ,SriLanka.");

        jPanel5.setBackground(new java.awt.Color(51, 0, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel112.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/developersjn.png"))); // NOI18N
        jLabel112.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel112MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel112)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel112)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(51, 0, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel115.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/developersjn.png"))); // NOI18N
        jLabel115.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel115MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel115)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel115)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel116.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel116.setText("Hameesha Rantharu");

        jLabel117.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel117.setText(" Ravindhu Lakmal");

        javax.swing.GroupLayout DeveloperLayout = new javax.swing.GroupLayout(Developer);
        Developer.setLayout(DeveloperLayout);
        DeveloperLayout.setHorizontalGroup(
            DeveloperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DeveloperLayout.createSequentialGroup()
                .addGroup(DeveloperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DeveloperLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(DeveloperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel72)
                            .addComponent(jLabel73)
                            .addComponent(jLabel74)))
                    .addGroup(DeveloperLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(DeveloperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(DeveloperLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel69)))
                        .addGap(69, 69, 69)
                        .addGroup(DeveloperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel116))
                        .addGap(59, 59, 59)
                        .addGroup(DeveloperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel117)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(763, Short.MAX_VALUE))
        );
        DeveloperLayout.setVerticalGroup(
            DeveloperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DeveloperLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DeveloperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DeveloperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel69)
                    .addGroup(DeveloperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel116)
                        .addComponent(jLabel117)))
                .addGap(103, 103, 103)
                .addComponent(jLabel72)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel73)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel74)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        dynamic.add(Developer, "card2");

        Attendance.setBackground(new java.awt.Color(51, 255, 102));

        dateChooserCombo3.setWeekStyle(datechooser.view.WeekDaysStyle.SHORT);

        jTableStAttendance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "INDEX", "NAME", "PRESENSE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableStAttendance.setColumnSelectionAllowed(true);
        jTableStAttendance.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane7.setViewportView(jTableStAttendance);
        jTableStAttendance.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jButton47.setBackground(new java.awt.Color(255, 255, 255));
        jButton47.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton47.setForeground(new java.awt.Color(255, 0, 51));
        jButton47.setText("VIEW STUDENT LIST");
        jButton47.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton47.setPreferredSize(new java.awt.Dimension(149, 23));
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel75.setText("Batch");

        AttenBatch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        AttenBatch.setForeground(new java.awt.Color(255, 0, 0));
        AttenBatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AttenBatchActionPerformed(evt);
            }
        });

        jComboBoxcrs.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBoxcrs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Course", "B_TECH", "S_TECH", "AGRICULTURE" }));
        jComboBoxcrs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton48.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton48.setForeground(new java.awt.Color(0, 0, 255));
        jButton48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_add_property_1_26px.png"))); // NOI18N
        jButton48.setText("  CREATE ATTENDANCE TABLE");
        jButton48.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton48.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButton48.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton48.setIconTextGap(8);
        jButton48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton48MouseClicked(evt);
            }
        });
        jButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton48ActionPerformed(evt);
            }
        });

        jButton49.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton49.setForeground(new java.awt.Color(0, 0, 255));
        jButton49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_select_column_26px.png"))); // NOI18N
        jButton49.setText("    CREATE TODAY COLUMN");
        jButton49.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton49.setIconTextGap(8);
        jButton49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton49MouseClicked(evt);
            }
        });
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton49ActionPerformed(evt);
            }
        });

        jButton50.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton50.setForeground(new java.awt.Color(0, 0, 255));
        jButton50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_database_26px.png"))); // NOI18N
        jButton50.setText("   ADD BASIC INFO TO TABLE");
        jButton50.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton50.setIconTextGap(8);
        jButton50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton50MouseClicked(evt);
            }
        });
        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton50ActionPerformed(evt);
            }
        });

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel76.setText("YEAR");

        AttenCyear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        AttenCyear.setForeground(new java.awt.Color(255, 0, 0));

        jComboBoxMonth.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBoxMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        jComboBoxMonth.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jCheckBoxPresense.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxPresense.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jCheckBoxPresense.setForeground(new java.awt.Color(0, 0, 255));
        jCheckBoxPresense.setText("  Set all Students are Present");
        jCheckBoxPresense.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jCheckBoxPresense.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                jCheckBoxPresenseComponentRemoved(evt);
            }
        });
        jCheckBoxPresense.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                jCheckBoxPresenseAncestorRemoved(evt);
            }
        });
        jCheckBoxPresense.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxPresenseStateChanged(evt);
            }
        });
        jCheckBoxPresense.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCheckBoxPresenseFocusLost(evt);
            }
        });
        jCheckBoxPresense.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxPresenseMouseClicked(evt);
            }
        });
        jCheckBoxPresense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxPresenseActionPerformed(evt);
            }
        });
        jCheckBoxPresense.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jCheckBoxPresensePropertyChange(evt);
            }
        });

        jButton51.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton51.setForeground(new java.awt.Color(0, 0, 255));
        jButton51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_attendance_mark_filled_26px.png"))); // NOI18N
        jButton51.setText("   ADD TODAY ATTENDANCE");
        jButton51.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton51.setIconTextGap(8);
        jButton51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton51MouseClicked(evt);
            }
        });
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });

        jButton52.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton52.setForeground(new java.awt.Color(0, 0, 255));
        jButton52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_today_26px.png"))); // NOI18N
        jButton52.setText("    View Student Attendance");
        jButton52.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton52.setIconTextGap(8);
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AttendanceLayout = new javax.swing.GroupLayout(Attendance);
        Attendance.setLayout(AttendanceLayout);
        AttendanceLayout.setHorizontalGroup(
            AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AttendanceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AttendanceLayout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addGroup(AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(AttendanceLayout.createSequentialGroup()
                                .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(AttenCyear, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jComboBoxMonth, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButton48, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton49, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton50, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton52, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBoxPresense, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AttendanceLayout.createSequentialGroup()
                        .addComponent(dateChooserCombo3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AttenBatch, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxcrs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton47, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(776, Short.MAX_VALUE))
        );
        AttendanceLayout.setVerticalGroup(
            AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AttendanceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateChooserCombo3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(AttenBatch, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxcrs, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton47, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel75)))
                .addGroup(AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AttendanceLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AttenCyear, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBoxPresense)
                        .addGap(18, 18, 18)
                        .addComponent(jButton48, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButton49, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton51, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jButton52, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AttendanceLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(133, Short.MAX_VALUE))
        );

        dynamic.add(Attendance, "card2");

        AttendanceViewPanel.setBackground(new java.awt.Color(153, 153, 153));

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel77.setText("Batch");

        AttenBatch1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        AttenBatch1.setForeground(new java.awt.Color(255, 0, 0));
        AttenBatch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AttenBatch1ActionPerformed(evt);
            }
        });

        jComboBoxcrs1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBoxcrs1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Course", "B_TECH", "S_TECH", "AGRICULTURE" }));
        jComboBoxcrs1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBoxcrs1.setMinimumSize(new java.awt.Dimension(131, 24));
        jComboBoxcrs1.setPreferredSize(new java.awt.Dimension(131, 24));

        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel78.setText("YEAR");

        AttenCyear1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        AttenCyear1.setForeground(new java.awt.Color(255, 0, 0));

        jComboBoxMonth1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBoxMonth1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        jComboBoxMonth1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton58.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton58.setForeground(new java.awt.Color(0, 0, 255));
        jButton58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StudentManagementSystem/img/icons8_today_26px.png"))); // NOI18N
        jButton58.setText("    View  Attendance");
        jButton58.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton58.setIconTextGap(8);
        jButton58.setPreferredSize(new java.awt.Dimension(245, 24));
        jButton58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton58ActionPerformed(evt);
            }
        });

        jTableAttendanceViewer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane8.setViewportView(jTableAttendanceViewer);

        attendanceText.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        attendanceText.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout AttendanceViewPanelLayout = new javax.swing.GroupLayout(AttendanceViewPanel);
        AttendanceViewPanel.setLayout(AttendanceViewPanelLayout);
        AttendanceViewPanelLayout.setHorizontalGroup(
            AttendanceViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AttendanceViewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AttendanceViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AttendanceViewPanelLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AttenBatch1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AttenCyear1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxcrs1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 829, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(attendanceText, javax.swing.GroupLayout.PREFERRED_SIZE, 856, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(755, Short.MAX_VALUE))
        );
        AttendanceViewPanelLayout.setVerticalGroup(
            AttendanceViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AttendanceViewPanelLayout.createSequentialGroup()
                .addGroup(AttendanceViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxMonth1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AttendanceViewPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(AttendanceViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel78)))
                    .addComponent(AttenCyear1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(AttenBatch1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AttendanceViewPanelLayout.createSequentialGroup()
                        .addGroup(AttendanceViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton58, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(jComboBoxcrs1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(attendanceText, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(175, 175, 175))
        );

        dynamic.add(AttendanceViewPanel, "card2");

        addFinalResults.setBackground(new java.awt.Color(204, 204, 255));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 102, 102));
        jLabel23.setText("STUDENTS FINAL RESULTS RECORDS ");

        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(102, 0, 102));
        jLabel81.setText("BATCH");

        resultBATCH.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        resultBATCH.setForeground(new java.awt.Color(255, 0, 51));

        jButton57.setBackground(new java.awt.Color(51, 255, 255));
        jButton57.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton57.setText("CREATE RESULT TABLE");
        jButton57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton57ActionPerformed(evt);
            }
        });

        resulStu_id.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        resulStu_id.setForeground(new java.awt.Color(255, 0, 51));

        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(102, 0, 102));
        jLabel82.setText("STUDENT ID");

        jButton59.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton59.setForeground(new java.awt.Color(0, 0, 255));
        jButton59.setText("ADD RESULT");
        jButton59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton59ActionPerformed(evt);
            }
        });

        StNameRes.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        StNameRes.setForeground(new java.awt.Color(255, 0, 0));
        StNameRes.setText("|");

        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(255, 51, 51));
        jLabel95.setText("INDEX NUMBER");

        resultIndexNo.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        resultIndexNo.setForeground(new java.awt.Color(255, 0, 51));

        btlbl.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btlbl.setForeground(new java.awt.Color(0, 0, 255));
        btlbl.setText("B-TECH");

        stlbl.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        stlbl.setForeground(new java.awt.Color(0, 0, 255));
        stlbl.setText("S-TECH");

        agrilbl.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        agrilbl.setForeground(new java.awt.Color(0, 0, 255));
        agrilbl.setText("AGRICULTURE");

        resultbtecIn.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        resultbtecIn.setForeground(new java.awt.Color(255, 0, 51));

        stIn.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        stIn.setForeground(new java.awt.Color(255, 0, 51));
        stIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stInActionPerformed(evt);
            }
        });

        agriIn.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        agriIn.setForeground(new java.awt.Color(0, 0, 255));

        jButton60.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton60.setForeground(new java.awt.Color(0, 0, 255));
        jButton60.setText("SEARCH");
        jButton60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton60ActionPerformed(evt);
            }
        });

        jTableStFinalResults.setBackground(new java.awt.Color(153, 204, 255));
        jTableStFinalResults.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTableStFinalResults.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTableStFinalResults.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ST_ID", "INDEX", "NAME", "B-TECH", "S-TECH", "AGRICULTURE"
            }
        ));
        jScrollPane9.setViewportView(jTableStFinalResults);

        jButton61.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton61.setForeground(new java.awt.Color(0, 0, 255));
        jButton61.setText("PRINT RESULT");
        jButton61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton61ActionPerformed(evt);
            }
        });

        jButton63.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton63.setForeground(new java.awt.Color(0, 0, 255));
        jButton63.setText("View");
        jButton63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton63ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addFinalResultsLayout = new javax.swing.GroupLayout(addFinalResults);
        addFinalResults.setLayout(addFinalResultsLayout);
        addFinalResultsLayout.setHorizontalGroup(
            addFinalResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addFinalResultsLayout.createSequentialGroup()
                .addComponent(jButton57, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel23)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(addFinalResultsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addFinalResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(addFinalResultsLayout.createSequentialGroup()
                        .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(resultIndexNo, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton59, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 849, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(addFinalResultsLayout.createSequentialGroup()
                        .addComponent(jLabel81)
                        .addGap(18, 18, 18)
                        .addComponent(resultBATCH, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel82)
                        .addGap(18, 18, 18)
                        .addComponent(resulStu_id, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton63, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addFinalResultsLayout.createSequentialGroup()
                        .addComponent(StNameRes, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resultbtecIn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stIn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(agrilbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(agriIn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(762, Short.MAX_VALUE))
        );
        addFinalResultsLayout.setVerticalGroup(
            addFinalResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addFinalResultsLayout.createSequentialGroup()
                .addGroup(addFinalResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton57, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addFinalResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton63, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(addFinalResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(resultBATCH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(resulStu_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton60, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addFinalResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(StNameRes)
                    .addGroup(addFinalResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btlbl)
                        .addComponent(stlbl)
                        .addComponent(agrilbl)
                        .addComponent(resultbtecIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(stIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(agriIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(addFinalResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel95)
                    .addComponent(resultIndexNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton59, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton61, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 78, Short.MAX_VALUE))
        );

        dynamic.add(addFinalResults, "card2");

        exams_and_marks.setBackground(new java.awt.Color(0, 204, 204));

        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(255, 255, 0));
        jLabel83.setText("STUDENTS MARKS RECORDS ");

        marksStuId.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        marksStuId.setForeground(new java.awt.Color(255, 0, 51));

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(102, 0, 102));
        jLabel84.setText("STUDENT ID");

        jButton66.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton66.setForeground(new java.awt.Color(0, 0, 255));
        jButton66.setText("CREATE TABLE");
        jButton66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton66ActionPerformed(evt);
            }
        });

        jLabel96.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(102, 0, 102));
        jLabel96.setText("BATCH");

        marks_batch.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        marks_batch.setForeground(new java.awt.Color(255, 0, 51));

        jLabel97.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(102, 0, 102));
        jLabel97.setText("EXAM");

        examTitle.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        examTitle.setForeground(new java.awt.Color(255, 0, 51));

        jButton67.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton67.setForeground(new java.awt.Color(0, 0, 255));
        jButton67.setText("SEARCH");
        jButton67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton67ActionPerformed(evt);
            }
        });

        marksSTUname.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        marksSTUname.setForeground(new java.awt.Color(255, 255, 0));
        marksSTUname.setText("|");

        marksIn.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        marksIn.setForeground(new java.awt.Color(255, 0, 51));

        btlbl1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btlbl1.setForeground(new java.awt.Color(0, 0, 255));
        btlbl1.setText("MARKS");

        jButton68.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton68.setForeground(new java.awt.Color(0, 0, 255));
        jButton68.setText("ADD MARKS");
        jButton68.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton68ActionPerformed(evt);
            }
        });

        jButton69.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton69.setForeground(new java.awt.Color(0, 0, 255));
        jButton69.setText("View");
        jButton69.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton69ActionPerformed(evt);
            }
        });

        jComboBoxchCourse1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jComboBoxchCourse1.setForeground(new java.awt.Color(0, 51, 255));
        jComboBoxchCourse1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Course", "B_Tech", "S_Tech", "Agriculture" }));
        jComboBoxchCourse1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton70.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton70.setForeground(new java.awt.Color(0, 0, 255));
        jButton70.setText("UPDATE MARKS");
        jButton70.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton70ActionPerformed(evt);
            }
        });

        jTableMarksView.setBackground(new java.awt.Color(51, 255, 51));
        jTableMarksView.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTableMarksView.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTableMarksView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane10.setViewportView(jTableMarksView);

        jButton71.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton71.setForeground(new java.awt.Color(0, 0, 255));
        jButton71.setText("PRINT MARKS");
        jButton71.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton71ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout exams_and_marksLayout = new javax.swing.GroupLayout(exams_and_marks);
        exams_and_marks.setLayout(exams_and_marksLayout);
        exams_and_marksLayout.setHorizontalGroup(
            exams_and_marksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exams_and_marksLayout.createSequentialGroup()
                .addGroup(exams_and_marksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(exams_and_marksLayout.createSequentialGroup()
                        .addGroup(exams_and_marksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, exams_and_marksLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel96)
                                .addGap(18, 18, 18)
                                .addComponent(marks_batch, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel97)
                                .addGap(18, 18, 18)
                                .addComponent(examTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxchCourse1, 0, 1, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, exams_and_marksLayout.createSequentialGroup()
                                .addGap(292, 292, 292)
                                .addComponent(jLabel83)))
                        .addGap(18, 18, 18)
                        .addComponent(jButton66))
                    .addGroup(exams_and_marksLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(exams_and_marksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(exams_and_marksLayout.createSequentialGroup()
                                .addComponent(jLabel84)
                                .addGap(18, 18, 18)
                                .addComponent(marksStuId, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jButton67, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(marksSTUname))
                            .addGroup(exams_and_marksLayout.createSequentialGroup()
                                .addComponent(btlbl1)
                                .addGap(8, 8, 8)
                                .addComponent(marksIn, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton68, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton70)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton69, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton71, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(exams_and_marksLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(795, Short.MAX_VALUE))
        );
        exams_and_marksLayout.setVerticalGroup(
            exams_and_marksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exams_and_marksLayout.createSequentialGroup()
                .addComponent(jLabel83)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(exams_and_marksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(marks_batch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(exams_and_marksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(examTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxchCourse1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton66, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(exams_and_marksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(marksStuId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton67)
                    .addComponent(marksSTUname))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(exams_and_marksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btlbl1)
                    .addComponent(marksIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton68)
                    .addComponent(jButton69, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton70)
                    .addComponent(jButton71, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );

        dynamic.add(exams_and_marks, "card2");

        graphs.setBackground(new java.awt.Color(255, 255, 153));

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 0, 0));
        jLabel80.setText("BATCH");

        jButton72.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton72.setForeground(new java.awt.Color(255, 0, 0));
        jButton72.setText("SHOW RESULTS");
        jButton72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton72ActionPerformed(evt);
            }
        });

        graphBatchIN.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        graphBatchIN.setForeground(new java.awt.Color(255, 0, 0));

        jProgressBar1.setForeground(new java.awt.Color(255, 0, 0));

        jProgressBar2.setForeground(new java.awt.Color(0, 0, 255));

        jProgressBar3.setForeground(new java.awt.Color(0, 255, 0));
        jProgressBar3.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jProgressBar3.setInheritsPopupMenu(true);

        jLabel98.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(255, 0, 0));
        jLabel98.setText("A");

        jLabel101.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(255, 0, 0));
        jLabel101.setText("B");

        jProgressBar4.setForeground(new java.awt.Color(255, 0, 0));
        jProgressBar4.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jProgressBar4.setInheritsPopupMenu(true);

        jProgressBar5.setForeground(new java.awt.Color(0, 0, 255));

        jProgressBar6.setForeground(new java.awt.Color(0, 255, 0));

        jProgressBar7.setForeground(new java.awt.Color(255, 0, 0));

        jProgressBar8.setForeground(new java.awt.Color(0, 0, 255));

        jProgressBar9.setForeground(new java.awt.Color(0, 255, 0));
        jProgressBar9.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jProgressBar9.setInheritsPopupMenu(true);

        jLabel102.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(255, 0, 0));
        jLabel102.setText("C");

        jLabel103.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(255, 0, 0));
        jLabel103.setText("S");

        jProgressBar10.setForeground(new java.awt.Color(255, 0, 0));
        jProgressBar10.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jProgressBar10.setInheritsPopupMenu(true);

        jProgressBar11.setForeground(new java.awt.Color(0, 0, 255));

        jProgressBar12.setForeground(new java.awt.Color(0, 255, 0));

        jLabel104.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(255, 0, 0));
        jLabel104.setText("F");

        jProgressBar13.setForeground(new java.awt.Color(255, 0, 0));
        jProgressBar13.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jProgressBar13.setInheritsPopupMenu(true);

        jProgressBar14.setForeground(new java.awt.Color(0, 0, 255));

        jProgressBar15.setForeground(new java.awt.Color(0, 255, 0));

        jLabel79.setBackground(new java.awt.Color(255, 0, 0));
        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel79.setText("B-Tech");

        jLabel105.setBackground(new java.awt.Color(0, 0, 255));
        jLabel105.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel105.setText("S-Tech");

        jLabel106.setBackground(new java.awt.Color(0, 255, 0));
        jLabel106.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel106.setText("Agriculture");

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 31, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(0, 255, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 31, Short.MAX_VALUE)
        );

        jLabelba.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelba.setForeground(new java.awt.Color(255, 0, 0));
        jLabelba.setText("0");

        jLabelbb.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelbb.setForeground(new java.awt.Color(255, 0, 0));
        jLabelbb.setText("0");

        jLabelbc.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelbc.setForeground(new java.awt.Color(255, 0, 0));
        jLabelbc.setText("0");

        jLabelbs.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelbs.setForeground(new java.awt.Color(255, 0, 0));
        jLabelbs.setText("0");

        jLabelbf.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelbf.setForeground(new java.awt.Color(255, 0, 0));
        jLabelbf.setText("0");

        jLabelsa.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelsa.setForeground(new java.awt.Color(0, 0, 255));
        jLabelsa.setText("0");

        jLabelsb.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelsb.setForeground(new java.awt.Color(0, 0, 255));
        jLabelsb.setText("0");

        jLabelsc.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelsc.setForeground(new java.awt.Color(0, 0, 255));
        jLabelsc.setText("0");

        jLabelss.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelss.setForeground(new java.awt.Color(0, 0, 255));
        jLabelss.setText("0");

        jLabelsf.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelsf.setForeground(new java.awt.Color(0, 0, 255));
        jLabelsf.setText("0");

        jLabelaa.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelaa.setForeground(new java.awt.Color(0, 255, 0));
        jLabelaa.setText("0");

        jLabelab.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelab.setForeground(new java.awt.Color(0, 255, 0));
        jLabelab.setText("0");

        jLabelac.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelac.setForeground(new java.awt.Color(0, 255, 0));
        jLabelac.setText("0");

        jLabelas.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelas.setForeground(new java.awt.Color(0, 255, 0));
        jLabelas.setText("0");

        jLabelaf.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelaf.setForeground(new java.awt.Color(0, 255, 0));
        jLabelaf.setText("0");

        jLabel107.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(255, 0, 0));
        jLabel107.setText("B-TECH");

        jLabel108.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(0, 0, 255));
        jLabel108.setText("S-TECH");

        jLabel109.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(0, 255, 0));
        jLabel109.setText("AGRI   ");

        jLabelTBA.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTBA.setForeground(new java.awt.Color(255, 0, 0));
        jLabelTBA.setText("A");

        jLabelTBB.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTBB.setForeground(new java.awt.Color(255, 0, 0));
        jLabelTBB.setText("B");

        jLabelTBC.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTBC.setForeground(new java.awt.Color(255, 0, 0));
        jLabelTBC.setText("C");

        jLabelTBS.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTBS.setForeground(new java.awt.Color(255, 0, 0));
        jLabelTBS.setText("S");

        jLabelTBF.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTBF.setForeground(new java.awt.Color(255, 0, 0));
        jLabelTBF.setText("F");

        jLabelTSA.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTSA.setForeground(new java.awt.Color(0, 0, 255));
        jLabelTSA.setText("A");

        jLabelTSB.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTSB.setForeground(new java.awt.Color(0, 0, 255));
        jLabelTSB.setText("B");

        jLabelTSC.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTSC.setForeground(new java.awt.Color(0, 0, 255));
        jLabelTSC.setText("C");

        jLabelTSS.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTSS.setForeground(new java.awt.Color(0, 0, 255));
        jLabelTSS.setText("S");

        jLabelTSF.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTSF.setForeground(new java.awt.Color(0, 0, 255));
        jLabelTSF.setText("F");

        jLabelTAA.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTAA.setForeground(new java.awt.Color(0, 255, 0));
        jLabelTAA.setText("A");

        jLabelTAB.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTAB.setForeground(new java.awt.Color(0, 255, 0));
        jLabelTAB.setText("B");

        jLabelTAC.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTAC.setForeground(new java.awt.Color(0, 255, 0));
        jLabelTAC.setText("C");

        jLabelTAS.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTAS.setForeground(new java.awt.Color(0, 255, 0));
        jLabelTAS.setText("S");

        jLabelTAF.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTAF.setForeground(new java.awt.Color(0, 255, 0));
        jLabelTAF.setText("F");

        jPanel6.setBackground(new java.awt.Color(102, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel110.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(255, 0, 0));
        jLabel110.setText("TOTAL   STUDENTS");

        jLabeltOTALsTUDENTS.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabeltOTALsTUDENTS.setForeground(new java.awt.Color(0, 0, 255));
        jLabeltOTALsTUDENTS.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel110)
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabeltOTALsTUDENTS, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel110)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabeltOTALsTUDENTS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout graphsLayout = new javax.swing.GroupLayout(graphs);
        graphs.setLayout(graphsLayout);
        graphsLayout.setHorizontalGroup(
            graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(graphsLayout.createSequentialGroup()
                .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(graphsLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(graphsLayout.createSequentialGroup()
                                .addComponent(jLabel80)
                                .addGap(18, 18, 18)
                                .addComponent(graphBatchIN, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jButton72, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(77, 77, 77)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel79)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(graphsLayout.createSequentialGroup()
                                .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(graphsLayout.createSequentialGroup()
                                        .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(graphsLayout.createSequentialGroup()
                                                .addComponent(jLabel103)
                                                .addGap(31, 31, 31)
                                                .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jProgressBar11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jProgressBar12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jProgressBar10, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(graphsLayout.createSequentialGroup()
                                                .addComponent(jLabel98)
                                                .addGap(31, 31, 31)
                                                .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jProgressBar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(graphsLayout.createSequentialGroup()
                                                .addComponent(jLabel101)
                                                .addGap(31, 31, 31)
                                                .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jProgressBar5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jProgressBar6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jProgressBar4, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(graphsLayout.createSequentialGroup()
                                                .addComponent(jLabel102)
                                                .addGap(31, 31, 31)
                                                .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jProgressBar8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jProgressBar7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jProgressBar9, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(18, 18, 18)
                                        .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelbs, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelba, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelbb, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelbc, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(38, 38, 38)
                                        .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelsa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelss, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelsc, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelsb, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(graphsLayout.createSequentialGroup()
                                        .addComponent(jLabel104)
                                        .addGap(31, 31, 31)
                                        .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jProgressBar14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jProgressBar15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jProgressBar13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelbf, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addComponent(jLabelsf, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(33, 33, 33)
                                .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabelas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelaa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelac, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                                    .addComponent(jLabelaf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(graphsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, graphsLayout.createSequentialGroup()
                                .addComponent(jLabel109)
                                .addGap(23, 23, 23)
                                .addComponent(jLabelTAA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(graphsLayout.createSequentialGroup()
                                .addComponent(jLabel108)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelTSA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(graphsLayout.createSequentialGroup()
                                .addComponent(jLabel107)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelTBA, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelTBB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTSB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTAB, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelTBC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTSC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTAC, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelTBS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTSS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTAS, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelTBF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTSF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTAF, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(739, Short.MAX_VALUE))
        );
        graphsLayout.setVerticalGroup(
            graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(graphsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel106, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel105, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel79, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton72, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(graphBatchIN, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel80, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(graphsLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(graphsLayout.createSequentialGroup()
                                .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelba, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelsa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelaa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(graphsLayout.createSequentialGroup()
                                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29)
                        .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(graphsLayout.createSequentialGroup()
                                .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelbb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelsb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(graphsLayout.createSequentialGroup()
                                        .addComponent(jProgressBar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jProgressBar5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jProgressBar6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28)
                        .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(graphsLayout.createSequentialGroup()
                                .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelbc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelsc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelac, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(graphsLayout.createSequentialGroup()
                                        .addComponent(jProgressBar7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jProgressBar8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jProgressBar9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel103, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, graphsLayout.createSequentialGroup()
                                .addComponent(jProgressBar10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jProgressBar11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jProgressBar12, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27))
                    .addGroup(graphsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 311, Short.MAX_VALUE)
                        .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelbs, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelss, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)))
                .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(graphsLayout.createSequentialGroup()
                        .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, graphsLayout.createSequentialGroup()
                                .addComponent(jProgressBar13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jProgressBar14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelbf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelsf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelaf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jProgressBar15, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(graphsLayout.createSequentialGroup()
                        .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel107)
                            .addComponent(jLabelTBA)
                            .addComponent(jLabelTBB)
                            .addComponent(jLabelTBC)
                            .addComponent(jLabelTBS)
                            .addComponent(jLabelTBF))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel108)
                            .addComponent(jLabelTSA)
                            .addComponent(jLabelTSB)
                            .addComponent(jLabelTSC)
                            .addComponent(jLabelTSS)
                            .addComponent(jLabelTSF))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(graphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel109)
                            .addComponent(jLabelTAA)
                            .addComponent(jLabelTAB)
                            .addComponent(jLabelTAC)
                            .addComponent(jLabelTAS)
                            .addComponent(jLabelTAF)))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(39, 39, 39))
        );

        dynamic.add(graphs, "card2");

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addComponent(StaticPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dynamic, javax.swing.GroupLayout.PREFERRED_SIZE, 1621, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(Title_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addComponent(Title_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dynamic, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(StaticPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        dynamic.removeAll();
        dynamic.removeAll();
        dynamic.repaint();
        dynamic.revalidate();
        dynamic.add(Student);
        dynamic.repaint();
        dynamic.revalidate();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        JOptionPane.showMessageDialog(null,"Sujan Software Solution...+94 713288376");
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseEntered
        JOptionPane.showMessageDialog(null, "If you have any truble this system\n please contact SSS");
    }//GEN-LAST:event_jLabel8MouseEntered

    private void btn_registrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrationActionPerformed

           //to visible registration form....
        St_base.removeAll();
        St_base.removeAll();
        St_base.repaint();
        St_base.revalidate();
        St_base.add(Stu_registration_form);
        St_base.repaint();
        St_base.revalidate();

    }//GEN-LAST:event_btn_registrationActionPerformed

    private void btn_view_stu_infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_view_stu_infoActionPerformed
        //to visible view_stu_info
        St_base.removeAll();
        St_base.removeAll();
        St_base.repaint();
        St_base.revalidate();
        St_base.add(Stu_view_info);
        St_base.repaint();
        St_base.revalidate();
    }//GEN-LAST:event_btn_view_stu_infoActionPerformed

    private void St_pic_selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_St_pic_selectActionPerformed
        //===========================================================================
        //METHOD FOR SELECT A PICTURE STUDENT(STUDENTMANAGEMENTSYSTEM)
        //===========================================================================
      
       JFileChooser file =new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        FileNameExtensionFilter filter =new FileNameExtensionFilter("*.images","jpg","png");
        int result = file.showSaveDialog(null);
        
        if(result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            lbl_image.setIcon(ResizeImage(path,null));
            ImgPath =path;
        }
        else{
            JOptionPane.showMessageDialog(null,"please select student picture");
        }
        //===========================================================================
    }//GEN-LAST:event_St_pic_selectActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        //===========================================================================
        dynamic.removeAll();
        dynamic.removeAll();
        dynamic.repaint();
        dynamic.revalidate();
        dynamic.add(Title);
        dynamic.repaint();
        dynamic.revalidate();
        //===========================================================================
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        //===========================================================================
            //THIS IS CLEAR ALL STUDENT REGISTRATION FORM
        //===========================================================================
                St_index.setText("");
                St_name.setText("");
                St_age.setText("");
                St_address.setText("");
                St_mail.setText("");
                St_tel.setText("");        
                St_batch.setText(""); 
                St_course_btech.setSelected(false);
                St_course_stech.setSelected(false);
                St_course_agri.setSelected(false);
                St_emgy_tel.setText("");
                St_parent_name.setText("");
                St_addmissionfee_chbox.setSelected(false);
                ImgPath=null;
                lbl_image.setIcon(null);
                St_index.requestFocus();
        //===========================================================================
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        dynamic.removeAll();
        dynamic.removeAll();
        dynamic.repaint();
        dynamic.revalidate();
        dynamic.add(Course);
        dynamic.repaint();
        dynamic.revalidate();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        dynamic.removeAll();
        dynamic.removeAll();
        dynamic.repaint();
        dynamic.revalidate();
        dynamic.add(Batch);
        dynamic.repaint();
        dynamic.revalidate();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        dynamic.removeAll();
        dynamic.removeAll();
        dynamic.repaint();
        dynamic.revalidate();
        dynamic.add(Finance);
        dynamic.repaint();
        dynamic.revalidate();// TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dynamic.removeAll();
        dynamic.removeAll();
        dynamic.repaint();
        dynamic.revalidate();
        dynamic.add(Title);
        dynamic.repaint();
        dynamic.revalidate();
    }//GEN-LAST:event_jButton1ActionPerformed
    //===========*********************************==============================  
    //THIS DECLARATION FOR INSERTING STUDENT DATA INTO DATABASE....
       String adfee = "No";
       String radio_text1 = "No";
       String radio_text2 = "No";
       String radio_text3 = "No";
    //===========*********************************==============================
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
    //===========================================================================
        //METHOD FOR INSERTING STUDENT DATA INTO DATABASE(STUDENTMANAGEMENTSYSTEM)
    //===========================================================================
       
       if(St_index.getText().length() == 0){
            JOptionPane.showMessageDialog(null,"Please Fillout the Student Index Number...");
        }
        else if(St_name.getText().length() == 0){
            JOptionPane.showMessageDialog(null,"Please Fillout the Student Name...");
        }
        else if(St_age.getText().length() == 0){
            JOptionPane.showMessageDialog(null,"Please Fillout the Student Age...");
        }
        else if(St_address.getText().length() == 0){
            JOptionPane.showMessageDialog(null,"Please Fillout the Student Address...");
        }
        else if(St_mail.getText().length() == 0){
            JOptionPane.showMessageDialog(null,"Please Fillout the Student E-mail...");
        }
        else if(St_tel.getText().length() == 0){
            JOptionPane.showMessageDialog(null,"Please Fillout the Student Telephone...");
        }
        else if(St_batch.getText().length() == 0){
            JOptionPane.showMessageDialog(null,"Please Fillout the Student Batch...");
        }
        else if(!St_course_btech.isSelected() && !St_course_stech.isSelected() && !St_course_agri.isSelected()){
            JOptionPane.showMessageDialog(null,"Please select atleast one course to follow...");
        }
        else if(St_emgy_tel.getText().length() == 0){
            JOptionPane.showMessageDialog(null,"Please Fillout the Student Emergency contact...");
        }
       else if(St_parent_name.getText().length() == 0){
            JOptionPane.showMessageDialog(null,"Please Fillout the Student Parent/Gardian Name...");
        }
       
        else{
            if(St_course_btech.isSelected()){
                radio_text1="Yes";
            }
            if( St_course_stech.isSelected()){
                radio_text2="Yes";
            }
             if( St_course_agri.isSelected()){
                radio_text3="Yes";
            }
            if(St_addmissionfee_chbox.isSelected()){
            adfee="Yes"; 
            }
           try {
                ps=conn.prepareStatement("INSERT INTO studentdetails(St_Index,St_Name,St_Age,St_Address,St_Email,St_Tel,St_Batch,S_Tech,B_Tech,Agriculture,St_Emgy_tel,St_Parent,Addmission,St_image)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1,St_index.getText());
                ps.setString(2,St_name.getText());
                ps.setString(3,St_age.getText());
                ps.setString(4,St_address.getText());
                ps.setString(5,St_mail.getText());
                ps.setString(6,St_tel.getText());
                ps.setString(7,St_batch.getText());
                ps.setString(8,radio_text1);
                ps.setString(9,radio_text2);
                ps.setString(10,radio_text3);
                ps.setString(11,St_emgy_tel.getText());
                ps.setString(12,St_parent_name.getText());
                ps.setString(13,adfee);
                InputStream img=new FileInputStream(new File(ImgPath));
                ps.setBlob(14, img);
                ps.executeUpdate();
                try {
                   String table="batch"+St_batch.getText();
                    ps=conn.prepareStatement("INSERT INTO "+table+"(Stu_id,Stu_name,Stu_tel,Stu_address)values(?,?,?,?)");
                    ps.setString(1,St_index.getText());
                    ps.setString(2,St_name.getText());
                    ps.setString(3,St_tel.getText());
                    ps.setString(4,St_address.getText());
                    ps.executeUpdate();
               } catch (Exception e) {
                   JOptionPane.showMessageDialog(null, e.getMessage());
               }
                JOptionPane.showMessageDialog(null, "data is Inserted Successfully...");
                St_index.setText("");
                St_name.setText("");
                St_age.setText("");
                St_address.setText("");
                St_mail.setText("");
                St_tel.setText("");        
                St_batch.setText(""); 
                St_course_btech.setSelected(false);
                St_course_stech.setSelected(false);
                St_course_agri.setSelected(false);
                St_emgy_tel.setText("");
                St_parent_name.setText("");
                St_addmissionfee_chbox.setSelected(false);
                ImgPath=null;
                lbl_image.setIcon(null);
                St_index.requestFocus();
                
           } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            } catch (FileNotFoundException ex) {
               Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex.getMessage());
           }
       }
       //===========================================================================
            //SUJAN SOFTWARE SOLUTION 19/04/2020=02:42 AM
       //===========================================================================
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       //===========================================================================
        //THIS BLOCK USED TO FIND STUDENT DETAILS ON REGISTRATION FORM....
       //===========================================================================
            if(St_index.getText().length() == 0){
                JOptionPane.showMessageDialog(null,"Please Fillout the Student Index Number...");
            }
            else{
                try{
            String sql ="SELECT * FROM `studentdetails` where St_Index='"+St_index.getText()+"'";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            if(rs.next()){
                String Name=rs.getString("St_Name");
                String age=rs.getString("St_Age");
                String address=rs.getString("St_Address");
                String mail=rs.getString("St_Email");
                String tel=rs.getString("St_Tel");
                String batch=rs.getString("St_Batch");
                String course_btech=rs.getString("B_Tech");
                String course_stech=rs.getString("S_Tech");
                String course_agri=rs.getString("Agriculture");
                String emgy=rs.getString("St_Emgy_tel");
                String parent_name=rs.getString("St_Parent");
                String addmissionfee_chbox=rs.getString("Addmission");
                byte[] img=rs.getBytes("St_image");
                ImageIcon image=new ImageIcon(img);
                Image im=image.getImage();
                Image myImg=im.getScaledInstance(lbl_image.getWidth(),lbl_image.getHeight(),Image.SCALE_SMOOTH);
                ImageIcon newImage=new ImageIcon(myImg);
                lbl_image.setIcon(newImage);
                St_name.setText(Name);
                St_age.setText(age);
                St_address.setText(address);
                St_mail.setText(mail);
                St_tel.setText(tel);
                St_batch.setText(batch);
                if("Yes".equals(course_btech)){
                    St_course_btech.setSelected(true);
                }
                if("Yes".equals(course_stech)){
                    St_course_stech.setSelected(true);
                }
                if("Yes".equals(course_agri)){
                    St_course_agri.setSelected(true);
                }
                St_emgy_tel.setText(emgy);
                St_parent_name.setText(parent_name);
                if("Yes".equals(addmissionfee_chbox)){
                    St_addmissionfee_chbox.setSelected(true);
                }
               // rs=pst.executeQuery(sql);
                }
            else{
                JOptionPane.showMessageDialog(null,"Index number does not Exists....");
                St_index.setText("");
                St_index.requestFocus();
            }
           }
            catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
            }
            
       //===========================================================================
            //SUJAN SOFTWARE SOLUTION 19/04/2020=04:13 AM
       //===========================================================================
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        batchbase.removeAll();
        batchbase.removeAll();
        batchbase.repaint();
        batchbase.revalidate();
        batchbase.add(addnewbatch);
        batchbase.repaint();
        batchbase.revalidate();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        batchbase.removeAll();
        batchbase.removeAll();
        batchbase.repaint();
        batchbase.revalidate();
        batchbase.add(Searchbatch);
        batchbase.repaint();
        batchbase.revalidate();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void btn_create_batchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_create_batchActionPerformed
        //create batch table....
                String batch=txtbatchyear.getText();
                if(txtbatchyear.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null,"Please type the batch year...");
                    }
                else{
                        try {
                                stmt=conn.createStatement();
                                String table="batch"+txtbatchyear.getText();
                                String sql="CREATE TABLE "+table+"(Stu_id VARCHAR(10) not null,"+
                                "Stu_name VARCHAR(100),"+"Stu_tel VARCHAR(11),"+"Stu_address VARCHAR(100),"+"PRIMARY KEY (Stu_id))";
                                stmt.executeUpdate(sql);
                                JOptionPane.showMessageDialog(null,batch+"  batch Created Successfully....");
                                txtbatchyear.setText("");
                                txtbatchyear.requestFocus();
                            } catch (SQLException | HeadlessException e) {
                                JOptionPane.showMessageDialog(null, e.getMessage());
                            }
                    }
    }//GEN-LAST:event_btn_create_batchActionPerformed

    private void btn_delete_batchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete_batchActionPerformed
        // detete batch table
                String batch=txtbatchyear.getText();
                if(txtbatchyear.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null,"Please type the batch year...");
                    }
                else{
            
                        try {
                                String table="batch"+txtbatchyear.getText();
                                String sql="DROP TABLE "+table;
                                stmt=conn.prepareStatement(sql);
                                stmt.executeUpdate(sql);
                                JOptionPane.showMessageDialog(null,batch+"Batch deleted successfully...");
                                txtbatchyear.setText("");
                                txtbatchyear.requestFocus();
                            } catch (SQLException ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                JOptionPane.showMessageDialog(null,ex.getMessage());
                            }
                    }
    }//GEN-LAST:event_btn_delete_batchActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
                String batch=batchTxtFind.getText();
                if(batchTxtFind.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null,"Please type the batch year...");
                }
                else{
                    String table="batch"+batchTxtFind.getText();
                    try {
                        DatabaseMetaData db1=conn.getMetaData();
                        ResultSet rs1=db1.getTables(null, null,table ,null);
                        if(rs1.next()){
                            try{
                                String sql ="SELECT * FROM  "+table+"";
                                ps=conn.prepareStatement(sql);
                                rs=ps.executeQuery(sql);
                                jTableFindBatch.setModel(DbUtils.resultSetToTableModel(rs));
                            }
                            catch(Exception e){
                                JOptionPane.showMessageDialog(null,e.getMessage());
                            }
                        }
                        else{
                            DefaultTableModel clear=new DefaultTableModel();
                            jTableFindBatch.setModel(clear);
                            JOptionPane.showMessageDialog(null, "Batch Does not Created...");
                        }
                    } catch (SQLException | HeadlessException e) {
                        JOptionPane.showMessageDialog(null,e.getMessage());
                    }
                    
                    
                }
                
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jTextC_Session_IdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextC_Session_IdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextC_Session_IdActionPerformed

    private void jTextC_remarksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextC_remarksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextC_remarksActionPerformed

    private void jButtonChooseResourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChooseResourseActionPerformed
        chFiles();
    }//GEN-LAST:event_jButtonChooseResourseActionPerformed

    private void jButtonAddCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddCourseActionPerformed
        insertCourses();
    }//GEN-LAST:event_jButtonAddCourseActionPerformed

    private void jButtonUpdateCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateCourseActionPerformed
        UpdateCourses();
    }//GEN-LAST:event_jButtonUpdateCourseActionPerformed

    private void jButtonDeleteCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteCourseActionPerformed
        Delete_Course_Rows();
    }//GEN-LAST:event_jButtonDeleteCourseActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        jComboBoxchCourse.setSelectedItem("Select Course Code");
        jTextC_Session_name.setText("");
        jTextC_Session_Id.setText("");
        jTextC_theory_hourse.setText("");
        jTextC_Practical_hours.setText("");
        jTextC_remarks.setText("");
        jComboBoxchCourse.setSelectedIndex(0);

    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        Course_base.removeAll();
        Course_base.removeAll();
        Course_base.repaint();
        Course_base.revalidate();
        Course_base.add(add_course);
        Course_base.repaint();
        Course_base.revalidate();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        Course_base.removeAll();
        Course_base.removeAll();
        Course_base.repaint();
        Course_base.revalidate();
        Course_base.add(Course_view);
        Course_base.repaint();
        Course_base.revalidate();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        //course viewing code call the methos here
        courcesViewing();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jTablecourceViewMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablecourceViewMouseDragged
       //
    }//GEN-LAST:event_jTablecourceViewMouseDragged

    private void jTablecourceViewMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablecourceViewMouseEntered
       //  JOptionPane.showMessageDialog(this,"If you want open resourse click the session id...");
    }//GEN-LAST:event_jTablecourceViewMouseEntered

    private void jTablecourceViewMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablecourceViewMousePressed
        //JOptionPane.showMessageDialog(this,"If you want open resourse click the session id...");
    }//GEN-LAST:event_jTablecourceViewMousePressed

    private void jTablecourceViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablecourceViewMouseClicked
        //here use to retrive file from db
        courceResourceFileOpen();
        
        
    }//GEN-LAST:event_jTablecourceViewMouseClicked

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        view_All_Student_Data();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        if(jComboBox1.getSelectedItem().equals("Select Cource Followers")){
            JOptionPane.showMessageDialog(null,"Please Select A cource to find Students list");
        }
        else if(jComboBox1.getSelectedItem().equals("B-Tech")){
            try {
                String qry="select St_Index,St_Name,St_Address,St_Tel,St_Parent,St_Emgy_tel from studentdetails WHERE B_Tech='Yes'";
                PreparedStatement ps=conn.prepareStatement(qry);
                rs=ps.executeQuery();
                jTableStudentView.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        else if(jComboBox1.getSelectedItem().equals("S-Tech")){
            try {
                String qry="select St_Index,St_Name,St_Address,St_Tel,St_Parent,St_Emgy_tel from studentdetails WHERE S_Tech='Yes'";
                PreparedStatement ps=conn.prepareStatement(qry);
                rs=ps.executeQuery();
                jTableStudentView.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        else if(jComboBox1.getSelectedItem().equals("Agriculture")){
            try {
                String qry="select St_Index,St_Name,St_Address,St_Tel,St_Parent,St_Emgy_tel from studentdetails WHERE Agriculture='Yes'";
                PreparedStatement ps=conn.prepareStatement(qry);
                rs=ps.executeQuery();
                jTableStudentView.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        


    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        if("".equals(findBatchStu.getText())){
            JOptionPane.showMessageDialog(null,"Please type the Year of the batch");
        }
        else{//findBatchStu
            String fiBatch=findBatchStu.getText();
            String CHtable="batch".concat(fiBatch);
            try {
                DatabaseMetaData dbm=conn.getMetaData();
                ResultSet rs1=dbm.getTables(null, null,CHtable ,null);
                if(rs1.next()){
                    try {
                        String qry="select St_Index,St_Name,St_Address,St_Tel,S_Tech,B_Tech,Agriculture from studentdetails WHERE St_Batch='"+fiBatch+"'";
                        PreparedStatement ps=conn.prepareStatement(qry);
                        rs=ps.executeQuery();
                        jTableStudentView.setModel(DbUtils.resultSetToTableModel(rs));
                    }
                    catch (SQLException | NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    } 
                }
                else{
                    DefaultTableModel clear=new DefaultTableModel();
                    jTableStudentView.setModel(clear);
                    JOptionPane.showMessageDialog(null, "Batch Does not Created...");
                }
            } 
            catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        String btch=jTextFieldBATCHinput.getText();
        String month=jComboMonths.getSelectedItem().toString();
        String level=jComboBoxLevel.getSelectedItem().toString();
        String table="fees".concat(btch).concat(level).concat(month);
        if("".equals(jTextFieldBATCHinput.getText())){
            JOptionPane.showMessageDialog(null,"Please fillout batch feild...");
        }
        else if(jComboMonths.getSelectedItem()=="Select Month"){
            JOptionPane.showMessageDialog(null,"Please Select Month...");
        }
        else if(jComboBoxLevel.getSelectedItem()=="Select Level"){
            JOptionPane.showMessageDialog(null,"Please Select Level...");
        }
        
        else{
            try {
                        String qry="select * from "+table+"";
                        PreparedStatement ps=conn.prepareStatement(qry);
                        rs=ps.executeQuery();
                        jTableFeesRetriver.setModel(DbUtils.resultSetToTableModel(rs));
                        
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
        
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        String id=jTextField1.getText();
        if("".equals(jTextField1.getText())){
            JOptionPane.showMessageDialog(null,"Please fillout the Student Index feild!!!");
        }
        else{
            try {
                String qry="select * from studentdetails WHERE St_Index='"+id+"'";
                PreparedStatement ps=conn.prepareStatement(qry);
                rs=ps.executeQuery();
                if(rs.next()){
                    financeStuName.setText(rs.getString("St_Name"));
                    jLabelBatch.setText(rs.getString("St_Batch"));
                    
                    String btec=rs.getString("B_Tech");
                    if("No".equals(btec)){
                        jRadioButton_bTech.setVisible(false);
                    }
                    String stec=rs.getString("S_Tech");
                    if("No".equals(stec)){
                        jRadioButton_sTech.setVisible(false);
                    }
                    String agri=rs.getString("Agriculture");
                    if("No".equals(agri)){
                        jRadioButton_agri.setVisible(false);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Invalid Index Number");
                }
                jTextFeesAmount.requestFocus();
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        }
        
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
       String month=jComboMonths.getSelectedItem().toString();
       String level=jComboBoxLevel.getSelectedItem().toString();
       String id=jTextField1.getText();
       String StName=financeStuName.getText();
       String btc=jLabelBatch.getText();
       String amt=jTextFeesAmount.getText();
       String date=dateChooserCombo1.getText();
       String radiText1="Unpaid";
       String radiText2="Unpaid";
       String radiText3="Unpaid";
       String table="fees".concat(btc).concat(level).concat(month);
        try {
            String sql ="SELECT * FROM `studentdetails` where St_Index='"+id+"'";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            if(rs.next()){
                String btec=rs.getString("B_Tech");
                String stec=rs.getString("S_Tech");
                String agri=rs.getString("Agriculture");
                if("No".equals(btec)){
                    radiText1="*****";
                }
                if("No".equals(stec)){
                    radiText2="*****";
                }
                if("No".equals(agri)){
                    radiText3="*****";
                }
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Invalid index number");
        }
       if(jTextField1.getText().length() == 0){
            JOptionPane.showMessageDialog(null,"Please Fillout the Student Index Number...");
        }
       else if(jComboMonths.getSelectedItem().equals("Select Month")){
            JOptionPane.showMessageDialog(null,"Please Select a month...");
       }
       else if(jComboBoxLevel.getSelectedItem().equals("Select Level")){
            JOptionPane.showMessageDialog(null,"Please Select a Level...");
       }
       
       else if(!jRadioButton_bTech.isSelected() && !jRadioButton_sTech.isSelected() && !jRadioButton_agri.isSelected()){
            JOptionPane.showMessageDialog(null,"Please select which course to paid...");
       }
       else if("".equals(jTextFeesAmount.getText())){
           JOptionPane.showMessageDialog(null,"Please Fillout the Amount ");
       }
       else{
           
           if(jRadioButton_bTech.isSelected()){
               radiText1="Paid";
           }
           if(jRadioButton_sTech.isSelected()){
               radiText2="Paid";
           }
           if(jRadioButton_agri.isSelected()){
               radiText3="Paid";
           }
           try {
               PreparedStatement ps=conn.prepareStatement("INSERT INTO "+table+"(Stu_id,Stu_name,B_Tech,S_Tech,Agriculture,Date,Amount)values(?,?,?,?,?,?,?)");
                ps.setString(1,id);
                ps.setString(2,StName);
                ps.setString(3,radiText1);
                ps.setString(4,radiText2);
                ps.setString(5,radiText3);
                ps.setString(6,date);
                ps.setInt(7,Integer.parseInt(jTextFeesAmount.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Added...");
                jRadioButton_bTech.setVisible(true);
                jRadioButton_sTech.setVisible(true);
                jRadioButton_agri.setVisible(true);
                jTextField1.requestFocus();
                financeStuName.setText("");
                jLabelBatch.setText("");
                jTextFeesAmount.setText("");
                jRadioButton_bTech.setSelected(false);
                jRadioButton_sTech.setSelected(false);
                jRadioButton_agri.setSelected(false);
           } catch (SQLException | HeadlessException e) {
               JOptionPane.showMessageDialog(null, e.getMessage());
           }
       }
       
       
       
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        String month=jComboMonths.getSelectedItem().toString();
        String batch=jTextFieldBATCHinput.getText();
        String level=jComboBoxLevel.getSelectedItem().toString();
        String id=jTextField1.getText();
        String StName=financeStuName.getText();
        String btc=jLabelBatch.getText();
        String amt=jTextFeesAmount.getText();
        String date=dateChooserCombo1.getText();
        String radiText1="Unpaid";
        String radiText2="Unpaid";
        String radiText3="Unpaid";
        String table="fees".concat(btc).concat(level).concat(month);
        try {
            String sql ="SELECT * FROM `studentdetails` where St_Index='"+id+"'";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            if(rs.next()){
                String btec=rs.getString("B_Tech");
                String stec=rs.getString("S_Tech");
                String agri=rs.getString("Agriculture");
                if("No".equals(btec)){
                    radiText1="UnFollow";
                }
                if("No".equals(stec)){
                    radiText2="UnFollow";
                }
                if("No".equals(agri)){
                    radiText3="UnFollow";
                }
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Invalid index number");
        }
        if(jTextField1.getText().length() == 0){
            JOptionPane.showMessageDialog(null,"Please Fillout the Student Index Number...");
        }
       else if(jComboMonths.getSelectedItem().equals("Select Month")){
            JOptionPane.showMessageDialog(null,"Please Select a month...");
       }
       else if(jComboBoxLevel.getSelectedItem().equals("Select Level")){
            JOptionPane.showMessageDialog(null,"Please Select a Level...");
       }
       
       else if(!jRadioButton_bTech.isSelected() && !jRadioButton_sTech.isSelected() && !jRadioButton_agri.isSelected()){
            JOptionPane.showMessageDialog(null,"Please select which course to paid...");
       }
       else if("".equals(jTextFeesAmount.getText())){
           JOptionPane.showMessageDialog(null,"Please Fillout the Amount ");
       }
       else{
            String UpdateQuery =null;
            PreparedStatement ps =null;
        try {
          UpdateQuery="UPDATE "+table+" SET `Stu_name`=?,`B_Tech`=?,`S_Tech`=?,`Agriculture`=?,`Date`=?,`Amount`=? WHERE `Stu_id`=?";
          ps =conn.prepareStatement(UpdateQuery);
            ps.setString(1,StName);
            ps.setString(2,radiText1);
            ps.setString(3,radiText2);
            ps.setString(4,radiText3);
            ps.setString(5,date);
            ps.setInt(6,Integer.parseInt(jTextFeesAmount.getText()));
            ps.setString(7,id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "data is Updated...");
                jRadioButton_bTech.setVisible(true);
                jRadioButton_sTech.setVisible(true);
                jRadioButton_agri.setVisible(true);
                jTextField1.requestFocus();
                financeStuName.setText("");
                jLabelBatch.setText("");
                jTextFeesAmount.setText("");
                jRadioButton_bTech.setSelected(false);
                jRadioButton_sTech.setSelected(false);
                jRadioButton_agri.setSelected(false);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
       }
        
        
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        //code to create a class fees table for a new month
        String batch=jTextFieldBATCHinput.getText();
        String level=jComboBoxLevel.getSelectedItem().toString();
        if("".equals(jTextFieldBATCHinput.getText())){
            JOptionPane.showMessageDialog(null,"Please fillout the batch feild...");
        }
        else if(jComboMonths.getSelectedItem().equals("Select Month")){
            JOptionPane.showMessageDialog(null,"Please Select curent Month to create monthly fees table!!!");
        }
        else if(jComboBoxLevel.getSelectedItem().equals("Select Level")){
            JOptionPane.showMessageDialog(null,"Please Select level of the batch!!!");
        }
        else{
            String Cmonth=jComboMonths.getSelectedItem().toString();
            String table="fees".concat(batch).concat(level).concat(Cmonth);
            try {
                DatabaseMetaData dbm=conn.getMetaData();
                ResultSet rs1=dbm.getTables(null, null,table ,null);
                if(rs1.next()){
                    JOptionPane.showMessageDialog(null,"Table Already created...");
                }
                else{
                    try {
                                stmt=conn.createStatement();
                                String sql="CREATE TABLE IF NOT EXISTS "+table+"(Stu_id VARCHAR(10) not null,"+
                                "Stu_name VARCHAR(100),"+"B_Tech VARCHAR(8),"+"S_Tech VARCHAR(8),"+"Agriculture VARCHAR(8),"+"Date VARCHAR(10),"+"Amount INT,"+"PRIMARY KEY (Stu_id))";
                                stmt.executeUpdate(sql);
                                JOptionPane.showMessageDialog(null,""+table+" Created Successfully...");
                                jComboMonths.setSelectedIndex(0);
                                jTextFieldBATCHinput.setText("");
                    } catch (SQLException | HeadlessException e) {
                            JOptionPane.showMessageDialog(null,e.getMessage());
                    }
                }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        String month=jComboMonths1.getSelectedItem().toString();
        String year=YearIn.getText();//
        String viewTble=year.concat(month).concat("Accounts");
        if(jComboMonths1.getSelectedItem().equals("Select Month")){
            JOptionPane.showMessageDialog(null,"Please select a month");
        }
        else if("".equals(YearIn.getText())){
            JOptionPane.showMessageDialog(null,"Please Type the year");
        }
        else{
            try {
                        String qry="select * from "+viewTble+"";
                        PreparedStatement ps=conn.prepareStatement(qry);
                        rs=ps.executeQuery();
                        jTableFeesRetriver1.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        String month=jComboMonths1.getSelectedItem().toString();
        String year=YearIn.getText();
        String date=dateChooserCombo2.getText();
        String finalYearAccount="TotalAccountsOf".concat(year);
        String monthAcTable=year.concat(month).concat("Accounts");
        String monthIncome=null;
        String monthExpense=null;
        String Tbalance=null;
        String Description=month.concat("Balance Barrowed..");
        int Totalin=0;
        int Totalex=0;
        int Tblanc=0;
        if(jComboMonths1.getSelectedItem().equals("Select Month")){
            JOptionPane.showMessageDialog(null, "Please Select the month..");
        }
        else if("".equals(YearIn.getText())){
            JOptionPane.showMessageDialog(null, "Please Type the Year..");
        }
        else{
            try {
                    String sql ="SELECT sum(Ex_Amount) FROM "+monthAcTable+"";
                    ps=conn.prepareStatement(sql);
                    rs=ps.executeQuery(sql);
                    if(rs.next()){
                    monthExpense=rs.getString("sum(Ex_Amount)");
                    Totalex=Integer.parseInt(monthExpense);
                    TotalExpensetxt.setText(monthExpense);
                    }
                   
                } catch (SQLException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
                try {
                    String sql ="SELECT sum(In_Amount) FROM "+monthAcTable+"";
                    ps=conn.prepareStatement(sql);
                    rs=ps.executeQuery(sql);
                    if(rs.next()){
                    monthIncome=rs.getString("sum(In_Amount)");
                    Totalin=Integer.parseInt(monthIncome);
                    TotalIncometxt.setText(monthIncome);
                }
                } catch (SQLException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
                
                 
                 Tblanc=Totalin-Totalex;
                 Tbalance=Integer.toString(Tblanc);
                 Totalbalancetxt.setText(Tbalance);
            int opt=JOptionPane.showConfirmDialog(null,"This is first time Finished "+month+" Transactions ?","Save",JOptionPane.YES_NO_OPTION);
            if(opt==0){
                
                try {
                         PreparedStatement ps=conn.prepareStatement("INSERT INTO "+finalYearAccount+"(Ex_Month,Ex_Amount,In_Month,In_Amount,Net_Balance)values(?,?,?,?,?)");
                         ps.setString(1,month);
                         ps.setInt(2,Totalex);
                         ps.setString(3,month);
                         ps.setInt(4,Totalin);
                         ps.setInt(5,Tblanc);
                         ps.executeUpdate();
                         JOptionPane.showMessageDialog(null,""+month+"Transactions Added...");
                } catch (SQLException | HeadlessException e) {
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
                try {
                    //To Jtable views
                        String qry="select * from "+finalYearAccount+"";
                        PreparedStatement ps=conn.prepareStatement(qry);
                        rs=ps.executeQuery();
                        jTableFeesRetriver1.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
                try {
                    insertNxtMntNtBlnc();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
            }
            else{
                String UpdateQuery =null;
                PreparedStatement ps =null;
                try {
                        UpdateQuery="UPDATE "+finalYearAccount+" SET `Ex_Amount`=?,`In_Month`=?,`In_Amount`=?,`Net_Balance`=? WHERE `Ex_Month`=?";
                         ps =conn.prepareStatement(UpdateQuery);
                         ps.setInt(1,Totalex);
                         ps.setString(2,month);
                         ps.setInt(3,Totalin);
                         ps.setInt(4,Tblanc);
                         ps.setString(5,month);
                         ps.executeUpdate();
                         JOptionPane.showMessageDialog(null,""+month+"Transactions Updated...");
                } catch (SQLException | HeadlessException e) {
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
                try {
                    //To Jtable views
                        String qry="select * from "+finalYearAccount+"";
                        ps=conn.prepareStatement(qry);
                        rs=ps.executeQuery();
                        jTableFeesRetriver1.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
                try {
                    upNxtMnthBlnc();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
            }
        }
        
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        //income insertion to a/c table
        String date=dateChooserCombo2.getText();
        String month=jComboMonths1.getSelectedItem().toString();
        String year=YearIn.getText();
        String Description=DescripsionIn.getText();
        String amount=jTextFeesAmount1.getText();
        String inserTable=year.concat(month).concat("Accounts");
        String TotalExamt=null;
        String TotalInamt=null;
        int Totalin=0;
        int Totalex=0;
        int Tblanc=0;
        if("".equals(dateChooserCombo2.getText())){
            JOptionPane.showMessageDialog(null,"Please set The Date");
        }
        else if(jComboMonths1.getSelectedItem().equals("Select Month")){
            JOptionPane.showMessageDialog(null,"Please select The month");
        }
        else if("".equals(YearIn.getText())){
            JOptionPane.showMessageDialog(null,"Please Fill the Year Feild");
        }
        else if("".equals(DescripsionIn.getText())){
            JOptionPane.showMessageDialog(null,"Please Fill the Descripsion Feild");
        }
        else if("".equals(jTextFeesAmount1.getText())){
            JOptionPane.showMessageDialog(null,"Please Fill the Amount Feild");
        }
        else{
            int opt=JOptionPane.showConfirmDialog(null,"Are you sure add this Entry ?","Save",JOptionPane.YES_NO_OPTION);
            if(opt==0){
                try {
                        PreparedStatement ps=conn.prepareStatement("INSERT INTO "+inserTable+"(In_Date,In_Description,In_Amount)values(?,?,?)");
                         ps.setString(1,date);
                         ps.setString(2,Description);
                         ps.setInt(3,Integer.parseInt(amount));
                         ps.executeUpdate();
                         JOptionPane.showMessageDialog(null,"Income "+Description+" Added..");
                         DescripsionIn.setText("");
                         DescripsionIn.requestFocus();
                         jTextFeesAmount1.setText("");
                    } catch (SQLException | NumberFormatException | HeadlessException e) {
                        JOptionPane.showMessageDialog(null,e.getMessage());
                    }
                try {
                    //To Jtable views
                        String qry="select * from "+inserTable+"";
                        PreparedStatement ps=conn.prepareStatement(qry);
                        rs=ps.executeQuery();
                        jTableFeesRetriver1.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
                try {
                    String sql ="SELECT sum(Ex_Amount) FROM "+inserTable+"";
                    ps=conn.prepareStatement(sql);
                    rs=ps.executeQuery(sql);
                    if(rs.next()){
                    TotalExamt=rs.getString("sum(Ex_Amount)");
                    Totalex=Integer.parseInt(TotalExamt);
                    TotalExpensetxt.setText(TotalExamt);
                }
                } catch (SQLException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
                try {
                    String sql ="SELECT sum(In_Amount) FROM "+inserTable+"";
                    ps=conn.prepareStatement(sql);
                    rs=ps.executeQuery(sql);
                    if(rs.next()){
                    TotalInamt=rs.getString("sum(In_Amount)");
                    Totalin=Integer.parseInt(TotalInamt);
                    TotalIncometxt.setText(TotalInamt);
                }
                } catch (SQLException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
                 
                
                Tblanc=Totalin-Totalex;
                String x=Integer.toString(Tblanc);
                Totalbalancetxt.setText(x);
            }
            else{
                JOptionPane.showMessageDialog(null,"Thanks..");
            }
        }
        
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        String month=jComboMonths1.getSelectedItem().toString();
        String year=YearIn.getText();
        String finalYearAccount="TotalAccountsOf".concat(year);
        String monthlyAcTable=year.concat(month).concat("Accounts");
        if(jComboMonths1.getSelectedItem().equals("Select Month")){
            JOptionPane.showMessageDialog(null,"Please Select Current month");
        }
        else if("".equals(YearIn.getText())){
            JOptionPane.showMessageDialog(null,"Please Type Current Year...");
        }
        else{
            try {
                    stmt=conn.createStatement();
                    String sql="CREATE TABLE IF NOT EXISTS "+monthlyAcTable+"(No INT AUTO_INCREMENT,"+
                    "Ex_Date VARCHAR(10),"+"Ex_Description VARCHAR(225),"+"Ex_Amount INT,"+"In_Date VARCHAR(10),"+"In_Description VARCHAR(225),"+"In_Amount INT,"+"PRIMARY KEY (No))";
                    stmt.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null,""+monthlyAcTable+" Created Successfully...");
                    } catch (SQLException | HeadlessException e) {
                            JOptionPane.showMessageDialog(null,e.getMessage());
                }
            try {
                DatabaseMetaData dbm=conn.getMetaData();
                ResultSet rs1=dbm.getTables(null, null,finalYearAccount ,null);
                if(rs1.next()){
                    //JOptionPane.showMessageDialog(null,"Table Already created...");
                }
                else{
                    try {
                        stmt=conn.createStatement();
                        String sql="CREATE TABLE IF NOT EXISTS "+finalYearAccount+"(No INT AUTO_INCREMENT,"+
                        "Ex_Month VARCHAR(30),"+"Ex_Amount INT,"+"In_Month VARCHAR(30),"+"In_Amount INT,"+"Net_Balance INT,"+"PRIMARY KEY (No))";
                        stmt.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null,""+finalYearAccount+" Created Successfully...");
                    } catch (SQLException | HeadlessException e) {
                        JOptionPane.showMessageDialog(null,e.getMessage());
                    }
                }
                    
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        FinanceBase.removeAll();
        FinanceBase.removeAll();
        FinanceBase.repaint();
        FinanceBase.revalidate();
        FinanceBase.add(FinanceFees);
        FinanceBase.repaint();
        FinanceBase.revalidate();
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        FinanceBase.removeAll();
        FinanceBase.removeAll();
        FinanceBase.repaint();
        FinanceBase.revalidate();
        FinanceBase.add(FinanceStatements);
        FinanceBase.repaint();
        FinanceBase.revalidate();
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
       String batch=jTextFieldBATCHinput1.getText();
       String month=jComboMonths1.getSelectedItem().toString();
       String level=jComboBoxLevel1.getSelectedItem().toString();
       String table="fees".concat(batch).concat(level).concat(month);
       String year=YearIn.getText();
       String date=dateChooserCombo2.getText();
       String totalFees = null;//2016januaryaccounts
       String inserTable=year.concat(month).concat("Accounts");
       String Description=batch.concat(month).concat("Total Fees");
       if("".equals(jTextFieldBATCHinput1.getText())){
           JOptionPane.showMessageDialog(null,"Please fill the batch.");
       }
       else if("".equals(YearIn.getText())){
           JOptionPane.showMessageDialog(null,"Please fill the Year.");
       }
       else if("".equals(dateChooserCombo2.getText())){
           JOptionPane.showMessageDialog(null,"Please Set the date.");
       }
       else if(jComboMonths1.getSelectedItem().equals("Select Month")){
           JOptionPane.showMessageDialog(null,"Please Select Month");
       }
       else if(jComboBoxLevel1.getSelectedItem().equals("Select Level")){
           JOptionPane.showMessageDialog(null,"Please Select Level");
       }
       else{
            try {
            String sql ="SELECT sum(Amount) FROM "+table+"";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            if(rs.next()){
                totalFees=rs.getString("sum(Amount)");
                //totalFeestxt.setText(totalFees);
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
            try {
                int opt=JOptionPane.showConfirmDialog(null,"Are you already add this?","Save",JOptionPane.YES_NO_OPTION);
                if(opt==0){
                        try {
                        String UpdateQuery =null;
                        PreparedStatement ps =null;
                        UpdateQuery="UPDATE "+inserTable+" SET `In_Date`=?,`In_Description`=?,`In_Amount`=? WHERE `In_Description`=?";
                        ps =conn.prepareStatement(UpdateQuery);
                        ps.setString(1,date);
                        ps.setString(2,Description);
                        ps.setInt(3,Integer.parseInt(totalFees));
                        ps.setString(4,Description);
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Total fess ="+totalFees+" Added...");
                    } catch (SQLException | NumberFormatException | HeadlessException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                }else{
                    try {
                        PreparedStatement ps=conn.prepareStatement("INSERT INTO "+inserTable+"(In_Date,In_Description,In_Amount)values(?,?,?)");
                         ps.setString(1,date);
                         ps.setString(2,Description);
                         ps.setInt(3,Integer.parseInt(totalFees));
                         ps.executeUpdate();
                         JOptionPane.showMessageDialog(null,"Total fees ="+totalFees+" Inserted.");
                    } catch (SQLException | NumberFormatException | HeadlessException e) {
                        JOptionPane.showMessageDialog(null,e.getMessage());
                    }
                }
                
           } catch (NumberFormatException e) {
               JOptionPane.showMessageDialog(null,e.getMessage());
           }
       }
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jTextFeesAmount1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFeesAmount1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFeesAmount1ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        String date=dateChooserCombo2.getText();
        String month=jComboMonths1.getSelectedItem().toString();
        String year=YearIn.getText();
        String Description=DescripsionIn.getText();
        String amount=jTextFeesAmount1.getText();
        String inserTable=year.concat(month).concat("Accounts");
        String TotalExamt=null;
        String TotalInamt=null;
        if("".equals(dateChooserCombo2.getText())){
            JOptionPane.showMessageDialog(null,"Please set The Date");
        }
        else if(jComboMonths1.getSelectedItem().equals("Select Month")){
            JOptionPane.showMessageDialog(null,"Please select The month");
        }
        else if("".equals(YearIn.getText())){
            JOptionPane.showMessageDialog(null,"Please Fill the Year Feild");
        }
        else if("".equals(DescripsionIn.getText())){
            JOptionPane.showMessageDialog(null,"Please Fill the Descripsion Feild");
        }
        else if("".equals(jTextFeesAmount1.getText())){
            JOptionPane.showMessageDialog(null,"Please Fill the Amount Feild");
        }
        else{
            int opt=JOptionPane.showConfirmDialog(null,"Are you sure add this Entry ?","Save",JOptionPane.YES_NO_OPTION);
            if(opt==0){
                try {
                         PreparedStatement ps=conn.prepareStatement("INSERT INTO "+inserTable+"(Ex_Date,Ex_Description,Ex_Amount)values(?,?,?)");
                         ps.setString(1,date);
                         ps.setString(2,Description);
                         ps.setInt(3,Integer.parseInt(amount));
                         ps.executeUpdate();
                         JOptionPane.showMessageDialog(null,"Expense"+Description+" Added..");
                         DescripsionIn.setText("");
                         DescripsionIn.requestFocus();
                         jTextFeesAmount1.setText("");
                    } catch (SQLException | NumberFormatException | HeadlessException e) {
                        JOptionPane.showMessageDialog(null,e.getMessage());
                    }
                try {
                    //To Jtable views
                        String qry="select * from "+inserTable+"";
                        PreparedStatement ps=conn.prepareStatement(qry);
                        rs=ps.executeQuery();
                        jTableFeesRetriver1.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
                try {
                    String sql ="SELECT sum(Ex_Amount) FROM "+inserTable+"";
                    ps=conn.prepareStatement(sql);
                    rs=ps.executeQuery(sql);
                    if(rs.next()){
                    TotalExamt=rs.getString("sum(Ex_Amount)");
                    TotalExpensetxt.setText(TotalExamt);
                }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
                try {
                    String sql ="SELECT sum(In_Amount) FROM "+inserTable+"";
                    ps=conn.prepareStatement(sql);
                    rs=ps.executeQuery(sql);
                    if(rs.next()){
                    TotalInamt=rs.getString("sum(In_Amount)");
                    TotalIncometxt.setText(TotalInamt);
                }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
                int Totalin=Integer.parseInt(TotalInamt);
                int Totalex=Integer.parseInt(TotalExamt);
                int Tblanc=Totalin-Totalex;
                String x=Integer.toString(Tblanc);
                Totalbalancetxt.setText(x);
            }
            else{
                JOptionPane.showMessageDialog(null,"Thanks..");
            }
        } 
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        String batch=jTextFieldBATCHinput1.getText();
        String Amount=jTextFeesAmount1.getText();
        String year=YearIn.getText();
        String month=jComboMonths1.getSelectedItem().toString();
        String inserTable=year.concat(month).concat("Accounts");
        String date=dateChooserCombo2.getText();
        String Admision=null;
        int amt=0;
        String Description="Total Addmission Fees of "+batch+"";
        
        int totalAdmision=0;
        int count=0;
        if("".equals(jTextFieldBATCHinput1.getText())){
            JOptionPane.showMessageDialog(null,"Please Type the batch....");
            jTextFieldBATCHinput1.requestFocus();
        }
        else if("".equals(jTextFeesAmount1.getText())){
            JOptionPane.showMessageDialog(null,"Please Type The Addmission Ammount....");
            jTextFeesAmount1.requestFocus();
        }
        else if("".equals(YearIn.getText())){
            JOptionPane.showMessageDialog(null,"Please Type The Year.");
            YearIn.requestFocus();
        }
        else if(jComboMonths1.getSelectedItem().equals("Select Month")){
            JOptionPane.showMessageDialog(null,"Please Select the MOnth ");
        }
        else if("".equals(dateChooserCombo2.getText())){
            JOptionPane.showMessageDialog(null,"Please Type The Year.");
           
        }
        else{
            int opt=JOptionPane.showConfirmDialog(null,"Is this first time ?","Save",JOptionPane.YES_NO_OPTION);
            try {
                    String sql ="SELECT count(Addmission) FROM `studentdetails`  where St_Batch ="+batch+" and Addmission='Yes'";
                    ps=conn.prepareStatement(sql);
                    rs=ps.executeQuery(sql);
                    if(rs.next()){
                    Admision=rs.getString("count(Addmission)");
                    count=Integer.parseInt(Admision);
                    amt=Integer.parseInt(jTextFeesAmount1.getText());
                    totalAdmision=count*amt;
                    String x=Integer.toString(totalAdmision);
                    TotalIncometxt.setText(x);
                }
                } catch (SQLException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
            
            if(opt==0){
                
            try {
                         PreparedStatement ps=conn.prepareStatement("INSERT INTO "+inserTable+"(In_Date,In_Description,In_Amount)values(?,?,?)");
                         ps.setString(1,date);
                         ps.setString(2,Description);
                         ps.setInt(3,totalAdmision);
                         ps.executeUpdate();
                         JOptionPane.showMessageDialog(null,"Income "+Description+" Added..");
                         DescripsionIn.setText("");
                         DescripsionIn.requestFocus();
                         jTextFeesAmount1.setText("");
                    } catch (SQLException | NumberFormatException | HeadlessException e) {
                        JOptionPane.showMessageDialog(null,e.getMessage());
                    }
            try {
                    //To Jtable views
                        String qry="select * from "+inserTable+"";
                        PreparedStatement ps=conn.prepareStatement(qry);
                        rs=ps.executeQuery();
                        jTableFeesRetriver1.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
            }
            else{
                try {
                        String UpdateQuery =null;
                        PreparedStatement ps =null;
                        UpdateQuery="UPDATE "+inserTable+" SET `In_Date`=?,`In_Description`=?,`In_Amount`=? WHERE `In_Description`=?";
                        ps =conn.prepareStatement(UpdateQuery);
                        ps.setString(1,date);
                        ps.setString(2,Description);
                        ps.setInt(3,totalAdmision);
                        ps.setString(4,Description);
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Total Addmission ="+totalAdmision+" Updated...");
                    } catch (SQLException | NumberFormatException | HeadlessException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                try {
                    //To Jtable views
                        String qry="select * from "+inserTable+"";
                        PreparedStatement ps=conn.prepareStatement(qry);
                        rs=ps.executeQuery();
                        jTableFeesRetriver1.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
            }  
        }
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        if(St_index.getText().length() == 0){
            JOptionPane.showMessageDialog(null,"Please Fillout the Student Index Number...");
        }
        else if(St_name.getText().length() == 0){
            JOptionPane.showMessageDialog(null,"Please Fillout the Student Name...");
        }
        else if(St_age.getText().length() == 0){
            JOptionPane.showMessageDialog(null,"Please Fillout the Student Age...");
        }
        else if(St_address.getText().length() == 0){
            JOptionPane.showMessageDialog(null,"Please Fillout the Student Address...");
        }
        else if(St_mail.getText().length() == 0){
            JOptionPane.showMessageDialog(null,"Please Fillout the Student E-mail...");
        }
        else if(St_tel.getText().length() == 0){
            JOptionPane.showMessageDialog(null,"Please Fillout the Student Telephone...");
        }
        else if(St_batch.getText().length() == 0){
            JOptionPane.showMessageDialog(null,"Please Fillout the Student Batch...");
        }
        else if(!St_course_btech.isSelected() && !St_course_stech.isSelected() && !St_course_agri.isSelected()){
            JOptionPane.showMessageDialog(null,"Please select atleast one course to follow...");
        }
        else if(St_emgy_tel.getText().length() == 0){
            JOptionPane.showMessageDialog(null,"Please Fillout the Student Emergency contact...");
        }
       else if(St_parent_name.getText().length() == 0){
            JOptionPane.showMessageDialog(null,"Please Fillout the Student Parent/Gardian Name...");
        }
       
        else{
            if(St_course_btech.isSelected()){
                radio_text1="Yes";
            }
            if( St_course_stech.isSelected()){
                radio_text2="Yes";
            }
             if( St_course_agri.isSelected()){
                radio_text3="Yes";
            }
            if(St_addmissionfee_chbox.isSelected()){
            adfee="Yes"; 
            }
            if(ImgPath == null){
                try {
                String UpdateQuery =null;
                PreparedStatement ps =null;
                UpdateQuery="UPDATE `studentdetails` SET `St_Name`=?,`St_Age`=?,`St_Address`=?,`St_Email`=?,`St_Tel`=?,`St_Batch`=?,`S_Tech`=?,`B_Tech`=?,`Agriculture`=?,`St_Emgy_tel`=?,`St_Parent`=?,`Addmission`=? WHERE `St_Index`=?";
                ps =conn.prepareStatement(UpdateQuery);
                ps.setString(1,St_name.getText());
                ps.setString(2,St_age.getText());
                ps.setString(3,St_address.getText());
                ps.setString(4,St_mail.getText());
                ps.setString(5,St_tel.getText());
                ps.setString(6,St_batch.getText());
                ps.setString(7,radio_text1);
                ps.setString(8,radio_text2);
                ps.setString(9,radio_text3);
                ps.setString(10,St_emgy_tel.getText());
                ps.setString(11,St_parent_name.getText());
                ps.setString(12,adfee);
               // ps.setBlob(13, img);
                ps.setString(13,St_index.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Updated...");
                try {
                   String table="batch"+St_batch.getText();
                   UpdateQuery="UPDATE "+table+" SET `Stu_name`=?,`Stu_tel`=?,`Stu_address`=? WHERE `Stu_id`=?";
                    ps =conn.prepareStatement(UpdateQuery);
                    ps.setString(1,St_name.getText());
                    ps.setString(2,St_tel.getText());
                    ps.setString(3,St_address.getText());
                    ps.setString(4,St_index.getText());
                    ps.executeUpdate();
               } catch (Exception e) {
                   JOptionPane.showMessageDialog(null, e.getMessage());
               }
                JOptionPane.showMessageDialog(null, "data is Updated Successfully...");
                St_index.setText("");
                St_name.setText("");
                St_age.setText("");
                St_address.setText("");
                St_mail.setText("");
                St_tel.setText("");        
                St_batch.setText(""); 
                St_course_btech.setSelected(false);
                St_course_stech.setSelected(false);
                St_course_agri.setSelected(false);
                St_emgy_tel.setText("");
                St_parent_name.setText("");
                St_addmissionfee_chbox.setSelected(false);
                ImgPath=null;
                lbl_image.setIcon(null);
                St_index.requestFocus();
                
           } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            }
            
       }
        
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed

        String year=YearIn.getText();//
        String finalYearAccount="TotalAccountsOf".concat(year);
        if("".equals(YearIn.getText())){
            JOptionPane.showMessageDialog(null,"Please Type the year");
        }
        else{
            try {
                        String qry="select * from "+finalYearAccount+"";
                        PreparedStatement ps=conn.prepareStatement(qry);
                        rs=ps.executeQuery();
                        jTableFeesRetriver1.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        int opt=JOptionPane.showConfirmDialog(null,"Are you sure close the System ?","Close The System",JOptionPane.YES_NO_OPTION);
        if(opt==0){
            try {
                Db_connection.connect().close();
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
           // this.dispose();
           System.exit(0);
        }
        
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButtonSavePwdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSavePwdActionPerformed
        try {
            passwordChanging();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonSavePwdActionPerformed

    private void jButtoncancelpwdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtoncancelpwdActionPerformed
        SettingBase.removeAll();
        SettingBase.removeAll();
        SettingBase.repaint();
        SettingBase.revalidate();
        SettingBase.add(jPanelRegisterTeacher);
        SettingBase.repaint();
        SettingBase.revalidate();
    }//GEN-LAST:event_jButtoncancelpwdActionPerformed

    private void pwdConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pwdConfirmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pwdConfirmActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dynamic.removeAll();
        dynamic.removeAll();
        dynamic.repaint();
        dynamic.revalidate();
        dynamic.add(settings);
        dynamic.repaint();
        dynamic.revalidate();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        SettingBase.removeAll();
        SettingBase.removeAll();
        SettingBase.repaint();
        SettingBase.revalidate();
        SettingBase.add(jPanelChangePwd);
        SettingBase.repaint();
        SettingBase.revalidate();
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        SettingBase.removeAll();
        SettingBase.removeAll();
        SettingBase.repaint();
        SettingBase.revalidate();
        SettingBase.add(jPanelRegisterTeacher);
        SettingBase.repaint();
        SettingBase.revalidate();
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        teacherDPchoicebtn();
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        addTeacherInfo();
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        clrTechFm();
    }//GEN-LAST:event_jButton44ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        UpdateTeacherInfo();
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
        viewTeacherList();
    }//GEN-LAST:event_jButton46ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        SettingBase.removeAll();
        SettingBase.removeAll();
        SettingBase.repaint();
        SettingBase.revalidate();
        SettingBase.add(jTeachersView);
        SettingBase.repaint();
        SettingBase.revalidate();
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jTableTeacherViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTeacherViewMouseClicked
       
        techClickOpenInfo();
    }//GEN-LAST:event_jTableTeacherViewMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //
        dynamic.removeAll();
        dynamic.removeAll();
        dynamic.repaint();
        dynamic.revalidate();
        dynamic.add(Developer);
        dynamic.repaint();
        dynamic.revalidate();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
       show_Students_In_JTable();

//to retrive and add id and staname only for jtable...
       //batch2016
//        try {
//            String qry="select Stu_id from batch2016";
//            PreparedStatement ps=conn.prepareStatement(qry);
//            rs=ps.executeQuery();
//            //if(rs.next()){
//                //String stInx=rs.getString("Stu_id");
//                //String stName=rs.getString("Stu_name");
//                jTableStAttendance.setColumnModel((TableColumnModel) DbUtils.resultSetToTableModel(rs));
//           // }
//           // jTableStAttendance.setModel(DbUtils.resultSetToTableModel(rs));
//        } catch (Exception e) {
//        }
    }//GEN-LAST:event_jButton47ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        dynamic.removeAll();
        dynamic.removeAll();
        dynamic.repaint();
        dynamic.revalidate();
        dynamic.add(Attendance);
        dynamic.repaint();
        dynamic.revalidate();
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton48MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton48MouseClicked
        //
        String dt=dateChooserCombo3.getCurrent().getCalendarType();
    }//GEN-LAST:event_jButton48MouseClicked

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed
        String batch=AttenBatch.getText();
        String course=jComboBoxcrs.getSelectedItem().toString();
        String aYear=AttenCyear.getText();
        String aMonth=jComboBoxMonth.getSelectedItem().toString();
        String AttendanceTable=batch.concat("batch").concat(course).concat(aYear).concat(aMonth).concat("attendance");
        //=============================================================
        if("".equals(AttenBatch.getText())){
            JOptionPane.showMessageDialog(null,"Type the batch");
            AttenBatch.requestFocus();
        }
        else if(jComboBoxcrs.getSelectedItem()=="Select Course"){
            JOptionPane.showMessageDialog(null,"Please Select the  Course");
        }
        else if("".equals(AttenCyear.getText())){
            JOptionPane.showMessageDialog(null,"Type the current year...");
            AttenCyear.requestFocus();
        }
        else if(jComboBoxMonth.getSelectedItem()=="Select Month"){
            JOptionPane.showMessageDialog(null,"Please Select the  Month");
        }
        else{
            try {
                DatabaseMetaData db1=conn.getMetaData();
                        ResultSet rs1=db1.getTables(null, null,AttendanceTable,null);
                        if(rs1.next()){
                            JOptionPane.showMessageDialog(null,AttendanceTable+" Table Already created...");
                        }
                        else{
                            //table create code....
                            try {
                                stmt=conn.createStatement();
                                String sql="CREATE TABLE IF NOT EXISTS "+AttendanceTable+"(Stu_id VARCHAR(10),"+
                                "Stu_name VARCHAR(100),"+"PRIMARY KEY (Stu_id))";
                                stmt.executeUpdate(sql);
                                JOptionPane.showMessageDialog(null,""+AttendanceTable+" Created Successfully...");
                    } catch (SQLException | HeadlessException e) {
                        JOptionPane.showMessageDialog(null,e.getMessage());
                    }
                        }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton48ActionPerformed

    private void jButton49MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton49MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton49MouseClicked

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
        String date=dateChooserCombo3.getText();
        String batch=AttenBatch.getText();
        String course=jComboBoxcrs.getSelectedItem().toString();
        String aYear=AttenCyear.getText();
        String aMonth=jComboBoxMonth.getSelectedItem().toString();
        String AttendanceTable=batch.concat("batch").concat(course).concat(aYear).concat(aMonth).concat("attendance");
        //==================================================================================
        if("".equals(dateChooserCombo3.getText())){
            JOptionPane.showMessageDialog(null,"Please set the Date first");
            dateChooserCombo3.requestFocus();
        }
        else if("".equals(AttenBatch.getText())){
            JOptionPane.showMessageDialog(null,"Type the batch");
            AttenBatch.requestFocus();
        }
        else if(jComboBoxcrs.getSelectedItem()=="Select Course"){
            JOptionPane.showMessageDialog(null,"Please Select the  Course");
        }
        else if("".equals(AttenCyear.getText())){
            JOptionPane.showMessageDialog(null,"Type the current year...");
            AttenCyear.requestFocus();
        }
        else if(jComboBoxMonth.getSelectedItem()=="Select Month"){
            JOptionPane.showMessageDialog(null,"Please Select the  Month");
        }
        else{
            try {
                //CHECK THE TABLE EXISTS OR NOT FROM THE DB
                        DatabaseMetaData db1=conn.getMetaData();
                        ResultSet rs1=db1.getTables(null, null,AttendanceTable,null);
                        if(rs1.next()){
                           //CODE FOR ADD NEW COLUMNS
                            try {
                                stmt=conn.createStatement();
                                String qry="ALTER TABLE `"+AttendanceTable+"` ADD `"+date+"` VARCHAR(10) DEFAULT '----'";
                                stmt.executeUpdate(qry);
                                JOptionPane.showMessageDialog(null,date+" Column Added Successfully...");
                            } catch (SQLException | HeadlessException e) {
                                JOptionPane.showMessageDialog(null,date+" Already added");
                            }
                           
                        }
                        else{
                            JOptionPane.showMessageDialog(null,AttendanceTable+" Table Does not created!!!.");
                        }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
        
    }//GEN-LAST:event_jButton49ActionPerformed

    private void jButton50MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton50MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton50MouseClicked

    private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton50ActionPerformed
        String batch=AttenBatch.getText();
        String course=jComboBoxcrs.getSelectedItem().toString();
        String aYear=AttenCyear.getText();
        String aMonth=jComboBoxMonth.getSelectedItem().toString();
        String AttendanceTable=batch.concat("batch").concat(course).concat(aYear).concat(aMonth).concat("attendance");
        //=============================================================
        if("".equals(AttenBatch.getText())){
            JOptionPane.showMessageDialog(null,"Type the batch");
            AttenBatch.requestFocus();
        }
        else if(jComboBoxcrs.getSelectedItem()=="Select Course"){
            JOptionPane.showMessageDialog(null,"Please Select the  Course");
        }
        else if("".equals(AttenCyear.getText())){
            JOptionPane.showMessageDialog(null,"Type the current year...");
            AttenCyear.requestFocus();
        }
        else if(jComboBoxMonth.getSelectedItem()=="Select Month"){
            JOptionPane.showMessageDialog(null,"Please Select the  Month");
        }
        //==========================================================================
        else{
            DefaultTableModel mode2 = (DefaultTableModel)jTableStAttendance.getModel();
            mode2.getRowCount();
          
                for (int  i= 0; i<jTableStAttendance.getRowCount(); i++) {
                   String id= (String) mode2.getValueAt( i, 0); 
                   String name= (String) mode2.getValueAt( i, 1);
                    try {
                         ps=conn.prepareStatement("INSERT INTO `"+AttendanceTable+"`(Stu_id,Stu_name)values(?,?)");
                         ps.setString(1,id);
                         ps.setString(2,name);
                         ps.executeUpdate();  
                    } catch (SQLException | HeadlessException e) {
                        JOptionPane.showMessageDialog(null,e.getMessage());
                    }
               }
                 JOptionPane.showMessageDialog(null,"Basic data inserted successfully");
        }
        
    }//GEN-LAST:event_jButton50ActionPerformed

    private void jCheckBoxPresenseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxPresenseMouseClicked
        
    }//GEN-LAST:event_jCheckBoxPresenseMouseClicked

    private void jCheckBoxPresenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxPresenseActionPerformed
          if(jCheckBoxPresense.isSelected()==true){
              DefaultTableModel model = (DefaultTableModel)jTableStAttendance.getModel();
                model.getRowCount();
           
                for (int  i= 0; i<jTableStAttendance.getRowCount(); i++) {
                    model.setValueAt(true, i, 2);
               }
          }
          else{
                DefaultTableModel model = (DefaultTableModel)jTableStAttendance.getModel();
                model.getRowCount();
           
                for (int  i= 0; i<jTableStAttendance.getRowCount(); i++) {
                    model.setValueAt(false, i, 2);
               }
          }
            
            //I FEEL HAPPY BECAUSE I LEARN SOMETHING NEW FEELING AWESOME.....*****
    }//GEN-LAST:event_jCheckBoxPresenseActionPerformed

    private void jCheckBoxPresenseStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxPresenseStateChanged
        
    }//GEN-LAST:event_jCheckBoxPresenseStateChanged

    private void jCheckBoxPresenseComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jCheckBoxPresenseComponentRemoved
       //
    }//GEN-LAST:event_jCheckBoxPresenseComponentRemoved

    private void jCheckBoxPresenseAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jCheckBoxPresenseAncestorRemoved
        //
    }//GEN-LAST:event_jCheckBoxPresenseAncestorRemoved

    private void jCheckBoxPresenseFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCheckBoxPresenseFocusLost
        //
    }//GEN-LAST:event_jCheckBoxPresenseFocusLost

    private void jCheckBoxPresensePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jCheckBoxPresensePropertyChange
       //
    }//GEN-LAST:event_jCheckBoxPresensePropertyChange

    private void jButton51MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton51MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton51MouseClicked

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        //ADD CURRENT DAY ATTENDANCE TO THE DATABASE
        String date=dateChooserCombo3.getText();
        String batch=AttenBatch.getText();
        String course=jComboBoxcrs.getSelectedItem().toString();
        String aYear=AttenCyear.getText();
        String aMonth=jComboBoxMonth.getSelectedItem().toString();
        String AttendanceTable=batch.concat("batch").concat(course).concat(aYear).concat(aMonth).concat("attendance");
        //==================================================================================
        DefaultTableModel mode2 = (DefaultTableModel)jTableStAttendance.getModel();
        mode2.getRowCount();
        //==================================================================================
        if("".equals(dateChooserCombo3.getText())){
            JOptionPane.showMessageDialog(null,"Please set the Date first");
            dateChooserCombo3.requestFocus();
        }
        else if("".equals(AttenBatch.getText())){
            JOptionPane.showMessageDialog(null,"Type the batch");
            AttenBatch.requestFocus();
        }
        else if(jComboBoxcrs.getSelectedItem()=="Select Course"){
            JOptionPane.showMessageDialog(null,"Please Select the  Course");
        }
        else if("".equals(AttenCyear.getText())){
            JOptionPane.showMessageDialog(null,"Type the current year...");
            AttenCyear.requestFocus();
        }
        else if(jComboBoxMonth.getSelectedItem()=="Select Month"){
            JOptionPane.showMessageDialog(null,"Please Select the  Month");
        }        
        //============================================================================
        else{
                for (int  i= 0; i<jTableStAttendance.getRowCount(); i++) {
                   String id= (String) mode2.getValueAt( i, 0);
                   boolean vl=  (boolean) mode2.getValueAt( i, 2);
                   
                        try {
                            String UpdateQuery =null;
                            UpdateQuery="UPDATE `"+AttendanceTable+"` SET `"+date+"`=? WHERE `Stu_id`='"+id+"'";
                            ps =conn.prepareStatement(UpdateQuery);
                            ps.setBoolean(1,vl);
                            ps.executeUpdate(); 
                    } catch (SQLException | HeadlessException e) {
                        JOptionPane.showMessageDialog(null,e.getMessage());
                    }
                }
                JOptionPane.showMessageDialog(null,"Attendance Added successfully");
        }
            
    }//GEN-LAST:event_jButton51ActionPerformed

    private void AttenBatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AttenBatchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AttenBatchActionPerformed

    private void AttenBatch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AttenBatch1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AttenBatch1ActionPerformed

    private void jButton58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton58ActionPerformed
        // TODO add your handling code here:AttendanceViewPanel  
        String batch=AttenBatch1.getText();
        String course=jComboBoxcrs1.getSelectedItem().toString();
        String aYear=AttenCyear1.getText();
        String aMonth=jComboBoxMonth1.getSelectedItem().toString();
        String AttendanceTable=batch.concat("batch").concat(course).concat(aYear).concat(aMonth).concat("attendance");
        String setlblText=batch.concat("==> "+course).concat(" | "+aYear).concat(" | "+aMonth).concat("| Attendance");
        
        //================================================================================================
        if("".equals(AttenBatch1.getText())){
            JOptionPane.showMessageDialog(null,"Type the batch");
            AttenBatch1.requestFocus();
        }
        else if(jComboBoxcrs1.getSelectedItem()=="Select Course"){
            JOptionPane.showMessageDialog(null,"Please Select the  Course");
        }
        else if("".equals(AttenCyear1.getText())){
            JOptionPane.showMessageDialog(null,"Type the current year...");
            AttenCyear1.requestFocus();
        }
        else if(jComboBoxMonth1.getSelectedItem()=="Select Month"){
            JOptionPane.showMessageDialog(null,"Please Select the  Month");
        }
        //======================================================================================================
        else{
            try {
                        DatabaseMetaData db1=conn.getMetaData();
                        ResultSet rs1=db1.getTables(null, null,AttendanceTable,null);
                        if(rs1.next()){
                            //SELECT ALL FROM ATTENDANCE TABLE...............
                            try {
                                attendanceText.setText(setlblText);
                                String qry="select * from `"+AttendanceTable+"`";
                                ps=conn.prepareStatement(qry);
                                rs=ps.executeQuery();
                                jTableAttendanceViewer.setModel(DbUtils.resultSetToTableModel(rs));
                            } catch (SQLException | HeadlessException e) {
                                JOptionPane.showMessageDialog(null,e.getMessage());
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null,AttendanceTable+" Table Does not created...");
                        }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton58ActionPerformed

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        dynamic.removeAll();
        dynamic.removeAll();
        dynamic.repaint();
        dynamic.revalidate();
        dynamic.add(AttendanceViewPanel);
        dynamic.repaint();
        dynamic.revalidate();
    }//GEN-LAST:event_jButton52ActionPerformed

    private void jLabel68MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel68MouseEntered
        JOptionPane.showMessageDialog(null,"I'm Sujan ! You are welcome always....");
    }//GEN-LAST:event_jLabel68MouseEntered

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        // PRINTING fiance STATEMENTS
        String title=DescripsionIn.getText();
        String date=dateChooserCombo2.getText();
        String head=title;
        String foot="DATE: "+date.concat("| SUJAN SOFTWARE SOLUTION | sjnkayal36@gmail.com |EXPRESS INSITITUTE|");
        if(jTableFeesRetriver1.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Table does not Have any data");
        }
        else if("".equals(DescripsionIn.getText())){
            JOptionPane.showMessageDialog(null,"Please Type the report Title in Discription feild... ");
            DescripsionIn.requestFocus();
        }
        else{
            MessageFormat header=new MessageFormat(head);
            MessageFormat footer=new MessageFormat(foot);
            try {
                jTableFeesRetriver1.print(JTable.PrintMode.NORMAL, header, footer);
            } catch (java.awt.print.PrinterException e) {
                JOptionPane.showMessageDialog(null,"Can not print !");
            }
        }
    }//GEN-LAST:event_jButton45ActionPerformed

    private void jButton54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton54ActionPerformed
        // TODO add your handling code here:jTableFeesRetriver
        String date=dateChooserCombo1.getText();
        String batch=jTextFieldBATCHinput.getText();
        String head=batch.concat(" BATCH CLASS FEES RECORDS ");
        String foot="DATE: "+date.concat("| SUJAN SOFTWARE SOLUTION | sjnkayal36@gmail.com |EXPRESS INSITITUTE |");
        if(jTableFeesRetriver.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Table does not Have any data");
        }
        else if("".equals(dateChooserCombo1.getText())){
            JOptionPane.showMessageDialog(null,"Please Set the date ");
            dateChooserCombo1.requestFocus();
        }
        else if("".equals(jTextFieldBATCHinput.getText())){
            JOptionPane.showMessageDialog(null,"Please Type the Batch ");
            jTextFieldBATCHinput.requestFocus();
        }
        else{
            MessageFormat header=new MessageFormat(head);
            MessageFormat footer=new MessageFormat(foot);
            try {
                jTableFeesRetriver.print(JTable.PrintMode.NORMAL, header, footer);
            } catch (java.awt.print.PrinterException e) {
                JOptionPane.showMessageDialog(null,"Can not print !");
            }
        }
    }//GEN-LAST:event_jButton54ActionPerformed

    private void jButton55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton55ActionPerformed
        // TODO add your handling code here:jTableStudentView StTbleTitilePrint
        String head=null;
        String foot="+94 71 3288376| SUJAN SOFTWARE SOLUTION | sjnkayal36@gmail.com |EXPRESS INSITITUTE |";
        if(jTableStudentView.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Table does not Have any data");
        }
        else{
            String tblNm=JOptionPane.showInputDialog("Please Type the Title of the Report");
            if(tblNm.equals("")){
                JOptionPane.showMessageDialog(null,"Please Type the name....");
            }
            else{
                head=tblNm;
                MessageFormat header=new MessageFormat(head);
                MessageFormat footer=new MessageFormat(foot);
                try {
                    jTableStudentView.print(JTable.PrintMode.NORMAL, header, footer);
                } catch (java.awt.print.PrinterException e) {
                     JOptionPane.showMessageDialog(null,"Can not print !");
                }
            } 
        }
    }//GEN-LAST:event_jButton55ActionPerformed

    private void jButton56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton56ActionPerformed
        // TODO add your handling code here:  jTableFindBatch
        String title=batchTxtFind.getText();
        String head=title+" STUDENTS ";
        String foot="+94 71 3288376| SUJAN SOFTWARE SOLUTION | sjnkayal36@gmail.com |EXPRESS INSITITUTE|";
        if(jTableFindBatch.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Table does not Have any data");
        }
        else if("".equals(batchTxtFind.getText())){
            JOptionPane.showMessageDialog(null,"Please Type the report Title in Discription feild... ");
            batchTxtFind.requestFocus();
        }
        else{
            MessageFormat header=new MessageFormat(head);
            MessageFormat footer=new MessageFormat(foot);
            try {
                jTableFindBatch.print(JTable.PrintMode.NORMAL, header, footer);
            } catch (java.awt.print.PrinterException e) {
                JOptionPane.showMessageDialog(null,"Can not print !");
            }
        }
    }//GEN-LAST:event_jButton56ActionPerformed

    private void jButton62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton62ActionPerformed
        // TODO add your handling code here:  addFinalResults
        dynamic.removeAll();
        dynamic.removeAll();
        dynamic.repaint();
        dynamic.revalidate();
        dynamic.add(addFinalResults);
        dynamic.repaint();
        dynamic.revalidate();
    }//GEN-LAST:event_jButton62ActionPerformed

    private void stInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stInActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stInActionPerformed

    private void jButton57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton57ActionPerformed
         // TODO add your handling code here: CREATING FINAL RESULT TABLE
        String batch=resultBATCH.getText();
        String tableName="batch".concat(batch).concat("finalresults");
        if(resultBATCH.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please enter the batch first...");
            resultBATCH.requestFocus();
        }
        else{   
            try {
                        DatabaseMetaData db1=conn.getMetaData();
                        ResultSet rs1=db1.getTables(null, null,tableName,null);
                        if(rs1.next()){
                            JOptionPane.showMessageDialog(null,tableName+" Table Already created");
                        }
                        else{
                            try {
                                stmt=conn.createStatement();
                                
                                
                                String sql="CREATE TABLE IF NOT EXISTS "+tableName+"(ST_ID VARCHAR(10),"+
                                "ST_INDEX VARCHAR(10),"+"NAME VARCHAR(100),"+"B_TECH VARCHAR(2) DEFAULT '--',"+"S_TECH VARCHAR(2) DEFAULT '--',"+"AGRICULTURE VARCHAR(2) DEFAULT '--',"+"PRIMARY KEY (ST_ID))";
                                stmt.executeUpdate(sql);
                                JOptionPane.showMessageDialog(null,""+tableName+" Created Successfully...");
                            } catch (SQLException | HeadlessException e) {
                                 JOptionPane.showMessageDialog(null,e.getMessage());
                            }
                        }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
                        
        }
        
    }//GEN-LAST:event_jButton57ActionPerformed

    private void jButton60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton60ActionPerformed
        // TODO add your handling code here: 
        String stId=resulStu_id.getText();
        if(resulStu_id.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please typr the student id");
        }
        else{
            try {
                String qry="select * from `studentdetails` where St_Index='"+stId+"'";
                ps=conn.prepareStatement(qry);
                rs=ps.executeQuery(qry);
                if(rs.next()){
                    String name=rs.getString("St_Name");
                    String batch=rs.getString("St_Batch");
                    String stec=rs.getString("S_Tech");
                    String btec=rs.getString("B_Tech");
                    String agri=rs.getString("Agriculture");
                    StNameRes.setText(name);
                    resultBATCH.setText(batch);
                    if("No".equals(stec)){
                        stlbl.setVisible(false);
                        stIn.setVisible(false);
                    }
                    if("No".equals(btec)){
                        btlbl.setVisible(false);
                        resultbtecIn.setVisible(false);
                    }
                    if("No".equals(agri)){
                        agrilbl.setVisible(false);
                        agriIn.setVisible(false);
                    }
                    
                }
                else{
                    JOptionPane.showMessageDialog(null,"Invalid Student ID....");
                }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton60ActionPerformed

    private void jButton59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton59ActionPerformed
        // TODO add your handling code here: insert into final result db table
        String batch=resultBATCH.getText();
        String stId=resulStu_id.getText();
        String name=StNameRes.getText();
        String index=resultIndexNo.getText();
        String btec=resultbtecIn.getText();
        String stec=stIn.getText();
        String agri=agriIn.getText();
        String tableName="batch".concat(batch).concat("finalresults");
        //=============================================================================
        String radiText1=btec;
        String radiText2=stec;
        String radiText3=agri;
        //==============================================================================
        try {
            String sql ="SELECT * FROM `studentdetails` where St_Index='"+stId+"'";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery(sql);
            if(rs.next()){
                String btecDb=rs.getString("B_Tech");
                String stecDb=rs.getString("S_Tech");
                String agriDb=rs.getString("Agriculture");
                if("No".equals(btecDb)){
                    radiText1="**";
                }
                else{
                    switch(resultbtecIn.getText()){
                        case "":JOptionPane.showMessageDialog(null,"PLEASE TYPE THE B-TECH RESULT");
                                resultbtecIn.requestFocus();
                            break;
                        case "A":
                            break;
                        case "B":
                            break;
                        case "C":
                            break;
                        case "S":
                            break;
                        case "F":
                            break;
                        default:JOptionPane.showMessageDialog(null,"B_TECH GRADE IS INVALID");
                                resultbtecIn.requestFocus();
                                resultIndexNo.setText("");
                            break;
                    }
                }
                if("No".equals(stecDb)){
                    radiText2="**";
                }
                else{
                    switch(stIn.getText()){
                        case "":JOptionPane.showMessageDialog(null,"PLEASE TYPE THE S-TECH RESULT");
                                stIn.requestFocus();
                            break;
                        case "A":
                            break;
                        case "B":
                            break;
                        case "C":
                            break;
                        case "S":
                            break;
                        case "F":
                            break;
                        default:JOptionPane.showMessageDialog(null,"S_TECH GRADE IS INVALID");
                                stIn.requestFocus();
                                resultIndexNo.setText("");
                            break;
                    }
                }
                if("No".equals(agriDb)){
                    radiText3="**";
                }
                else{
                    switch(agriIn.getText()){
                        case "":JOptionPane.showMessageDialog(null,"PLEASE TYPE THE AGRICULTURE RESULT");
                                       agriIn.requestFocus();
                            break;
                        case "A":
                            break;
                        case "B":
                            break;
                        case "C":
                            break;
                        case "S":
                            break;
                        case "F":
                            break;
                        default:JOptionPane.showMessageDialog(null,"AGRICULTURE GRADE IS INVALID");
                                agriIn.requestFocus();
                                resultIndexNo.setText("");
                                break;
                    }
                }
            }
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        //=================================================================================    
        if(resulStu_id.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please type the Student id");
            resulStu_id.requestFocus();
        }

        else if(resultIndexNo.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please type the St A/L index number...");
            resultIndexNo.requestFocus();
        }
        
        else{
            try {
                ps=conn.prepareStatement("INSERT INTO `"+tableName+"`(ST_ID,ST_INDEX,NAME,B_TECH,S_TECH,AGRICULTURE)values(?,?,?,?,?,?)");
                ps.setString(1, stId);
                ps.setString(2, index);
                ps.setString(3, name);
                ps.setString(4, radiText1);
                ps.setString(5, radiText2);
                ps.setString(6, radiText3);
                ps.executeUpdate(); 
                JOptionPane.showMessageDialog(null,"Results Added...");
                
                resulStu_id.requestFocus();
                StNameRes.setText("");
                btlbl.setVisible(true);
                resultbtecIn.setVisible(true);
                
                stlbl.setVisible(true);
                stIn.setVisible(true);
                
                agrilbl.setVisible(true);
                agriIn.setVisible(true);
                
                resultbtecIn.setText("");
                stIn.setText("");
                agriIn.setText("");
                resultIndexNo.setText("");
                
                
                
                try {
                    String qry="SELECT * FROM `"+tableName+"`";
                    ps=conn.prepareStatement(qry);
                    rs=ps.executeQuery();
                    jTableStFinalResults.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
                
            }
        }
                resulStu_id.requestFocus();
                StNameRes.setText("");
                btlbl.setVisible(true);
                resultbtecIn.setVisible(true);
                
                stlbl.setVisible(true);
                stIn.setVisible(true);
                
                agrilbl.setVisible(true);
                agriIn.setVisible(true);
                
                resultbtecIn.setText("");
                stIn.setText("");
                agriIn.setText("");
                resultIndexNo.setText("");
    }//GEN-LAST:event_jButton59ActionPerformed

    private void jButton63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton63ActionPerformed
        // TODO add your handling code here: REsult viewing
        String batch=resultBATCH.getText();
        String tableName="batch".concat(batch).concat("finalresults");
        if(resultBATCH.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please type the batch");
            resultBATCH.requestFocus();
        }
        else{
            try {
                    String qry="SELECT * FROM `"+tableName+"`";
                    ps=conn.prepareStatement(qry);
                    rs=ps.executeQuery();
                    jTableStFinalResults.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
        }
    }//GEN-LAST:event_jButton63ActionPerformed

    private void jButton61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton61ActionPerformed
        // TODO add your handling code here: PRINT FINAL RESULTS
        String batch=resultBATCH.getText();
        String head=batch+" BATCH FINAL RESULTS";
        String foot="+94 71 3288376 | SUJAN SOFTWARE SOLUTION | sjnkayal36@gmail.com |EXPRESS INSITITUTE|";
        if(jTableStFinalResults.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Table does not Have any data");
        }
        else if("".equals(resultBATCH.getText())){
            JOptionPane.showMessageDialog(null,"PLEASE TYPE THE BATCH ");
            resultBATCH.requestFocus();
        }
        else{
            MessageFormat header=new MessageFormat(head);
            MessageFormat footer=new MessageFormat(foot);
            try {
                jTableStFinalResults.print(JTable.PrintMode.NORMAL, header, footer);
            } catch (java.awt.print.PrinterException e) {
                JOptionPane.showMessageDialog(null,"Can not print !");
            }
        }
    }//GEN-LAST:event_jButton61ActionPerformed

    private void jButton64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton64ActionPerformed
        // TODO add your handling code here: graphs
        dynamic.removeAll();
        dynamic.removeAll();
        dynamic.repaint();
        dynamic.revalidate();
        dynamic.add(graphs);
        dynamic.repaint();
        dynamic.revalidate();
    }//GEN-LAST:event_jButton64ActionPerformed

    private void jButton66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton66ActionPerformed
        // TODO add your handling code here: CREATE EXAM TABLE
        String batch=marks_batch.getText();
        String course=jComboBoxchCourse1.getSelectedItem().toString();
        String examtitle=examTitle.getText();
        String examTable="batch".concat(batch).concat(course).concat(examtitle);
        if("".equals(marks_batch.getText())){
            JOptionPane.showMessageDialog(null,"Please type the batch...");
            marks_batch.requestFocus();
        }
        else if(jComboBoxchCourse1.getSelectedItem()=="Course"){
            JOptionPane.showMessageDialog(null,"Please Select the course...");
        }
        else if("".equals(examTitle.getText())){
            JOptionPane.showMessageDialog(null,"Please type the name of the exam...");
            examTitle.requestFocus();
        }
        else{
            try {
                        DatabaseMetaData db1=conn.getMetaData();
                        ResultSet rs1=db1.getTables(null, null,examTable,null);
                        if(rs1.next()){
                            JOptionPane.showMessageDialog(null,examTable+" Table Already created");
                        }
                        else{
                            try {
                                stmt=conn.createStatement();
                                String sql="CREATE TABLE IF NOT EXISTS "+examTable+"(ST_ID VARCHAR(10),"+
                                "NAME VARCHAR(100),"+"MARKS VARCHAR(3) DEFAULT '--',"+"PRIMARY KEY (ST_ID))";
                                stmt.executeUpdate(sql);
                                JOptionPane.showMessageDialog(null,""+examTable+" Created Successfully...");
                                marks_batch.setText("");
                            } catch (SQLException | HeadlessException e) {
                                JOptionPane.showMessageDialog(null,e.getMessage());
                            }
                        }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
        
    }//GEN-LAST:event_jButton66ActionPerformed

    private void jButton67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton67ActionPerformed
        // TODO add your handling code here: search student list for add exam marks
        String stId=marksStuId.getText();
        String course=jComboBoxchCourse1.getSelectedItem().toString();
        if("".equals(marksStuId.getText())){
            JOptionPane.showMessageDialog(null,"Please type the Student id");
            marksStuId.requestFocus();
        }
        else if(jComboBoxchCourse1.getSelectedItem()=="Course"){
            JOptionPane.showMessageDialog(null,"Please Select the course...");
        }
        else{
            if(jComboBoxchCourse1.getSelectedItem()=="B_Tech"){
                try {
                    String qry="select * from studentdetails WHERE B_Tech='Yes' and St_Index='"+stId+"'";
                    ps=conn.prepareStatement(qry);
                    rs=ps.executeQuery();
                    if(rs.next()){
                        String name=rs.getString("St_Name");
                        String batch=rs.getString("St_Batch");
                        marksSTUname.setText(name);
                        marks_batch.setText(batch);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Student Does not follow the course...");
                    }
                } catch (SQLException | HeadlessException e) {
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
            }
            else if(jComboBoxchCourse1.getSelectedItem()=="S_Tech"){
                try {
                    String qry="select * from studentdetails WHERE S_Tech='Yes' and St_Index='"+stId+"'";
                    ps=conn.prepareStatement(qry);
                    rs=ps.executeQuery();
                    if(rs.next()){
                        String name=rs.getString("St_Name");
                        String batch=rs.getString("St_Batch");
                        marksSTUname.setText(name);
                        marks_batch.setText(batch);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Student Does not follow the course...");
                    }
                } catch (SQLException | HeadlessException e) {
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
            }
            else if(jComboBoxchCourse1.getSelectedItem()=="Agriculture"){
                 try {
                    String qry="select * from studentdetails WHERE Agriculture='Yes' and St_Index='"+stId+"'";
                    ps=conn.prepareStatement(qry);
                    rs=ps.executeQuery();
                    if(rs.next()){
                        String name=rs.getString("St_Name");
                        String batch=rs.getString("St_Batch");
                        marksSTUname.setText(name);
                        marks_batch.setText(batch);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Student Does not follow the course...");
                    }
                } catch (SQLException | HeadlessException e) {
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jButton67ActionPerformed

    private void jButton68ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton68ActionPerformed
        // TODO add your handling code here: INSERT INTO MARKS COURSE VISE TO THE MARKS TABLE
        String batch=marks_batch.getText();
        String course=jComboBoxchCourse1.getSelectedItem().toString();
        String examtitle=examTitle.getText();
        String examTable="batch".concat(batch).concat(course).concat(examtitle);
        //===========================================================================
        String stID=marksStuId.getText();
        String name=marksSTUname.getText();
        String marks=marksIn.getText();
        //===========================================================================
        if("".equals(marks_batch.getText())){
            JOptionPane.showMessageDialog(null,"Please verify the Student...");
            marks_batch.requestFocus();
        }
        else if(jComboBoxchCourse1.getSelectedItem()=="Course"){
            JOptionPane.showMessageDialog(null,"Please Select the course...");
        }
        else if("".equals(examTitle.getText())){
            JOptionPane.showMessageDialog(null,"Please type the name of the exam...");
            examTitle.requestFocus();
        }
        else if("".equals(marksStuId.getText())){
            JOptionPane.showMessageDialog(null,"Please type the Student id");
            marksStuId.requestFocus();
        }
        else if(marksIn.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please INPUT the marks/Grade");
            marksIn.requestFocus();
        }
        else{
            try {
                        DatabaseMetaData db1=conn.getMetaData();
                        ResultSet rs1=db1.getTables(null, null,examTable,null);
                        if(rs1.next()){
                            try {
                                ps=conn.prepareStatement("INSERT INTO `"+examTable+"`(ST_ID,NAME,MARKS)values(?,?,?)");
                                ps.setString(1, stID);
                                ps.setString(2, name);
                                ps.setString(3, marks);
                                ps.executeUpdate(); 
                                JOptionPane.showMessageDialog(null,"Results Added...");
                                
                                try {
                                    //jTableMarksView
                                    String qry="SELECT * FROM `"+examTable+"`";
                                    ps=conn.prepareStatement(qry);
                                    rs=ps.executeQuery();
                                    jTableMarksView.setModel(DbUtils.resultSetToTableModel(rs));
                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(null,e.getMessage());
                                }
                            } catch (SQLException | HeadlessException e) {
                                JOptionPane.showMessageDialog(null,e.getMessage());
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null,examTable+" Table does not created");
                        }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
        
        
    }//GEN-LAST:event_jButton68ActionPerformed

    private void jButton69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton69ActionPerformed
        // TODO add your handling code here:  jTableMarksView
        String batch=marks_batch.getText();
        String course=jComboBoxchCourse1.getSelectedItem().toString();
        String examtitle=examTitle.getText();
        String examTable="batch".concat(batch).concat(course).concat(examtitle);
        if("".equals(marks_batch.getText())){
            JOptionPane.showMessageDialog(null,"Please type the batch...");
            marks_batch.requestFocus();
        }
        else if(jComboBoxchCourse1.getSelectedItem()=="Course"){
            JOptionPane.showMessageDialog(null,"Please Select the course...");
        }
        else if("".equals(examTitle.getText())){
            JOptionPane.showMessageDialog(null,"Please type the name of the exam...");
            examTitle.requestFocus();
        }
        else{
            try {
                        DatabaseMetaData db1=conn.getMetaData();
                        ResultSet rs1=db1.getTables(null, null,examTable,null);
                        if(rs1.next()){
                            try {
                                    //jTableMarksView
                                    String qry="SELECT * FROM `"+examTable+"`";
                                    ps=conn.prepareStatement(qry);
                                    rs=ps.executeQuery();
                                    jTableMarksView.setModel(DbUtils.resultSetToTableModel(rs));
                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(null,e.getMessage());
                                }
                        }
                        else{
                            JOptionPane.showMessageDialog(null,examTable+" Tabledoes not created....");
                        }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton69ActionPerformed

    private void jButton70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton70ActionPerformed
        // TODO add your handling code here: update students marks....
        String batch=marks_batch.getText();
        String course=jComboBoxchCourse1.getSelectedItem().toString();
        String examtitle=examTitle.getText();
        String examTable="batch".concat(batch).concat(course).concat(examtitle);
        //===========================================================================
        String stID=marksStuId.getText();
        String name=marksSTUname.getText();
        String marks=marksIn.getText();
        //===========================================================================
        if("".equals(marks_batch.getText())){
            JOptionPane.showMessageDialog(null,"Please verify the Student...");
            marks_batch.requestFocus();
        }
        else if(jComboBoxchCourse1.getSelectedItem()=="Course"){
            JOptionPane.showMessageDialog(null,"Please Select the course...");
        }
        else if("".equals(examTitle.getText())){
            JOptionPane.showMessageDialog(null,"Please type the name of the exam...");
            examTitle.requestFocus();
        }
        else if("".equals(marksStuId.getText())){
            JOptionPane.showMessageDialog(null,"Please type the Student id");
            marksStuId.requestFocus();
        }
        else if(marksIn.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please INPUT the marks/Grade");
            marksIn.requestFocus();
        }
        else{
            try {
                        DatabaseMetaData db1=conn.getMetaData();
                        ResultSet rs1=db1.getTables(null, null,examTable,null);
                        if(rs1.next()){
                            try {
                                String UpdateQuery =null;
                                UpdateQuery="UPDATE `"+examTable+"` SET `MARKS`=? WHERE `ST_ID`='"+stID+"'";
                                ps =conn.prepareStatement(UpdateQuery);
                                ps.setString(1,marks);
                                ps.executeUpdate();     
                                JOptionPane.showMessageDialog(null,name+" Marks Updated...");
                                
                                try {
                                    //jTableMarksView
                                    String qry="SELECT * FROM `"+examTable+"`";
                                    ps=conn.prepareStatement(qry);
                                    rs=ps.executeQuery();
                                    jTableMarksView.setModel(DbUtils.resultSetToTableModel(rs));
                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(null,e.getMessage());
                                }
                            } catch (SQLException | HeadlessException e) {
                                JOptionPane.showMessageDialog(null,e.getMessage());
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null,examTable+" Table does not created");
                        }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton70ActionPerformed

    private void jButton71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton71ActionPerformed
        // TODO add your handling code here: PRINT THE STUDENT MARKS TABLE
        String head=null;
        String foot="+94 71 3288376| SUJAN SOFTWARE SOLUTION | sjnkayal36@gmail.com |EXPRESS INSITITUTE |";
        if(jTableMarksView.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Table does not Have any data");
        }
        else{
            String tblNm=JOptionPane.showInputDialog("Please Type the Title of the Report");
            if(tblNm.equals("")){
                JOptionPane.showMessageDialog(null,"Please Type the name....");
            }
            else{
                head=tblNm;
                MessageFormat header=new MessageFormat(head);
                MessageFormat footer=new MessageFormat(foot);
                try {
                    jTableMarksView.print(JTable.PrintMode.NORMAL, header, footer);
                } catch (java.awt.print.PrinterException e) {
                     JOptionPane.showMessageDialog(null,"Can not print !");
                }
            } 
        }
    }//GEN-LAST:event_jButton71ActionPerformed

    private void jButton65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton65ActionPerformed
        // TODO add your handling code here:exams_and_marks
        dynamic.removeAll();
        dynamic.removeAll();
        dynamic.repaint();
        dynamic.revalidate();
        dynamic.add(exams_and_marks);
        dynamic.repaint();
        dynamic.revalidate();
    }//GEN-LAST:event_jButton65ActionPerformed

    private void jButton72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton72ActionPerformed
        String batch=graphBatchIN.getText();
        
        String tableName="batch".concat(batch).concat("finalresults");
        int allStu=0;
        //int value=0;
        //===================================================================
        if("".equals(graphBatchIN.getText())){
            JOptionPane.showMessageDialog(null,"Must fillout the batch feild...");
            graphBatchIN.requestFocus();
        }
        else{
           //CHECK IF RESULT TABLE EXISTS OR NOT
            try {
                        DatabaseMetaData db1=conn.getMetaData();
                        ResultSet rs1=db1.getTables(null, null,tableName,null);
                        if(rs1.next()){
                            try {
                                    String qry="SELECT count(ST_ID) FROM `"+tableName+"`";
                                    ps=conn.prepareStatement(qry);
                                    rs=ps.executeQuery();
                                    if(rs.next()){
                                        String alSt=rs.getString("count(ST_ID)");
                                        allStu=Integer.parseInt(alSt);
                                        jLabeltOTALsTUDENTS.setText(alSt);
                                    }
                                } catch (SQLException | NumberFormatException e) {
                                        JOptionPane.showMessageDialog(null,e.getMessage());
                                }
                        }
                        else{
                            JOptionPane.showMessageDialog(null,tableName+" Table Results not entered...");
                        }
            } 
            catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
 //==================================================================================================================           
            try {
                String qry="SELECT count(ST_ID) FROM `"+tableName+"` where B_TECH='A'";
                ps=conn.prepareStatement(qry);
                rs=ps.executeQuery();
                if(rs.next()){
                    String a=rs.getString("count(ST_ID)");
                    double nA=Integer.parseInt(a);
                    double value=((float)(nA/allStu)*100);
                    try {
                        for (int i = 0; i <=value; i++) {
                            jProgressBar1.setValue(i);
                            jLabelba.setText(Integer.toString(i)+" %");
                        }
                    } catch (HeadlessException e) {
                            System.out.println(e);
                    }
                    jLabelTBA.setText((a)+" A");
                }
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
            
            //=====================
            try {
                String qry="SELECT count(ST_ID) FROM `"+tableName+"` where S_TECH='A'";
                ps=conn.prepareStatement(qry);
                rs=ps.executeQuery();
                if(rs.next()){
                    String a=rs.getString("count(ST_ID)");
                    double nA=Integer.parseInt(a);
                    double value=((float)(nA/allStu)*100);
                    try {
                        for (int i = 0; i <= value; i++) {
                            jProgressBar2.setValue(i);
                            jLabelsa.setText(Integer.toString(i)+" %");
                        }
                    } catch (HeadlessException e) {
                            System.out.println(e);
                     }
                    jLabelTSA.setText((a)+" A");
                }
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
            //====================
            try {
                String qry="SELECT count(ST_ID) FROM `"+tableName+"` where AGRICULTURE='A'";
                ps=conn.prepareStatement(qry);
                rs=ps.executeQuery();
                if(rs.next()){
                    String a=rs.getString("count(ST_ID)");
                    double nA=Integer.parseInt(a);
                    double value=((float)(nA/allStu)*100);
                    try {
                        for (int i = 0; i <= value; i++) {
                            jProgressBar3.setValue(i);
                            jLabelaa.setText(Integer.toString(i)+" %");
                        }
                    } catch (HeadlessException e) {
                            System.out.println(e);
                     }
                    jLabelTAA.setText((a)+" A");
                }
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
            //===========================================================================A
            try {
                String qry="SELECT count(ST_ID) FROM `"+tableName+"` where B_TECH='B'";
                ps=conn.prepareStatement(qry);
                rs=ps.executeQuery();
                if(rs.next()){
                    String a=rs.getString("count(ST_ID)");
                    double nA=Integer.parseInt(a);
                    double value=((float)(nA/allStu)*100);
                    try {
                        for (int i = 0; i <= value; i++) {
                            jProgressBar4.setValue(i);
                            jLabelbb.setText(Integer.toString(i)+" %");
                        }
                    } catch (HeadlessException e) {
                            System.out.println(e);
                    }
                    jLabelTBB.setText((a)+" B");
                }
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
            
            //=====================
            try {
                String qry="SELECT count(ST_ID) FROM `"+tableName+"` where S_TECH='B'";
                ps=conn.prepareStatement(qry);
                rs=ps.executeQuery();
                if(rs.next()){
                    String a=rs.getString("count(ST_ID)");
                    double nA=Integer.parseInt(a);
                    double value=((float)(nA/allStu)*100);
                    try {
                        for (int i = 0; i <= value; i++) {
                            jProgressBar5.setValue(i);
                            jLabelsb.setText(Integer.toString(i)+" %");
                        }
                    } catch (HeadlessException e) {
                            System.out.println(e);
                     }
                    jLabelTSB.setText((a)+" B");
                }
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
            //====================
            try {
                String qry="SELECT count(ST_ID) FROM `"+tableName+"` where AGRICULTURE='B'";
                ps=conn.prepareStatement(qry);
                rs=ps.executeQuery();
                if(rs.next()){
                    String a=rs.getString("count(ST_ID)");
                    double nA=Integer.parseInt(a);
                    double value=((float)(nA/allStu)*100);
                    try {
                        for (int i = 0; i <= value; i++) {
                            jProgressBar6.setValue(i);
                            jLabelab.setText(Integer.toString(i)+" %");
                        }
                    } catch (HeadlessException e) {
                            System.out.println(e);
                     }
                    jLabelTAB.setText((a)+" B");
                }
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
            //===========================================================================B
            try {
                String qry="SELECT count(ST_ID) FROM `"+tableName+"` where B_TECH='C'";
                ps=conn.prepareStatement(qry);
                rs=ps.executeQuery();
                if(rs.next()){
                    String a=rs.getString("count(ST_ID)");
                    double nA=Integer.parseInt(a);
                    double value=((float)(nA/allStu)*100);
                    try {
                        for (int i = 0; i <= value; i++) {
                            jProgressBar7.setValue(i);
                            jLabelbc.setText(Integer.toString(i)+" %");
                        }
                    } catch (HeadlessException e) {
                            System.out.println(e);
                    }
                    jLabelTBC.setText((a)+" C");
                }
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
            
            //=====================
            try {
                String qry="SELECT count(ST_ID) FROM `"+tableName+"` where S_TECH='C'";
                ps=conn.prepareStatement(qry);
                rs=ps.executeQuery();
                if(rs.next()){
                    String a=rs.getString("count(ST_ID)");
                    double nA=Integer.parseInt(a);
                    double value=((float)(nA/allStu)*100);
                    try {
                        for (int i = 0; i <= value; i++) {
                            jProgressBar8.setValue(i);
                            jLabelsc.setText(Integer.toString(i)+" %");
                        }
                    } catch (HeadlessException e) {
                            System.out.println(e);
                     }
                    jLabelTSC.setText((a)+" C");
                }
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
            //====================
            try {
                String qry="SELECT count(ST_ID) FROM `"+tableName+"` where AGRICULTURE='C'";
                ps=conn.prepareStatement(qry);
                rs=ps.executeQuery();
                if(rs.next()){
                    String a=rs.getString("count(ST_ID)");
                    double nA=Integer.parseInt(a);
                    double value=((float)(nA/allStu)*100);
                    try {
                        for (int i = 0; i <= value; i++) {
                            jProgressBar9.setValue(i);
                            jLabelac.setText(Integer.toString(i)+" %");
                        }
                    } catch (HeadlessException e) {
                            System.out.println(e);
                     }
                    jLabelTAC.setText((a)+" C");
                }
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
            //===========================================================================C
             try {
                String qry="SELECT count(ST_ID) FROM `"+tableName+"` where B_TECH='S'";
                ps=conn.prepareStatement(qry);
                rs=ps.executeQuery();
                if(rs.next()){
                    String a=rs.getString("count(ST_ID)");
                    double nA=Integer.parseInt(a);
                    double value=((float)(nA/allStu)*100);
                    try {
                        for (int i = 0; i <= value; i++) {
                            jProgressBar10.setValue(i);
                            jLabelbs.setText(Integer.toString(i)+" %");
                        }
                    } catch (HeadlessException e) {
                            System.out.println(e);
                    }
                    jLabelTBS.setText((a)+" S");
                }
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
            
            //=====================
            try {
                String qry="SELECT count(ST_ID) FROM `"+tableName+"` where S_TECH='S'";
                ps=conn.prepareStatement(qry);
                rs=ps.executeQuery();
                if(rs.next()){
                    String a=rs.getString("count(ST_ID)");
                    double nA=Integer.parseInt(a);
                    double value=((float)(nA/allStu)*100);
                    try {
                        for (int i = 0; i <= value; i++) {                         
                            jProgressBar11.setValue(i);
                            jLabelss.setText(Integer.toString(i)+" %");
                        }
                    } catch (HeadlessException e) {
                            System.out.println(e);
                     }
                    jLabelTSS.setText((a)+" S");
                }
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
            //====================
            try {
                String qry="SELECT count(ST_ID) FROM `"+tableName+"` where AGRICULTURE='S'";
                ps=conn.prepareStatement(qry);
                rs=ps.executeQuery();
                if(rs.next()){
                    String a=rs.getString("count(ST_ID)");
                    double nA=Integer.parseInt(a);
                    double value=((float)(nA/allStu)*100);
                    try {
                        for (int i = 0; i <= value; i++) {
                            jProgressBar12.setValue(i);
                            jLabelas.setText(Integer.toString(i)+" %");
                        }
                    } catch (HeadlessException e) {
                            System.out.println(e);
                     }
                    jLabelTAS.setText((a)+" S");
                }
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
            //============================================================================S
             try {
                String qry="SELECT count(ST_ID) FROM `"+tableName+"` where B_TECH='F'";
                ps=conn.prepareStatement(qry);
                rs=ps.executeQuery();
                if(rs.next()){
                    String a=rs.getString("count(ST_ID)");
                    double nA=Integer.parseInt(a);
                    double value=((float)(nA/allStu)*100);
                    try {
                        for (int i = 0; i <= value; i++) {
                            jProgressBar13.setValue(i);
                            jLabelbf.setText(Integer.toString(i)+" %");
                        }
                    } catch (HeadlessException e) {
                            System.out.println(e);
                    }
                    jLabelTBF.setText((a)+" F");
                }
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
            
            //=====================
            try {
                String qry="SELECT count(ST_ID) FROM `"+tableName+"` where S_TECH='F'";
                ps=conn.prepareStatement(qry);
                rs=ps.executeQuery();
                if(rs.next()){
                    String a=rs.getString("count(ST_ID)");
                    double nA=Integer.parseInt(a);
                    double value=((float)(nA/allStu)*100);
                    try {
                        for (int i = 0; i <= value; i++) {
                            jProgressBar14.setValue(i);
                            jLabelsf.setText(Integer.toString(i)+" %");
                        }
                    } catch (HeadlessException e) {
                            System.out.println(e);
                     }
                    jLabelTSF.setText((a)+" F");
                }
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
            //====================
            try {
                String qry="SELECT count(ST_ID) FROM `"+tableName+"` where AGRICULTURE='F'";
                ps=conn.prepareStatement(qry);
                rs=ps.executeQuery();
                if(rs.next()){
                    String a=rs.getString("count(ST_ID)");
                    double nA=Integer.parseInt(a);
                    double value=((float)(nA/allStu)*100);
                    try {
                        for (int i = 0; i <= value; i++) {
                            jProgressBar15.setValue(i);
                            jLabelaf.setText(Integer.toString(i)+" %");
                        }
                    } catch (HeadlessException e) {
                            System.out.println(e);
                     }
                    jLabelTAF.setText((a)+" F");
                }
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
            //============================================================================F
            
           
        }
    }//GEN-LAST:event_jButton72ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here: PRIVACY AND AGREEMENT CLIENT AND OUR COMPANY....
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:  HELP FOR USER GUIDES
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel112MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel112MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel112MouseEntered

    private void jLabel115MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel115MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel115MouseEntered
    public void tableHead(){
        JTableHeader stResulttbl=jTableStFinalResults.getTableHeader();
        stResulttbl.setBackground(Color.red);
        stResulttbl.setForeground(Color.white);
        
        //jTableMarksView
        JTableHeader stMarkstbl=jTableStFinalResults.getTableHeader();
        stMarkstbl.setBackground(Color.red);
        stMarkstbl.setForeground(Color.white);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AttenBatch;
    private javax.swing.JTextField AttenBatch1;
    private javax.swing.JTextField AttenCyear;
    private javax.swing.JTextField AttenCyear1;
    public static javax.swing.JPanel Attendance;
    public static javax.swing.JPanel AttendanceViewPanel;
    public static javax.swing.JPanel Batch;
    public static javax.swing.JPanel Course;
    public static javax.swing.JPanel Course_base;
    public static javax.swing.JPanel Course_title;
    public static javax.swing.JPanel Course_view;
    public static javax.swing.JPanel Course_view1;
    public static javax.swing.JTextField DescripsionIn;
    public static javax.swing.JPanel Developer;
    public static javax.swing.JPanel Finance;
    public static javax.swing.JPanel FinanceBase;
    public static javax.swing.JPanel FinanceFees;
    public static javax.swing.JPanel FinanceStatements;
    public static javax.swing.JPanel FinanceTitle;
    public static javax.swing.JPanel Searchbatch;
    public static javax.swing.JPanel SettingBase;
    private javax.swing.JLabel StNameRes;
    public static javax.swing.JCheckBox St_addmissionfee_chbox;
    public static javax.swing.JTextField St_address;
    public static javax.swing.JTextField St_age;
    public static javax.swing.JPanel St_base;
    public static javax.swing.JTextField St_batch;
    public static javax.swing.JRadioButton St_course_agri;
    public static javax.swing.JRadioButton St_course_btech;
    public static javax.swing.JRadioButton St_course_stech;
    public static javax.swing.JTextField St_emgy_tel;
    public static javax.swing.JTextField St_index;
    public static javax.swing.JTextField St_mail;
    public static javax.swing.JTextField St_name;
    public static javax.swing.JTextField St_parent_name;
    public static javax.swing.JButton St_pic_select;
    public static javax.swing.JPanel St_reg;
    public static javax.swing.JPanel St_select;
    public static javax.swing.JTextField St_tel;
    public static javax.swing.JPanel St_view;
    public static javax.swing.JPanel StaticPane;
    public static javax.swing.JPanel Stu_registration_form;
    public static javax.swing.JPanel Stu_view_info;
    public static javax.swing.JPanel Student;
    public static javax.swing.JLabel TECH_IMG_LBL;
    public static javax.swing.JLabel TECH_IMG_LBL1;
    public static javax.swing.JTextField TREG_address;
    public static javax.swing.JTextField TREG_dob;
    public static javax.swing.JTextField TREG_email;
    public static javax.swing.JTextField TREG_joindate;
    public static javax.swing.JTextField TREG_name;
    public static javax.swing.JTextField TREG_nic;
    public static javax.swing.JTextField TREG_qualific;
    public static javax.swing.JTextField TREG_subject;
    public static javax.swing.JTextField TREG_tel;
    public static javax.swing.JPanel Title;
    private javax.swing.JPanel Title_panel;
    public static javax.swing.JTextField TotalExpensetxt;
    public static javax.swing.JTextField TotalIncometxt;
    public static javax.swing.JTextField Totalbalancetxt;
    public static javax.swing.JTextField YearIn;
    private javax.swing.JPanel addFinalResults;
    public static javax.swing.JPanel add_course;
    public static javax.swing.JPanel addnewbatch;
    public static javax.swing.JTextField agriIn;
    private javax.swing.JLabel agrilbl;
    public static javax.swing.JLabel attendanceText;
    public static javax.swing.JPanel batchTitle;
    private javax.swing.JTextField batchTxtFind;
    public static javax.swing.JPanel batchbase;
    private javax.swing.JLabel btlbl;
    private javax.swing.JLabel btlbl1;
    public static javax.swing.JButton btn_create_batch;
    public static javax.swing.JButton btn_delete_batch;
    private javax.swing.JButton btn_registration;
    private javax.swing.JButton btn_view_stu_info;
    public static javax.swing.JLabel courceRePath;
    private javax.swing.JPanel course_form;
    public static datechooser.beans.DateChooserCombo dateChooserCombo1;
    public static datechooser.beans.DateChooserCombo dateChooserCombo2;
    private datechooser.beans.DateChooserCombo dateChooserCombo3;
    private datechooser.beans.DateChooserDialog dateChooserDialog1;
    public static javax.swing.JPanel dynamic;
    public static javax.swing.JTextField examTitle;
    private javax.swing.JPanel exams_and_marks;
    public static javax.swing.JLabel financeStuName;
    public static javax.swing.JTextField findBatchStu;
    public static javax.swing.JTextField graphBatchIN;
    private javax.swing.JPanel graphs;
    private javax.swing.JPanel homeTitle;
    private javax.swing.JButton jButton1;
    public static javax.swing.JButton jButton10;
    public static javax.swing.JButton jButton11;
    public static javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    public static javax.swing.JButton jButton16;
    public static javax.swing.JButton jButton17;
    public static javax.swing.JButton jButton18;
    public static javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    public static javax.swing.JButton jButton20;
    public static javax.swing.JButton jButton21;
    public static javax.swing.JButton jButton22;
    public static javax.swing.JButton jButton23;
    public static javax.swing.JButton jButton24;
    public static javax.swing.JButton jButton25;
    public static javax.swing.JButton jButton26;
    public static javax.swing.JButton jButton27;
    public static javax.swing.JButton jButton28;
    public static javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    public static javax.swing.JButton jButton31;
    public static javax.swing.JButton jButton32;
    public static javax.swing.JButton jButton33;
    public static javax.swing.JButton jButton34;
    public static javax.swing.JButton jButton35;
    public static javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    public static javax.swing.JButton jButton38;
    public static javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    public static javax.swing.JButton jButton40;
    public static javax.swing.JButton jButton41;
    public static javax.swing.JButton jButton42;
    public static javax.swing.JButton jButton43;
    public static javax.swing.JButton jButton44;
    public static javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    public static javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    public static javax.swing.JButton jButton53;
    public static javax.swing.JButton jButton54;
    public static javax.swing.JButton jButton55;
    public static javax.swing.JButton jButton56;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton58;
    private javax.swing.JButton jButton59;
    public static javax.swing.JButton jButton6;
    private javax.swing.JButton jButton60;
    private javax.swing.JButton jButton61;
    private javax.swing.JButton jButton62;
    private javax.swing.JButton jButton63;
    private javax.swing.JButton jButton64;
    private javax.swing.JButton jButton65;
    private javax.swing.JButton jButton66;
    private javax.swing.JButton jButton67;
    private javax.swing.JButton jButton68;
    private javax.swing.JButton jButton69;
    public static javax.swing.JButton jButton7;
    private javax.swing.JButton jButton70;
    private javax.swing.JButton jButton71;
    private javax.swing.JButton jButton72;
    public static javax.swing.JButton jButton8;
    public static javax.swing.JButton jButton9;
    public static javax.swing.JButton jButtonAddCourse;
    public static javax.swing.JButton jButtonChooseResourse;
    public static javax.swing.JButton jButtonDeleteCourse;
    private javax.swing.JButton jButtonSavePwd;
    public static javax.swing.JButton jButtonUpdateCourse;
    private javax.swing.JButton jButtoncancelpwd;
    private javax.swing.JCheckBox jCheckBoxPresense;
    public static javax.swing.JComboBox<String> jComboBox1;
    public static javax.swing.JComboBox<String> jComboBoxLevel;
    public static javax.swing.JComboBox<String> jComboBoxLevel1;
    public static javax.swing.JComboBox<String> jComboBoxMonth;
    public static javax.swing.JComboBox<String> jComboBoxMonth1;
    public static javax.swing.JComboBox<String> jComboBoxchCourse;
    public static javax.swing.JComboBox<String> jComboBoxchCourse1;
    private javax.swing.JComboBox<String> jComboBoxcrs;
    private javax.swing.JComboBox<String> jComboBoxcrs1;
    public static javax.swing.JComboBox<String> jComboMonths;
    public static javax.swing.JComboBox<String> jComboMonths1;
    public static javax.swing.JComboBox<String> jCombocourse;
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    public static javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    public static javax.swing.JLabel jLabel112;
    public static javax.swing.JLabel jLabel113;
    public static javax.swing.JLabel jLabel114;
    public static javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    public static javax.swing.JLabel jLabel12;
    public static javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    public static javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    public static javax.swing.JLabel jLabel21;
    public static javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    public static javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    public static javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    public static javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    public static javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    public static javax.swing.JLabel jLabelBatch;
    private javax.swing.JLabel jLabelTAA;
    private javax.swing.JLabel jLabelTAB;
    private javax.swing.JLabel jLabelTAC;
    private javax.swing.JLabel jLabelTAF;
    private javax.swing.JLabel jLabelTAS;
    private javax.swing.JLabel jLabelTBA;
    private javax.swing.JLabel jLabelTBB;
    private javax.swing.JLabel jLabelTBC;
    private javax.swing.JLabel jLabelTBF;
    private javax.swing.JLabel jLabelTBS;
    private javax.swing.JLabel jLabelTSA;
    private javax.swing.JLabel jLabelTSB;
    private javax.swing.JLabel jLabelTSC;
    private javax.swing.JLabel jLabelTSF;
    private javax.swing.JLabel jLabelTSS;
    public static javax.swing.JLabel jLabelTaddress;
    public static javax.swing.JLabel jLabelTdoJoin;
    public static javax.swing.JLabel jLabelTemail;
    public static javax.swing.JLabel jLabelTname;
    public static javax.swing.JLabel jLabelTnic;
    public static javax.swing.JLabel jLabelTqualific;
    public static javax.swing.JLabel jLabelTsubject;
    public static javax.swing.JLabel jLabelTtel;
    private javax.swing.JLabel jLabelaa;
    private javax.swing.JLabel jLabelab;
    private javax.swing.JLabel jLabelac;
    private javax.swing.JLabel jLabelaf;
    private javax.swing.JLabel jLabelas;
    private javax.swing.JLabel jLabelba;
    private javax.swing.JLabel jLabelbb;
    private javax.swing.JLabel jLabelbc;
    private javax.swing.JLabel jLabelbf;
    private javax.swing.JLabel jLabelbs;
    public static javax.swing.JLabel jLabeldob;
    private javax.swing.JLabel jLabelsa;
    private javax.swing.JLabel jLabelsb;
    private javax.swing.JLabel jLabelsc;
    private javax.swing.JLabel jLabelsf;
    private javax.swing.JLabel jLabelss;
    private javax.swing.JLabel jLabeltOTALsTUDENTS;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    public static javax.swing.JPanel jPanelChangePwd;
    public static javax.swing.JPanel jPanelRegisterTeacher;
    public static javax.swing.JProgressBar jProgressBar1;
    public static javax.swing.JProgressBar jProgressBar10;
    public static javax.swing.JProgressBar jProgressBar11;
    public static javax.swing.JProgressBar jProgressBar12;
    public static javax.swing.JProgressBar jProgressBar13;
    public static javax.swing.JProgressBar jProgressBar14;
    public static javax.swing.JProgressBar jProgressBar15;
    public static javax.swing.JProgressBar jProgressBar2;
    public static javax.swing.JProgressBar jProgressBar3;
    public static javax.swing.JProgressBar jProgressBar4;
    public static javax.swing.JProgressBar jProgressBar5;
    public static javax.swing.JProgressBar jProgressBar6;
    public static javax.swing.JProgressBar jProgressBar7;
    public static javax.swing.JProgressBar jProgressBar8;
    public static javax.swing.JProgressBar jProgressBar9;
    public static javax.swing.JRadioButton jRadioButton_agri;
    public static javax.swing.JRadioButton jRadioButton_bTech;
    public static javax.swing.JRadioButton jRadioButton_sTech;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    public static javax.swing.JTable jTableAttendanceViewer;
    public static javax.swing.JTable jTableFeesRetriver;
    public static javax.swing.JTable jTableFeesRetriver1;
    public static javax.swing.JTable jTableFindBatch;
    public static javax.swing.JTable jTableMarksView;
    public static javax.swing.JTable jTableStAttendance;
    public static javax.swing.JTable jTableStFinalResults;
    public static javax.swing.JTable jTableStudentView;
    public static javax.swing.JTable jTableTeacherView;
    public static javax.swing.JTable jTablecourceView;
    public static javax.swing.JPanel jTeachersView;
    public static javax.swing.JTextField jTextC_Practical_hours;
    public static javax.swing.JTextField jTextC_Session_Id;
    public static javax.swing.JTextField jTextC_Session_name;
    public static javax.swing.JTextField jTextC_remarks;
    public static javax.swing.JTextField jTextC_theory_hourse;
    public static javax.swing.JTextField jTextFeesAmount;
    public static javax.swing.JTextField jTextFeesAmount1;
    public static javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldBATCHinput;
    private javax.swing.JTextField jTextFieldBATCHinput1;
    public static javax.swing.JLabel lbl_image;
    public static javax.swing.JPanel main_panel;
    public static javax.swing.JTextField marksIn;
    private javax.swing.JLabel marksSTUname;
    public static javax.swing.JTextField marksStuId;
    public static javax.swing.JTextField marks_batch;
    public static javax.swing.JPasswordField pwdConfirm;
    public static javax.swing.JPasswordField pwdNew;
    public static javax.swing.JPasswordField pwdOld;
    public static javax.swing.JTextField pwdUserId;
    public static javax.swing.JTextField resulStu_id;
    public static javax.swing.JTextField resultBATCH;
    public static javax.swing.JTextField resultIndexNo;
    public static javax.swing.JTextField resultbtecIn;
    private javax.swing.JPanel settings;
    public static javax.swing.JTextField stIn;
    private javax.swing.JLabel stlbl;
    public static javax.swing.JTextField txtbatchyear;
    // End of variables declaration//GEN-END:variables

    private Object setValuwAt(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Object setValueAt(boolean b, int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Object setSelected(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
