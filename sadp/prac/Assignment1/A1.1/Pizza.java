abstract class Pizza{

	String name;
	
	public void prepare(){
		System.out.println("Preparing "+name+"...");
	}
	
	public void bake(){
		System.out.println("The pizza is now being baked...");
	}
	
	public void cut(){
		System.out.println("The pizza is finally cut into pieces...");
	}
	
	public void box(){
		System.out.println("The pizza is placed in a box and is now ready to ne delivered!");
	}
	
	public String getName(){
		return name;
	}
} 
