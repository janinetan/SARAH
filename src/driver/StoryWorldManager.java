package driver;

import Models.Event;

public class StoryWorldManager {
	
	private String folderName = "assets/"; 
	private String negativeExt = "_neg";
	
	public String getLocationBg(String location){
		return folderName + "loc-" + location + ".png";
	}
	
	public String getVPImagepath(String vp, int ruling ){
		String imagePath = folderName + "vp-" + vp.toLowerCase();
		
		if (vp.equalsIgnoreCase("liam")){
//			check if liam is not sick here
//				imagePath += negativeExt;
		}
		else if (vp.equalsIgnoreCase("sarah")){
			if (ruling == Event.RULING_BAD)
				imagePath += negativeExt;
		}
		imagePath += ".png";
		
		return imagePath;
	}

	public String getIconImagepath(String act) {
		return folderName + "icn-" + act.replace(" ", "_") + ".png";
	}
	
//	object image paths
	
}
