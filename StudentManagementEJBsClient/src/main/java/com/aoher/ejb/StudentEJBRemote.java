package com.aoher.ejb;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;
import com.aoher.dto.StudentDTO;

@Remote
public interface StudentEJBRemote {

    StudentDTO getStudent(int id) throws SQLException;
    List<StudentDTO> getStudents();
    boolean addStudent(StudentDTO studentDTO);
    boolean updateStudent(StudentDTO studentDTO) throws SQLException;
    boolean deleteStudent(StudentDTO studentDTO)	throws SQLException;
}
