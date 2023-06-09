package gr.aueb.softeng.team02.model.view.Search.Information;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gr.aueb.softeng.team02.dao.Initializer;
import gr.aueb.softeng.team02.memorydao.MemoryInitializer;
import gr.aueb.softeng.team02.model.AcademicYearException;
import gr.aueb.softeng.team02.view.Search.Information.InformationPresenter;


public class InformationPresenterTest {
    private Initializer init;
    private InformationPresenter presenter;
    private InformationViewStub view;
    @Before
    public void setUp()throws AcademicYearException {
        init = new MemoryInitializer();
        init.prepareData();
        view = new InformationViewStub() ;
        presenter = new InformationPresenter(init.getOfferedSubjectDAO());
        presenter.setView(view);

    }
    @Test
    public void testSetInfo(){
        presenter.setInfo("Algebra 1");
        Assert.assertEquals("Advanced Mathematics",view.getDesc());
        Assert.assertEquals("Algebra 1",view.getTitle());
        Assert.assertEquals(8,view.getEcts());
        Assert.assertEquals("Stauros Toumpis",view.getProf());
        Assert.assertEquals(0,view.getPrereSize());

   }



}