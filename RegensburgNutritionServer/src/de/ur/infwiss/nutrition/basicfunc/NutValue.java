package de.ur.infwiss.nutrition.basicfunc;

//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;


/*Container Class representing the parameters and result of one call*/
@XmlRootElement(name="NutritionInfo")
public class NutValue {
    
	private String ingredient;
	private String substance; 
	private String language;
	private int intakeQuantity;
	private String intakeUnit;
	private int containedQuantity;
	private String outPutUnit;
	
	public NutValue()
	{
		
	}
	
	public NutValue(String ingredient,String substance,String language,int intakeQuantity,String intakeUnit,int containedQuantity,String outPutUnit) {
	    this.setIngredient(ingredient);
	    this.setSubstance(substance);
	    this.setLanguage(language);
	    this.setIntakeUnit(intakeUnit);
	    this.setIntakeQuantity(intakeQuantity);
	    this.setContainedQuantity(containedQuantity);
	    this.setOutPutUnit(outPutUnit);
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public String getSubstance() {
		return substance;
	}

	public void setSubstance(String substance) {
		this.substance = substance;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getIntakeQuantity() {
		return intakeQuantity;
	}

	public void setIntakeQuantity(int intakeQuantity) {
		this.intakeQuantity = intakeQuantity;
	}

	public String getIntakeUnit() {
		return intakeUnit;
	}

	public void setIntakeUnit(String intakeUnit) {
		this.intakeUnit = intakeUnit;
	}

	public int getContainedQuantity() {
		return containedQuantity;
	}

	public void setContainedQuantity(int containedQuantity) {
		this.containedQuantity = containedQuantity;
	}

	public String getOutPutUnit() {
		return outPutUnit;
	}

	public void setOutPutUnit(String outPutUnit) {
		this.outPutUnit = outPutUnit;
	}
	
 /*	public static void main(String[] args) {
		try {
	    // create JAXB context and instantiate marshaller
	    JAXBContext context = JAXBContext.newInstance(NutValue.class);
	    Marshaller m = context.createMarshaller();
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	    NutValue nut = new NutValue("A","B","C",1,"D",2,"E");
	    // Write to System.out
	    m.marshal(nut, System.out);
		}
		catch (Exception e) {System.out.println(e.toString());}
	} */

}
