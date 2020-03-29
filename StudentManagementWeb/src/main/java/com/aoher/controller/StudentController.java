package main.java.com.aoher.controller;

import com.aoher.dto.StudentDTO;
import com.aoher.ejb.StudentEJBRemote;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class StudentController extends HttpServlet {

    private static final long serialVersionUID = 2L;

    @EJB
    private StudentEJBRemote studentEJB;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getPathInfo();

        try {
            switch (action) {
                case "/new":
                    newStudentForm(request, response);
                    break;
                case "/add":
                    addStudent(request, response);
                    break;
                case "/edit":
                    editStudentForm(request, response);
                    break;
                case "/update":
                    updateStudent(request, response);
                    break;
                case "/delete":
                    deleteStudent(request, response);
                    break;
                default:
                    listStudent(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("student", studentEJB.getStudents());
        request.getRequestDispatcher("jsp/displayStudent.jsp").forward(request, response);
    }

    private void newStudentForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("jsp/studentForm.jsp").forward(request, response);
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName(firstName);
        studentDTO.setLastName(lastName);
        studentDTO.setEmail(email);

        studentEJB.addStudent(studentDTO);
        listStudent(request, response);
    }

    private void editStudentForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        StudentDTO existingStudent = studentEJB.getStudent(id);
        request.setAttribute("student", existingStudent);
        request.getRequestDispatcher("jsp/updateStudentForm.jsp").forward(request, response);
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        StudentDTO studentDTO = new StudentDTO(id, firstName, lastName, email);
        studentEJB.updateStudent(studentDTO);

        listStudent(request, response);
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        int id = Integer.parseInt(request.getParameter("id"));
        StudentDTO studentDTO = new StudentDTO(id);
        studentEJB.deleteStudent(studentDTO);

        listStudent(request, response);
    }
}
