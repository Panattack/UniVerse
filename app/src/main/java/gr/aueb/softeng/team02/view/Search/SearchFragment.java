package gr.aueb.softeng.team02.view.Search;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.softeng.team02.R;
import gr.aueb.softeng.team02.dao.Initializer;
import gr.aueb.softeng.team02.dao.OfferedSubjectDAO;
import gr.aueb.softeng.team02.memorydao.MemoryInitializer;
import gr.aueb.softeng.team02.memorydao.OfferedSubjectDAOMemory;
import gr.aueb.softeng.team02.model.OfferedSubject;
import gr.aueb.softeng.team02.view.SubjectInfo;


public class SearchFragment extends Fragment implements SearchView{


        private Initializer init;
        private LinearLayout subjectContainer;
        private View myView;

        private int student_id;


        private SearchPresenter presenter;

//TODO presenter does the switch to another Activity
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        myView = inflater.inflate(R.layout.fragment_search, container, false);

        subjectContainer = myView.findViewById(R.id.subjectContainer);

        //Bundle bundle = getArguments();
        //this.student_id = bundle.getInt("STUDENT_ID", 0);

        init = new MemoryInitializer();

        presenter = new SearchPresenter(new OfferedSubjectDAOMemory());
        presenter.setView(this);
        presenter.initSubView();

        return myView;
    }

    public void viewSub(List<OfferedSubject> sub){

        for( OfferedSubject k : sub){
            String title = k.getTitle();
            TextView subjectTextView = createSubjectTextView(title); // we make a new TextView that has the subject title
            subjectContainer.addView(subjectTextView);

        }
    }

    public TextView createSubjectTextView(String title){
        // because of an error
        LinearLayout subjectLayout = new LinearLayout(requireContext());
        subjectLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 16);
        subjectLayout.setLayoutParams(layoutParams);

        TextView textView = new TextView(requireContext());
        textView.setText(title);
        textView.setTextSize(20);
        textView.setPadding(16, 16, 16, 16);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override

            //TODO the presenter does that;
            public void onClick(View v) {
                // Redirect to another activity based on the selected subject
                Intent intent = new Intent(requireContext(), SubjectInfo.class);
                intent.putExtra("subject", title);
                startActivity(intent);
            }
        });

        View lineView = new View(requireContext());
        LinearLayout.LayoutParams lineLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        layoutParams.setMargins(16, 0, 16, 0);
        lineView.setLayoutParams(layoutParams);
        lineView.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.black));

        subjectContainer.addView(textView);
        subjectContainer.addView(lineView);
        // an error between
        ViewGroup parent = (ViewGroup) subjectLayout.getParent();
        if (parent != null) {
            parent.removeView(subjectLayout);
        }

        subjectContainer.addView(subjectLayout);

        return textView;
    }





}