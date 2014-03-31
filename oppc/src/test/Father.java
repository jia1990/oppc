package test;

public class Father {
	
	public Father(){
		System.out.println("Father 类的构造子：");
		System.out.println("Father.class"+"--------->"+Father.class);
	    System.out.println("Father.getClass"+"------->"+(  getClass().getGenericSuperclass()));
		}

}
