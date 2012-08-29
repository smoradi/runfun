/*
 * Copyright © 2011 - DigitalFace.ca. All Rights Reserved
 * Licensed under the GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007 (http://www.gnu.org/licenses/gpl.txt).
 */
package ca.digitalface.jasperoo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Trigger annotation for this add-on.
 
 * @since 1.1
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface RooJasperoo {
	
	String getReportableMethod() default "isReportable";
	
}

