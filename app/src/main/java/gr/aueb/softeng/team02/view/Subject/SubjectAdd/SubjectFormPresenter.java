package gr.aueb.softeng.team02.view.Subject.SubjectAdd;

import android.content.Intent;
import android.widget.Toast;

import java.util.List;

import gr.aueb.softeng.team02.dao.SubjectDAO;
import gr.aueb.softeng.team02.model.Subject;

public class SubjectFormPresenter {

    private SubjectFormView view;
    private SubjectDAO sub;

    public SubjectFormPresenter(SubjectDAO sub ) {
        this.sub=sub;
    }

    public void setView(SubjectFormView view){
        this.view=view;
    }

    public void valid(){

        String title = view.getSubTitle();
        if(allWritten()) { // we first check if all the attributes are written
            if (this.sub.exists(title)) { // second we check if we have another Subject with the same name
                errorExist();

            } else {
                int id = Integer.parseInt(view.getId());
                int ects = Integer.parseInt(view.getEcts());
                Subject a = new Subject(id, view.getProf(), ects, view.getDesc(), title);
                this.sub.save(a);
                view.messageSave();
                goBack();
            }

        }
        else{
            errorNotWritten();
        }

    }

    private void errorNotWritten() {

        // We make them invisible in the case they were some visible
        view.invTitle();
        view.invDesc();
        view.invProf();
        view.invEcts();
        view.invEcts();

        String title = view.getSubTitle();
        String prof = view.getProf();
        String desc = view.getDesc();
        String ects = view.getEcts();
        String id = view.getId();

        if(title.equals("")){
            view.setexTitle();
        }
        if(prof.equals("")){
            view.setexProf();
        }
        if(desc.equals("")){
            view.setexDesc();
        }
        if(ects.equals("")){
            view.setexEcts();
        }
        if(id.equals("")){
            view.setexId();
        }

        view.printEr1();

    }

    public void errorExist(){
        view.sameSubject();

    }

    public boolean allWritten(){
        String title = view.getSubTitle();
        String prof = view.getProf();
        String desc = view.getDesc();
        String ects = view.getEcts();
        String id = view.getId();

        if( title.equals("") || prof.equals("")|| desc.equals("")|| ects.equals("")|| id.equals("")){
            return false;
        }
        return true;
    }

    public void createSubject(){
        String title = view.getSubTitle();
        Subject k = this.sub.findSubject(title);
        this.sub.delete(k);

        String prof = view.getProf();
        String desc = view.getDesc();
        int id = Integer.parseInt(view.getId());
        int ects = Integer.parseInt(view.getEcts());
        Subject a = new Subject(id, view.getProf(), ects, view.getDesc(), title);
        this.sub.save(a);
        view.messageSave();

    }

    public void goBack(){
        view.getBack();
    }

}