package java_8_featrue;


public class Java8Feature {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

interface Defaulable {
    // Interfaces now allow default methods, the implementer may or 
    // may not implement (override) them.
    default String notRequired() { 
        return "Default implementation"; 
    }      
    
    default void method(){
    	
    }
}




