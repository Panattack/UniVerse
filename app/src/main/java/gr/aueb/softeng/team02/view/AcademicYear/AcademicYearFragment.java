package gr.aueb.softeng.team02.view.AcademicYear;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import gr.aueb.softeng.team02.R;
import gr.aueb.softeng.team02.dao.Initializer;
import gr.aueb.softeng.team02.memorydao.MemoryInitializer;
import gr.aueb.softeng.team02.view.Authentication.UserLoginActivity;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;

public class AcademicYearFragment extends Fragment implements AcademicYearFragmentView {

    private ArrayList<String> yearList;
    private ArrayAdapter<String> adapter;
    Initializer init;
    private AcademicYearFragmentViewModel model;
    private Spinner year_spinner;
    private Spinner semester_spinner;
    private Button submitButton;
    private View myView;
    Button addYearButton;

    EditText ects;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_academic_year, container, false);
        year_spinner = (Spinner) myView.findViewById(R.id.spinner2);
        semester_spinner = (Spinner) myView.findViewById(R.id.spinnerSemester);
        Bundle bundle = getArguments();
        // int student_id = bundle.getInt("STUDENT_ID", 0);
        init = new MemoryInitializer();
        ects = myView.findViewById(R.id.ectsTxt);
        model = new AcademicYearFragmentViewModel();
        model.getPresenter().setView(this);
        model.getPresenter().initLists();

        addYearButton = myView.findViewById(R.id.add_year_button);

        submitButton = myView.findViewById(R.id.submitBtnSecretary);

        return myView;
    }

    @Override
    public void onStart() {
        super.onStart();
        model.getPresenter().startProcess();
    }


    public String getECTS() {
        return ects.getText().toString().trim();
    }


    @Override
    public void setForm(HashMap<String, Integer> subjects) {

    }

    @Override
    public void submit() {

    }

    @Override
    public void startSubmission() {
        int flag = 0;
        if (getSelectedYear() != null && getSelectedSemester() != null && getECTS() != null & Integer.parseInt(getECTS()) >= 30 && Integer.parseInt(getECTS()) <= 80) {
            flag = model.getPresenter().submitNewAcademicYear(getSelectedYear(), Integer.parseInt(getSelectedSemester()), Integer.parseInt(getECTS()));
        }
        if (flag == -1) {
            // TODO error in submission
        }
    }

    public void createYearList(ArrayList<String> years) {
        // Create an ArrayAdapter using the choices ArrayList
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, years);

        // Specify the layout for the dropdown items
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the ArrayAdapter on the Spinner
        year_spinner.setAdapter(adapter);

    }

    public void createSemesterList(ArrayList<String> semesters) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, semesters);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semester_spinner.setAdapter(adapter);
    }

    @Override
    public String getSelectedSemester() {
        return semester_spinner.getSelectedItem().toString();
    }

    @Override
    public String getSelectedYear() { //ArrayList<String> years) {
        return year_spinner.getSelectedItem().toString();
    }
}
