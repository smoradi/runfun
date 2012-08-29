package ca.digitalface.jasperoo.reports;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;

import org.springframework.ui.jasperreports.JasperReportsUtils;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;

/**
 * The Javadoc for JasperReportsMultiFormatView says that it is a 
 * <blockquote>Jasper Reports view class that allows for the actual rendering 
 * format to be specified at runtime using a parameter contained in the model.
 * </blockquote> 
 * 
 * By default, it supports the following formates: 
 * <ul>
 * <li><code>csv</code> - <code>JasperReportsCsvView</code></li>
 * <li><code>html</code> - <code>JasperReportsHtmlView</code></li>
 * <li><code>pdf</code> - <code>JasperReportsPdfView</code></li>
 * <li><code>xls</code> - <code>JasperReportsXlsView</code></li>
 * </ul>
 * 
 * This class also provides support for the following:
 * <ul>
 * <li><code>odt</code> - <code>JROdtExporter</code></li>
 * <li><code>xml</code> - <code>JRXmlExporter</code></li>
 * <li><code>rtf</code> - <code>JRRtfExporter</code></li>
 * </ul>
 * 
 * It does so by rendering the report using JasperReportsUtils and the various 
 * exporters identified.
 * 
 * It should also support the following, but they have proven to be unreliable... 
 * more testing is needed.
 * <ul>
 * <li><code>docx</code> - <code>JRDocxExporter</code></li>
 * <li><code>txt</code> - <code>JRTextExporter</code></li>
 * </ul>
 * 
 * 
 * @author Waldo Rochow
 * @see org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView
 * @see org.springframework.ui.jasperreports.JasperReportsUtils
 * @see net.sf.jasperreports.engine.export.oasis.JROdtExporter
 * @see net.sf.jasperreports.engine.export.JRXmlExporter
 * @see net.sf.jasperreports.engine.export.JRRtfExporter
 * @see net.sf.jasperreports.engine.export.ooxml.JRDocxExporter
 * @see net.sf.jasperreports.engine.export.JRTextExporter
 * @since 0.1.0
 *
 */
public class CustomJasperReportsMultiFormatView extends JasperReportsMultiFormatView {

	/**
	 * {@inheritDoc}
	 */
	protected void renderReport(JasperPrint populatedReport, Map model, HttpServletResponse response) throws Exception {
		setReportDataKey("reportData");
		
		String format = model.get("format").toString();
		String filename = populatedReport.getName()+"."+format;
		response.addHeader("Content-Disposition", "attachment; filename=\""+filename+"\"");
		
		/*
		 * Early testing has problems.
		 * This should work, it just doesn't for me. It may just be because I use OpenOffice to edit docx files.
		 * 
		 *if(format.endsWith("docx")){
		 *	JRDocxExporter exporter = new JRDocxExporter();
		 *	exporter.setParameters(model);
		 *	JasperReportsUtils.render(exporter, populatedReport, response.getOutputStream());
		 *} else
		 */
		
		if(format.endsWith("odt")){
			JROdtExporter exporter = new JROdtExporter();
		 	exporter.setParameters(model);
		 	JasperReportsUtils.render(exporter, populatedReport, response.getOutputStream());
		} else if(format.endsWith("xml")){
			JRXmlExporter exporter = new JRXmlExporter();
		 	exporter.setParameters(model);
		 	JasperReportsUtils.render(exporter, populatedReport, response.getOutputStream());
		} else if(format.endsWith("rtf")){
			JRRtfExporter exporter = new JRRtfExporter();
		 	exporter.setParameters(model);
		 	JasperReportsUtils.render(exporter, populatedReport, response.getOutputStream());
		/* 
		 * Early testing had problems. 
		 * This should work, it just doesn't for me.
		 * 	
		 *} else if(format.endsWith("txt")){
		 *	JRTextExporter exporter = new JRTextExporter();
		 * 	exporter.setParameters(model);
		 * 	JasperReportsUtils.render(exporter, populatedReport, response.getOutputStream());
		 */ 	
		} else {
			//pdf, csv, html, xls
		    super.renderReport(populatedReport,model,response);
		}
        
    }
}
