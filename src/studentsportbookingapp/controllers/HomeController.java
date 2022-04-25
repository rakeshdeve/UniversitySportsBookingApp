package studentsportbookingapp.controllers;

import studentsportbookingapp.views.HomeView;

public class HomeController {

	private HomeView homeView;
	private static HomeController instance = null;

	public HomeController() 
	{
		this.homeView = new HomeView();
	}
	
	public static HomeController getInstance() {
		if (instance == null) {
			instance = new HomeController();
		}
		return instance;
	}
	public void getMainView() 
	{
		homeView.printMainView();
	}
	
}

