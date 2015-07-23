package de.ur.infwiss.nutrition.server.resources;

public class PermissionManagement {
	
/*
 * Constant Permission Token
 * 	
 */
public static final String PERMISSION_ACCESS_BASICFUNC="PERMISSION_ACCESS_BASICFUNC";	


/*
 * Constant Permission Request
 * 
 */
public static final String REQUEST_PERMISSION__GETNUTVALUE=PermissionManagement.PERMISSION_ACCESS_BASICFUNC;



/*
 * Can be refined, without affecting other modules
 * 
 */
public static boolean grantedPermission(String request,String permission) {
  return request.equals(permission);	
}

}
