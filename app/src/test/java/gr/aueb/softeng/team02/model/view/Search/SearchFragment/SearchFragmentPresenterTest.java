package gr.aueb.softeng.team02.model.view.Search.SearchFragment;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gr.aueb.softeng.team02.dao.Initializer;
import gr.aueb.softeng.team02.memorydao.MemoryInitializer;
import gr.aueb.softeng.team02.model.AcademicYearException;
import gr.aueb.softeng.team02.view.Search.SearchFragment.SearchPresenter;


public class SearchFragmentPresenterTest {

    private SearchPresenter presenter;
    private SeachFragmentViewStub view;
    private Initializer init;

    @Before
    public void setUp() throws AcademicYearException {
        init = new MemoryInitializer();
        init.prepareData();
        view = new SeachFragmentViewStub();
        presenter = new SearchPresenter(init.getOfferedSubjectDAO(), init.getAcademicYearDAO());
        presenter.setView(view);

    }

    /**
     * We test to see if the presenter got the right amount of Subjects
     **/

    @Test
    public void testInitSubView() {
        presenter.initSubView();
        Assert.assertEquals(36, view.getTittles().size());

    }

    /**
     * We check to see that when we type a Subject the presenter decides correct , if it exists or not
     **/
    @Test
    public void testDecide() {
        // The subject does not exist
        presenter.decide("Cats");
        Assert.assertEquals(2, view.getK());

        //The subject exists
        presenter.decide("Java");
        Assert.assertEquals(1, view.getK());

    }

    /**
     * We check to see if the presenter gets the title correct
     **/
    @Test
    public void testGetTitle() {
        view.setTitle("Kapa");
        String get = presenter.getTitle();
        Assert.assertEquals("Kapa", get);

    }


}
