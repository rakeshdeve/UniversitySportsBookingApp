package studentsportbookingapp.controllers;

import studentsportbookingapp.views.*;

public class ReportController {

	private ReportView reportView;
	private static ReportController instance = null;

	public ReportController() 
	{
		this.reportView = new ReportView();
	}
	
	public static ReportController getInstance() {
		if (instance == null) {
			instance = new ReportController();
		}
		return instance;
	}
	public void monthlyReportView() {
		reportView.monthlyReportView();
	}
	public void monthlyChampionReportView() {
		reportView.monthlyChampionReportView();
	}
}
