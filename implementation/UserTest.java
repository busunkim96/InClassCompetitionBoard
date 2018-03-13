import java.util.*;


class UserTest
{

    public static void main(String[] args)
    {  
         Student student = new Student();
         student.name = "Jimmy";
         System.out.println(student.name);
         
         Faculty faculty = new Faculty();
         faculty.name = "Ann";
         System.out.println(faculty.name);
    }
}