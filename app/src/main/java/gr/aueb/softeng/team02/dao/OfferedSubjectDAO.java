package gr.aueb.softeng.team02.dao;

import java.util.List;

import gr.aueb.softeng.team02.model.AcademicYear;
import gr.aueb.softeng.team02.model.OfferedSubject;

public interface OfferedSubjectDAO {
    public void save(OfferedSubject entity);
    public void delete(OfferedSubject entity);
    public List<OfferedSubject> findAll();
    public List<OfferedSubject> findByModulo(int mod,String year);
    public List<OfferedSubject> findByYear(String year, int semester);
    public OfferedSubject findByYearAndName(String year, String title);

    public List<OfferedSubject> findAllSubjectsByYearAndBySemester(String year, int semester);
    public OfferedSubject findByTitle(String title);
}
