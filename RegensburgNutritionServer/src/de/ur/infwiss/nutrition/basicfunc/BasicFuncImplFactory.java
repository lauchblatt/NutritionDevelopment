package de.ur.infwiss.nutrition.basicfunc;


import javax.servlet.ServletContext;




public enum BasicFuncImplFactory {
	  instance;

	  
	  private BasicFuncInterface impl=null;
	  
	  private BasicFuncImplFactory() {  
		  
	  }
	  
	  public BasicFuncInterface getBasicFuncImpl(ServletContext sContext) throws InstantiationException, IllegalAccessException, ClassNotFoundException {  
		  if (impl==null)  {
		     //String cl = sContext.getInitParameter("BasiFuncImpl");
		     //impl=(BasicFuncInterface) Class.forName(cl).newInstance();
			 impl=new BasicFuncImpl_Test();
		  }
		  return impl;
	  } 
	  
	}

