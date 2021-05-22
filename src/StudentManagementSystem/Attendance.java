
package StudentManagementSystem;

/**
 *
 * @author Sujan
 */
public class Attendance {
    private String id;
    private String name;
    
    public Attendance(String Sid,String Sname){
        this.id=Sid;
        this.name=Sname;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
}
