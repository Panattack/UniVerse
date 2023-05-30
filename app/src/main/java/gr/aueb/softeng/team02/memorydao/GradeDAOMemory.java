package gr.aueb.softeng.team02.memorydao;

import gr.aueb.softeng.team02.dao.GradeDAO;
import gr.aueb.softeng.team02.model.Grade;

import java.util.HashSet;
import java.util.Set;


public class GradeDAOMemory implements GradeDAO {

    protected static HashSet<Grade> entities = new HashSet<Grade>();

    @Override
    public Set<Grade> findByStudent(int studentId) {
        HashSet<Grade> studentGrades = new HashSet<Grade>();

        for (Grade grade : this.entities) {
            if (grade.getStudentId() == studentId) {
                studentGrades.add(grade);
            }
        }
        return studentGrades;
    }
    @Override
    public Grade findBySubject(String title, int studentId) {
        for (Grade grade : this.entities) {
            if (grade.getStudentId() == studentId && grade.getTitle().equals(title)) {
                return grade;
            }
        }
        return null;
    }

    @Override
    public void save(Grade entity) {
        if (!entities.contains(entity))
            entities.add(entity);
    }

    @Override
    public void delete(Grade entity) {
        if (entities.contains(entity))
            entities.add(entity);
    }


}