package driver;

public interface IController {
	
	/**
	* returns control to main controller
	*/
	public void getControl(IController prevController);
//	sample content for return control
//	dispose received controller
//	welcomeF.setVisible( true );
	
	/**
	* disposes the resources of this controller
	*/
	public void dispose();
//	sample content for dispose; basta yung pang sara ng view ng controller
//	defFrame.dispose();
//	defPanel = null;
//	model.unregister( movieP );
//	movieP = null;
//	model.unregister( cinemaP );
//	cinemaP = null;
}
